package com.yosep.spring.aws.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class S3Util {
	// bucketName
	private String bucketName = "yoggaebibucket";
	private String accessKey = ""; // 엑세스 키
	private String secretKey = ""; // 보안 엑세스 키
	
	// bucketName getter
	public String getBucketName() {
		return bucketName;
	}
	
	private AmazonS3 conn;
	
	public S3Util() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		this.conn = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.AP_NORTHEAST_2)
				.build();
	}
	
	// 버킷 리스트를 가져오는 메서드
	public List<Bucket> getBucketList() {
		return conn.listBuckets();
	}
	
	// 버킷을 생성하는 메서드
	public Bucket createBucket(String bucketName) {
		return conn.createBucket(bucketName);
	}
	
	// 폴더 생성( 폴더는 파일명 뒤에 "/"를 붙여야한다.
	public void createFolder(String bucketName, String folderName) {
		conn.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
	}
	
	// 파일 업로드
	public void fileUpload(String bucketName, String fileName, byte[] fileData) throws FileNotFoundException {
		// 파일 구별자를 '/'로 설정(\->/) 이게 기존에 / 였어도 넘어오면서 \로 바뀌는것 같다.
		String filePath = (fileName).replace(File.separatorChar, '/');
		ObjectMetadata metaData = new ObjectMetadata();
		
		// 메타데이터 설정 --> 원래는 128KB까지 업로드 가능했으나 파일크기만큼 버퍼를 설정시켰다.
		metaData.setContentLength(fileData.length);
		// 스트림 생성
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData); 
		// 파일 넣기
		conn.putObject(bucketName, filePath, byteArrayInputStream, metaData);
	}
	
	// 파일 삭제
	public void fileDelete(String fileName) {
		System.out.println("fileName: " + fileName);
		String imgName = (fileName).replace(File.separatorChar, '/');
		conn.deleteObject(this.getBucketName(),imgName);
		System.out.println("삭제성공");
	}
	
	// 파일 URL
	public String getFileURL(String bucketName, String fileName) {
		System.out.println("넘어오는 파일명: " + fileName);
		String imgName = (fileName).replace(File.separatorChar, '/');
		return conn.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, imgName)).toString();
	}
	
	// src파일 읽어오기
	public S3ObjectInputStream getSrcFile(String bucketName, String fileName) throws IOException {
		System.out.println("넘어오는 파일명: " + fileName);
		fileName = (fileName).replace(File.separatorChar, '/');
		// 해당 파일 s3객체에 담기
		S3Object s3object = conn.getObject(new GetObjectRequest(bucketName,fileName));
		// s3객체를 스트림으로 변환
		S3ObjectInputStream objectInputStream = s3object.getObjectContent();
		
		return objectInputStream;
	}
}
