
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.OperationComptable;
import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.dao.OperationComptableDao;
import com.zsmart.accountingProject.service.facade.*;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
												   Date dateOperationComptableMax, Date dateSaisieMin, Date dateSaisieMax, String codeCompteComptable, String codeTypeOperation) {
		return entityManager
				.createQuery(constructQuery(libelle, raisonSocial, referenceFacture, idMin, idMax, montantMin,
						montantMax, dateOperationComptableMin, dateOperationComptableMax, dateSaisieMin, dateSaisieMax, codeCompteComptable, codeTypeOperation))
				.getResultList();
	}

	private String constructQuery(String libelle, String raisonSocial, String referenceFacture, Long idMin,
								  Long idMax, BigDecimal montantMin, BigDecimal montantMax, Date dateOperationComptableMin,
								  Date dateOperationComptableMax, Date dateSaisieMin, Date dateSaisieMax, String codeCompteComptable, String codeTypeOperation) {
		String query = "SELECT o FROM OperationComptable o where 1=1 ";
		query += SearchUtil.addConstraint("o", "libelle", "LIKE", libelle);
		query += SearchUtil.addConstraint("o", "compteComptable.code", "LIKE", codeCompteComptable);
		query += SearchUtil.addConstraint("o", "societe.raisonSocial", "=", raisonSocial);
		query += SearchUtil.addConstraint("o", "typeOperationComptable.code", "=", codeTypeOperation);
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
		return findByCriteria(null, null, null, null, null, null, null, dateDebut, dateFin, null, null, code, null);

	}

	@Override
	public List<OperationComptable> findOperationComptablee(Date dateDebut, Date dateFin) {
		return findByCriteria(null, null, null, null, null, null, null, dateDebut, dateFin, null, null, null, null);

	}

	@Override
	public Resource generateExcelFile(List<OperationComptable> operationComptables) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Operations Comptable");

		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 8000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 6000);
		sheet.setColumnWidth(5, 6000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 4000);

		Row header = sheet.createRow(0);
		CellStyle headerStyle = workbook.createCellStyle();

		XSSFFont font = (workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		font.setBold(true);
		headerStyle.setFont(font);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("Libelle");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(1);
		headerCell.setCellValue("Societe");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(2);
		headerCell.setCellValue("Groupe Operation Comptable");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(3);
		headerCell.setCellValue("Date Operation");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(4);
		headerCell.setCellValue("Date Saisie");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(5);
		headerCell.setCellValue("Compte Comptable");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(6);
		headerCell.setCellValue("Type Operation");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(7);
		headerCell.setCellValue("Type Paiement");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(8);
		headerCell.setCellValue("Montant");
		headerCell.setCellStyle(headerStyle);


		int rowNum = 1;

		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);

		for (OperationComptable operationComptable : operationComptables) {
			Row row = sheet.createRow(rowNum++);

			Cell cell = row.createCell(0);
			cell.setCellValue(operationComptable.getLibelle());
			cell.setCellStyle(style);

			cell = row.createCell(1);
			cell.setCellValue(operationComptable.getSociete().getRaisonSocial());
			cell.setCellStyle(style);

			cell = row.createCell(2);
			cell.setCellValue(operationComptable.getOperationComptableGroupe().getLibelle());
			cell.setCellStyle(style);

			cell = row.createCell(3);
			cell.setCellValue(DateUtil.formateDate(operationComptable.getDateOperationComptable()));
			cell.setCellStyle(style);

			cell = row.createCell(4);
			cell.setCellValue(DateUtil.formateDate(operationComptable.getDateSaisie()));
			cell.setCellStyle(style);

			String compte = operationComptable.getCompteComptable() == null ? "none" : operationComptable.getCompteComptable().getCode() + " " + operationComptable.getCompteComptable().getLibelle();
			cell = row.createCell(5);
			cell.setCellValue(compte);
			cell.setCellStyle(style);

			cell = row.createCell(6);
			cell.setCellValue(operationComptable.getTypeOperationComptable().getLibelle());
			cell.setCellStyle(style);

			String type = operationComptable.getCaisse() == null ? operationComptable.getCompteBanquaire() == null ? "--" : operationComptable.getCompteBanquaire().getLibelle() : operationComptable.getCaisse().getLibelle();
			cell = row.createCell(7);
			cell.setCellValue(type);
			cell.setCellStyle(style);

			cell = row.createCell(8);
			cell.setCellValue(NumberUtil.toString(operationComptable.getMontant()));
			cell.setCellStyle(style);

		}

		try {
			try {
				FileUtils.cleanDirectory(new File("src\\main\\resources\\excelFiles\\"));
			} catch (IOException e) {
				;
			}

			Long milli = new Date().getTime();
			File currDir = new File(".");
			String path = currDir.getAbsolutePath();
			System.out.println(path);
			String fileLocation = path.substring(0, path.length() - 1) + "src\\main\\resources\\excelFiles\\" + milli + "_temp.xlsx";

			FileOutputStream outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();
			Path path1 = Paths.get(path.substring(0, path.length() - 1) + "src\\main\\resources\\excelFiles\\");
			Path file = path1.resolve(milli + "_temp.xlsx");
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}


}

