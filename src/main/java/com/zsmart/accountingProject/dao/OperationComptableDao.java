package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OperationComptableDao extends JpaRepository<OperationComptable, Long> {


    public OperationComptable findByCaisse(Caisse caisse);

    public OperationComptable findByTypeOperationComptable(TypeOperationComptable typeOperationComptable);

    public OperationComptable findByCompteBanquaire(CompteBanquaire compteBanquaire);

    public OperationComptable findByCompteComptable(CompteComptable compteComptable);

    public OperationComptable findByOperationComptableGroupe(OperationComptableGroupe operationComptableGroupe);

    public OperationComptable findByFacture(Facture facture);

    public List<OperationComptable> findBySociete(Societe societe);

    public int deleteByCaisse(Caisse caisse);

    public int deleteByTypeOperationComptable(TypeOperationComptable typeOperationComptable);

    public int deleteByCompteBanquaire(CompteBanquaire compteBanquaire);

    public int deleteByCompteComptable(CompteComptable compteComptable);

    public int deleteByOperationComptableGroupe(OperationComptableGroupe operationComptableGroupe);

    public int deleteByFacture(Facture facture);

    public OperationComptable findByAdherantIdAndId(Long adherentId, Long id);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);

}
