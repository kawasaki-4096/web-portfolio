package com.studio4096.portfolio.entity;

import java.io.Serializable;
import java.util.Date;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;
@IQTable(name="IMAGE_DATA")
public class ImageData implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4157758811889642982L;
	@IQColumn(name="image_id",autoIncrement=true,primaryKey=true)
	public Long imageId;
	@IQColumn(name="image_path")
    public String imagePath;
	@IQColumn(name="file_name")
    public String fileName;
	@IQColumn(name="thumbnail_name")
    public String thumbnailName;
	@IQColumn
    public String title;
	@IQColumn(name="content_type")
    public String contentType;
	@IQColumn
    public int x;
	@IQColumn
    public int y;
	@IQColumn(name="real_size")
    public String realSize;
	@IQColumn
    public String media;
	@IQColumn(length=0)
    public String description;
	@IQColumn(name="public_flag")
    public String publicFlag;
	@IQColumn
    public String enabled;
	@IQColumn
    public Date created;
	@IQColumn
    public Date modified;
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getThumbnailName() {
		return thumbnailName;
	}
	public void setThumbnailName(String thumbnailName) {
		this.thumbnailName = thumbnailName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getRealSize() {
		return realSize;
	}
	public void setRealSize(String realSize) {
		this.realSize = realSize;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublicFlag() {
		return publicFlag;
	}
	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}

}
