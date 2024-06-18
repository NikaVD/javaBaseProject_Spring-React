package com.traineeveronikadavydova.hotelWebsiteWithBooking.utils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class FileUploadUtil implements Runnable {
    private final String uploadDir;
    private final String fileName;
    private final MultipartFile multipartFile;

    public String getUploadDir() {
        return uploadDir;
    }

    public String getFileName() {
        return fileName;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }


    public FileUploadUtil(String uploadDir, String fileName, MultipartFile multipartFile) {
        this.uploadDir = uploadDir;
        this.fileName = fileName;
        this.multipartFile = multipartFile;
    }

    @Override
    public void run() {
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            ResizeImage.resizeImage(filePath.toFile());


        } catch (IOException ioe) {
            try {
                throw new IOException("Could not save image file: " + fileName, ioe);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



}
