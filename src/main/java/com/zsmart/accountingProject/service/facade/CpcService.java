package com.zsmart.accountingProject.service.facade;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.zsmart.accountingProject.bean.Cpc;

public interface CpcService {

	public Cpc save(Cpc cpc);

	public Cpc saveWithCpcSousClasses(Cpc cpc);

	public List<Cpc> findAll();

	public Cpc findById(Long id);

	public int delete(Cpc cpc);

	public void deleteById(Long id);

	public void clone(Cpc cpc, Cpc cpcClone);

	public Cpc clone(Cpc cpc);

	public List<Cpc> clone(List<Cpc> cpcs);

	public List<Cpc> findByCriteria(String referenceSociete, Long idMin, Long idMax, Date dateDebutMin,
			Date dateDebutMax, Date dateFinMin, Date dateFinMax, BigDecimal totalChargeMin, BigDecimal totalChargeMax,
			BigDecimal totalProduitMin, BigDecimal totalProduitMax, BigDecimal resultatMin, BigDecimal resultatMax);

	public Cpc findCpcClasseComptable(Date dateDebut, Date dateFin);
}
