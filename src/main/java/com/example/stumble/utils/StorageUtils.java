package com.example.stumble.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class StorageUtils {

    public static void uploadImage(MultipartFile file, String email) throws IOException {
        Path staticFolderPath = Paths.get("src", "main", "resources", "static", "images");
        Path filePath = staticFolderPath.resolve(getPath(file, email));
        try(InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Could not save image file");
        }
    }

    public static String getPath(MultipartFile file, String email){
        return email.split(".com")[0] + "." +
                file.getOriginalFilename().split("[.]")[1];
    }
}
