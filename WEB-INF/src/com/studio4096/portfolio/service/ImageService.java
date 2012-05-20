package com.studio4096.portfolio.service;

import static com.studio4096.portfolio.cnstants.Enabled.TRUE;
import static com.studio4096.portfolio.exception.ServiceException.Kind.FILE_ERROR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;
import net.sourceforge.stripes.action.FileBean;

import org.apache.commons.io.FilenameUtils;
import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;

import com.iciql.Db;
import com.iciql.IciqlException;
import com.studio4096.portfolio.action.PortfolioActionBeanContext;
import com.studio4096.portfolio.cnstants.ApplicationSetting;
import com.studio4096.portfolio.cnstants.Enabled;
import com.studio4096.portfolio.dao.DaoCommand;
import com.studio4096.portfolio.dao.DaoManager;
import com.studio4096.portfolio.entity.ImageData;
import com.studio4096.portfolio.exception.ServiceException;
import com.studio4096.portfolio.exception.ServiceException.Kind;

public class ImageService {
	
//	private static final List<String> 
	
	public static List<ImageData> findImageList() throws ServiceException {
		List<ImageData> imageList;
		
		DaoManager dm = DaoManager.getDefaultDaoManager();
		imageList = dm.executeAndClose(new DaoCommand<List<ImageData>>() {
			public List<ImageData> execute(Connection connection,DaoManager daoManager)
					throws SQLException,ServiceException {
				Db db= Db.open(connection);
				ImageData image = new ImageData();
				return db.from(image).select();
			}
		});
		
		List<String> imagePathList = new LinkedList<String>();
		for(ImageData oneImage : imageList){
			imagePathList.add(oneImage.getImagePath() +oneImage.getFileName());
		}
		ApplicationSetting setting =ApplicationSetting.getInstance();
		String basePath=setting.getcontentFileBasePath();
		File imageDir = new File(basePath + setting.getImageDirName());
		File[] files =imageDir.listFiles();
		if(files == null){
			return imageList;
		}
		for(File f :  files){
			if(f.isDirectory()){
				continue;
			}
			if(!Sanselan.hasImageFileExtension(f)){
				continue;
			}
			String extention =FilenameUtils.getExtension(f.getName());
			if(!ThumbnailatorUtils.isSupportedOutputFormat(extention)){
				continue;
			}
			
			String imagePath =f.getParent();
			String regex ="^"+Pattern.quote(imageDir.getAbsolutePath());
			imagePath=imagePath.replaceFirst(regex, "");
			if(imagePathList.contains(imagePath + f.getName())){
				continue;
			}
			try {
				imageList.add(newImageData(f,imagePath));
			} catch (ImageReadException e) {
				throw new ServiceException(e, FILE_ERROR);
			} catch (IOException e) {
				throw new ServiceException(e, FILE_ERROR);
			}
		}
		return imageList;
	}
	
	public static ImageData newImageData(File f,String imagePath) throws ImageReadException, IOException, ServiceException{
		
		ImageData imd =new ImageData();
		imd.setImagePath(imagePath);
		imd.setFileName(f.getName());
		imd.setTitle(null);
		imd.setMedia(null);
		imd.setDescription(null);
		imd.setTitle(null);
		imd.setPublicFlag(Enabled.FALSE.VAL);
		imd.setEnabled(Enabled.TRUE.VAL);
		
		ImageInfo imginf = Sanselan.getImageInfo(f);
		imd.setX(imginf.getWidth());
		imd.setY(imginf.getHeight());
		imd.setContentType(imginf.getMimeType());
		
		{
			ApplicationSetting setting =ApplicationSetting.getInstance();
			String basePath=setting.getcontentFileBasePath();
			int maxWidth,maxHeight;
			maxWidth =150;
			maxHeight =150;
			StringBuilder thumbsnalePath =new StringBuilder();
			thumbsnalePath.append(basePath + setting.getThumbsDirName());
			thumbsnalePath.append(FilenameUtils.getBaseName(f.getName()));
			thumbsnalePath.append("_s.");
			thumbsnalePath.append(FilenameUtils.getExtension(f.getName()));
			File thumbnail =getResolvedDuplecationFile(thumbsnalePath.toString());
			Thumbnails.of(f).size(maxWidth, maxHeight).toFile(thumbnail);
			imd.setThumbnailName(thumbnail.getName());
		}
		
		
		Date now =Calendar.getInstance().getTime();
		imd.setCreated(now);
		imd.setModified(now);
		
		saveImage(imd);
		return imd;
	}
	
