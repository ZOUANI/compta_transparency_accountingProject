package com.zsmart.accountingProject.service.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

import com.zsmart.accountingProject.bean.FactureFournisseur;
import com.zsmart.accountingProject.bean.Fournisseur;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FactureFournisseurService {

public FactureFournisseur save(FactureFournisseur facturefournisseur);
public FactureFournisseur saveWithOperations(FactureFournisseur facturefournisseur);
public FactureFournisseur saveWithOperationsAndFactureItems(FactureFournisseur facturefournisseur);
public void uploadScan(MultipartFile file,FactureFournisseur factureFournisseur);
public Resource getScan(Long id);
public List<FactureFournisseur>  findAll();
public FactureFournisseur findById(Long id);
 public FactureFournisseur findBySocieteIdAndReference(Long id, String ref);
 public List<BigDecimal> calculateChargeParAnneeEtSocieteId(int annee,Long id);
public int delete(FactureFournisseur facturefournisseur);
public void  deleteById(Long id);
public void clone(FactureFournisseur facturefournisseur,FactureFournisseur facturefournisseurClone);
public FactureFournisseur clone(FactureFournisseur facturefournisseur);
public List<FactureFournisseur> clone(List<FactureFournisseur>facturefournisseurs);
 public List<FactureFournisseur>  findByCriteria(Long idMin,Long idMax);

}
