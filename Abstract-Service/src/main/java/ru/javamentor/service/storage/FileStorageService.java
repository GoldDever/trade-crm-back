package ru.javamentor.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeImage(MultipartFile image, Long id);
    Resource loadImageAsResource(String ImageUrl);
    String fileFormat(String filename);
}
