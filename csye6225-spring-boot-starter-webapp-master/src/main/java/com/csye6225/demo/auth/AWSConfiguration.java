package com.csye6225.demo.auth;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {

    @Value("${cloud.aws.region}")
    private String region;



    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials("AKIAJP2M6S2UBJZFDV2A", "ZpuFiCcbVQY2mWHJK2BT3usOUriMYJQ9x/gMN7ea");
    }

    @Bean
    public AmazonS3Client amazonS3Client(BasicAWSCredentials awsCredentials) {
        AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials);
        amazonS3Client.setRegion(Region.getRegion(Regions.fromName(region)));
        return amazonS3Client;
//        AmazonS3 s3Client=new AmazonS3Client();
//        return s3Client;
    }
}