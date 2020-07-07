package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Comptable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdherantDao extends JpaRepository <Adherant,Long> {
    public List<Adherant> findByComptable(Comptable comptable);


}
