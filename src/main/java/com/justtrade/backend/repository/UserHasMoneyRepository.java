package com.justtrade.backend.repository;

import com.justtrade.backend.entity.DataUser;
import com.justtrade.backend.entity.UserHasMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserHasMoneyRepository extends JpaRepository<UserHasMoney,Long> {
}
