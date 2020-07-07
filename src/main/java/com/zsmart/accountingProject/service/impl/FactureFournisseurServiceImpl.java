
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.*;
import com.zsmart.accountingProject.dao.FactureFournisseurDao;
import com.zsmart.accountingProject.service.facade.*;
import com.zsmart.accountingProject.service.util.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service

public class FactureFournisseurServiceImpl implements FactureFournisseurService {


    @Autowired

    private FactureFournisseurDao facturefournisseurDao;

    @Autowired

    private EntityManager entityManager;

    @Autowired

    private EtatFactureService etatFactureService;

    @Autowired

    private FournisseurService fournisseurService;
    @Autowired

    private SpringTemplateEngine templateEngine;

    @Autowired

    private OperationComptableService operationcomptableService;
    @Autowired

    private TypeOperationComptableService typeOperationComptableService;

    @Autowired
    private SocieteService societeService;
    private final Path root = Paths.get("src\\main\\resources\\uploads");
    @Autowired
    private OperationComptableGroupeService operationComptableGroupeService;
    @Autowired
    private FactureItemService factureItemService;

    @Override
    public FactureFournisseur save(FactureFournisseur facturefournisseur) {

        if (facturefournisseur == null) {
            return null;
        } else {
            facturefournisseurDao.save(facturefournisseur);
            return facturefournisseur;
        }
    }

    @Override
    public FactureFournisseur saveWithOperations(FactureFournisseur facturefournisseur) {
        if (facturefournisseur == null) {
            return null;
        } else {
            if (facturefournisseur.getOperationComptable()==null || facturefournisseur.getOperationComptable().isEmpty()) {
                return null;
            } else {
                facturefournisseurDao.save(facturefournisseur);
                for (OperationComptable operationcomptable : facturefournisseur.getOperationComptable()) {
                    operationcomptable.setFacture(facturefournisseur);
                    operationcomptableService.save(operationcomptable);
                }
                return facturefournisseur;
            }
        }
    }

    @Override
    public FactureFournisseur saveWithOperationsAndFactureItems(FactureFournisseur facturefournisseur) {
        if (facturefournisseur==null){
            return null;
        }else {
            if (facturefournisseur.getFactureItems() == null
                    || facturefournisseur.getFactureItems().isEmpty()) {
                return null;
            } else {
                OperationComptableGroupe groupe = null;
                if (facturefournisseur.getId() == null) {
                    groupe = new OperationComptableGroupe();
                    groupe.setLibelle("GroupFacture" + facturefournisseur.getReference() + facturefournisseur.getSociete().getRaisonSocial());
                    groupe.setCode("CodeGroup" + facturefournisseur.getReference() + facturefournisseur.getSociete().getRaisonSocial());
                    groupe.setAdherant(facturefournisseur.getAdherant());
                    groupe.setDateSaisie(facturefournisseur.getDateSaisie());
                    operationComptableGroupeService.save(groupe);

                } else {
                    groupe = facturefournisseur.getOperationComptable().get(0).getOperationComptableGroupe();
                }
                BigDecimal totalpaye = facturefournisseur.getTotalPayerHt() == null ? BigDecimal.ZERO : facturefournisseur.getTotalPayerHt();
                facturefournisseur.setTotalPayerHt(totalpaye);
                BigDecimal totalrestant = facturefournisseur.getTotalRestantHt() == null ? facturefournisseur.getTotalHt() : facturefournisseur.getTotalRestantHt();
                facturefournisseur.setTotalRestantHt(totalrestant);
                facturefournisseur.setEtatFacture(etatFactureService.findEtatFactureByLibelleLike("draft"));
                facturefournisseurDao.save(facturefournisseur);
                for (OperationComptable operationcomptable : generateOperationsForFacture(facturefournisseur)) {
                    if (groupe != null) {
                        operationcomptable.setOperationComptableGroupe(groupe);
                    }
                    operationcomptable.setFacture(facturefournisseur);
                    operationcomptable.setAdherant(facturefournisseur.getAdherant());
                    operationcomptableService.save(operationcomptable);
                }
                for (FactureItem factureItem : facturefournisseur.getFactureItems()) {
                    factureItem.setFacture(facturefournisseur);
                    factureItemService.save(factureItem);
                }
                return facturefournisseur;
            }
        }
    }

