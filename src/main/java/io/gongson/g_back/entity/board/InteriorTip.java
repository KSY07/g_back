package io.gongson.g_back.entity.board;

import io.gongson.g_back.dto.InteriorTipDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Entity
@Table(name="interior_tip")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InteriorTip {
    @Id
    private String id;
    private String title;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;
    private int likes;
    private LocalDateTime createdAt;
    @Lob
    private byte[] thumbnail;

    public static InteriorTip createByDTO(InteriorTipDTO.Create dto, String thumbnail) {
        return InteriorTip.builder()
                .id(UUID.randomUUID().toString())
                .title(dto.getTitle())
                .content(dto.getContent())
                .likes(0)
                .createdAt(LocalDateTime.now())
                .thumbnail(Base64.getDecoder().decode(thumbnail))
                .build();
    }
}
