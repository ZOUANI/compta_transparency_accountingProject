package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.DeclarationTva;
import com.zsmart.accountingProject.bean.Facture;
import com.zsmart.accountingProject.bean.FactureItem;
import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.dao.DeclarationTvaDao;
import com.zsmart.accountingProject.service.facade.DeclarationTvaService;
import com.zsmart.accountingProject.service.facade.FactureService;
import com.zsmart.accountingProject.service.facade.SocieteService;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeclarationTvaServiceImpl implements DeclarationTvaService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DeclarationTvaDao declarationTvaDao;
    @Autowired
    private FactureService  factureService;
    @Autowired
    private SocieteService societeService;
    @Autowired
    private DeclarationTvaService declarationTvaService;
    @Override
    public DeclarationTva findById(Long id) {
        if (id==null) {
            return null;
        }
        else {
            return declarationTvaDao.getOne(id);
        }    }

    @Override
    public DeclarationTva save(DeclarationTva declarationTva) {

            declarationTvaDao.save(declarationTva);
        factureService.updateByDeclarationTva(declarationTva);
        return declarationTva;
    }
    @Override
    public DeclarationTva findbyFacture(Facture facture) {
    if (facture!=null) {
    return sortByTypefacture(factureService.findByCriteria(facture));
        }
    else return null;
    }

    public DeclarationTva sortByTypefacture(List<Facture> factures) {
        DeclarationTva declarationTva= new DeclarationTva();
        List<Facture> facturesGain= new ArrayList<>();
        List<Facture> facturesCharge= new ArrayList<>();

            for (Facture element : factures) {
                if (element.getDeclarationTva() != null) {
                    element.setTraiter(true);
                } else {
                    element.setTraiter(false);
                }
                if (element.getTypeFacture().equals("collecter")) {
                    facturesGain.add(element);
                }
                if (element.getTypeFacture().equals("deductible")) {
                    facturesCharge.add(element);
                }
                declarationTva.setSociete(element.getSociete());
           if (element.getDeclarationTva()!=null)    declarationTva.setId(element.getDeclarationTva().getId());
            }
            declarationTva.setFacturesGain(facturesGain);
            declarationTva.setFacturescharge(facturesCharge);
            return declarationTva;

    }
    @Override
    public DeclarationTva update(DeclarationTva declarationTva) {
            DeclarationTva foundeddeclaration= declarationTvaService.findById(declarationTva.getId());
            foundeddeclaration.setTotalTvaGain(declarationTva.getTotalTvaGain());
            foundeddeclaration.setTotalTvaCharge(declarationTva.getTotalTvaCharge());
            foundeddeclaration.setDifferenceChargeGain(declarationTva.getDifferenceChargeGain());
            foundeddeclaration.setCotisation(declarationTva.getCotisation());
            foundeddeclaration.setSociete(declarationTva.getSociete());
            declarationTvaDao.save(foundeddeclaration);
        return declarationTva;
    }


    @Override
    public List<DeclarationTva> findByCriteria( Societe societe) {
        return sortFactures(entityManager.createQuery(constructQuery(societe)).getResultList());
    }

        private List<DeclarationTva> sortFactures(List<DeclarationTva> declarationTvas)
        {


            for (DeclarationTva element : declarationTvas ){
                List<Facture> newfacturegain = new ArrayList<Facture>();
                List<Facture> newfacturecharge=new ArrayList<Facture>();
      if (element.getFacturesGain()!=null)         {
                    for (Facture elementfacture : element.getFacturesGain()) {
                        if (elementfacture.getTypeFacture().equals("deductible")) {
                            newfacturecharge.add(elementfacture);
                        } else {
                            newfacturegain.add(elementfacture);
                        }
                    }
                    element.setFacturesGain(newfacturegain);
                    element.setFacturescharge(newfacturecharge);
                }
            }
            return declarationTvas;
        }
    private String constructQuery(  Societe societe) {
        String query = "SELECT d FROM DeclarationTva d where 1=1";
        query += SearchUtil.addConstraint("d", "societe.raisonSocial", "=", societe.getRaisonSocial());
        query += SearchUtil.addConstraint("d", "societe.nom", "=", societe.getNom());
        query += SearchUtil.addConstraint("d", "societe.prenom", "=", societe.getPrenom());
        query += SearchUtil.addConstraint("d", "societe.registreComerce", "=", societe.getRegistreComerce());
        query += SearchUtil.addConstraint("d", "societe.authentificationCnss", "=", societe.getAuthentificationCnss());
        query += SearchUtil.addConstraint("d", "societe.juridiction", "=", societe.getJuridiction());
        query += SearchUtil.addConstraint("d", "societe.ice","=", societe.getIce());
        query += SearchUtil.addConstraint("d", "societe.identifiantFiscal","=", societe.getIdentifiantFiscal());

        return query;
    }

    @Override
    public List<DeclarationTva> findAll() {
        return declarationTvaDao.findAll();
    }


}
