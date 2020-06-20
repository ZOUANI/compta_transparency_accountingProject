package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.Role;

public interface RoleService {
    public Role save(Role role);

    public Role findByName(String name);
}