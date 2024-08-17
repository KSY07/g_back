package io.gongson.g_back.entity.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.gongson.g_back.entity.auth.Company;
import io.gongson.g_back.entity.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="reviews")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Reviews {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @ManyToOne
    @JsonIgnore
    private User user;

    private String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String contents;

    private double rating;

}
