package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.FactureFournisseur;
import com.zsmart.accountingProject.bean.Fournisseur;
import com.zsmart.accountingProject.bean.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FactureFournisseurDao extends JpaRepository<FactureFournisseur, Long> {


    public FactureFournisseur findByFournisseur(Fournisseur fournisseur);

    public FactureFournisseur findByAdherantIdAndId(Long adhrentId, Long id);

    public int deleteByFournisseur(Fournisseur fournisseur);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);

    public FactureFournisseur findBySocieteAndReference(Societe societe, String ref);

    public List<FactureFournisseur> findByAnneeAndSocieteAndMois(int annee, Societe societe, int mois);

}
