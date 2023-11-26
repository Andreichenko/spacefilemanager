package com.frei.spacefilemanager.controller;

import com.frei.spacefilemanager.entities.FileDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.frei.spacefilemanager.service.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping
    public List<String> listFiles() {
        return fileService.listFiles();
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
        InputStream inputStream = fileService.downloadFile(fileName);
        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .body(resource);
    }

    @GetMapping("/search")
    public List<FileDetail> searchFiles(@RequestParam String query) {
        return fileService.searchFiles(query);
    }


    @GetMapping("/details")
    public List<FileDetail> listFileDetails() {
        return fileService.listFileDetails();
    }
}
