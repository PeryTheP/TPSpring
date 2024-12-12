package com.example.tpspring;

import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called blogRepository
// CRUD refers Create, Read, Update, Delete
public interface BlogRepository extends CrudRepository<Blog, Integer> {
}



