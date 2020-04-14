
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.service.facade.OperationComptableService;
import com.zsmart.accountingProject.dao.OperationComptableDao;
import com.zsmart.accountingProject.service.util.SearchUtil;
import com.zsmart.accountingProject.bean.OperationComptable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import org.springframework.stereotype.Service;
import java.util.List;
import com.zsmart.accountingProject.bean.Caisse;
import com.zsmart.accountingProject.service.facade.CaisseService;
import com.zsmart.accountingProject.bean.TypeOperationComptable;
import com.zsmart.accountingProject.service.facade.TypeOperationComptableService;
import com.zsmart.accountingProject.bean.CompteBanquaire;
import com.zsmart.accountingProject.service.facade.CompteBanquaireService;
import com.zsmart.accountingProject.bean.CompteComptable;
import com.zsmart.accountingProject.bean.CpcCompteComptable;
import com.zsmart.accountingProject.bean.CpcSousClasse;
import com.zsmart.accountingProject.service.facade.CompteComptableService;
import com.zsmart.accountingProject.bean.OperationComptableGroupe;
import com.zsmart.accountingProject.bean.SousClasseComptable;
import com.zsmart.accountingProject.service.facade.OperationComptableGroupeService;
import com.zsmart.accountingProject.bean.Facture;
import com.zsmart.accountingProject.service.facade.FactureService;

@Service

public class OperationComptableServiceImpl implements OperationComptableService {

	@Autowired

	private OperationComptableDao operationcomptableDao;

	@Autowired

	private EntityManager entityManager;

	@Autowired

	private CaisseService caisseService;

	@Autowired

	private TypeOperationComptableService typeoperationcomptableService;

	@Autowired

	private CompteBanquaireService comptebanquaireService;

	@Autowired

	private CompteComptableService comptecomptableService;

	@Autowired

	private OperationComptableGroupeService operationcomptablegroupeService;

	@Autowired

	private FactureService factureService;

	

	@Override
	public List<Object[]> findGroupeByClasseCompteComptable(Date dateDebut, Date dateFin, String code) {
		return findOperationComptableCpc("compteComptable.sousClasseComptable.classeComptable", dateDebut, dateFin, code);
	}
	
	@Override
	public List<Object[]> findGroupeBySousClasseCompteComptable(Date dateDebut, Date dateFin, String code) {
		return findOperationComptableCpc("compteComptable.sousClasseComptable", dateDebut, dateFin, code);
	}
	
	@Override
	public List<Object[]> findGroupeByCompteComptable(Date dateDebut, Date dateFin, int code) {
		String query = "SELECT o.compteComptable, SUM(o.montant) FROM OperationComptable o WHERE 1=1 AND o.compteComptable.sousClasseComptable.numero LIKE '"+ code + "%'";
		query += SearchUtil.addConstraintMinMaxDate("o", "dateOperationComptable", dateDebut, dateFin);
		query += " GROUP BY o.compteComptable";
		List<Object[]> res = entityManager.createQuery(query).getResultList();
		return res;	
	}


	private List<Object[]> findOperationComptableCpc(String groupeByAttribute,Date dateDebut, Date dateFin, String code) {
		String query = "SELECT o."+groupeByAttribute+", SUM(o.montant) FROM OperationComptable o WHERE 1=1 AND o.compteComptable.code LIKE '"+ code + "%'";
		query += SearchUtil.addConstraintMinMaxDate("o", "dateOperationComptable", dateDebut, dateFin);
		query += " GROUP BY o." +groupeByAttribute;
		List<Object[]> res = entityManager.createQuery(query).getResultList();
		return res;
	}
	
	@Override
	public OperationComptable save(OperationComptable operationcomptable) {

		if (operationcomptable == null) {
			return null;
		} else {
			operationcomptableDao.save(operationcomptable);
			return operationcomptable;
		}
	}

	@Override
	public List<OperationComptable> findAll() {
		return operationcomptableDao.findAll();
	}

	@Override
	public OperationComptable findById(Long id) {
		return operationcomptableDao.getOne(id);
	}

	@Override
	public int delete(OperationComptable operationcomptable) {
		if (operationcomptable == null) {
			return -1;
		} else {
			operationcomptableDao.delete(operationcomptable);
			return 1;
		}
	}

	@Override
	public void deleteById(Long id) {
		operationcomptableDao.deleteById(id);
	}

