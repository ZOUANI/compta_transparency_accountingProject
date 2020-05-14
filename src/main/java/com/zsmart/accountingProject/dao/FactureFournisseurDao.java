package com.zsmart.accountingProject.dao;
import com.zsmart.accountingProject.bean.Fournisseur;
import com.zsmart.accountingProject.bean.FactureFournisseur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
 public interface FactureFournisseurDao extends JpaRepository<FactureFournisseur,Long> {


	 public FactureFournisseur findByFournisseur(Fournisseur fournisseur);

	 public int deleteByFournisseur(Fournisseur fournisseur);

	 public FactureFournisseur findByReferenceSocieteAndReference(String refsoc,String ref);

	 public List<FactureFournisseur> findByAnneeAndReferenceSocieteAndMois(int annee,String refSoc,int mois);

}
