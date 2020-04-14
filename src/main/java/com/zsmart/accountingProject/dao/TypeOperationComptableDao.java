package com.zsmart.accountingProject.dao;
import com.zsmart.accountingProject.bean.TypeOperationComptable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
 public interface TypeOperationComptableDao extends JpaRepository<TypeOperationComptable,Long> {




}
