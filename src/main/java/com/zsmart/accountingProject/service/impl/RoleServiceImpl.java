package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Role;
import com.zsmart.accountingProject.dao.RoleDao;
import com.zsmart.accountingProject.service.facade.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role save(Role role) {
        Role loadedRole = roleDao.findByAuthority(role.getAuthority());
        if (loadedRole == null) {
            roleDao.save(role);
            return role;
        }
        return loadedRole;
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByAuthority(name);
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}