	protected static File getResolvedDuplecationFile(CharSequence filePath){
		String s =filePath.toString();
		File file =new File(s);
		if(!file.exists()){
			return file;
		}
		String path =FilenameUtils.getFullPath(s);
		String basename =FilenameUtils.getBaseName(s);
		String extention =FilenameUtils.getExtension(s);
		
		int i =1;
		while (file.exists()){
			StringBuilder sb =new StringBuilder();
			sb.append(path);
			sb.append(basename);
			sb.append("("+i+").");
			sb.append(extention);
			file =new File(sb.toString());
			i++;
		}
		return file;
	}
	
	public static ImageData newImageData(FileBean imageFile,String imagePath) throws ServiceException{
		
		ImageData imd =new ImageData();
		imd.setImagePath(imagePath);
		imd.setFileName(imageFile.getFileName());
		imd.setTitle(null);
		imd.setMedia(null);
		imd.setDescription(null);
		imd.setTitle(null);
		imd.setPublicFlag(Enabled.FALSE.VAL);
		imd.setEnabled(Enabled.TRUE.VAL);
		
		try {
			ImageInfo imageInfo;
			imageInfo = Sanselan.getImageInfo(imageFile.getInputStream(),imageFile.getFileName());
			imd.setX(imageInfo.getWidth());
			imd.setY(imageInfo.getHeight());
			imd.setContentType(imageInfo.getMimeType());
		} catch (ImageReadException e) {
			throw new ServiceException(e, FILE_ERROR);
		} catch (IOException e) {
			throw new ServiceException(e, FILE_ERROR);
		}
		
		Date now =Calendar.getInstance().getTime();
		imd.setCreated(now);
		imd.setModified(now);
		
		saveImage(imd);
		
    	String path =ApplicationSetting.getInstance().getcontentFileBasePath() +imd.getImagePath();
    	File file=new File(path +File.separator+imd.getFileName());
    	try {
			writeInputStreamToFile(file, imageFile.getInputStream());
		} catch (IOException e) {
			throw new ServiceException(e, FILE_ERROR);
		}
		return imd;
	}

	private static void writeInputStreamToFile(File file, InputStream is)
			throws IOException {
		// write the inputStream to a FileOutputStream
		OutputStream out = new FileOutputStream(file);

		int read = 0;
		byte[] bytes = new byte[1024];
		while ((read = is.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}

		is.close();
		out.flush();
		out.close();
	}	

	public static int saveImage(final ImageData imageData) throws ServiceException {
		DaoManager dm = DaoManager.getDefaultDaoManager();
		return dm.executeAndClose(new DaoCommand<Integer>() {

			public Integer execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				Db db= Db.open(connection);
				Date now = Calendar.getInstance().getTime();
				imageData.setModified(now);
				if (imageData.getImageId() == null) {
					imageData.setEnabled(TRUE.VAL);
					imageData.setCreated(now);
					db.insert(imageData);
					return 1;
				} else {
					imageData.setEnabled(TRUE.VAL);
					return db.update(imageData);
				}
			}
		});
	}
	
	public static Map<Long, ImageData> findImageDataMap() throws ServiceException {
		Map<Long, ImageData> map = new HashMap<Long, ImageData>();
		List<ImageData> pageList = findImageList();
		for (ImageData page : pageList) {
			map.put(page.getImageId(), page);
		}
		return map;
	}

	public static ImageData getImageData(final Long imageId) throws ServiceException {
		DaoManager dm = DaoManager.getDefaultDaoManager();
		return dm.executeAndClose(new DaoCommand<ImageData>() {

			public ImageData execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				Db db= Db.open(connection);
				try{
					
				ImageData image = new ImageData();
				return db.from(image).where(image.imageId).is(imageId).selectFirst();
				}catch (IciqlException e) {
					throw new ServiceException(e,Kind.DB_ERROR);
				}
			}
		});
	}

	public static ImageData getImageData(Long imageId,
			PortfolioActionBeanContext context) throws ServiceException {
		if (imageId == null) {
			return null;
		}
		Map<Long, ImageData> imageCache = context.getImageDataCache();
		ImageData image = imageCache.get(imageId);
		if (image != null) {
			return image;
		}
		// has no cache.
		image = getImageData(imageId);
		if (image != null) {
			imageCache.put(imageId, image);
		}
		return image;
	}

}
