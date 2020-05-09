
package com.zsmart.accountingProject.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsmart.accountingProject.bean.CompteComptable;
import com.zsmart.accountingProject.bean.CpcCompteComptable;
import com.zsmart.accountingProject.bean.CpcSousClasse;
import com.zsmart.accountingProject.bean.SousClasseComptable;
import com.zsmart.accountingProject.dao.CpcCompteComptableDao;
import com.zsmart.accountingProject.service.facade.CompteComptableService;
import com.zsmart.accountingProject.service.facade.CpcCompteComptableService;
import com.zsmart.accountingProject.service.facade.CpcSousClasseService;
import com.zsmart.accountingProject.service.facade.OperationComptableService;
import com.zsmart.accountingProject.service.util.SearchUtil;

@Service
public class CpcCompteComptableServiceImpl implements CpcCompteComptableService {

	@Autowired

	private CpcCompteComptableDao cpccomptecomptableDao;

	@Autowired

	private EntityManager entityManager;

	@Autowired

	private CompteComptableService comptecomptableService;

	@Autowired

	private CpcSousClasseService cpcsousclasseService;

	@Autowired
	private OperationComptableService operationComptableService;

	@Override
	public List<CpcCompteComptable> findCpcCompteComptable(Date dateDebut, Date dateFin,
			int codeSousClasseComptable) {
		List<Object[]> res = operationComptableService.findGroupeByCompteComptable(dateDebut, dateFin,codeSousClasseComptable );
	
		List<CpcCompteComptable> cpcCompteComptables = new ArrayList<>();
		cpcCompteComptables.addAll(constructCpcCompteComptable(res));
		return cpcCompteComptables;
	}

	@Override
	public List<CpcCompteComptable> find(int numeroSousClasse) {
		return cpccomptecomptableDao.find(numeroSousClasse);
	}

	private List<CpcCompteComptable> constructCpcCompteComptable(List<Object[]> cpcCompteComptableCruds) {
		List<CpcCompteComptable> cpcCompteComptables = new ArrayList<>();
		for (Object[] chargOperationComptable : cpcCompteComptableCruds) {
			CpcCompteComptable cpcCompteComptable = new CpcCompteComptable();
			cpcCompteComptable.setCompteComptable((CompteComptable) chargOperationComptable[0]);
			cpcCompteComptable.setMontant((BigDecimal) chargOperationComptable[1]);
			cpcCompteComptables.add(cpcCompteComptable);
		}

		return cpcCompteComptables;
	}

	@Override
	public CpcCompteComptable save(CpcCompteComptable cpccomptecomptable) {

		if (cpccomptecomptable == null) {
			return null;
		} else {
			cpccomptecomptableDao.save(cpccomptecomptable);
			return cpccomptecomptable;
		}
	}

	@Override
	public List<CpcCompteComptable> findAll() {
		return cpccomptecomptableDao.findAll();
	}

	@Override
	public CpcCompteComptable findById(Long id) {
		return cpccomptecomptableDao.getOne(id);
	}

	@Override
	public int delete(CpcCompteComptable cpccomptecomptable) {
		if (cpccomptecomptable == null) {
			return -1;
		} else {
			cpccomptecomptableDao.delete(cpccomptecomptable);
			return 1;
		}
	}

	@Override
	public void deleteById(Long id) {
		cpccomptecomptableDao.deleteById(id);
	}

	public void clone(CpcCompteComptable cpccomptecomptable, CpcCompteComptable cpccomptecomptableClone) {
		if (cpccomptecomptable != null && cpccomptecomptableClone != null) {
			cpccomptecomptableClone.setId(cpccomptecomptable.getId());
			cpccomptecomptableClone.setMontant(cpccomptecomptable.getMontant());
			cpccomptecomptableClone
					.setCompteComptable(comptecomptableService.clone(cpccomptecomptable.getCompteComptable()));
			cpccomptecomptableClone.setCpcSousClasse(cpcsousclasseService.clone(cpccomptecomptable.getCpcSousClasse()));
		}
	}

	public CpcCompteComptable clone(CpcCompteComptable cpccomptecomptable) {
		if (cpccomptecomptable == null) {
			return null;
		} else {
			CpcCompteComptable cpccomptecomptableClone = new CpcCompteComptable();
			clone(cpccomptecomptable, cpccomptecomptableClone);
			return cpccomptecomptableClone;
		}
	}

	public List<CpcCompteComptable> clone(List<CpcCompteComptable> cpccomptecomptables) {
		if (cpccomptecomptables == null) {
			return null;
		} else {
			List<CpcCompteComptable> cpccomptecomptablesClone = new ArrayList();
			cpccomptecomptables.forEach((cpccomptecomptable) -> {
				cpccomptecomptablesClone.add(clone(cpccomptecomptable));
			});
			return cpccomptecomptablesClone;
		}
	}

	@Override
	public List<CpcCompteComptable> findByCriteria(Long idMin, Long idMax, BigDecimal montantMin,
			BigDecimal montantMax) {
		return entityManager.createQuery(constructQuery(idMin, idMax, montantMin, montantMax)).getResultList();
	}

	private String constructQuery(Long idMin, Long idMax, BigDecimal montantMin, BigDecimal montantMax) {
		String query = "SELECT c FROM CpcCompteComptable c where 1=1 ";
		query += SearchUtil.addConstraintMinMax("c", "id", idMin, idMax);
		query += SearchUtil.addConstraintMinMax("c", "montant", montantMin, montantMax);

		return query;
	}

}
