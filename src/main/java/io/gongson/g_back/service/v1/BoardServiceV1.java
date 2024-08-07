package io.gongson.g_back.service.v1;

import io.gongson.g_back.dto.InteriorTipDTO;
import io.gongson.g_back.entity.board.InteriorTip;
import io.gongson.g_back.repository.InteriorTipRepository;
import io.gongson.g_back.service.BoardService;
import io.gongson.g_back.utils.FileType;
import io.gongson.g_back.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceV1 implements BoardService {

    private final InteriorTipRepository interiorTipRepository;
    private final FileUtils fileUtils;

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

}
