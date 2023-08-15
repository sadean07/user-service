package com.justtrade.backend.repository;

import com.justtrade.backend.entity.DataUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataUserRepository extends JpaRepository<DataUser,Long>, JpaSpecificationExecutor<DataUser> {
    Optional<DataUser> findByUsernameAndIsActiveTrue(String username);
    @Query(value = "SELECT * FROM data_user du WHERE du.email = :email AND du.is_active = true", nativeQuery = true)
    Optional<DataUser> findByEmail(@Param("email") String email);
}
