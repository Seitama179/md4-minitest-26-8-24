package com.example.minitestbook.service.role;

import com.example.minitestbook.model.AppRole;
import com.example.minitestbook.repository.IAppRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppRoleService implements IAppRoleService {
    @Autowired
    private IAppRoleRepo appRoleRepo;

    @Override
    public Page<AppRole> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<AppRole> findAll() {
        return appRoleRepo.findAll();
    }

    @Override
    public void save(AppRole appRole) {
        appRoleRepo.save(appRole);
    }

    @Override
    public Optional<AppRole> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Long id, AppRole appRole) {

    }

    @Override
    public void remove(Long id) {

    }
}
