package com.example.minitestbook.repository;

import com.example.minitestbook.model.AppRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRoleRepo extends CrudRepository<AppRole, Long> {

}
