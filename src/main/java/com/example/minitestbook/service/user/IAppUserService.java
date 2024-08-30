package com.example.minitestbook.service.user;

import com.example.minitestbook.model.AppUser;
import com.example.minitestbook.service.IGenerateService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAppUserService extends IGenerateService<AppUser>, UserDetailsService {
}
