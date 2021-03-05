package ru.javamentor.service.storage;

public class FileStorageException extends RuntimeException {
    public FileStorageException() {
    }

    public FileStorageException(String message) {
        super(message);
    }
}
