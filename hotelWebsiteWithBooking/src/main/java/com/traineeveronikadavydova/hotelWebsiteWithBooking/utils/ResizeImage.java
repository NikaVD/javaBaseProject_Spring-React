package com.traineeveronikadavydova.hotelWebsiteWithBooking.utils;
import com.traineeveronikadavydova.hotelWebsiteWithBooking.service.PhotoServiceImpl;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;




@Service
public class ResizeImage {
//    @Value("${image.size}")
//    private static Integer imageSize;

    private static final Integer imageSize = 200;

    public synchronized static boolean resizeImage (File originalImage) throws IOException {
      try {
          BufferedImage bufferedImage = ImageIO.read(originalImage);
          BufferedImage outputImage = Scalr.resize(bufferedImage, imageSize);
          Path path = Paths.get(originalImage.getPath());
          File resizedImage = path.toFile();
          String extension = originalImage.getName().substring(originalImage.getName().lastIndexOf(".") + 1);
          ImageIO.write(outputImage, extension, resizedImage);
          return true;
      } catch (IOException e) {
          LoggerFactory.logger(PhotoServiceImpl.class).error(e.getMessage());
          return false;
      }
    }
}
