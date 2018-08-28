package org.fkit.oa.util.contorller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.fkit.oa.util.entity.FileEntity;
import org.fkit.oa.util.entity.Files;
import org.fkit.oa.util.repository.FileRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;



@Controller
@RequestMapping("/oa")
public class FileUPload  {
	@Resource
	private FileRepository fileRepository;
	
	
	 @RequestMapping(value="/fileUpload",method=RequestMethod.POST)
	 @ResponseBody
	 public Map<String, Object> upload(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		 String realPath=request.getServletContext().getRealPath("/uploadFile");
		  //接收前台传过来的参数
		   Map<String, Object> modelMap = new HashMap<String, Object>();   
		    try {
		    //处理上传文件方法getFilesFromRequest
		      List<FileEntity> list = getFilesFromRequest(request);
		      if (list == null || list.size() == 0) {
		        throw new Exception("上传失败");
		      }
		      String fileId = "";
		      for (FileEntity entity : list) {
//		        response.getWriter().write(entity.getFileName());
		    	  int number =1;
		         fileUplode(number, entity,realPath);
		 //写入数据库上传文件的相关信息
		         Files files = new Files();
		         files.setFile_name(entity.getFileName());
		         files.setFile_UUID_name(entity.getUUIDFileName());
		         files.setFile_path(realPath);
		         files.setFile_size(entity.getFileSize());
		         Files file =  (Files)fileRepository.save(files); 
		         fileId+=file.getId();
		      } 
		      modelMap.put("fileId", fileId);
		      modelMap.put("success", "true");      
		    } catch (Exception e) {
		      modelMap.put("error", "false");
		    }
		    return modelMap;
		  }
	
	 /**
	   * 从request中提取上传的文件列表
	   * 
	   * @param request HttpServletRequest
	   */
	
	public static List<FileEntity>  getFilesFromRequest(HttpServletRequest request) {
		  List<FileEntity> files = new ArrayList<FileEntity>();
		    //多附件上传需要用到的MultipartHttpServletRequest，可以度娘一下它的用法
		    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		    Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		    try {
		        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
		          InputStream inputstream = entity.getValue().getInputStream();
		          if (!(inputstream.markSupported())) {
		            inputstream = new PushbackInputStream(inputstream, 8);
		          }

		          String fileName = entity.getValue().getOriginalFilename();
		          long fileSize = entity.getValue().getSize();
		          String prefix =
		              fileName.lastIndexOf(".") >= 1 ? fileName.substring(fileName.lastIndexOf(".") + 1)
		                  : null;
		              FileEntity file = new FileEntity();
		              file.setInputStream(inputstream);
		              file.setFileType(prefix);
		              file.setFileName(fileName);
		              file.setUUIDFileName(UUID.randomUUID().toString()+"."+prefix);
		              file.setFileSize(fileSize);
		              files.add(file);
		        }
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		  
		    return files;
		}
	 
	 /**
	   * issue附件上传客户端到服务器
	   * @throws Exception 
	   */
	  public void fileUplode(int number,FileEntity entity,String realPath)
	          throws Exception {
	    //number为流水编号、attachmentType为上传附件的类型，entity文件实体类
	      OutputStream os = null;
	      try {
	    	  //创建文件存放路径
	    	  File file =new File(realPath); 
	    	  if(!file .exists()  && !file .isDirectory()) {
	    		  file .mkdir();  
	    	  }
	          // 创建一个输出流
	    	  File toFile = new File(file,entity.getUUIDFileName());
	          os = new FileOutputStream(toFile);
	          // 设置缓存
	          byte[] buffer = new byte[1024];
	          int length = 0;
	          // 读取myFile文件输出到toFile文件中
	          while ((length = entity.getInputStream().read(buffer)) > 0) {
	              os.write(buffer, 0, length);
	          }

	      } catch (Exception ex) {
	          String falsemes = "";
	          if (ex.getCause() != null) {
	              falsemes = ex.getCause().getMessage();
	          }
	          throw new Exception(ex.getMessage() + falsemes);
	      } finally {
	          try {
	              if (os != null) {
	                  os.close();
	              }
	          } catch (IOException e) {
	              throw new Exception(e.getMessage());
	          }
	      }

	  }
	  
	  @RequestMapping("/fileDownload")
	  public  void fileDownload(String fileName, HttpServletResponse res,HttpServletRequest request,Model model) throws IOException {
          res.setHeader("content-type", "application/octet-stream");
          res.setContentType("application/octet-stream");
          fileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
          res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
          byte[] buff = new byte[1024];
          BufferedInputStream bis = null;
          OutputStream os = null;
          try {
            os = res.getOutputStream();
            String filePath =request.getServletContext().getRealPath("/uploadFile");
            File file = new File(filePath,fileName); 
            if(!file.exists()) {
            	model.addAttribute("message", "所下载的文件不存在！");
            	return;
            }
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
              os.write(buff, 0, buff.length);
              os.flush();
              i = bis.read(buff);
            }
          } catch (IOException e) {
            e.printStackTrace();
          } finally {
            if (bis != null) {
              try {
                bis.close();
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
          }
	  }
}
		 /* String filePath =request.getServletContext().getRealPath("/uploadFile");
		  try {
			  // 解决中文文件名乱码关键行 
			  fileName = new String(fileName.getBytes("iso-8859-1"),"UTF-8");//为了解决中文名称乱码问题  
			  File file = new File(filePath,fileName);  
			  if(!file.exists()){
				  model.addAttribute("message", "您所下载的文件不存在！");
				  return;
			  }
			  response.addHeader("connect-disposition", "attachment;filename="+fileName);
			  IOUtils.copy(new FileInputStream(file), response.getOutputStream());
			  
		}catch (IOException e) {
			e.printStackTrace();
		}
	  }
}*/
