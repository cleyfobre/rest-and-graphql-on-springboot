package com.demo.springgraphql2.service.impl;

import com.demo.springgraphql2.dto.TargetDto;
import com.demo.springgraphql2.entity.Target;
import com.demo.springgraphql2.repository.TargetRepository;
import com.demo.springgraphql2.service.TargetService;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class TargetServiceImpl implements TargetService {

    @Autowired
    private AmazonS3 s3;

    @Value("${my.aws.bucket}")
    private String bucket;

    @Value("${my.aws.region}")
    private String region;

    @Value("${my.aws.s3-url}")
    private String s3Url;

    @Autowired
    private TargetRepository targetRepository;

    @Override
    public Target getTarget(Long id) {
        Optional<Target> targetsOptional = targetRepository.findById(id);
        if (targetsOptional.isPresent()) {
            return targetsOptional.get();
        }
        return null;
    }

    @Override
    public TargetDto.Partner getTargets(TargetDto.PartnerInfoRequest request) {

        // Set expiration time for the signed URL
        Date expiration = new Date(System.currentTimeMillis() + 3600000); // URL expires in 1 hour from now

        // Set custom response headers if needed
        ResponseHeaderOverrides responseHeaders = new ResponseHeaderOverrides();
        responseHeaders.setContentType("application/json");

        // Generate the presigned URL along with the custom response headers
        GeneratePresignedUrlRequest s3Request = new GeneratePresignedUrlRequest(bucket, "test/test.txt")
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration)
                .withResponseHeaders(responseHeaders);
        URL signedUrl = s3.generatePresignedUrl(s3Request);
        System.out.println("signedUrl.toString(): " + signedUrl.toString());
        return null;
    }

}
