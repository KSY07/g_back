package io.gongson.g_back.entity.board;

import jakarta.persistence.*;

@Entity
@Table(name="construction_examples")
public class ConstructionExample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String imgFileDir;
}
