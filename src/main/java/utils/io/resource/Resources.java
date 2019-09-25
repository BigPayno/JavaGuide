package utils.io.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.core.io.ClassPathResource;

import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

public final class Resources {
	private Resources(){
		
	}
	private static CharSource from(File file, Charset charset) {
		if(file.exists()) {
			return Files.asCharSource(file, charset);
		}else {
			return CharSource.empty();
		}
	}

	private static CharSource from(String classpath, Charset charset) throws IOException {
		ClassPathResource resource = new ClassPathResource(classpath);
		ByteSource byteSource;
		try (InputStream is = resource.getInputStream()) {
			byteSource = ByteSource.wrap(ByteStreams.toByteArray(is));
		}
		return byteSource.asCharSource(charset);
	}

	public static CharSource from(String path, ResourceType type, Charset charset){
		CharSource charSource;
		try {
			if (type.equals(ResourceType.FILE_RESOURCE)) {
				File from = new File(path);
				charSource=from(from, charset);
			} else if (type.equals(ResourceType.JAR_RESOURCE)) {
				charSource=from(path, charset);
			} else {
				charSource=from(path, charset);
			}
		} catch (IOException e) {
			return CharSource.empty();
		}
		return charSource;
	}
}
