package com.studio4096.portfolio.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.studio4096.portfolio.cnstants.ApplicationSetting;
import com.studio4096.portfolio.entity.ImageData;
import com.studio4096.portfolio.exception.ServiceException;
import com.studio4096.portfolio.service.ImageService;

public class ImageEditActionBean extends BaseAdminActionBean {
	Log log = new Log4JLogger(this.getClass().getName());
	
	private ImageData imageData;
	public ImageData getImageData() { return imageData; }
	public void setImageData(ImageData imageData) { this.imageData = imageData; }

	private FileBean imageFile;

	public FileBean getImageFile() {
		return imageFile;
	}

	public void setImageFile(FileBean imageFile) {
		this.imageFile = imageFile;
	}
	
	private List<ImageData> imageList;
	public List<ImageData> getImageList() {
		return imageList;
	}

	@DefaultHandler
	public Resolution showForm() {
		try {
			imageList=ImageService.findImageList();
		} catch (ServiceException e) {
			if(log.isErrorEnabled()){
				log.error("",e);
			}
		}
		return new ForwardResolution("/WEB-INF/view/admin/imageEdit.jsp");
	}
	
	public Resolution edit() {
    	try {
    		ApplicationSetting setting =ApplicationSetting.getInstance();
    		if(imageData.getImageId() == null){
    			ImageService.newImageData(imageFile, setting.getImageDirName());
    		}else{
    			ImageData oldData = ImageService.getImageData(imageData.getImageId());
    			if(imageFile ==null){
    				imageData.setContentType(oldData.getContentType());
    				imageData.setFileName(oldData.getContentType());
    				imageData.setImagePath(oldData.getImagePath());
    				imageData.setFileName(oldData.getFileName());
    				imageData.setX(oldData.getX());
    				imageData.setY(oldData.getY());
    				imageData.setThumbnailName(oldData.getThumbnailName());
    			}
				imageData.setCreated(oldData.getCreated());
    			ImageService.saveImage(oldData);
    		}
		} catch (ServiceException e) {
			if(log.isErrorEnabled()){
				log.error("",e);
			}
			return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"ファイルの描き込みエラーです。");
		}
    	return null;
	}


}
