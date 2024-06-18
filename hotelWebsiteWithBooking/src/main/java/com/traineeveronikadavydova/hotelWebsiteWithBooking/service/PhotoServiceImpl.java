package com.traineeveronikadavydova.hotelWebsiteWithBooking.service;

import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Photo;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Review;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.model.Room;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.repository.PhotoRepository;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.utils.FileUploadUtil;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.utils.ResizeImage;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Value("${upload.path}")
    private String uploadPath;


    private final PhotoRepository photoRepository;

    private final ExecutorService executor;


    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void uploadPhotoAdmin(MultipartFile[] multipartFiles, Room room) throws IOException {
        for(MultipartFile multipartFile:multipartFiles) {
            System.out.println("Currently Executing thread name - " + Thread.currentThread().getName());
            String photoFileName = UUID.randomUUID() + "_" + StringUtils.cleanPath(multipartFile.getOriginalFilename());
            Photo photo = new Photo();
            photo.setPhotosName(photoFileName);
            photo.setRoom(room);
            photo.setPhotosImagePath(uploadPath.substring(83) + room.getName() + "/" + photoFileName);
            photoRepository.save(photo);

            String uploadDir = new StringBuilder().append(uploadPath).append(room.getName()).toString();

            executor.submit(() -> {
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    try {
                        Files.createDirectories(uploadPath);
                    } catch (IOException e) {
                        LoggerFactory.logger(PhotoServiceImpl.class).error(e.getMessage());;
                    }
                }

                try (InputStream inputStream = multipartFile.getInputStream()) {
                    Path filePath = uploadPath.resolve(photoFileName);

                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    ResizeImage.resizeImage(filePath.toFile());


                } catch (IOException ioe) {
                    try {
                        throw new IOException("Could not save image file: " + photoFileName, ioe);
                    } catch (IOException e) {
                        LoggerFactory.logger(PhotoServiceImpl.class).error(e.getMessage());;
                    }
                }
            });

        }


    }

    @Override
    public void uploadPhotoReview(MultipartFile[] multipartFiles, Room room, Review review) throws IOException {
        for(MultipartFile multipartFile:multipartFiles) {
            String photoFileName = UUID.randomUUID() + "_" + StringUtils.cleanPath(multipartFile.getOriginalFilename());
            Photo photo = new Photo();
            photo.setPhotosName(photoFileName);
            photo.setRoom(room);
            photo.setReview(review);
            photo.setPhotosImagePath(uploadPath.substring(83) + room.getName() + "/" + review.getUser().getUsername() + "/" + photoFileName);
            photoRepository.save(photo);

            String uploadDir = uploadPath + room.getName() + "/" + review.getUser().getUsername();
            executor.submit(() -> {
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    try {
                        Files.createDirectories(uploadPath);
                    } catch (IOException e) {
                        LoggerFactory.logger(PhotoServiceImpl.class).error(e.getMessage());;
                    }
                }

                try (InputStream inputStream = multipartFile.getInputStream()) {
                    Path filePath = uploadPath.resolve(photoFileName);

                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    ResizeImage.resizeImage(filePath.toFile());


                } catch (IOException ioe) {
                    try {
                        throw new IOException("Could not save image file: " + photoFileName, ioe);
                    } catch (IOException e) {
                        LoggerFactory.logger(PhotoServiceImpl.class).error(e.getMessage());
                    }
                }
            });

        }

    }

    @Override
    @PreDestroy
    public void stop() {
        executor.shutdown();
    }

    @Override
    public List<Photo> findAllPhotosByReview(Review review) {
        return photoRepository.findByReview(review);
    }

    @Override
    public List<Photo> findAllPhotosByRoom(Room room) {
        return photoRepository.findByRoomAndReviewIsNull(room);
    }

    @Override
    public List<Photo> findAllPhotos() {return photoRepository.findAll();}
}
