package com.example.minitestbook.repository;

import com.example.minitestbook.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAppUserRepo extends CrudRepository<AppUser, Long> {

    Optional<AppUser> findByName(String name);
}
