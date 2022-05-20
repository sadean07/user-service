package com.justtrade.backend.repository;

import com.justtrade.backend.entity.DataUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataUserRepository extends JpaRepository<DataUser,Long> {
    Optional<DataUser> findByUsername(String username);
}
