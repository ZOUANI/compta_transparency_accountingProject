package com.zsmart.accountingProject.service.facade;

import java.io.IOException;
import java.util.List;

import com.zsmart.accountingProject.bean.DeclarationTva;
import com.zsmart.accountingProject.bean.Facture;
public interface FactureService {

public Facture save(Facture facture); 
public Facture  saveWithPaimentFactures (Facture facture);
public Facture  saveWithOperationComptable (Facture facture);
public Facture  saveWithFactureItems (Facture facture);
public List<Facture>  findAll();
public Facture findById(Long id);
public Facture  findByReference(String  reference);
public int delete(Facture facture);
public void  deleteById(Long id);
public void  deleteByReference(String  reference);
public int deleteWithAll(Long id) throws IOException;
public void clone(Facture facture,Facture factureClone);
public Facture clone(Facture facture);
public List<Facture> clone(List<Facture>factures);
public List<Facture>  findByCriteria(Facture facture);
public int updateByDeclarationTva(DeclarationTva declarationTva);
}
