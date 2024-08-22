package io.gongson.g_back.entity.board;

import io.gongson.g_back.entity.auth.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="construction_examples")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ConstructionExample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String contents;
    @ElementCollection
    private List<String> imgUrlList;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="company_pk", referencedColumnName="id")
    private Company company;
    private LocalDateTime createdDate;
}
