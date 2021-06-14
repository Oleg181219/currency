package com.test.currency.repositories;

import com.test.currency.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TransactionRepositories extends JpaRepository<Transaction, Long> {

    @Override
    List<Transaction> findAll();
}
