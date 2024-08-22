package io.gongson.g_back.repository;

import io.gongson.g_back.entity.auth.Company;
import io.gongson.g_back.entity.board.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {
    Optional<List<Reviews>> findByCompany(Company company);
}
