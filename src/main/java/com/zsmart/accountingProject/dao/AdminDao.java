package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin, Long> {
}
