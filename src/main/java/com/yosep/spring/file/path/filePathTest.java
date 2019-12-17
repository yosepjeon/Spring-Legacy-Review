package com.yosep.spring.file.path;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class filePathTest {
	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	public void test() throws IOException {
		Resource r = new ClassPathResource( "default/default-profile.gif");
		File file = new File(r.getURI());
		byte[] bytes = FileUtils.readFileToByteArray(file);
		
		System.out.println(bytes.length);
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file2 = new File(classLoader.getResource("default/default-profile.gif").getFile());
		byte[] bytes2 = FileUtils.readFileToByteArray(file2);
		
		System.out.println(bytes2.length);
	}

}
