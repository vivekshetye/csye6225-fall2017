package com.csye6225.demo.auth;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {


    @Bean
    public AmazonS3 amazonS3Client() {
        AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard().withCredentials(new InstanceProfileCredentialsProvider(false)).build();
        return amazonS3Client;
    }
}