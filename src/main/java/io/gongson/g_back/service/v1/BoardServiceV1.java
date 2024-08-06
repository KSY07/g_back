package io.gongson.g_back.service.v1;

import io.gongson.g_back.dto.InteriorTipDTO;
import io.gongson.g_back.entity.board.InteriorTip;
import io.gongson.g_back.repository.InteriorTipRepository;
import io.gongson.g_back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceV1 implements BoardService {

    private final InteriorTipRepository interiorTipRepository;

    @Override
    public void saveInteriorTip(InteriorTipDTO.Create dto, String thumbnail) {
        interiorTipRepository.save(InteriorTip.createByDTO(dto, thumbnail));
    }

    @Override
    public InteriorTip getInteriorTip(String id) {
        return interiorTipRepository.findById(id).orElse(null);
    }

    @Override
    public List<InteriorTip> getInteriorTips() {
        return interiorTipRepository.findAll();
    }

}
