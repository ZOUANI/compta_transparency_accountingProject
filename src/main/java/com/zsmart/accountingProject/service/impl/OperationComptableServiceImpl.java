
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.OperationComptable;
import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.dao.OperationComptableDao;
import com.zsmart.accountingProject.service.facade.*;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.ExcelUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service

public class OperationComptableServiceImpl implements OperationComptableService {

	private static final String FILE_NAME = "";
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
	@Autowired

	private SocieteService societeService;


	@Override
	public List<Object[]> findGroupeByClasseCompteComptable(Date dateDebut, Date dateFin, String code, Long socId) {
		return findOperationComptableCpc("compteComptable.sousClasseComptable.classeComptable", dateDebut, dateFin, code, socId);
	}

	@Override
	public List<Object[]> findGroupeBySousClasseCompteComptable(Date dateDebut, Date dateFin, String code, Long socId) {
		return findOperationComptableCpc("compteComptable.sousClasseComptable", dateDebut, dateFin, code, socId);
	}

	@Override
	public List<Object[]> findGroupeByCompteComptable(Date dateDebut, Date dateFin, int code, Long socId) {
		String query = "SELECT o.compteComptable, SUM(o.montant) FROM OperationComptable o WHERE 1=1 AND o.compteComptable.sousClasseComptable.numero LIKE '" + code + "%'";
		query += SearchUtil.addConstraintMinMaxDate("o", "dateOperationComptable", dateDebut, dateFin);
		query += SearchUtil.addConstraint("o", "societe.id", "=", socId);
		query += " GROUP BY o.compteComptable";
		List<Object[]> res = entityManager.createQuery(query).getResultList();
		return res;
	}


