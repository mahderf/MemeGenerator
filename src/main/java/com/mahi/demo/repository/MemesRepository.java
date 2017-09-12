package com.mahi.demo.repository;

import com.mahi.demo.models.Memes;
import org.springframework.data.repository.CrudRepository;

public interface MemesRepository extends CrudRepository<Memes,Long>{
}
