package io.gongson.g_back.service.v1;

import io.gongson.g_back.dto.ConstructionExampleDTO;
import io.gongson.g_back.dto.InteriorTipDTO;
import io.gongson.g_back.dto.ReviewDTO;
import io.gongson.g_back.entity.auth.Company;
import io.gongson.g_back.entity.auth.User;
import io.gongson.g_back.entity.board.ConstructionExample;
import io.gongson.g_back.entity.board.InteriorTip;
import io.gongson.g_back.entity.board.Reviews;
import io.gongson.g_back.repository.*;
import io.gongson.g_back.service.BoardService;
import io.gongson.g_back.service.ObjectStorageService;
import io.gongson.g_back.utils.FileType;
import io.gongson.g_back.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceV1 implements BoardService {

    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;
    private final ConstructionExampleRepository constructionExampleRepository;
    private final InteriorTipRepository interiorTipRepository;
    private final FileUtils fileUtils;
    private final UserRepository userRepository;

    private final ObjectStorageService objectStorageService;

    @Override
    public void saveInteriorTip(InteriorTipDTO.Create dto, MultipartFile thumbnail) {
        try {
            interiorTipRepository.save(InteriorTip.createByDTO(dto, fileUtils.saveFile(thumbnail, FileType.BOARD_THUMBNAIL)));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public InteriorTipDTO.Info getInteriorTip(String id) {

        return interiorTipRepository.findById(id).orElse(null).toDto();
    }

    @Override
    public List<InteriorTipDTO.Info> getInteriorTips() {
        return interiorTipRepository.findAll().stream().map(InteriorTip::toDto).toList();
    }

    @Override
    public void createReview(ReviewDTO.Create dto, List<MultipartFile> images) {
        Company c = companyRepository.findById(Long.valueOf(dto.getCompanyPk())).orElseThrow(
                () -> new RuntimeException("Not Found Company")
        );

        User u = userRepository.findByUserId(dto.getUserId()).orElseThrow(
                () -> new RuntimeException("Not Found User")
        );

        List<String> imageUrlList = new ArrayList<>();

        for(MultipartFile image : images) {
            String url = objectStorageService.uploadFile(image);
            imageUrlList.add(url);
        }

        reviewRepository.save(Reviews.builder()
                        .title(dto.getTitle())
                        .company(c)
                        .user(u)
                        .contents(dto.getContents())
                        .rating(dto.getRating())
                        .createdDate(LocalDateTime.now())
                        .imgUrlList(imageUrlList)
                .build());
    }

    @Override
    public List<ReviewDTO.Info> getReviews(long companyPk) {
        return reviewRepository.findByCompany(companyRepository.findById(companyPk).orElseThrow(
                () -> new RuntimeException("Not Valid Reviews")
        )).orElseThrow(() -> new RuntimeException("Reivews Not Found")).stream().map(e -> {
            return ReviewDTO.Info.builder()
                    .companyPk(e.getId())
                    .title(e.getTitle())
                    .contents(e.getContents())
                    .rating(e.getRating())
                    .imageUrlList(e.getImgUrlList())
                    .userName(e.getUser().getName())
                    .createdDate(e.getCreatedDate())
                    .build();
        }).toList();
    }

    @Override
    public void createConstructionExample(ConstructionExampleDTO.Create dto, List<MultipartFile> images) {
        Company c = companyRepository.findById(dto.getCompanyPk()).orElseThrow(
                () -> new RuntimeException("Not Valid Company")
        );

        List<String> imageUrlList = new ArrayList<>();

        for(MultipartFile image : images) {
            String url = objectStorageService.uploadFile(image);
            imageUrlList.add(url);
        }

        constructionExampleRepository.save(ConstructionExample.builder()
                        .company(c)
                        .title(dto.getTitle())
                        .contents(dto.getContents())
                        .imgUrlList(imageUrlList)
                        .createdDate(LocalDateTime.now())
                        .build());

    }

    @Override
    public List<ConstructionExampleDTO.Info> getConstructionExamples(long companyPk) {
        return constructionExampleRepository.findByCompany(
                companyRepository.findById(companyPk).orElseThrow(
                        () -> new RuntimeException("Not Valid Company Pk")
                )
        ).orElseThrow(
                () -> new RuntimeException("Not Found construction Example")
        ).stream().map(e -> {
                    return ConstructionExampleDTO.Info.builder()
                            .title(e.getTitle())
                            .contents(e.getContents())
                            .imgUrlList(e.getImgUrlList())
                            .createdDate(e.getCreatedDate())
                            .build();
                }
        ).toList();
    }
}
