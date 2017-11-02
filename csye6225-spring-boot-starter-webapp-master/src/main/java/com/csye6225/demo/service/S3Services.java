package com.csye6225.demo.service;

import java.io.File;

public interface S3Services {
    public void uploadFile(String keyName, File file);
    public void deleteFileFromS3(String key);
}