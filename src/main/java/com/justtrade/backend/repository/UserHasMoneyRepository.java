package com.justtrade.backend.repository;

import com.justtrade.backend.entity.UserHasMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHasMoneyRepository extends JpaRepository<UserHasMoney,Long> {

    UserHasMoney getByUserId(Long userId);
}
