package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Comptable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComptableDao extends JpaRepository<Comptable,Long> {
}
