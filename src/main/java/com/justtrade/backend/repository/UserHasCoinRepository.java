package com.justtrade.backend.repository;

import com.justtrade.backend.entity.UserHasCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserHasCoinRepository extends JpaRepository<UserHasCoin,Long> {

    UserHasCoin getByDataUserIdAndCoinId(Long userId, Long coindId);
}
