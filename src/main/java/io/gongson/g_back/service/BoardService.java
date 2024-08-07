package io.gongson.g_back.service;

import io.gongson.g_back.dto.InteriorTipDTO;
import io.gongson.g_back.entity.board.InteriorTip;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    void saveInteriorTip(InteriorTipDTO.Create dto, MultipartFile thumbnail);
    InteriorTipDTO.Info getInteriorTip(String id);
    List<InteriorTipDTO.Info> getInteriorTips();
}
