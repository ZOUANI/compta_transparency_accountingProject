package com.zsmart.accountingProject.dao;
import com.zsmart.accountingProject.bean.Client;
import com.zsmart.accountingProject.bean.FactureClient;

import com.zsmart.accountingProject.bean.FactureFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
 public interface FactureClientDao extends JpaRepository<FactureClient,Long> {


	 public FactureClient findByClient(Client client);

	 public int deleteByClient(Client client);

	public FactureClient findByReferenceSocieteAndReference(String refsoc, String ref);

	public List<FactureClient> findByAnneeAndReferenceSocieteAndMois(int annee, String refSoc, int mois);

}