	private List<Object[]> findOperationComptableCpc(String groupeByAttribute, Date dateDebut, Date dateFin, String code, Long socId) {
		String query = "SELECT o." + groupeByAttribute + ", SUM(o.montant) FROM OperationComptable o WHERE 1=1 AND o.compteComptable.code LIKE '" + code + "%'";
		query += SearchUtil.addConstraintMinMaxDate("o", "dateOperationComptable", dateDebut, dateFin);
		query += SearchUtil.addConstraint("o", "societe.id", "=", socId);
		query += " GROUP BY o." + groupeByAttribute;
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
	public List<OperationComptable> findBySocId(Long id) {
		Societe societe=societeService.findById(id);
		return operationcomptableDao.findBySociete(societe);
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

    @Override
    public OperationComptable findByAdherantIdAndId(Long adherentId, Long id) {
        try {
            return operationcomptableDao.findByAdherantIdAndId(adherentId, id);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteByAdherantIdAndId(Long adherentId, Long id) {
        operationcomptableDao.deleteByAdherantIdAndId(adherentId, id);
    }

    public void clone(OperationComptable operationcomptable, OperationComptable operationcomptableClone) {
        if (operationcomptable != null && operationcomptableClone != null) {
            operationcomptableClone.setId(operationcomptable.getId());
            operationcomptableClone.setLibelle(operationcomptable.getLibelle());
            operationcomptableClone.setSociete(operationcomptable.getSociete());
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
    public List<OperationComptable> findByCriteria(String libelle, String raisonSocial, String referenceFacture,
                                                   Long idMin, Long idMax, BigDecimal montantMin, BigDecimal montantMax, Date dateOperationComptableMin,
                                                   Date dateOperationComptableMax, Date dateSaisieMin, Date dateSaisieMax, String codeCompteComptable, String codeTypeOperation, Long adherentId, String opGroupeLibelle) {
        return entityManager
                .createQuery(constructQuery(libelle, raisonSocial, referenceFacture, idMin, idMax, montantMin,
                        montantMax, dateOperationComptableMin, dateOperationComptableMax, dateSaisieMin, dateSaisieMax, codeCompteComptable, codeTypeOperation, adherentId, opGroupeLibelle))
                .getResultList();
    }

    private String constructQuery(String libelle, String raisonSocial, String referenceFacture, Long idMin,
                                  Long idMax, BigDecimal montantMin, BigDecimal montantMax, Date dateOperationComptableMin,
                                  Date dateOperationComptableMax, Date dateSaisieMin, Date dateSaisieMax, String codeCompteComptable, String codeTypeOperation, Long adherentId, String opGroupeLibelle) {
        String query = "SELECT o FROM OperationComptable o where 1=1 ";
        query += SearchUtil.addConstraint("o", "libelle", "LIKE", libelle);
        query += SearchUtil.addConstraint("o", "compteComptable.code", "LIKE", codeCompteComptable);
        query += SearchUtil.addConstraint("o", "operationComptableGroupe.libelle", "LIKE", opGroupeLibelle);
        query += SearchUtil.addConstraint("o", "societe.raisonSocial", "=", raisonSocial);
        query += SearchUtil.addConstraint("o", "typeOperationComptable.code", "=", codeTypeOperation);
        query += SearchUtil.addConstraint("o", "referenceFacture", "=", referenceFacture);
        query += SearchUtil.addConstraint("o", "adherant.id", "=", adherentId);
        query += SearchUtil.addConstraintMinMax("o", "id", idMin, idMax);
        query += SearchUtil.addConstraintMinMax("o", "montant", montantMin, montantMax);
        query += SearchUtil.addConstraintMinMaxDate("o", " dateOperationComptable", dateOperationComptableMin,
                dateOperationComptableMax);
        query += SearchUtil.addConstraintMinMaxDate("o", " dateSaisie", dateSaisieMin, dateSaisieMax);

        return query;
    }

    @Override
    public List<OperationComptable> findOperationComptable(Date dateDebut, Date dateFin, String code) {
        return findByCriteria(null, null, null, null, null, null, null, dateDebut, dateFin, null, null, code, null, null, null);

    }

	@Override
	public List<OperationComptable> findOperationComptablee(Date dateDebut, Date dateFin) {
        return findByCriteria(null, null, null, null, null, null, null, dateDebut, dateFin, null, null, null, null, null, null);

    }

	@Override
	public Resource generateExcelFile(List<OperationComptable> operationComptables) {
        XSSFWorkbook workbook = new XSSFWorkbook();


        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont font = (workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        headerStyle.setFont(font);

        List<String> headerData = Arrays.asList("Libelle", "Societe", "Groupe Operation Comptable", "Date Operation", "Date Saisie", "Compte Comptable", "Type Operation", "Type Paiement", "Montant");

        XSSFSheet sheet = ExcelUtil.initSheet(workbook, "Operations Comptable", null, headerData, headerStyle);

        List<List<String>> cellData = new ArrayList<>();

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        for (OperationComptable operationComptable : operationComptables) {

            String compte = operationComptable.getCompteComptable() == null ? "none" : operationComptable.getCompteComptable().getCode() + " " + operationComptable.getCompteComptable().getLibelle();
            String type = operationComptable.getCaisse() == null ? operationComptable.getCompteBanquaire() == null ? "--" : operationComptable.getCompteBanquaire().getLibelle() : operationComptable.getCaisse().getLibelle();
            cellData.add(Arrays.asList(operationComptable.getLibelle(),
                    operationComptable.getSociete().getRaisonSocial(),
                    operationComptable.getOperationComptableGroupe().getLibelle(),
                    DateUtil.formateDate(operationComptable.getDateOperationComptable()),
                    DateUtil.formateDate(operationComptable.getDateSaisie()),
                    compte,
                    operationComptable.getTypeOperationComptable().getLibelle(),
                    type,
                    NumberUtil.toString(operationComptable.getMontant())));

        }
        ExcelUtil.fillTable(sheet, cellData, style, 1);

        return ExcelUtil.exportBlob(workbook);

    }


}

