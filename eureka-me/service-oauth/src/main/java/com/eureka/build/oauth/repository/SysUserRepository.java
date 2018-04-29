package com.eureka.build.oauth.repository;


import com.eureka.build.oauth.domain.SysUser;
import com.eureka.build.oauth.repository.support.WiselyRepository;

import java.util.Optional;

public interface SysUserRepository
        extends WiselyRepository<SysUser,Long> {
    Optional<SysUser> findOneWithRolesByUsername(String username);
}
