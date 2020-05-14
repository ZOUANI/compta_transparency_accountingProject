package com.zsmart.accountingProject.ws.rest.provided ;


import java.math.BigDecimal;
import java.util.ArrayList;

import com.zsmart.accountingProject.ws.rest.vo.FactureClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.zsmart.accountingProject.service.facade.FactureFournisseurService;
import com.zsmart.accountingProject.bean.FactureFournisseur;
import com.zsmart.accountingProject.ws.rest.vo.FactureFournisseurVo;
import com.zsmart.accountingProject.ws.rest.converter.FactureFournisseurConverter;
import com.zsmart.accountingProject.service.util.* ;
@RestController
@RequestMapping("/accountingProject/FactureFournisseur")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FactureFournisseurRest {

 @Autowired 
 private FactureFournisseurService factureFournisseurService;

 @Autowired 
private FactureFournisseurConverter factureFournisseurConverter ;

@PostMapping("/")
public FactureFournisseurVo save(@RequestBody FactureFournisseurVo factureFournisseurVo){
FactureFournisseur factureFournisseur= factureFournisseurConverter.toItem(factureFournisseurVo);
return factureFournisseurConverter.toVo(factureFournisseurService.save(factureFournisseur));
}
 @PostMapping("/saveWithOperations/")
 public FactureFournisseurVo saveWithOperations(@RequestBody FactureFournisseurVo factureFournisseurVo){
  factureFournisseurConverter.setFournisseur(true);
  factureFournisseurConverter.setOperationComptable(true);
  factureFournisseurConverter.getOperationComptableConverter().setTypeOperationComptable(true);
  factureFournisseurConverter.getOperationComptableConverter().setCompteBanquaire(true);
  factureFournisseurConverter.getOperationComptableConverter().setCaisse(true);
  FactureFournisseur factureFournisseur= factureFournisseurConverter.toItem(factureFournisseurVo);
  return factureFournisseurConverter.toVo(factureFournisseurService.saveWithOperations(factureFournisseur));
 }
@DeleteMapping("/{id}")
public void deleteById(@PathVariable Long id){
factureFournisseurService.deleteById(id);
}
@GetMapping("/")
public List<FactureFournisseurVo> findAll(){
return factureFournisseurConverter.toVo(factureFournisseurService.findAll());
}
 @GetMapping("/calculateChargeByAnneeAndRefSoc/{annee}/{refSoc}")
 public List<BigDecimal> calculateChargeByAnneeAndRefSoc(@PathVariable int annee,@PathVariable String refSoc){
  return factureFournisseurService.calculateChargeParAnneeEtRefSociete(annee,refSoc);
 }

 @GetMapping("/findByRefAndRefSociete/{refsoc}/{ref}")
 public FactureFournisseurVo findByReferenceAndReferenceSociete(@PathVariable String refsoc, @PathVariable String ref) {
 factureFournisseurConverter.setFournisseur(true);
  factureFournisseurConverter.setOperationComptable(true);
  factureFournisseurConverter.getOperationComptableConverter().setTypeOperationComptable(true);
  return factureFournisseurConverter.toVo(factureFournisseurService.findByReferenceSocieteAndReference(refsoc,ref));
 }

 public FactureFournisseurConverter getFactureFournisseurConverter(){
return factureFournisseurConverter;
}
 
 public void setFactureFournisseurConverter(FactureFournisseurConverter factureFournisseurConverter){
this.factureFournisseurConverter=factureFournisseurConverter;
}

 public FactureFournisseurService getFactureFournisseurService(){
return factureFournisseurService;
}
 
 public void setFactureFournisseurService(FactureFournisseurService factureFournisseurService){
this.factureFournisseurService=factureFournisseurService;
}

}