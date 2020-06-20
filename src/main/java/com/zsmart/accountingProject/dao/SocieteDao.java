package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocieteDao extends JpaRepository<Societe, Long> {
    public List<Societe> findByAdherantId(Long id);

    public List<Societe> findByComptableId(Long id);

}
