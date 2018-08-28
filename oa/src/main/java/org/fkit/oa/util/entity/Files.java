package org.fkit.oa.util.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OA_ID_FILE")
public class Files  implements Serializable{

	/**
	 * 文件对象
	 */
	private static final long serialVersionUID = -1938546397427839094L;
	@Id
	@Column(name="file_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String file_name;
	private String file_UUID_name;
	private String file_path;
	private long file_size;
	
	private long upload_file_id;
	 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_UUID_name() {
		return file_UUID_name;
	}
	public void setFile_UUID_name(String file_UUID_name) {
		this.file_UUID_name = file_UUID_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public long getUpload_file_id() {
		return upload_file_id;
	}
	public void setUpload_file_id(long upload_file_id) {
		this.upload_file_id = upload_file_id;
	}

}
