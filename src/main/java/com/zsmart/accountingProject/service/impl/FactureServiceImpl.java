
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.*;
import com.zsmart.accountingProject.dao.FactureDao;
import com.zsmart.accountingProject.service.facade.*;
import com.zsmart.accountingProject.service.util.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service

public class FactureServiceImpl implements FactureService {


    @Autowired

    private FactureDao factureDao;

    @Autowired

    private EntityManager entityManager;

    @Autowired

    private PaiementFactureService paiementfactureService;
    @Autowired

    private CompteBanquaireService compteBanquaireService;
    @Autowired

    private CaisseService caisseService;

    @Autowired

    private OperationComptableService operationcomptableService;

    @Autowired

    private TypeOperationComptableService typeOperationComptableService;

    @Autowired

    private FactureItemService factureitemService;

    @Autowired

    private EtatFactureService etatfactureService;


    @Autowired
    private OperationComptableGroupeService operationComptableGroupeService;

    private String uploadDir = "src\\main\\resources\\uploads";

    @Override
    public Facture save(Facture facture) {

        if (facture == null) {
            return null;
        } else {

            facture.setTraiter(facture.getDeclarationTva() != null);

            factureDao.save(facture);
            return facture;
        }
    }

    @Override
    public Facture saveWithPaimentFactures(Facture facture) {

        if (facture == null) {
            return null;
        } else {
            if (facture.getPaimentFactures().isEmpty()) {
                return null;
            } else {
                factureDao.save(facture);
                for (PaiementFacture paiementfacture : facture.getPaimentFactures()) {
                    paiementfacture.setFacture(facture);
                    paiementfactureService.save(paiementfacture);
                }
                return facture;
            }
        }
    }

    @Override
    public Facture saveWithOperationComptable(Facture facture) {

        if (facture == null) {
            return null;
        } else {
            if (facture.getOperationComptable() == null || facture.getOperationComptable().isEmpty()) {
                return null;
            } else {
                factureDao.save(facture);
                for (OperationComptable operationcomptable : facture.getOperationComptable()) {
                    operationcomptable.setFacture(facture);
                    operationcomptableService.save(operationcomptable);
                }
                return facture;
            }
        }
    }

    @Override
    public Facture saveWithFactureItems(Facture facture) {

        if (facture == null) {
            return null;
        } else {
            if (facture.getFactureItems().isEmpty()) {
                return null;
            } else {
                factureDao.save(facture);
                for (FactureItem factureitem : facture.getFactureItems()) {
                    factureitem.setFacture(facture);
                    factureitemService.save(factureitem);
                }
                return facture;
            }
        }
    }

    @Override
    public List<Facture> findAll() {
        return factureDao.findAll();
    }

