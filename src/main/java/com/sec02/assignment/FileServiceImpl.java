package com.sec02.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileServiceImpl implements FileService {
    private final String folderPath;

    private final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public FileServiceImpl(String folderPath) {
        this.folderPath = folderPath;
    }

    private String readFile(String fileName) throws IOException {
        String resourcePath = folderPath + "/" + fileName;
        logger.info("Trying to read file: {}", resourcePath);
        try (InputStream in = FileServiceImpl.class.getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }

            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (IOException e) {
            logger.info("Received error trying to read file: {}, Error: {}", resourcePath, e.getMessage());
            throw e;
        }
    }

    @Override
    public Mono<String> read(String fileName) {
        logger.info("creating the publisher for reading the file...");
        return Mono.fromCallable(() -> readFile(fileName));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return null;
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return null;
    }
}
