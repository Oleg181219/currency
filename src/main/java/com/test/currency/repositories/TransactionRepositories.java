package com.test.currency.repositories;

import com.test.currency.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TransactionRepositories extends JpaRepository<Transaction, Long> {

    @Override
    List<Transaction> findAll();


    @Query("select t " +
            "from Transaction t " +
            "GROUP BY t.sourceName, t.targetName " +
            "ORDER BY count (t) desc ")
    List<Transaction> findAllPopular();

}
