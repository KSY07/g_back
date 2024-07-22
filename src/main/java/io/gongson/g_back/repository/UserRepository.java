package io.gongson.g_back.repository;

import io.gongson.g_back.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
