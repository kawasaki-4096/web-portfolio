package com.studio4096.portfolio.dto;

import java.io.Serializable;

import com.studio4096.portfolio.entity.ImageData;
import com.studio4096.portfolio.entity.Page;

public class PageDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4943719534351934711L;
	public PageDto preview;
	public PageDto getPreview() { return preview; }
	public Page current;
	public Page getCurrent() { return current; }
	public ImageData image;
	public ImageData getImage() { return image; }
	public PageDto next;
	public PageDto getNext() { return next; }
}