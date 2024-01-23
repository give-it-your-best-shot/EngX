package com.engx.engxserver.service.base;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface AvatarStorageService {
    boolean save(long userId, MultipartFile image) throws IOException;

    byte[] get(long userId) throws IOException;
}
