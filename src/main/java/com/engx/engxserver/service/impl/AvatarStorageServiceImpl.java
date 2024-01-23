package com.engx.engxserver.service.impl;

import com.engx.engxserver.service.base.AvatarStorageService;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AvatarStorageServiceImpl implements AvatarStorageService {

    @Value("${application.avatar.src}")
    private String root;

    @Override
    public boolean save(long userId, MultipartFile image) throws IOException {
        if (image.isEmpty()) return false;
        Files.createDirectories(Paths.get(this.root));
        Path destinationFile = Paths.get(this.root).resolve(userId + ".jpg");
        Files.copy(image.getInputStream(), destinationFile);
        return true;
    }

    @Override
    public byte[] get(long userId) throws IOException {
        Path file = Paths.get(this.root).resolve(userId + ".jpg");

        try (InputStream imageStream = new FileInputStream(file.toFile())) {
            byte[] image = imageStream.readAllBytes();
            imageStream.close();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resource nullImageResource = new ClassPathResource("null.jpg");
        InputStream nullStream = nullImageResource.getInputStream();
        byte[] nullImage = nullStream.readAllBytes();
        nullStream.close();
        return nullImage;
    }
}
