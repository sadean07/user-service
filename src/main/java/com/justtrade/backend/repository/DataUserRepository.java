package com.justtrade.backend.repository;

import com.justtrade.backend.entity.DataUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataUserRepository extends JpaRepository<DataUser,Long> {
}
