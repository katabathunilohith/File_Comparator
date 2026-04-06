package com.example.filecompare.controller;

import com.example.filecompare.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@RestController
public class FileController {

    @PostMapping("/compare")
    public String compareFiles(@RequestParam("file1") MultipartFile file1,
                               @RequestParam("file2") MultipartFile file2) {
        try {
            File f1 = new File("file1.txt");
            FileOutputStream fos1 = new FileOutputStream(f1);
            fos1.write(file1.getBytes());
            fos1.close();

            File f2 = new File("file2.txt");
            FileOutputStream fos2 = new FileOutputStream(f2);
            fos2.write(file2.getBytes());
            fos2.close();

            return FileService.compareFiles("file1.txt", "file2.txt");

        } catch (Exception e) {
            return "Error processing files";
        }
    }
}