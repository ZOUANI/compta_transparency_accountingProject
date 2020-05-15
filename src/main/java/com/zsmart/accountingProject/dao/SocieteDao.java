package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocieteDao extends JpaRepository<Societe,Long> {


}
