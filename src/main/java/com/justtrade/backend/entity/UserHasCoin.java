package com.justtrade.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHasCoin {

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private DataUser dataUser;

    @OneToOne
    @JoinColumn(name = "coinId", referencedColumnName = "id")
    private DataCoin dataCoin;

    private int jumlah;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

