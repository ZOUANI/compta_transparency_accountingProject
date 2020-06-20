
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.*;
import com.zsmart.accountingProject.dao.FactureClientDao;
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
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

public class FactureClientServiceImpl implements FactureClientService {


    @Autowired

    private FactureClientDao factureclientDao;

    @Autowired

    private EntityManager entityManager;
    private final Path root = Paths.get("src\\main\\resources\\uploads");
    @Autowired

    private EtatFactureService etatFactureService;
    @Autowired

    private ClientService clientService;
    @Autowired

    private OperationComptableService operationcomptableService;

    @Autowired
    private SocieteService societeService;
    @Autowired
    private FactureItemService factureItemService;
    @Autowired

    private TypeOperationComptableService typeOperationComptableService;
    @Autowired
    private OperationComptableGroupeService operationComptableGroupeService;


    @Override
    public FactureClient save(FactureClient factureclient) {

        if (factureclient == null) {
            return null;
        } else {
            factureclientDao.save(factureclient);
            return factureclient;
        }
    }

    @Override
    public FactureClient saveWithOperations(FactureClient factureclient) {
        if (factureclient == null) {
            return null;
        } else {
            if (factureclient.getOperationComptable()==null || factureclient.getOperationComptable().isEmpty()) {
                return null;
            } else {
                factureclientDao.save(factureclient);
                for (OperationComptable operationcomptable : factureclient.getOperationComptable()) {
                    operationcomptable.setFacture(factureclient);
                    operationcomptableService.save(operationcomptable);
                }
                return factureclient;
            }
        }
    }
    @Override
    public FactureClient saveWithOperationsAndFactureItems(FactureClient factureClient) {
        if (factureClient==null){
            return null;
        }else {
            if (factureClient.getFactureItems() == null
                    || factureClient.getFactureItems().isEmpty()) {
                return null;
            } else {
                OperationComptableGroupe groupe;
                if (factureClient.getId() == null) {
                    groupe = new OperationComptableGroupe();
                    groupe.setLibelle("GroupFacture" + factureClient.getReference() + factureClient.getSociete().getRaisonSocial());
                    groupe.setCode("CodeGroup" + factureClient.getReference() + factureClient.getSociete().getRaisonSocial());
                    groupe.setAdherant(factureClient.getAdherant());
                    operationComptableGroupeService.save(groupe);

                } else {
                    groupe = factureClient.getOperationComptable().get(0).getOperationComptableGroupe();

                }
                BigDecimal totalpaye = factureClient.getTotalPayerHt() == null ? BigDecimal.ZERO : factureClient.getTotalPayerHt();
                factureClient.setTotalPayerHt(totalpaye);
                BigDecimal totalrestant = factureClient.getTotalRestantHt() == null ? factureClient.getTotalHt() : factureClient.getTotalRestantHt();
                factureClient.setTotalRestantHt(totalrestant);
                factureClient.setEtatFacture(etatFactureService.findEtatFactureByLibelleLike("draft"));
                factureclientDao.save(factureClient);

                for (OperationComptable operationcomptable : generateOperationsForFacture(factureClient)) {
                    if (groupe != null) {
                        operationcomptable.setOperationComptableGroupe(groupe);
                    }
                    operationcomptable.setFacture(factureClient);
                    operationcomptable.setAdherant(factureClient.getAdherant());
                    operationcomptableService.save(operationcomptable);
                }
                for (FactureItem factureItem : factureClient.getFactureItems()) {
                    factureItem.setFacture(factureClient);
                    factureItemService.save(factureItem);
                }
                return factureClient;
            }
        }
    }

