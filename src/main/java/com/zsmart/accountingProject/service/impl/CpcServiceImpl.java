
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Cpc;
import com.zsmart.accountingProject.bean.CpcCompteComptable;
import com.zsmart.accountingProject.bean.CpcSousClasse;
import com.zsmart.accountingProject.dao.CpcCompteComptableDao;
import com.zsmart.accountingProject.dao.CpcDao;
import com.zsmart.accountingProject.dao.CpcSousClasseDao;
import com.zsmart.accountingProject.service.facade.CpcService;
import com.zsmart.accountingProject.service.facade.CpcSousClasseService;
import com.zsmart.accountingProject.service.facade.OperationComptableService;
import com.zsmart.accountingProject.service.util.ExcelUtil;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service

public class CpcServiceImpl implements CpcService {
	
	
	@Autowired
	CpcCompteComptableDao cpcCompteComptableDao;
	@Autowired

	CpcSousClasseDao cpcSousClasseDao;
	@Autowired

	OperationComptableService operationComptableService;

	@Autowired

	private CpcDao cpcDao;

	@Autowired

	private EntityManager entityManager;

	@Autowired

	private CpcSousClasseService cpcsousclasseService;

	@Override
	public Cpc findCpcClasseComptable(Date dateDebut, Date dateFin, Long socId) {

		List<Object[]> classeProduits = operationComptableService.findGroupeByClasseCompteComptable(dateDebut, dateFin,
				"7", socId);
		List<Object[]> classeCharges = operationComptableService.findGroupeByClasseCompteComptable(dateDebut, dateFin,
				"6", socId);
		Cpc cpc = new Cpc();
		if (ListUtil.isEmpty(classeCharges)) {
			cpc.setTotalProduit(BigDecimal.ZERO);
		} else {
			Object[] chargOperationComptableCharge = classeCharges.get(0);
			cpc.setTotalCharge((BigDecimal) chargOperationComptableCharge[1]);

		}
		if (ListUtil.isEmpty(classeProduits)) {
			cpc.setTotalCharge(BigDecimal.ZERO);
		} else {
			Object[] chargOperationComptableProduit = classeProduits.get(0);
			cpc.setTotalProduit((BigDecimal) chargOperationComptableProduit[1]);
		}
		cpc.setDateDebut(dateDebut);
		cpc.setDateFin(dateFin);
		cpc.setResultat(cpc.getTotalProduit().subtract(cpc.getTotalCharge()));

		cpc.setCpcSousClasses(cpcsousclasseService.findCpcSousClasseComptable(dateDebut, dateFin, socId));
		return cpc;
	}

