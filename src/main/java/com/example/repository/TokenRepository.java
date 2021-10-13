package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.TokenClass;

@Repository("confirmationTokenRepository")
public interface TokenRepository extends CrudRepository<TokenClass, String> {
    TokenClass findByConfirmationToken(String confirmationToken);
}