    @Override
    public List<OperationComptable> generateOperationsForFacture(FactureClient factureClient) {
        if (factureClient == null) {
            return null;
        } else {
            if (factureClient.getOperationComptable() == null || factureClient.getOperationComptable().isEmpty()) {
                List<OperationComptable> operationComptables = new ArrayList<>();
                OperationComptable vente = new OperationComptable();
                vente.setLibelle("vente");
                vente.setSociete(factureClient.getSociete());
                vente.setReferenceFacture(factureClient.getReference());
                vente.setDateOperationComptable(factureClient.getDateFacture());
                vente.setDateSaisie(new Date());
                vente.setMontant(factureClient.getTotalHt());
                vente.setTypeOperationComptable(typeOperationComptableService.findById((long) 1));
                operationComptables.add(vente);
                OperationComptable tva = new OperationComptable();
                tva.setLibelle("TVA");
                tva.setSociete(factureClient.getSociete());
                tva.setReferenceFacture(factureClient.getReference());
                tva.setDateOperationComptable(factureClient.getDateFacture());
                tva.setDateSaisie(new Date());
                tva.setMontant(factureClient.getTva());
                tva.setTypeOperationComptable(typeOperationComptableService.findById((long) 1));
                operationComptables.add(tva);


                return operationComptables;

            } else {
                for (int i = 0; i < factureClient.getOperationComptable().size(); i++) {
                    if (i == 0) {
                        factureClient.getOperationComptable().get(i).setReferenceFacture(factureClient.getReference());
                        factureClient.getOperationComptable().get(i).setDateOperationComptable(factureClient.getDateFacture());
                        factureClient.getOperationComptable().get(i).setMontant(factureClient.getTotalHt());
                        factureClient.getOperationComptable().get(i).setSociete(factureClient.getSociete());
                    } else if (i == 1) {
                        factureClient.getOperationComptable().get(i).setReferenceFacture(factureClient.getReference());
                        factureClient.getOperationComptable().get(i).setDateOperationComptable(factureClient.getDateFacture());
                        factureClient.getOperationComptable().get(i).setMontant(factureClient.getTva());
                        factureClient.getOperationComptable().get(i).setSociete(factureClient.getSociete());
                    }
                }
                return factureClient.getOperationComptable();
            }
        }
    }

    @Override
    public Resource exportExcelFile(List<FactureClient> factureClients) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        CellStyle headerStyle = workbook.createCellStyle();

        XSSFFont font = (workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        headerStyle.setFont(font);


        List<String> headerData = Arrays.asList("Reference", "Type Facture", "Trimestre", "Date Facture", "Date Saisie", "Total Ht", "Total TTC", "TVA", "Total Paye HT", "Total Restant HT", "Etat Facture", "Client");
        XSSFSheet sheet = ExcelUtil.initSheet(workbook, "Factures", null, headerData, headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        List<List<String>> cellData = new ArrayList<>();

        for (FactureClient facture : factureClients) {
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
                    facture.getClient().getLibelle()));

        }
        ExcelUtil.fillTable(sheet, cellData, style, 1);

