package ru.javamentor.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@PropertySource("classpath:application.properties")
public class FileServiceImpl implements FileService {
    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Override
    public String upload(MultipartFile resource) throws IOException {
        Path path = Paths.get(env.getProperty("directory_path"), resource.getOriginalFilename());
        Files.write(path, resource.getBytes());
        return path.toString();
    }
}
