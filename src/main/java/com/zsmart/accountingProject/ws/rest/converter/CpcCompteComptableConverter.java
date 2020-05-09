package com.zsmart.accountingProject.ws.rest.converter;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zsmart.accountingProject.service.util.*;
import com.zsmart.accountingProject.bean.CpcCompteComptable; 
import com.zsmart.accountingProject.ws.rest.vo.CpcCompteComptableVo; 

 @Component 
public class CpcCompteComptableConverter extends AbstractConverter<CpcCompteComptable,CpcCompteComptableVo>{ 

private boolean compteComptable; 

 @Autowired
 private CompteComptableConverter compteComptableConverter ; 
private boolean cpcSousClasse; 

 @Autowired
 private CpcSousClasseConverter cpcSousClasseConverter ; 

 @Override 
 public CpcCompteComptable toItem(CpcCompteComptableVo vo) {
 if (vo == null) {
    return null;
      } else {
CpcCompteComptable item = new CpcCompteComptable();

 if(compteComptable&& vo.getCompteComptableVo() != null) {
 item.setCompteComptable(compteComptableConverter.toItem(vo.getCompteComptableVo()));
} 
 
 if(cpcSousClasse&& vo.getCpcSousClasseVo() != null) {
 item.setCpcSousClasse(cpcSousClasseConverter.toItem(vo.getCpcSousClasseVo()));
} 
 
 if (vo.getId() != null) {
 item.setId(NumberUtil.toLong(vo.getId()));
} 

 if (vo.getMontant() != null) {
 item.setMontant(NumberUtil.toBigDecimal(vo.getMontant()));
} 

return item;
 }
 }

  @Override 
 public CpcCompteComptableVo toVo(CpcCompteComptable item) {
 if (item == null) {
    return null;
      } else {
CpcCompteComptableVo vo = new CpcCompteComptableVo();

 if(compteComptable&& item.getCompteComptable() != null) {
 vo.setCompteComptableVo(compteComptableConverter.toVo(item.getCompteComptable()));
} 
 
 if(cpcSousClasse&& item.getCpcSousClasse() != null) {
 vo.setCpcSousClasseVo(cpcSousClasseConverter.toVo(item.getCpcSousClasse()));
} 
 
 if (item.getId() != null) {
 vo.setId(NumberUtil.toString(item.getId()));
} 

 if (item.getMontant() != null) {
 vo.setMontant(NumberUtil.toString(item.getMontant()));
} 

return vo;
 }
 }

  public boolean isCompteComptable() {
   return compteComptable;
  }

  public void setCompteComptable(boolean compteComptable) {
   this.compteComptable = compteComptable;
  }

  public CompteComptableConverter getCompteComptableConverter() {
   return compteComptableConverter;
  }

  public void setCompteComptableConverter(CompteComptableConverter compteComptableConverter) {
   this.compteComptableConverter = compteComptableConverter;
  }

  public boolean isCpcSousClasse() {
   return cpcSousClasse;
  }

  public void setCpcSousClasse(boolean cpcSousClasse) {
   this.cpcSousClasse = cpcSousClasse;
  }

  public CpcSousClasseConverter getCpcSousClasseConverter() {
   return cpcSousClasseConverter;
  }

  public void setCpcSousClasseConverter(CpcSousClasseConverter cpcSousClasseConverter) {
   this.cpcSousClasseConverter = cpcSousClasseConverter;
  }

  public void init() {

compteComptable = true; 

cpcSousClasse = true; 
}
 } 
