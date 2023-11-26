package com.frei.spacefilemanager.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.frei.spacefilemanager.entities.FileDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FileServiceTest {

    private AmazonS3 s3Client;
    private FileService fileService;

    @BeforeEach
    void setUp() {
        s3Client = Mockito.mock(AmazonS3.class);
        fileService = new FileService(s3Client);
    }

    @Test
    void listFileDetails_returnsCorrectDetails() {

        ListObjectsV2Result mockResult = new ListObjectsV2Result();
        S3ObjectSummary summary = new S3ObjectSummary();
        summary.setKey("test-file.txt");
        summary.setSize(12345L);
        summary.setLastModified(new Date());
        mockResult.getObjectSummaries().add(summary);
        when(s3Client.listObjectsV2("callbot")).thenReturn(mockResult);

        List<FileDetail> details = fileService.listFileDetails();
        assertEquals(1, details.size());
        assertEquals("test-file.txt", details.get(0).getName());
        assertEquals(12345L, details.get(0).getSize());
    }

    @Test
    void searchFiles_returnsFilteredFiles() {
        // Create Mock data
        ListObjectsV2Result mockResult = new ListObjectsV2Result();
        mockResult.getObjectSummaries().add(createMockSummary("24-11-23_20:29_79118945036.mp3", 1000));
        mockResult.getObjectSummaries().add(createMockSummary("24-11-23_20:30_79118945037.mp3", 2000));
        when(s3Client.listObjectsV2("callbot")).thenReturn(mockResult);

        // Testing exact match testing
        List<FileDetail> exactMatch = fileService.searchFiles("24-11-23_20:29_79118945036.mp3");
        assertEquals(1, exactMatch.size());
        assertEquals("24-11-23_20:29_79118945036.mp3", exactMatch.get(0).getName());

        // Partial match testing
        List<FileDetail> partialMatch = fileService.searchFiles("20:29");
        assertEquals(1, partialMatch.size());
        assertEquals("24-11-23_20:29_79118945036.mp3", partialMatch.get(0).getName());

        // Test date search (if date is included in file name)
        List<FileDetail> dateMatch = fileService.searchFiles("24-11-23");
        assertEquals(2, dateMatch.size());
    }

    private S3ObjectSummary createMockSummary(String fileName, long fileSize) {
        S3ObjectSummary summary = new S3ObjectSummary();
        summary.setKey(fileName);
        summary.setSize(fileSize);
        summary.setLastModified(new Date());
        return summary;
    }
}
