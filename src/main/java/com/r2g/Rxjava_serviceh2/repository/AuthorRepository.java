package com.r2g.Rxjava_serviceh2.repository;

import com.r2g.Rxjava_serviceh2.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

}