	@Override
	public Cpc findByDateDebutAndDateFin(Date dateDebut, Date dateFin) {
		try {
			return cpcDao.findCpcByDateDebutAndDateFin(dateDebut, dateFin);
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	@Override
	public void deleteByAdherantIdAndId(Long adherentId, Long id) {

		cpcDao.deleteByAdherantIdAndId(adherentId, id);

	}

	@Override
	public Cpc findCpcByAdherantIdAndId(Long adherentId, Long id) {
		try {
			return cpcDao.findCpcByAdherantIdAndId(adherentId, id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Cpc save(Cpc cpc) {

		if (cpc == null) {
			return null;
		} else {
			cpcDao.save(cpc);
			return cpc;
		}
	}

	@Override
	public Cpc saveWithCpcSousClasses(Cpc cpc) {

		if (cpc == null) {
			return null;
		} else if (findByDateDebutAndDateFin(cpc.getDateDebut(), cpc.getDateFin()) != null) {
			return null;
		} else {
			if (ListUtil.isEmpty(cpc.getCpcSousClasses())) {
				return null;
			} else {
				cpcDao.save(cpc);
				for (CpcSousClasse cpcsousclasse : cpc.getCpcSousClasses()) {
					cpcsousclasse.setCpc(cpc);
					cpcsousclasseService.saveWithCpcCompteComptables(cpcsousclasse);
				}
				return cpc;
			}
		}
	}

	@Override
	public List<Cpc> findAll() {
		return cpcDao.findAll();
	}

	@Override
	public Cpc findById(Long id) {
		return cpcDao.getOne(id);
	}

	@Override
	public int delete(Cpc cpc) {
		if (cpc == null) {
			return -1;
		} else {
			
			cpcDao.delete(cpc);
			return 1;
		}
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
//		
//		deleteCpc("CpcCompteComptable","cpcSousClasse.cpc.id", id);
//		deleteCpc("CpcSousClasse","cpc.id", id);
		cpcCompteComptableDao.deleteByCpcSousClasseCpcId(id);
		cpcSousClasseDao.deleteByCpcId(id);
		cpcDao.deleteById(id);
		
	}
	
//	private int deleteCpc(String table, String column, Long id) {
//		String query = "DELETE FROM "+table+" c  WHERE c."+column+" = "+id;
//				return entityManager.createQuery(query).executeUpdate();
//	} 

	public void clone(Cpc cpc, Cpc cpcClone) {
		if (cpc != null && cpcClone != null) {
			cpcClone.setId(cpc.getId());
			cpcClone.setDateDebut(cpc.getDateDebut());
			cpcClone.setDateFin(cpc.getDateFin());
			cpcClone.setSociete(cpc.getSociete());
			cpcClone.setTotalCharge(cpc.getTotalCharge());
			cpcClone.setTotalProduit(cpc.getTotalProduit());
			cpcClone.setResultat(cpc.getResultat());
			cpcClone.setCpcSousClasses(cpcsousclasseService.clone(cpc.getCpcSousClasses()));
		}
	}

	public Cpc clone(Cpc cpc) {
		if (cpc == null) {
			return null;
		} else {
			Cpc cpcClone = new Cpc();
			clone(cpc, cpcClone);
			return cpcClone;
		}
	}

	@Override
	public Resource getExcel(Cpc cpc) {
		XSSFWorkbook workbook = new XSSFWorkbook();

		CellStyle headerStyle = workbook.createCellStyle();

		XSSFFont font = (workbook).createFont();
		headerStyle.setFillForegroundColor(IndexedColors.BLACK1.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		font.setBold(true);
		headerStyle.setFont(font);
		Map<String, Integer> headerData = new LinkedHashMap<>();

		headerData.put("CPC", 12000);
		headerData.put("TOTAL", 6000);

		XSSFSheet sheet = ExcelUtil.initSheet(workbook, "CPC", headerData, null, headerStyle);


		CellStyle boldStyle = workbook.createCellStyle();
		boldStyle.setWrapText(true);
		XSSFFont ft = (workbook).createFont();
		ft.setBold(true);
		boldStyle.setFont(ft);

		CellStyle normalStyle = workbook.createCellStyle();
		normalStyle.setWrapText(true);

		if (cpc.getCpcSousClasses() != null && !cpc.getCpcSousClasses().isEmpty()) {
			int i = 0;
			for (CpcSousClasse cpcSousClasse : cpc.getCpcSousClasses()) {
				if (i > 0) {
					if (cpc.getCpcSousClasses().get(i - 1).getSousClasseComptable().getNumero().toString().charAt(0) != cpcSousClasse.getSousClasseComptable().getNumero().toString().charAt(0)) {
						List<List<String>> totalCharge = new ArrayList<>();
						totalCharge.add(Arrays.asList("TOTAL CHARGE", cpc.getTotalCharge().toString()));
						ExcelUtil.fillTable(sheet, totalCharge, headerStyle, sheet.getLastRowNum() + 1);
					}
				}
				List<List<String>> csc = new ArrayList<>();
				csc.add(Arrays.asList(cpcSousClasse.getSousClasseComptable().getLibelle(), cpcSousClasse.getMontant().toString()));
				ExcelUtil.fillTable(sheet, csc, boldStyle, sheet.getLastRowNum() + 1);
				for (CpcCompteComptable cpcCompteComptable : cpcSousClasse.getCpcCompteComptables()) {
					List<List<String>> ccc = new ArrayList<>();
					ccc.add(Arrays.asList(cpcCompteComptable.getCompteComptable().getLibelle(), cpcCompteComptable.getMontant().toString()));
					ExcelUtil.fillTable(sheet, ccc, normalStyle, sheet.getLastRowNum() + 1);
				}
				i++;

			}
			List<List<String>> lastTwoRows = new ArrayList<>();
			lastTwoRows.add(Arrays.asList("TOTAL PRODUIT", cpc.getTotalProduit().toString()));
			lastTwoRows.add(Arrays.asList("RESULTAT", cpc.getResultat().toString()));
			ExcelUtil.fillTable(sheet, lastTwoRows, headerStyle, sheet.getLastRowNum() + 1);
		}
		return ExcelUtil.exportBlob(workbook);


	}

	public List<Cpc> clone(List<Cpc> cpcs) {
		if (cpcs == null) {
			return null;
		} else {
			List<Cpc> cpcsClone = new ArrayList();
			cpcs.forEach((cpc) -> {
				cpcsClone.add(clone(cpc));
			});
			return cpcsClone;
		}
	}

	@Override
	public List<Cpc> findByCriteria(Long socId, Long adherentId, Long idMin, Long idMax, Date dateDebutMin,
									Date dateDebutMax, Date dateFinMin, Date dateFinMax, BigDecimal totalChargeMin, BigDecimal totalChargeMax,
									BigDecimal totalProduitMin, BigDecimal totalProduitMax, BigDecimal resultatMin, BigDecimal resultatMax) {
		return entityManager.createQuery(
				constructQuery(socId, adherentId, idMin, idMax, dateDebutMin, dateDebutMax, dateFinMin, dateFinMax,
						totalChargeMin, totalChargeMax, totalProduitMin, totalProduitMax, resultatMin, resultatMax))
				.getResultList();
	}

	private String constructQuery(Long socId, Long adherentId, Long idMin, Long idMax, Date dateDebutMin, Date dateDebutMax,
								  Date dateFinMin, Date dateFinMax, BigDecimal totalChargeMin, BigDecimal totalChargeMax,
								  BigDecimal totalProduitMin, BigDecimal totalProduitMax, BigDecimal resultatMin, BigDecimal resultatMax) {
		String query = "SELECT c FROM Cpc c where 1=1 ";
		query += SearchUtil.addConstraint("c", "societe.id", "=", socId);
		query += SearchUtil.addConstraint("c", "adherant.id", "=", adherentId);
		query += SearchUtil.addConstraintMinMax("c", "id", idMin, idMax);
		query += SearchUtil.addConstraintMinMaxDate("c", " dateDebut", dateDebutMin, dateDebutMax);
		query += SearchUtil.addConstraintMinMaxDate("c", " dateFin", dateFinMin, dateFinMax);
		query += SearchUtil.addConstraintMinMax("c", "totalCharge", totalChargeMin, totalChargeMax);
		query += SearchUtil.addConstraintMinMax("c", "totalProduit", totalProduitMin, totalProduitMax);
		query += SearchUtil.addConstraintMinMax("c", "resultat", resultatMin, resultatMax);

		return query;
	}

}
