package com.example.tpspring;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called blogRepository
// CRUD refers Create, Read, Update, Delete
public interface BlogRepository extends CrudRepository<Blog, Integer> {
    @Query("SELECT b FROM Blog b WHERE b.auteur.id = :userId OR b.datePubli IS NOT NULL")
    List<Blog> findAllForPublisher(@Param("userId") Integer userId);

    @Query("SELECT b FROM Blog b WHERE b.datePubli IS NOT NULL")
    List<Blog> findAllPublic();
}



