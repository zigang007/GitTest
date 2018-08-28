package org.fkit.oa.news.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.fkit.common.util.pager.PageModel;
import org.fkit.oa.news.domain.News;
import org.fkit.oa.news.repository.NewsRepository;
import org.fkit.oa.news.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/news")
public class NewsController {
	@Resource
	private NewsRepository newsRepository;
	@Resource
	private NewsService newsService;
	
	/**
	 * 到新闻发布界面
	 * @return
	 */
	@RequestMapping("/to_addNews")
	public String showAddnews() {
		return "news/addNews";
	}
	
	/**
	 * 添加新闻
	 * @return
	 */
	@RequestMapping("/addNews")
	public void addNews(String contents,String news_title) {
		News news = new News();
		news.setCreate_time(new Date());
		news.setNews_contents(contents);
		news.setNews_title(news_title);
		newsRepository.save(news);
	}
	/**
	 * 查看新闻
	 * @param news_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewNews")
	public String viewNews(String news_id,Model model) {
		News news = newsRepository.findeNewsById(Long.valueOf(news_id));
		model.addAttribute("news", news);
		return "news/viewNews";
	}
	/**
	 * 到新闻管理界面
	 * @return
	 */
	@RequestMapping("/manage")
	public String toNewsManage(Model model,News news,PageModel pageModel) {
		List<News> newsList =newsService.findForPageByQuery(news, pageModel);
		model.addAttribute("news", newsList);
		model.addAttribute("news_title", news.getNews_title());
		return "news/news";
	}
	/**
	 * 删除新闻
	 * @return
	 */
	@RequestMapping("/deleteNews")
	public String deleteNews(Model model,String ids) {
		if(ids !=null && !"".equals(ids)) {
			try {
				/** 批量删除  */
				newsService.deleteNewsById(ids);
				model.addAttribute("tip", "删除成功");
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("tip", "删除失败");
			}
		}
		return "forward:/news/manage";
	}
	
}
