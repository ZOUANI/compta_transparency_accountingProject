package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FournisseurDao extends JpaRepository<Fournisseur, Long> {
    public List<Fournisseur> findByAdherant(Adherant adherant);

    public Fournisseur findByAdherantIdAndId(Long adherentId, Long id);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);


}
