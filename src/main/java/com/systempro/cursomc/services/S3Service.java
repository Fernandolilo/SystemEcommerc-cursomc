package com.systempro.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucktName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			// este metodo consegue extratir o arquivo que foi enviado
			String fileName = multipartFile.getOriginalFilename();
			// objeto basico de leitura, este objeto encapsula o process de leitura apartir da origem
			InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao conver IO: " + e.getMessage());
		}

	}

	public URI uploadFile(InputStream is, String fileName, String conttType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(conttType);
			LOG.info("Iniciando upload");
			s3client.putObject(bucktName, fileName, is, meta);
			LOG.info("Upload finalizado");

			return s3client.getUrl(bucktName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao conver URL em URI");
		}

	}

}
