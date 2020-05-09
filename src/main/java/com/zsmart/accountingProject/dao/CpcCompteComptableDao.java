package com.zsmart.accountingProject.dao;
import com.zsmart.accountingProject.bean.CompteComptable;
import com.zsmart.accountingProject.bean.CpcSousClasse;
import com.zsmart.accountingProject.bean.CpcCompteComptable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
 public interface CpcCompteComptableDao extends JpaRepository<CpcCompteComptable,Long> {


	 public CpcCompteComptable findByCompteComptable(CompteComptable compteComptable);
	 public CpcCompteComptable findByCpcSousClasse(CpcSousClasse cpcSousClasse);

	 public int deleteByCompteComptable(CompteComptable compteComptable);
	 public int deleteByCpcSousClasse(CpcSousClasse cpcSousClasse);
	 public int deleteByCpcSousClasseCpcId (Long id);
	 @Query("SELECT NEW com.zsmart.accountingProject.bean.CpcCompteComptable(o.compteComptable,SUM(o.montant)) FROM OperationComptable o" +
			 " WHERE o.compteComptable.code LIKE :numeroClasse% GROUP BY o.compteComptable")
	 public List<CpcCompteComptable> find(@Param("numeroClasse") int numeroClasse);

}
