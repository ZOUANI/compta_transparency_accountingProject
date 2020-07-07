package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Client;
import com.zsmart.accountingProject.bean.FactureClient;
import com.zsmart.accountingProject.bean.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FactureClientDao extends JpaRepository<FactureClient, Long> {


    public FactureClient findByClient(Client client);

    public FactureClient findByAdherantIdAndId(Long adhrentId, Long id);

    public FactureClient findByAdherantIdAndIdAndSocieteId(Long adhrentId, Long id, Long socId);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);

    public int deleteByClient(Client client);

    public FactureClient findBySocieteAndReference(Societe societe, String ref);

    public List<FactureClient> findByAnneeAndSocieteIdAndAdherantIdAndMois(int annee, Long socId, Long adhrentId, int mois);

}
