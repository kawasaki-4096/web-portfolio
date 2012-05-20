package com.studio4096.portfolio.dto;

import java.io.Serializable;

import com.studio4096.portfolio.entity.ImageData;

public class WorkDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7987158622979363967L;
	public ImageData imageData;
	public ImageData getImageData() {
		return imageData;
	}
	public String linkUrl;
	public String getLinkUrl() {
		return linkUrl;
	}

}
