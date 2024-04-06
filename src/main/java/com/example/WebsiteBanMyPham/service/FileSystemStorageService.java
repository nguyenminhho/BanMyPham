package com.example.WebsiteBanMyPham.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService{
    private final Path rootLocatoion;

    public FileSystemStorageService(){
        this.rootLocatoion = Paths.get("src/main/resources/static/uploads");
    }
    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocatoion);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {

            Path destinationFile = this.rootLocatoion.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
