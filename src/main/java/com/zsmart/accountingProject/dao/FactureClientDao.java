package com.zsmart.accountingProject.dao;
import com.zsmart.accountingProject.bean.Client;
import com.zsmart.accountingProject.bean.FactureClient;

import com.zsmart.accountingProject.bean.FactureFournisseur;
import com.zsmart.accountingProject.bean.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
 public interface FactureClientDao extends JpaRepository<FactureClient,Long> {


	 public FactureClient findByClient(Client client);

	 public int deleteByClient(Client client);

	public FactureClient findBySocieteAndReference(Societe societe,String ref);

	public List<FactureClient> findByAnneeAndSocieteAndMois(int annee, Societe societe, int mois);

}
