
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.*;
import com.zsmart.accountingProject.dao.FactureDao;
import com.zsmart.accountingProject.service.facade.*;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    private OperationComptableService operationcomptableService;

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
            if (facture.getOperationComptable()==null || facture.getOperationComptable().isEmpty()) {
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
        return factureDao.getOne(id);
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
        Facture facture=factureDao.findByid(id);
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
    public List<Facture> findByCriteria( Facture facture) {
        return entityManager.createQuery(constructQuery(facture.getSociete().getRaisonSocial(), facture.getAnnee(), facture.getTrimester())).getResultList();
    }



    private String constructQuery(  String raisonSocial, Integer annee, Integer trimester) {
        String query = "SELECT f FROM Facture f where 1=1";

        query += SearchUtil.addConstraint("f", "societe.raisonSocial", "=", raisonSocial);
        query += SearchUtil.addConstraint("f", "annee","=", annee);
        query += SearchUtil.addConstraint("f", "trimester","=", trimester);


        return query;
    }
    @Override
    public int updateByDeclarationTva(DeclarationTva declarationTva) {

        for (Facture element : declarationTva.getFacturescharge() )   {
            element.setDeclarationTva(declarationTva);
            factureDao.save(element);

        }
        for (Facture element : declarationTva.getFacturesGain() )   {
            element.setDeclarationTva(declarationTva);
            factureDao.save(element);
        }

        return 1;
    }


}