    @Override
    public Facture findById(Long id) {
        try {
            return factureDao.getOne(id);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public Facture findByReference(String reference) {
        return factureDao.findByReference(reference);
    }


    @Override
    public int delete(Facture facture) {
        if (facture == null) {
            return -1;
        } else {
            factureDao.delete(facture);
            return 1;
        }
    }

    @Override
    public int deleteWithAll(Long id) throws IOException {
        Facture facture = factureDao.findByid(id);
        if (facture == null) {
            return -1;
        } else {
            Files.delete(Paths.get(uploadDir + "\\" + facture.getScanPath()));

            if (facture.getOperationComptable() != null
                    && !facture.getOperationComptable().isEmpty()) {


                operationComptableGroupeService.delete(facture.getOperationComptable().get(0).getOperationComptableGroupe());
                for (OperationComptable op : facture.getOperationComptable()
                ) {
                    operationcomptableService.delete(op);
                }
            }
            if (facture.getFactureItems() != null
                    && !facture.getFactureItems().isEmpty()) {

                for (FactureItem item : facture.getFactureItems()
                ) {
                    factureitemService.delete(item);
                }
            }
            factureDao.delete(facture);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        factureDao.deleteById(id);
    }

    @Override
    public void deleteByReference(String reference) {
        factureDao.deleteByReference(reference);
    }

    public void clone(Facture facture, Facture factureClone) {
        if (facture != null && factureClone != null) {
            factureClone.setId(facture.getId());
            factureClone.setReference(facture.getReference());
            factureClone.setTypeFacture(facture.getTypeFacture());
            factureClone.setAnnee(facture.getAnnee());
            factureClone.setMois(facture.getMois());
            factureClone.setTrimester(facture.getTrimester());
            factureClone.setTotalHt(facture.getTotalHt());
            factureClone.setTotalTtc(facture.getTotalTtc());
            factureClone.setTva(facture.getTva());
            factureClone.setTotalPayerHt(facture.getTotalPayerHt());
            factureClone.setTotalRestantHt(facture.getTotalRestantHt());
            factureClone.setDateFacture(facture.getDateFacture());
            factureClone.setDateSaisie(facture.getDateSaisie());
            factureClone.setSociete(facture.getSociete());
            factureClone.setEtatFacture(etatfactureService.clone(facture.getEtatFacture()));
            factureClone.setPaimentFactures(paiementfactureService.clone(facture.getPaimentFactures()));
            factureClone.setOperationComptable(operationcomptableService.clone(facture.getOperationComptable()));
            factureClone.setFactureItems(factureitemService.clone(facture.getFactureItems()));
        }
    }

    public Facture clone(Facture facture) {
        if (facture == null) {
            return null;
        } else {
            Facture factureClone = new Facture();
            clone(facture, factureClone);
            return factureClone;
        }
    }

    public List<Facture> clone(List<Facture> factures) {
        if (factures == null) {
            return null;
        } else {
            List<Facture> facturesClone = new ArrayList();
            factures.forEach((facture) -> {
                facturesClone.add(clone(facture));
            });
            return facturesClone;
        }
    }

    @Override
    public List<Facture> findByCriteria(Facture facture, Date dateFactureMin, Date dateFactureMax, Date dateSaisieMin, Date dateSaisieMax) {
        return entityManager.createQuery(constructQuery(facture, dateFactureMin, dateFactureMax, dateSaisieMin, dateSaisieMax)).getResultList();
    }


    private String constructQuery(Facture facture, Date dateFactureMin, Date dateFactureMax, Date dateSaisieMin, Date dateSaisieMax) {
        String query = "SELECT f FROM Facture f where 1=1";
        if (facture.getSociete() != null) {
            query += SearchUtil.addConstraint("f", "societe.raisonSocial", "=", facture.getSociete().getRaisonSocial());
            query += SearchUtil.addConstraint("f", "societe.identifiantFiscal", "=", facture.getSociete().getIdentifiantFiscal());
            query += SearchUtil.addConstraint("f", "societe.ice", "=", facture.getSociete().getIce());
        }

        query += SearchUtil.addConstraint("f", "reference", "LIKE", facture.getReference());
        query += SearchUtil.addConstraint("f", "typeFacture", "LIKE", facture.getTypeFacture());
        query += SearchUtil.addConstraint("f", "annee", "=", facture.getAnnee());
        query += SearchUtil.addConstraint("f", "trimester", "=", facture.getTrimester());
        query += SearchUtil.addConstraintMinMaxDate("f", " dateFacture", dateFactureMin,
                dateFactureMax);
        query += SearchUtil.addConstraintMinMaxDate("f", " dateSaisie", dateSaisieMin, dateSaisieMax);


        return query;
    }

    @Override
    public int updateByDeclarationTva(DeclarationTva declarationTva) {

        for (Facture element : declarationTva.getFacturescharge()) {
            element.setDeclarationTva(declarationTva);
            factureDao.save(element);

        }
        for (Facture element : declarationTva.getFacturesGain()) {
            element.setDeclarationTva(declarationTva);
            factureDao.save(element);
        }

        return 1;
    }

    @Override
    public boolean existsByAdherantIdAndReferenceAndSocieteId(Long adherentId, String reference, Long societeId) {
        return factureDao.existsByAdherantIdAndReferenceAndSocieteId(adherentId, reference, societeId);
    }


    @Override
    public Resource exportExcelFile(List<Facture> factures) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        CellStyle headerStyle = workbook.createCellStyle();

        XSSFFont font = (workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        headerStyle.setFont(font);


        List<String> headerData = Arrays.asList("Reference", "Type Facture", "Trimestre", "Date Facture", "Date Saisie", "Total Ht", "Total TTC", "TVA", "Total Paye HT", "Total Restant HT", "Etat Facture", "Societe");
        XSSFSheet sheet = ExcelUtil.initSheet(workbook, "Factures", null, headerData, headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        List<List<String>> cellData = new ArrayList<>();

        for (Facture facture : factures) {
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
                    facture.getSociete().getRaisonSocial()));

        }
        ExcelUtil.fillTable(sheet, cellData, style, 1);

        return ExcelUtil.exportBlob(workbook);


    }

    @Override
    public Facture findByAdherantIdAndId(Long adherentId, Long id) {
        return factureDao.findByAdherantIdAndId(adherentId, id);
    }

    @Override
    public int pay(PaiementFacture paiementFacture) {
        if (paiementFacture.getFacture() == null) {
            return -1;
        } else {
            Facture facture = findById(paiementFacture.getFacture().getId());
            if (facture == null) {
                return -2;
            } else {
                if (paiementFacture.getMontant().compareTo(facture.getTotalRestantHt()) > 0) {
                    return -3;
                } else {
                    facture.setTotalPayerHt(facture.getTotalPayerHt().add(paiementFacture.getMontant()));
                    facture.setTotalRestantHt(facture.getTotalRestantHt().subtract(paiementFacture.getMontant()));
                    if (facture.getTotalRestantHt().compareTo(BigDecimal.ZERO) == 0) {
                        facture.setEtatFacture(etatfactureService.findEtatFactureByLibelleLike("payed"));
                    } else if (facture.getTotalPayerHt().compareTo(BigDecimal.ZERO) == 0) {
                        facture.setEtatFacture(etatfactureService.findEtatFactureByLibelleLike("approved"));
                    } else {
                        facture.setEtatFacture(etatfactureService.findEtatFactureByLibelleLike("partial"));

                    }
                    OperationComptable op = constructOp(
                            facture.getSociete(),
                            paiementFacture.getMontant(),
                            paiementFacture.getDatePaiment(),
                            facture.getReference(),
                            null,
                            new Date(),
                            paiementFacture.getTypePaiment().getLibelle(),
                            facture.getOperationComptable().get(0).getOperationComptableGroupe(),
                            facture,
                            paiementFacture.getCaisse(),
                            paiementFacture.getCompteBanquaire()
                    );
                    if (Util.instanceOf(facture, FactureClient.class)) {
                        op.setTypeOperationComptable(typeOperationComptableService.findById((long) 1));
                        if (paiementFacture.getCompteBanquaire() != null) {
                            paiementFacture.getCompteBanquaire().setSolde(paiementFacture.getCompteBanquaire().getSolde().add(paiementFacture.getMontant()));
                            compteBanquaireService.save(paiementFacture.getCompteBanquaire());
                        } else {
                            paiementFacture.getCaisse().setSolde(paiementFacture.getCaisse().getSolde().add(paiementFacture.getMontant()));
                            caisseService.save(paiementFacture.getCaisse());
                        }
                    } else if (Util.instanceOf(facture, FactureFournisseur.class)) {
                        if (paiementFacture.getCompteBanquaire() != null) {
                            paiementFacture.getCompteBanquaire().setSolde(paiementFacture.getCompteBanquaire().getSolde().subtract(paiementFacture.getMontant()));
                            compteBanquaireService.save(paiementFacture.getCompteBanquaire());
                        } else {
                            paiementFacture.getCaisse().setSolde(paiementFacture.getCaisse().getSolde().subtract(paiementFacture.getMontant()));
                            caisseService.save(paiementFacture.getCaisse());
                        }
                        op.setTypeOperationComptable(typeOperationComptableService.findById((long) 2));

                    }
                    op.setAdherant(facture.getAdherant());
                    op = operationcomptableService.save(op);
                    paiementFacture.setOperationComptable(op);
                    paiementFacture.setDateSaisie(new Date());
                    factureDao.save(facture);
                    paiementfactureService.save(paiementFacture);
                    return 0;

                }
            }

        }
    }

    @Override
    public int deletePaiement(PaiementFacture paiement) {
        if (paiement == null) {
            return -1;
        } else {
            PaiementFacture paiementFacture = paiementfactureService.findById(paiement.getId());
            if (paiementFacture.getFacture() == null) {
                return -2;
            } else {
                Facture facture = findById(paiementFacture.getFacture().getId());
                operationcomptableService.delete(paiementFacture.getOperationComptable());
                facture.setTotalPayerHt(facture.getTotalPayerHt().subtract(paiementFacture.getMontant()));
                facture.setTotalRestantHt(facture.getTotalRestantHt().add(paiementFacture.getMontant()));
                if (facture.getTotalRestantHt().compareTo(BigDecimal.ZERO) == 0) {
                    facture.setEtatFacture(etatfactureService.findEtatFactureByLibelleLike("payed"));
                } else if (facture.getTotalPayerHt().compareTo(BigDecimal.ZERO) == 0) {
                    facture.setEtatFacture(etatfactureService.findEtatFactureByLibelleLike("approved"));
                } else {
                    facture.setEtatFacture(etatfactureService.findEtatFactureByLibelleLike("partial"));
                }
                if (Util.instanceOf(facture, FactureClient.class)) {
                    if (paiementFacture.getCompteBanquaire() != null) {
                        paiementFacture.getCompteBanquaire().setSolde(paiementFacture.getCompteBanquaire().getSolde().subtract(paiementFacture.getMontant()));
                        compteBanquaireService.save(paiementFacture.getCompteBanquaire());
                    } else {
                        paiementFacture.getCaisse().setSolde(paiementFacture.getCaisse().getSolde().subtract(paiementFacture.getMontant()));
                        caisseService.save(paiementFacture.getCaisse());
                    }
                } else if (Util.instanceOf(facture, FactureFournisseur.class)) {
                    if (paiementFacture.getCompteBanquaire() != null) {
                        paiementFacture.getCompteBanquaire().setSolde(paiementFacture.getCompteBanquaire().getSolde().add(paiementFacture.getMontant()));
                        compteBanquaireService.save(paiementFacture.getCompteBanquaire());
                    } else {
                        paiementFacture.getCaisse().setSolde(paiementFacture.getCaisse().getSolde().add(paiementFacture.getMontant()));
                        caisseService.save(paiementFacture.getCaisse());
                    }

                }
                factureDao.save(facture);
                paiementfactureService.delete(paiementFacture);
                return 0;
            }
        }
    }

    @Override
    public boolean isClient(Long id) {
        Facture facture = findById(id);
        return Util.instanceOf(facture, FactureClient.class);
    }

    private OperationComptable constructOp(Societe societe,
                                           BigDecimal montant,
                                           Date dateOp,
                                           String refFac,
                                           TypeOperationComptable typeOperationComptable,
                                           Date dateSaisie,
                                           String libelle,
                                           OperationComptableGroupe operationComptableGroupe,
                                           Facture facture,
                                           Caisse caisse,
                                           CompteBanquaire compteBanquaire) {
        OperationComptable operationComptable = new OperationComptable();
        operationComptable.setSociete(societe);
        operationComptable.setMontant(montant);
        operationComptable.setDateOperationComptable(dateOp);
        operationComptable.setReferenceFacture(refFac);
        operationComptable.setTypeOperationComptable(typeOperationComptable);
        operationComptable.setDateSaisie(dateSaisie);
        operationComptable.setLibelle(libelle);
        operationComptable.setOperationComptableGroupe(operationComptableGroupe);
        operationComptable.setFacture(facture);
        operationComptable.setCaisse(caisse);
        operationComptable.setCompteBanquaire(compteBanquaire);
        return operationComptable;
    }

}
