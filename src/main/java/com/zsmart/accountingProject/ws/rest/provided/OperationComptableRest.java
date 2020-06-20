package com.zsmart.accountingProject.ws.rest.provided ;


import com.zsmart.accountingProject.bean.OperationComptable;
import com.zsmart.accountingProject.service.facade.OperationComptableService;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.converter.OperationComptableConverter;
import com.zsmart.accountingProject.ws.rest.vo.OperationComptableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/accountingProject/OperationComptable")
@CrossOrigin(origins = {"http://localhost:4200"})
public class OperationComptableRest {

 @Autowired 
 private OperationComptableService operationComptableService;

 @Autowired 
private OperationComptableConverter operationComptableConverter ;

 @PreAuthorize("hasRole('ADMIN')  or hasRole('COMPTABLE')")
 @PostMapping("/")
 public OperationComptableVo save(@RequestBody OperationComptableVo operationComptableVo) {
  operationComptableConverter.setTypeOperationComptable(true);
  operationComptableConverter.setCompteBanquaire(true);
  operationComptableConverter.setCaisse(true);
  operationComptableConverter.setSociete(true);
  operationComptableConverter.setAdherent(true);
  operationComptableConverter.setOperationComptableGroupe(true);
  operationComptableConverter.setCompteComptable(true);
  operationComptableConverter.setFacture(true);
  OperationComptable operationComptable = operationComptableConverter.toItem(operationComptableVo);
  return operationComptableConverter.toVo(operationComptableService.save(operationComptable));
 }

 @PreAuthorize("hasRole('ADMIN') ")
 @DeleteMapping("/delete/{id}")
 public void deleteById(@PathVariable Long id) {
  operationComptableService.deleteById(id);
 }

 @PreAuthorize("hasRole('ADMIN') ")
 @GetMapping("/")
 public List<OperationComptableVo> findAll() {
  operationComptableConverter.getSocieteConverter().setFacture(false);
  operationComptableConverter.setSociete(true);
  operationComptableConverter.setTypeOperationComptable(true);
  operationComptableConverter.setCaisse(true);
  operationComptableConverter.setCompteBanquaire(true);
  operationComptableConverter.setFacture(true);
  operationComptableConverter.getFactureConverter().setSociete(false);
  operationComptableConverter.getFactureConverter().setOperationComptable(false);
  operationComptableConverter.getFactureConverter().setFactureItems(false);
  return operationComptableConverter.toVo(operationComptableService.findAll());
 }

 @PreAuthorize("hasRole('ADMIN') ")
 @GetMapping("/findBySocId/{id}")
 public List<OperationComptableVo> findBySocId(@PathVariable Long id) {
  operationComptableConverter.setTypeOperationComptable(true);
  operationComptableConverter.setCaisse(true);
  operationComptableConverter.setCompteBanquaire(true);
  operationComptableConverter.setSociete(true);
  operationComptableConverter.setCompteComptable(true);
  return operationComptableConverter.toVo(operationComptableService.findBySocId(id));
 }