    @Override
    public List<OperationComptable> generateOperationsForFacture(FactureFournisseur factureFournisseur) {
        if (factureFournisseur == null) {
            return null;
        } else {
            if (factureFournisseur.getOperationComptable() == null || factureFournisseur.getOperationComptable().isEmpty()) {
                List<OperationComptable> operationComptables = new ArrayList<>();
                OperationComptable achat = new OperationComptable();
                achat.setLibelle("Achat");
                achat.setSociete(factureFournisseur.getSociete());
                achat.setReferenceFacture(factureFournisseur.getReference());
                achat.setDateOperationComptable(factureFournisseur.getDateFacture());
                achat.setDateSaisie(new Date());
                achat.setMontant(factureFournisseur.getTotalHt());
                achat.setTypeOperationComptable(typeOperationComptableService.findById((long) 1));
                operationComptables.add(achat);
                OperationComptable tva = new OperationComptable();
                tva.setLibelle("TVA");
                tva.setSociete(factureFournisseur.getSociete());
                tva.setReferenceFacture(factureFournisseur.getReference());
                tva.setDateOperationComptable(factureFournisseur.getDateFacture());
                tva.setDateSaisie(new Date());
                tva.setMontant(factureFournisseur.getTva());
                tva.setTypeOperationComptable(typeOperationComptableService.findById((long) 1));
                operationComptables.add(tva);


                return operationComptables;

            } else {
                for (int i = 0; i < factureFournisseur.getOperationComptable().size(); i++) {
                    if (i == 0) {
                        factureFournisseur.getOperationComptable().get(i).setReferenceFacture(factureFournisseur.getReference());
                        factureFournisseur.getOperationComptable().get(i).setDateOperationComptable(factureFournisseur.getDateFacture());
                        factureFournisseur.getOperationComptable().get(i).setMontant(factureFournisseur.getTotalHt());
                        factureFournisseur.getOperationComptable().get(i).setSociete(factureFournisseur.getSociete());
                    } else if (i == 1) {
                        factureFournisseur.getOperationComptable().get(i).setReferenceFacture(factureFournisseur.getReference());
                        factureFournisseur.getOperationComptable().get(i).setDateOperationComptable(factureFournisseur.getDateFacture());
                        factureFournisseur.getOperationComptable().get(i).setMontant(factureFournisseur.getTva());
                        factureFournisseur.getOperationComptable().get(i).setSociete(factureFournisseur.getSociete());
                    }
                }
                return factureFournisseur.getOperationComptable();
            }
        }
    }

    @Override
    public Resource exportExcelFile(List<FactureFournisseur> factureFournisseurs) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        CellStyle headerStyle = workbook.createCellStyle();

