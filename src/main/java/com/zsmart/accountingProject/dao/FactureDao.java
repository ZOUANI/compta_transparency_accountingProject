package com.zsmart.accountingProject.dao;
import com.zsmart.accountingProject.bean.EtatFacture;
import com.zsmart.accountingProject.bean.Facture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
 public interface FactureDao extends JpaRepository<Facture,Long> {

	 public Facture findByReference(String reference);

	 public int deleteByReference(String reference);

	 public Facture findByEtatFacture(EtatFacture etatFacture);

	 public int deleteByEtatFacture(EtatFacture etatFacture);

	 public Facture findByid(Long id);

}
