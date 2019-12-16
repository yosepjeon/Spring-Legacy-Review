package com.yosep.spring.aws.util;


import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	
	/*
	 * String uploadPath 파일의 저장경로
	 * String originalName 원본 파일 이름
	 * byte[] fileData 파일 데이터
	 */
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		// S3 서버 관련 설정
		S3Util s3 = new S3Util();
		String bucketName = "yoggaebibucket";
		
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		String savedPath = calcPath(uploadPath);
		String imagePath = "profile/" + uploadPath;
		
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = (savedPath + savedName).replace(File.separatorChar, '/');
		
		s3.fileUpload(bucketName, uploadPath + uploadedFileName, fileData);
		
		return uploadedFileName;
	}
	
	// 이미지 파일이 아닌 경우에 아이콘 생성
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		String iconName = uploadPath = path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// 경로 설정처리
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		
		// 년도 설정
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		
		// 월 설정
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		
		// 일 설정
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		S3Util s3 = new S3Util();
		String bucketName = "yoggaebibucket";
		
		return datePath;
	}
}
