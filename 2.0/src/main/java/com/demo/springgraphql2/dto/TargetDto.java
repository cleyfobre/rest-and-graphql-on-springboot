package com.demo.springgraphql2.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

public class TargetDto {

    @Data
    @Builder
    public static class GenaralResponse {
        private int code;
        private String message;
        private Object content;
    }

    @Data
    @Builder
    public static class PartnerInfoRequest {
        private String keyword;
        private String carNumber;
        private String businessNumber;
        private String industryCode;
    }

    @Data
    @Builder
    public static class Partner {
        private String name;
        private String code;
        private String regNum;
        private String city;
        private String address;
    }

    @Data
    @Builder
    public static class AnalysisRequest {
        private String command;
        private String groupKey;
        private String loginId;
        private String secureKey;
        private String partnerCode;
        private String fileName;
        private int fileSize;
        private String fileType;
        private String dtgModelName;
        private String vinNo;
        private String carBsnCode;
        private String carRegNum;
        private String partnerRegNum;
        private String driverCode;
        private String driveDate;
        private String driveStartTime;
        private String driveEndTime;
        private int driveDistance;
        private String errCode;
        private String appCode;
        private String fileStandard;
    }

    @Data
    @Builder
    public static class KeyPair {
        private String groupKey;
        private String fileKey;
    }

    @Data
    @Builder
    public static class FileNameRequest {
        private String carRegNum;
        private String driveCnt;
        private String driverCode;
    }

    @Data
    @Builder
    public static class ZipFileNameRequest {
        private String partnerRegNum;
        private String groupKey;
        private String fileKey;
        private List<String> fileNames;
    }


}
