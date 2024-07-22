package io.gongson.g_back.entity.board;

import io.gongson.g_back.entity.auth.Company;
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
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="company_pk", referencedColumnName="id")
    private Company company;
}
