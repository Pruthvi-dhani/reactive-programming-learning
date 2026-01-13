package com.sec02.assignment;

public class TestFileService {
    private static final String filesFolderPath = "/sec02assignmentfiles";

    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl(filesFolderPath);
        fileService.read("example.txt").subscribe(i -> System.out.println("File contains: " + i),
                err -> System.out.println("Got this error: " + err.getMessage()));
    }
}
