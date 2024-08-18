package io.gongson.g_back.service.v1;

import io.gongson.g_back.service.ObjectStorageService;
import io.gongson.g_back.utils.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.net.URI;

@Service
public class ObjectStorageServiceImpl implements ObjectStorageService {

    @Value("${ncloud.accessKey}")
    private String accessKey;
    @Value("${ncloud.secretKey}")
    private String secretKey;
    @Value("${ncloud.cdnUrl}")
    private String cdnUrl;
    private String regionName = "kr-standard";
    private String bucketName = "gongson-statics";


    @Override
    public String uploadFile(MultipartFile file) {
        // 업로드 후, cdn 서버의 링크를 리턴해주어야한다.
        String objectKey = "";

        objectKey += "interiorTip/" + file.getOriginalFilename();

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);

        S3Client s3 = S3Client.builder()
                .region(Region.of(regionName))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .endpointOverride(URI.create("https://kr.object.ncloudstorage.com"))
                .build();

        try {
            s3.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(objectKey)
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
                    );
        } catch (S3Exception | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        s3.close();
        return cdnUrl + file.getOriginalFilename();
    }
}
