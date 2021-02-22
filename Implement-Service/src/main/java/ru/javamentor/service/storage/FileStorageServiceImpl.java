package ru.javamentor.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final String uploadPath = "images/";
    Logger logger = Logger.getLogger(FileStorageService.class.getName());

    @Override
    public String storeImage(MultipartFile image, Long id) {
        if (image == null || image.isEmpty()) {
            throw new FileStorageException("Загружаемый файл не существует.");
        }
        String fileFormat = fileFormat(image.getOriginalFilename());
        if (!(fileFormat.equals(".png") || fileFormat.equals(".jpg"))) {
            throw new FileStorageException("Формат файла не подходит. Для загрузки доступны, только файлы формата: .png,.jpeg");
        }
        if (!new File(uploadPath).exists()) {
            try {
                Files.createDirectory(Path.of(uploadPath).toAbsolutePath());
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
                throw new FileStorageException("Не удалось создать директорию для файла.");
            }
        }
        String filePath = uploadPath + id + fileFormat;
        try {
            image.transferTo(Path.of(filePath));
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new FileStorageException("Не удалось загрузить файл.");
        }
        return filePath;
    }

    @Override
    public Resource loadImageAsResource(String ImageUrl) {
        try {
            Path file = Path.of(ImageUrl);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileStorageException("Файл не найден.");
            }
        } catch (MalformedURLException e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new FileStorageException("Не удалось получить файл.");
        }
    }

    @Override
    public String fileFormat(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
