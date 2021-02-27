package ru.javamentor.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    void storeProductImage(MultipartFile image, String idFromErp);
    Resource loadImageAsResource(String ImageUrl);
    String fileFormat(String filename);
}
