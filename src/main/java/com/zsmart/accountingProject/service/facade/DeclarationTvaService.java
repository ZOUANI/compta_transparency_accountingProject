package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.DeclarationTva;
import com.zsmart.accountingProject.bean.Facture;
import com.zsmart.accountingProject.bean.Societe;

import java.util.List;


public interface DeclarationTvaService {
    public DeclarationTva findById(Long id);
    public DeclarationTva save(DeclarationTva declarationTva);
    public List<DeclarationTva> findAll();
    public DeclarationTva update(DeclarationTva declarationTva);
    public DeclarationTva findbyFacture(Facture facture);
    public List<DeclarationTva>  findByCriteria(Societe societe);

}
