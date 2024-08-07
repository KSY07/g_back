package io.gongson.g_back.entity.board;

import io.gongson.g_back.dto.InteriorTipDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private String thumbnailDir;

    public static InteriorTip createByDTO(InteriorTipDTO.Create dto, String thumbnail) {
        return InteriorTip.builder()
                .id(UUID.randomUUID().toString())
                .title(dto.getTitle())
                .content(dto.getContent())
                .likes(0)
                .createdAt(LocalDateTime.now())
                .thumbnailDir(thumbnail)
                .build();
    }

    public InteriorTipDTO.Info toDto() {
        String ext = thumbnailDir.substring(thumbnailDir.lastIndexOf("."));
        byte[] thumbnailBytes;
        try {
            thumbnailBytes = Files.readAllBytes(Path.of(thumbnailDir));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return InteriorTipDTO.Info.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .likes(this.likes)
                .createdAt(this.createdAt)
                .thumbnailBase64String(String.format("data:image/%s;base64,%s", ext, Base64.getEncoder().encodeToString(thumbnailBytes)))
                .build();
    }
}
