package com.berkaykomur.ordermanagement.repository;

import com.berkaykomur.ordermanagement.entity.RefreshToken;
import com.berkaykomur.ordermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Modifying
    @Query("""
            update RefreshToken r 
            set r.revoked=true
            where r.user =:user and r.revoked=false
            """)
   void revokeAllRefreshTokens(@Param("user")User user);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

}