        XSSFFont font = (workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        headerStyle.setFont(font);


        List<String> headerData = Arrays.asList("Reference", "Type Facture", "Trimestre", "Date Facture", "Date Saisie", "Total Ht", "Total TTC", "TVA", "Total Paye HT", "Total Restant HT", "Etat Facture", "Fournisseur");
        XSSFSheet sheet = ExcelUtil.initSheet(workbook, "Factures", null, headerData, headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        List<List<String>> cellData = new ArrayList<>();

        for (FactureFournisseur facture : factureFournisseurs) {
            String etat = facture.getEtatFacture() == null ? "--" : facture.getEtatFacture().getLibelle();
            cellData.add(Arrays.asList(facture.getReference(),
                    facture.getTypeFacture(),
                    NumberUtil.toString(facture.getTrimester()),
                    DateUtil.formateDate(facture.getDateFacture()),
                    DateUtil.formateDate(facture.getDateSaisie()),
                    NumberUtil.toString(facture.getTotalHt()),
                    NumberUtil.toString(facture.getTotalTtc()),
                    NumberUtil.toString(facture.getTva()),
                    NumberUtil.toString(facture.getTotalPayerHt()),
                    NumberUtil.toString(facture.getTotalRestantHt()),
                    etat,
                    facture.getFournisseur().getLibelle()));

        }
        ExcelUtil.fillTable(sheet, cellData, style, 1);

        return ExcelUtil.exportBlob(workbook);
    }

    @Override
    public FactureFournisseur findByAdherantIdAndId(Long adhrentId, Long id) {
        return facturefournisseurDao.findByAdherantIdAndId(adhrentId, id);
    }

    @Override
    public FactureFournisseur findByAdherantIdAndIdAndSocieteId(Long adhrentId, Long id, Long socId) {
        return facturefournisseurDao.findByAdherantIdAndIdAndSocieteId(adhrentId, id, socId);
    }

    @Override
    public BigDecimal getTotalExpense(Long adherentId, Long socId, Date dateDebut, Date dateFin) {
        FactureFournisseur facture = new FactureFournisseur();
        Societe societe = societeService.findById(socId);
        facture.setAdherant(new Adherant());
        facture.getAdherant().setId(adherentId);
        facture.setSociete(societe);

        List<FactureFournisseur> factureFournisseurs = findByCriteria(facture, dateDebut, dateFin, null, null);

        BigDecimal total = BigDecimal.ZERO;

        for (FactureFournisseur factureFournisseur : factureFournisseurs
        ) {
            total = total.add(factureFournisseur.getTotalPayerHt());
        }
        return total;
    }

    @Override
    public void deleteFacWithAll(Long adherentId, Long facId) throws IOException {
        FactureFournisseur factureFournisseur = facturefournisseurDao.findByAdherantIdAndId(adherentId, facId);
        if (factureFournisseur != null) {
            Files.delete(this.root.resolve(factureFournisseur.getScanPath()));

            if (factureFournisseur.getOperationComptable() != null
                    && !factureFournisseur.getOperationComptable().isEmpty()) {


                operationComptableGroupeService.delete(factureFournisseur.getOperationComptable().get(0).getOperationComptableGroupe());
                for (OperationComptable op : factureFournisseur.getOperationComptable()
                ) {
                    operationcomptableService.delete(op);
                }
            }
            if (factureFournisseur.getFactureItems() != null
                    && !factureFournisseur.getFactureItems().isEmpty()) {

                for (FactureItem item : factureFournisseur.getFactureItems()
                ) {
                    factureItemService.delete(item);
                }
            }
            facturefournisseurDao.delete(factureFournisseur);
        }
    }

    @Override
    public Resource toPdf(FactureFournisseur factureFournisseur) throws IOException {
        FactureFournisseur facture = findById(factureFournisseur.getId());
        return PdfUtil.htmlToPdf(facture, templateEngine, "factureFournisseur");
    }

    @Override
    public void uploadScan(MultipartFile file, FactureFournisseur factureFournisseur) {
        if (factureFournisseur != null) {
            try {
                if (factureFournisseur.getId() != null) {
                    FactureFournisseur factureFournisseur1 = findById(factureFournisseur.getId());
                    Files.delete(this.root.resolve(factureFournisseur1.getScanPath()));
                }

                Files.copy(file.getInputStream(), this.root.resolve(factureFournisseur.getSociete().getRaisonSocial() + "Fournisseur" + factureFournisseur.getReference()));
                factureFournisseur.setScanPath(factureFournisseur.getSociete().getRaisonSocial() + "Fournisseur" + factureFournisseur.getReference());

            } catch (Exception e) {
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        }
    }

    @Override
    public Resource getScan(Long id) {
        FactureFournisseur factureFournisseur=findById(id);
        try {
                Path file = root.resolve(factureFournisseur.getScanPath());
                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) {
                    return resource;
                } else {
                    throw new RuntimeException("Could not read the file!");
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }

    }

    @Override
    public List<FactureFournisseur> findAll() {
        return facturefournisseurDao.findAll();
    }

    @Override
    public FactureFournisseur findById(Long id) {

        return facturefournisseurDao.getOne(id);
    }

    @Override
    public FactureFournisseur findBySocieteIdAndReference(Long id, String ref) {
        Societe societe=societeService.findById(id);
        return facturefournisseurDao.findBySocieteAndReference(societe, ref);
    }

    @Override
    public List<BigDecimal> calculateChargeParAnneeEtSocieteId(int annee, Long id) {
        List<BigDecimal> data=new ArrayList<>();
        Societe societe=societeService.findById(id);
        for (int i = 1; i <13 ; i++) {
            List<FactureFournisseur> factureFournisseurs=facturefournisseurDao.findByAnneeAndSocieteAndMois(annee,societe,i);
            BigDecimal total=BigDecimal.ZERO;
            for (FactureFournisseur f:factureFournisseurs
                 ) {
                total=total.add(f.getTotalTtc());
            }
            data.add(total);
        }
        return data;

    }

    @Override
    public int delete(FactureFournisseur facturefournisseur) {
        if (facturefournisseur == null) {
            return -1;
        } else {
            facturefournisseurDao.delete(facturefournisseur);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        facturefournisseurDao.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByAdherentIdAndId(Long adherentId, Long id) {
        facturefournisseurDao.deleteByAdherantIdAndId(adherentId, id);
    }

    @Override
    public int setEtatFacture(Long etatId, Long adhrentId, Long id) {
        FactureFournisseur factureFournisseur = findByAdherantIdAndId(adhrentId, id);
        EtatFacture etatFacture = etatFactureService.findById(etatId);
        if (factureFournisseur == null || etatFacture == null) {
            return -1;
        } else {
            factureFournisseur.setEtatFacture(etatFacture);
            facturefournisseurDao.save(factureFournisseur);
            return 0;
        }
    }

    public void clone(FactureFournisseur facturefournisseur, FactureFournisseur facturefournisseurClone) {
        if (facturefournisseur != null && facturefournisseurClone != null) {
            facturefournisseurClone.setId(facturefournisseur.getId());
            facturefournisseurClone.setFournisseur(fournisseurService.clone(facturefournisseur.getFournisseur()));
        }
    }

    public FactureFournisseur clone(FactureFournisseur facturefournisseur) {
        if (facturefournisseur == null) {
            return null;
        } else {
            FactureFournisseur facturefournisseurClone = new FactureFournisseur();
            clone(facturefournisseur, facturefournisseurClone);
            return facturefournisseurClone;
        }
    }

    public List<FactureFournisseur> clone(List<FactureFournisseur> facturefournisseurs) {
        if (facturefournisseurs == null) {
            return null;
        } else {
            List<FactureFournisseur> facturefournisseursClone = new ArrayList();
            facturefournisseurs.forEach((facturefournisseur) -> {
                facturefournisseursClone.add(clone(facturefournisseur));
            });
            return facturefournisseursClone;
        }
    }

    @Override
    public List<FactureFournisseur> findByCriteria(FactureFournisseur facture, Date dateFactureMin, Date dateFactureMax, Date dateSaisieMin, Date dateSaisieMax) {
        return entityManager.createQuery(constructQuery(facture, dateFactureMin, dateFactureMax, dateSaisieMin, dateSaisieMax)).getResultList();
    }


    private String constructQuery(FactureFournisseur facture, Date dateFactureMin, Date dateFactureMax, Date dateSaisieMin, Date dateSaisieMax) {
        String query = "SELECT f FROM FactureFournisseur f where 1=1";
        if (facture.getSociete() != null) {
            query += SearchUtil.addConstraint("f", "societe.raisonSocial", "=", facture.getSociete().getRaisonSocial());
            query += SearchUtil.addConstraint("f", "societe.identifiantFiscal", "=", facture.getSociete().getIdentifiantFiscal());
            query += SearchUtil.addConstraint("f", "societe.ice", "=", facture.getSociete().getIce());
        }
        if (facture.getEtatFacture() != null) {
            query += SearchUtil.addConstraint("f", "etatFacture.id", "=", facture.getEtatFacture().getId());
        }
        if (facture.getFournisseur() != null) {
            query += SearchUtil.addConstraint("f", "fournisseur.id", "=", facture.getFournisseur().getId());

        }
        query += SearchUtil.addConstraint("f", "reference", "LIKE", facture.getReference());
        query += SearchUtil.addConstraint("f", "adherant.id", "=", facture.getAdherant().getId());
        query += SearchUtil.addConstraint("f", "typeFacture", "LIKE", facture.getTypeFacture());
        query += SearchUtil.addConstraint("f", "annee", "=", facture.getAnnee());
        query += SearchUtil.addConstraint("f", "trimester", "=", facture.getTrimester());
        query += SearchUtil.addConstraintMinMaxDate("f", " dateFacture", dateFactureMin,
                dateFactureMax);
        query += SearchUtil.addConstraintMinMaxDate("f", " dateSaisie", dateSaisieMin, dateSaisieMax);


        return query;
    }
}
