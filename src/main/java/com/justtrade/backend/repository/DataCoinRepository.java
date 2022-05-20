package com.justtrade.backend.repository;

import com.justtrade.backend.entity.DataCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCoinRepository extends JpaRepository<DataCoin,Long> {
}
