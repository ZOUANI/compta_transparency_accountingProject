package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Banque;
import com.zsmart.accountingProject.bean.CompteBanquaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompteBanquaireDao extends JpaRepository<CompteBanquaire, Long> {


    public CompteBanquaire findByBanque(Banque banque);

    public List<CompteBanquaire> findCompteBanquairesByAdherantId(Long id);

    public CompteBanquaire findByAdherantIdAndId(Long adherentId, Long id);

    public int deleteByBanque(Banque banque);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);

}
