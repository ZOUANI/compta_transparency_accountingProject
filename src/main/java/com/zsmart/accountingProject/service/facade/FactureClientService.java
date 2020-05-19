package com.zsmart.accountingProject.service.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Date; 
import java.math.BigDecimal; 
import com.zsmart.accountingProject.bean.FactureClient;
import com.zsmart.accountingProject.bean.Client;
import com.zsmart.accountingProject.bean.Societe;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FactureClientService {

public FactureClient save(FactureClient factureclient);
public FactureClient saveWithOperations(FactureClient factureclient);
public FactureClient saveWithOperationsAndFactureItems(FactureClient factureClient);
public void uploadScan(MultipartFile file, FactureClient factureClient);
public Resource getScan(Long id);
public List<FactureClient>  findAll();
public FactureClient findById(Long id);
 public FactureClient findBySocieteIdAndReference(Long id, String ref);
 public List<BigDecimal> calculateGainParAnneeEtSocieteId(int annee,Long id);
public int delete(FactureClient factureclient);
public void  deleteById(Long id);
public void clone(FactureClient factureclient,FactureClient factureclientClone);
public FactureClient clone(FactureClient factureclient);
public List<FactureClient> clone(List<FactureClient>factureclients);
 public List<FactureClient>  findByCriteria(Long idMin,Long idMax);

}