 @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE') ")
 @PostMapping("/findByCriteria/")
 public List<OperationComptableVo> findByCriteria(@RequestBody OperationComptableVo operationComptableVo) {
  operationComptableConverter.setSociete(true);
  operationComptableConverter.setAdherent(true);
  operationComptableConverter.setCompteComptable(true);
  OperationComptable operationComptable = operationComptableConverter.toItem(operationComptableVo);
  Date dateopDebut = DateUtil.parse(operationComptableVo.getDateOperationComptableMin());
  Date dateopFin = DateUtil.parse(operationComptableVo.getDateOperationComptableMax());
  Date dateSaisieDebut = DateUtil.parse(operationComptableVo.getDateSaisieMin());
  Date dateSaisieFin = DateUtil.parse(operationComptableVo.getDateSaisieMax());
  String raisonSocial = operationComptable.getSociete() != null ? operationComptable.getSociete().getRaisonSocial() : null;
  BigDecimal montantMin = operationComptableVo.getMontantMin() != null ? NumberUtil.toBigDecimal(operationComptableVo.getMontantMin()) : null;
  BigDecimal montantMax = operationComptableVo.getMontantMax() != null ? NumberUtil.toBigDecimal(operationComptableVo.getMontantMax()) : null;
  String codeCompteComptable = operationComptable.getCompteComptable() != null ? operationComptable.getCompteComptable().getCode() : null;
  String codeTypeOperation = operationComptable.getTypeOperationComptable() != null ? operationComptable.getTypeOperationComptable().getCode() : null;
  String opGroupeLibelle = operationComptable.getOperationComptableGroupe() != null ? operationComptable.getOperationComptableGroupe().getLibelle() : null;

  operationComptableConverter.setCompteComptable(true);
  operationComptableConverter.setSociete(true);
  operationComptableConverter.setOperationComptableGroupe(true);
  operationComptableConverter.setCompteBanquaire(true);
  operationComptableConverter.setCaisse(true);
  operationComptableConverter.setAdherent(true);
  operationComptableConverter.setTypeOperationComptable(true);
  operationComptableConverter.setOperationComptableGroupe(true);
  operationComptableConverter.getOperationComptableGroupeConverter().setOperationComptables(false);

  return operationComptableConverter.toVo(operationComptableService.findByCriteria(operationComptable.getLibelle(),
          raisonSocial,
          operationComptable.getReferenceFacture(),
          null,
          null,
          montantMin,
          montantMax,
          dateopDebut,
          dateopFin,
          dateSaisieDebut,
          dateSaisieFin,
          codeCompteComptable,
          codeTypeOperation,
          operationComptable.getAdherant().getId(),
          opGroupeLibelle));
 }

 @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE')")
 @DeleteMapping("/delete/adherent/{adherentId}/id/{id}")
 public void deleteById(@PathVariable Long adherentId, @PathVariable Long id) {
  operationComptableService.deleteByAdherantIdAndId(adherentId, id);
 }

 @PreAuthorize("hasRole('ADMIN')  or hasRole('COMPTABLE')")
 @PostMapping("/deleteAll/")
 public void deleteAllByAdherentId(@RequestBody List<OperationComptableVo> operationComptableVos) {
  for (OperationComptableVo operationComptableVo : operationComptableVos
  ) {
   if (operationComptableVo.getId() != null)
    operationComptableService.deleteByAdherantIdAndId(NumberUtil.toLong(operationComptableVo.getAdherantVo().getId()), NumberUtil.toLong(operationComptableVo.getId()));
  }

 }

 @PreAuthorize("hasRole('ADMIN')  or hasRole('COMPTABLE')")
 @GetMapping("/adherent/{adherentId}/id/{id}")
 public OperationComptableVo findByAdherentAndId(@PathVariable Long adherentId, @PathVariable Long id) {
  try {
   return operationComptableConverter.toVo(operationComptableService.findByAdherantIdAndId(adherentId, id));
  } catch (EntityNotFoundException e) {
   return null;
  }

 }

 @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE') ")
 @PostMapping("/excel/")
 public Resource excel(@RequestBody List<OperationComptableVo> operationComptablesVo) {
  operationComptableConverter.setSociete(true);
  operationComptableConverter.setTypeOperationComptable(true);
  operationComptableConverter.setOperationComptableGroupe(true);
  operationComptableConverter.setCompteComptable(true);
  operationComptableConverter.setCaisse(true);
  operationComptableConverter.setCompteBanquaire(true);
  List<OperationComptable> operationComptables = operationComptableConverter.toItem(operationComptablesVo);

  return operationComptableService.generateExcelFile(operationComptables);

 }

 public OperationComptableConverter getOperationComptableConverter() {
  return operationComptableConverter;
 }

 public void setOperationComptableConverter(OperationComptableConverter operationComptableConverter) {
  this.operationComptableConverter = operationComptableConverter;
 }

 public OperationComptableService getOperationComptableService() {
  return operationComptableService;
 }

 public void setOperationComptableService(OperationComptableService operationComptableService) {
  this.operationComptableService = operationComptableService;
 }

}