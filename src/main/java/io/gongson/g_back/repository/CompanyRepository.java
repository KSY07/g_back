package io.gongson.g_back.repository;

import io.gongson.g_back.entity.auth.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
