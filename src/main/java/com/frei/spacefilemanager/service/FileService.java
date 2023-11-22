package com.frei.spacefilemanager.service;

import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.GetObjectRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.io.InputStream;

@Service
public class FileService {

    private final AmazonS3 s3Client;
    private final String bucketName = "callbot";

    public FileService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public List<String> listFiles() {
        ListObjectsV2Result result = s3Client.listObjectsV2(bucketName);
        return result.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    public InputStream downloadFile(String fileName) {
        S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, fileName));
        return s3object.getObjectContent();
    }
}
