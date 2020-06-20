package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Cpc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface CpcDao extends JpaRepository<Cpc, Long> {

    public Cpc findCpcByDateDebutAndDateFin(Date dateDebut, Date dateFin);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);

    public Cpc findCpcByAdherantIdAndId(Long adherentId, Long id);

}
