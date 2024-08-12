package io.gongson.g_back.repository;

import io.gongson.g_back.entity.auth.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyIdAndPassword(String companyId, String password);
}
