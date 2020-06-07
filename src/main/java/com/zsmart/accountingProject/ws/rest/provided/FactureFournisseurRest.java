package com.zsmart.accountingProject.ws.rest.provided ;


import com.zsmart.accountingProject.bean.FactureFournisseur;
import com.zsmart.accountingProject.service.facade.FactureFournisseurService;
import com.zsmart.accountingProject.ws.rest.converter.FactureFournisseurConverter;
import com.zsmart.accountingProject.ws.rest.vo.FactureFournisseurVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

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
  factureFournisseurConverter.getOperationComptableConverter().setSociete(true);
  FactureFournisseur factureFournisseur = factureFournisseurConverter.toItem(factureFournisseurVo);

  return factureFournisseurConverter.toVo(factureFournisseurService.saveWithOperations(factureFournisseur));
 }

 @PostMapping("/saveWithOperationsAndFactureItems/")
 public FactureFournisseurVo saveWithOperationsAndFactureItems(@RequestPart(value = "file", required = true) MultipartFile file, @RequestPart(value = "facture", required = true) FactureFournisseurVo factureFournisseurVo) {
  factureFournisseurConverter.setFournisseur(true);
  factureFournisseurConverter.setOperationComptable(true);
  factureFournisseurConverter.setFactureItems(true);
  factureFournisseurConverter.getOperationComptableConverter().setTypeOperationComptable(true);
  factureFournisseurConverter.getOperationComptableConverter().setCompteBanquaire(true);
  factureFournisseurConverter.getOperationComptableConverter().setCaisse(true);
  factureFournisseurConverter.getOperationComptableConverter().setSociete(true);
  factureFournisseurConverter.getOperationComptableConverter().setOperationComptableGroupe(true);
  FactureFournisseur factureFournisseur = factureFournisseurConverter.toItem(factureFournisseurVo);
  factureFournisseurService.uploadScan(file, factureFournisseur);
  return factureFournisseurConverter.toVo(factureFournisseurService.saveWithOperationsAndFactureItems(factureFournisseur));
 }
 @GetMapping(value = "/scan/{id}")
 public Resource getScan(@PathVariable Long id){
  return factureFournisseurService.getScan(id);
 }
 @GetMapping("/{id}")
 public FactureFournisseurVo findById(Long id){
  return factureFournisseurConverter.toVo(factureFournisseurService.findById(id));
 }
@DeleteMapping("/{id}")
public void deleteById(@PathVariable Long id){
factureFournisseurService.deleteById(id);
}
@GetMapping("/")
public List<FactureFournisseurVo> findAll(){
return factureFournisseurConverter.toVo(factureFournisseurService.findAll());
}
 @GetMapping("/calculateChargeByAnneeAndSocieteId/{annee}/{id}")
 public List<BigDecimal> calculateChargeByAnneeAndRefSoc(@PathVariable int annee,@PathVariable Long id){
  return factureFournisseurService.calculateChargeParAnneeEtSocieteId(annee,id);
 }

 @GetMapping("/findByRefAndSocieteId/{id}/{ref}")
 public FactureFournisseurVo findByReferenceAndReferenceSociete(@PathVariable Long id, @PathVariable String ref) {
 factureFournisseurConverter.setFournisseur(true);
  factureFournisseurConverter.setOperationComptable(true);
  factureFournisseurConverter.setFactureItems(true);
  factureFournisseurConverter.getOperationComptableConverter().setTypeOperationComptable(true);
  factureFournisseurConverter.getOperationComptableConverter().setSociete(true);
  return factureFournisseurConverter.toVo(factureFournisseurService.findBySocieteIdAndReference(id, ref));
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