package com.sec02.assignment;

public class TestFileService {
    private static final String filesFolderPath = "/Users/j.pruthvichowdary/Desktop/workflow/reactive-programming-learning/src/main/java/com/sec02/assignment/data";

    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl(filesFolderPath);
        // read from an existing file
        fileService.read("example.txt").subscribe(i -> System.out.println("File contains: " + i),
                err -> System.out.println("Got this error: " + err.getMessage()));
        // write to a new file
        fileService.write("example1.txt", "Word 1\nWord 2").subscribe();
    }
}
