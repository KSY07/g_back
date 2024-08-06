package io.gongson.g_back.entity.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="interior_tip")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InteriorTip {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


}
