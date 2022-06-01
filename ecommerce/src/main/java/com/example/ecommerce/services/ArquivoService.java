package com.example.ecommerce.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoService {

	@Autowired
	MailService mailService;
	
	@Value("${pasta.arquivos.imagem}")
	private String diretorioArquivos;
	
	private Path localFinalArquivo;
	
	public void criarArquivo(String fileName, MultipartFile file) throws Exception {
		try {
			if (fileName.contains("..")){
				throw new Exception ("Nome do arquivo inválido.");
			}
			this.localFinalArquivo = Paths.get(diretorioArquivos).toAbsolutePath().normalize();
			Path destinoLocal = localFinalArquivo.resolve(fileName);
			Files.copy(file.getInputStream(), destinoLocal, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			throw new IOException ("Nome foi possivel mover o arquivo.-" + ex.getStackTrace());
		}
	}
}