	public void clone(OperationComptable operationcomptable, OperationComptable operationcomptableClone) {
		if (operationcomptable != null && operationcomptableClone != null) {
			operationcomptableClone.setId(operationcomptable.getId());
			operationcomptableClone.setLibelle(operationcomptable.getLibelle());
			operationcomptableClone.setReferenceSociete(operationcomptable.getReferenceSociete());
			operationcomptableClone.setReferenceFacture(operationcomptable.getReferenceFacture());
			operationcomptableClone.setMontant(operationcomptable.getMontant());
			operationcomptableClone.setDateOperationComptable(operationcomptable.getDateOperationComptable());
			operationcomptableClone.setDateSaisie(operationcomptable.getDateSaisie());
			operationcomptableClone.setCaisse(caisseService.clone(operationcomptable.getCaisse()));
			operationcomptableClone.setTypeOperationComptable(
					typeoperationcomptableService.clone(operationcomptable.getTypeOperationComptable()));
			operationcomptableClone
					.setCompteBanquaire(comptebanquaireService.clone(operationcomptable.getCompteBanquaire()));
			operationcomptableClone
					.setCompteComptable(comptecomptableService.clone(operationcomptable.getCompteComptable()));
			operationcomptableClone.setOperationComptableGroupe(
					operationcomptablegroupeService.clone(operationcomptable.getOperationComptableGroupe()));
			operationcomptableClone.setFacture(factureService.clone(operationcomptable.getFacture()));
		}
	}

	public OperationComptable clone(OperationComptable operationcomptable) {
		if (operationcomptable == null) {
			return null;
		} else {
			OperationComptable operationcomptableClone = new OperationComptable();
			clone(operationcomptable, operationcomptableClone);
			return operationcomptableClone;
		}
	}

	public List<OperationComptable> clone(List<OperationComptable> operationcomptables) {
		if (operationcomptables == null) {
			return null;
		} else {
			List<OperationComptable> operationcomptablesClone = new ArrayList();
			operationcomptables.forEach((operationcomptable) -> {
				operationcomptablesClone.add(clone(operationcomptable));
			});
			return operationcomptablesClone;
		}
	}

	@Override
	public List<OperationComptable> findByCriteria(String libelle, String referenceSociete, String referenceFacture,
			Long idMin, Long idMax, BigDecimal montantMin, BigDecimal montantMax, Date dateOperationComptableMin,
			Date dateOperationComptableMax, Date dateSaisieMin, Date dateSaisieMax,String codeCompteComptable) {
		return entityManager
				.createQuery(constructQuery(libelle, referenceSociete, referenceFacture, idMin, idMax, montantMin,
						montantMax, dateOperationComptableMin, dateOperationComptableMax, dateSaisieMin, dateSaisieMax,codeCompteComptable))
				.getResultList();
	}

	private String constructQuery(String libelle, String referenceSociete, String referenceFacture, Long idMin,
			Long idMax, BigDecimal montantMin, BigDecimal montantMax, Date dateOperationComptableMin,
			Date dateOperationComptableMax, Date dateSaisieMin, Date dateSaisieMax,String codeCompteComptable) {
		String query = "SELECT o FROM OperationComptable o where 1=1 ";
		query += SearchUtil.addConstraint("o", "libelle", "=", libelle);
		query += SearchUtil.addConstraint("o", "compteComptable.code", "LIKE", codeCompteComptable);
		query += SearchUtil.addConstraint("o", "referenceSociete", "=", referenceSociete);
		query += SearchUtil.addConstraint("o", "referenceFacture", "=", referenceFacture);
		query += SearchUtil.addConstraintMinMax("o", "id", idMin, idMax);
		query += SearchUtil.addConstraintMinMax("o", "montant", montantMin, montantMax);
		query += SearchUtil.addConstraintMinMaxDate("o", " dateOperationComptable", dateOperationComptableMin,
				dateOperationComptableMax);
		query += SearchUtil.addConstraintMinMaxDate("o", " dateSaisie", dateSaisieMin, dateSaisieMax);

		return query;
	}

	@Override
	public List<OperationComptable> findOperationComptable(Date dateDebut, Date dateFin, String code) {
		return findByCriteria(null, null, null, null, null, null, null, dateDebut, dateFin, null, null, code);

	}
	
	
	
	 
}

