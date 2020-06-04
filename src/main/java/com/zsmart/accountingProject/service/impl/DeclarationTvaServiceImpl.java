package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.DeclarationTva;
import com.zsmart.accountingProject.bean.Facture;
import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.dao.DeclarationTvaDao;
import com.zsmart.accountingProject.service.facade.DeclarationTvaService;
import com.zsmart.accountingProject.service.facade.FactureService;
import com.zsmart.accountingProject.service.facade.SocieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeclarationTvaServiceImpl implements DeclarationTvaService {
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
    public List<DeclarationTva> findAll() {
        return declarationTvaDao.findAll();
    }


}
