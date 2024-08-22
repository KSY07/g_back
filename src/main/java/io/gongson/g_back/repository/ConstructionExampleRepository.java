package io.gongson.g_back.repository;

import io.gongson.g_back.entity.auth.Company;
import io.gongson.g_back.entity.board.ConstructionExample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConstructionExampleRepository extends JpaRepository<ConstructionExample, Long> {
    Optional<List<ConstructionExample>> findByCompany(Company company);
}
