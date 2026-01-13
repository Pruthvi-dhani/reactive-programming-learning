package com.sec02.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileServiceImpl implements FileService {
    private final String folderPath;

    private final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public FileServiceImpl(String folderPath) {
        this.folderPath = folderPath;
    }

    private String readFile(String fileName) throws IOException {
        String resourcePath = folderPath + "/" + fileName;
        logger.info("Trying to read file: {}", resourcePath);
        Path path = Paths.get(resourcePath);
        try (InputStream in = Files.newInputStream(path)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (NoSuchFileException e) {
            logger.info("File doesn't exist at path: {}", resourcePath);
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        catch (IOException e) {
            logger.info("Received error trying to read file: {}, Error: {}", resourcePath, e.getMessage());
            throw e;
        }
    }

    private Void writeToFile(String content, String fileName) throws IOException {
        String resourcePath = folderPath + "/" + fileName;
        logger.info("Trying to write to file: {}", resourcePath);
        Path path = Paths.get(resourcePath);
        Files.writeString(path, content, StandardCharsets.UTF_8);
        return null;
    }

    private Void deleteFile(String fileName) throws IOException {
        String resourcePath = folderPath + "/" + fileName;
        logger.info("Trying to delete file: {}", resourcePath);
        Path path = Paths.get(resourcePath);
        Files.delete(path);
        return null;
    }

    @Override
    public Mono<String> read(String fileName) {
        logger.info("creating the publisher for reading the file...");
        return Mono.fromCallable(() -> readFile(fileName));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        logger.info("creating the publisher for writing the file...");
        return Mono.fromCallable(() -> writeToFile(content, fileName));
    }

    @Override
    public Mono<Void> delete(String fileName) {
        logger.info("creating the publisher for deleting the file...");
        return Mono.fromCallable(() -> deleteFile(fileName));
    }
}