        return ExcelUtil.exportBlob(workbook);
    }

    @Override
    public FactureClient findByAdherantIdAndId(Long adhrentId, Long id) {
        return factureclientDao.findByAdherantIdAndId(adhrentId, id);
    }

    @Override
    public void deleteFacWithAll(Long adherentId, Long facId) throws IOException {
        FactureClient factureClient = factureclientDao.findByAdherantIdAndId(adherentId, facId);
        if (factureClient != null) {
            Files.delete(this.root.resolve(factureClient.getScanPath()));

            if (factureClient.getOperationComptable() != null
                    && !factureClient.getOperationComptable().isEmpty()) {


                operationComptableGroupeService.delete(factureClient.getOperationComptable().get(0).getOperationComptableGroupe());
                for (OperationComptable op : factureClient.getOperationComptable()
                ) {
                    operationcomptableService.delete(op);
                }
            }
            if (factureClient.getFactureItems() != null
                    && !factureClient.getFactureItems().isEmpty()) {

                for (FactureItem item : factureClient.getFactureItems()
                ) {
                    factureItemService.delete(item);
                }
            }
            factureclientDao.delete(factureClient);
        }
    }


    @Override
    public void uploadScan(MultipartFile file, FactureClient factureClient) {
        if (factureClient != null) {
            try {
                if (factureClient.getId() != null) {
                    FactureClient factureClientLast = findById(factureClient.getId());
                    Files.delete(this.root.resolve(factureClientLast.getScanPath()));
                }

                Files.copy(file.getInputStream(), this.root.resolve(factureClient.getSociete().getRaisonSocial() + "Client" + factureClient.getReference()));
                factureClient.setScanPath(factureClient.getSociete().getRaisonSocial() + "Client" + factureClient.getReference());

            } catch (Exception e) {
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        }
    }

    @Override
    public Resource getScan(Long id) {
        FactureClient factureClient=findById(id);
        try {
            Path file = root.resolve(factureClient.getScanPath());
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
    public List<FactureClient> findAll() {
        return factureclientDao.findAll();
    }

    @Override
    public FactureClient findById(Long id) {
        return factureclientDao.getOne(id);
    }

    @Override
    public FactureClient findBySocieteIdAndReference(Long id, String ref) {
        Societe societe=societeService.findById(id);
        return factureclientDao.findBySocieteAndReference(societe,ref);
    }

    @Override
    public List<BigDecimal> calculateGainParAnneeEtSocieteId(int annee,Long id) {
        List<BigDecimal> data=new ArrayList<>();
        Societe societe=societeService.findById(id);
        for (int i = 1; i <13 ; i++) {
            List<FactureClient> factureFournisseurs=factureclientDao.findByAnneeAndSocieteAndMois(annee,societe,i);
            BigDecimal total=BigDecimal.ZERO;
            for (FactureClient f:factureFournisseurs
            ) {
                total=total.add(f.getTotalTtc());
            }
            data.add(total);
        }
        return data;
    }

    @Override
    public int delete(FactureClient factureclient) {
        if (factureclient == null) {
            return -1;
        } else {
            factureclientDao.delete(factureclient);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        factureclientDao.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByAdherantIdAndId(Long adherentId, Long id) {
        factureclientDao.deleteByAdherantIdAndId(adherentId, id);
    }

    @Override
    public int setEtatFacture(Long etatId, Long adhrentId, Long id) {
        FactureClient factureClient = findByAdherantIdAndId(adhrentId, id);
        EtatFacture etatFacture = etatFactureService.findById(etatId);
        if (factureClient == null || etatFacture == null) {
            return -1;
        } else {
            factureClient.setEtatFacture(etatFacture);
            factureclientDao.save(factureClient);
            return 0;
        }
    }

    public void clone(FactureClient factureclient, FactureClient factureclientClone) {
        if (factureclient != null && factureclientClone != null) {
            factureclientClone.setId(factureclient.getId());
            factureclientClone.setClient(clientService.clone(factureclient.getClient()));
        }
    }

    public FactureClient clone(FactureClient factureclient) {
        if (factureclient == null) {
            return null;
        } else {
            FactureClient factureclientClone = new FactureClient();
            clone(factureclient, factureclientClone);
            return factureclientClone;
        }
    }

    public List<FactureClient> clone(List<FactureClient> factureclients) {
        if (factureclients == null) {
            return null;
        } else {
            List<FactureClient> factureclientsClone = new ArrayList();
            factureclients.forEach((factureclient) -> {
                factureclientsClone.add(clone(factureclient));
            });
            return factureclientsClone;
        }
    }

    @Override
    public List<FactureClient> findByCriteria(FactureClient facture, Date dateFactureMin, Date dateFactureMax, Date dateSaisieMin, Date dateSaisieMax) {
        return entityManager.createQuery(constructQuery(facture, dateFactureMin, dateFactureMax, dateSaisieMin, dateSaisieMax)).getResultList();
    }


    private String constructQuery(FactureClient facture, Date dateFactureMin, Date dateFactureMax, Date dateSaisieMin, Date dateSaisieMax) {
        String query = "SELECT f FROM FactureClient f where 1=1";
        if (facture.getSociete() != null) {
            query += SearchUtil.addConstraint("f", "societe.raisonSocial", "=", facture.getSociete().getRaisonSocial());
            query += SearchUtil.addConstraint("f", "societe.identifiantFiscal", "=", facture.getSociete().getIdentifiantFiscal());
            query += SearchUtil.addConstraint("f", "societe.ice", "=", facture.getSociete().getIce());
        }
        if (facture.getEtatFacture() != null) {
            query += SearchUtil.addConstraint("f", "etatFacture.id", "=", facture.getEtatFacture().getId());
        }
        if (facture.getClient() != null) {
            query += SearchUtil.addConstraint("f", "client.id", "=", facture.getClient().getId());

        }
        query += SearchUtil.addConstraint("f", "adherant.id", "=", facture.getAdherant().getId());
        query += SearchUtil.addConstraint("f", "reference", "LIKE", facture.getReference());
        query += SearchUtil.addConstraint("f", "typeFacture", "LIKE", facture.getTypeFacture());
        query += SearchUtil.addConstraint("f", "annee", "=", facture.getAnnee());
        query += SearchUtil.addConstraint("f", "trimester", "=", facture.getTrimester());
        query += SearchUtil.addConstraintMinMaxDate("f", " dateFacture", dateFactureMin,
                dateFactureMax);
        query += SearchUtil.addConstraintMinMaxDate("f", " dateSaisie", dateSaisieMin, dateSaisieMax);


        return query;
    }
}
