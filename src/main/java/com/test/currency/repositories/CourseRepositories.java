package com.test.currency.repositories;

import com.test.currency.model.dto.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CourseRepositories extends JpaRepository<Quotes, Long> {

    ArrayList<Quotes> findAll();
}
