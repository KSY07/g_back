package io.gongson.g_back.service;

import io.gongson.g_back.dto.InteriorTipDTO;
import io.gongson.g_back.entity.board.InteriorTip;

import java.util.List;

public interface BoardService {
    void saveInteriorTip(InteriorTipDTO.Create dto, String thumbnail);
    InteriorTip getInteriorTip(String id);
    List<InteriorTip> getInteriorTips();
}
