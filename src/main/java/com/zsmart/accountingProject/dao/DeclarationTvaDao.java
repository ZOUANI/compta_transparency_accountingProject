package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.DeclarationTva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationTvaDao extends JpaRepository<DeclarationTva,Long> {




}
