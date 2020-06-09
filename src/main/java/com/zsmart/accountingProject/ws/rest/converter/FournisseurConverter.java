package com.zsmart.accountingProject.ws.rest.converter;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zsmart.accountingProject.service.util.*;
import com.zsmart.accountingProject.bean.Fournisseur; 
import com.zsmart.accountingProject.ws.rest.vo.FournisseurVo; 

 @Component 
public class FournisseurConverter extends AbstractConverter<Fournisseur,FournisseurVo>{ 

private boolean factureFournisseurs; 

 @Autowired
 private FactureFournisseurConverter factureFournisseurConverter ; 
    @Autowired
    private AdherantConverter adherantConverter;
    @Autowired
    private ComptableConverter comptableConverter;
    private Boolean adherant;
    private Boolean comptable;
 @Override 
 public Fournisseur toItem(FournisseurVo vo) {
 if (vo == null) {
    return null;
      } else {
Fournisseur item = new Fournisseur();

 if (StringUtil.isNotEmpty(vo.getIce())) {
 item.setIce(vo.getIce());
} 

 if (StringUtil.isNotEmpty(vo.getIdentifiantFiscale())) {
 item.setIdentifiantFiscale(vo.getIdentifiantFiscale());
} 

 if (StringUtil.isNotEmpty(vo.getRc())) {
 item.setRc(vo.getRc());
} 

 if (StringUtil.isNotEmpty(vo.getLibelle())) {
 item.setLibelle(vo.getLibelle());
} 

 if (StringUtil.isNotEmpty(vo.getCode())) {
 item.setCode(vo.getCode());
} 

 if (vo.getId() != null) {
 item.setId(NumberUtil.toLong(vo.getId()));
} 

 if (ListUtil.isNotEmpty(vo.getFactureFournisseursVo ()) && factureFournisseurs) {
 item.setFactureFournisseurs(factureFournisseurConverter.toItem(vo.getFactureFournisseursVo())); 
} 
            if (vo.getAdherantVo()!=null && adherant) {
                item.setAdherant(adherantConverter.toItem(vo.getAdherantVo()));
            }
            if (vo.getComptableVo()!=null && comptable) {
                item.setComptable(comptableConverter.toItem(vo.getComptableVo()));
            }
return item;
 }
 }

  @Override 
 public FournisseurVo toVo(Fournisseur item) {
 if (item == null) {
    return null;
      } else {
FournisseurVo vo = new FournisseurVo();

 if (StringUtil.isNotEmpty(item.getIce())) {
 vo.setIce(item.getIce());
} 

 if (StringUtil.isNotEmpty(item.getIdentifiantFiscale())) {
 vo.setIdentifiantFiscale(item.getIdentifiantFiscale());
} 

 if (StringUtil.isNotEmpty(item.getRc())) {
 vo.setRc(item.getRc());
} 

 if (StringUtil.isNotEmpty(item.getLibelle())) {
 vo.setLibelle(item.getLibelle());
} 

 if (StringUtil.isNotEmpty(item.getCode())) {
 vo.setCode(item.getCode());
} 

 if (item.getId() != null) {
 vo.setId(NumberUtil.toString(item.getId()));
} 

 if(ListUtil.isNotEmpty(item.getFactureFournisseurs()) && factureFournisseurs) {
 vo.setFactureFournisseursVo(factureFournisseurConverter.toVo(item.getFactureFournisseurs()));
} 
           if (item.getAdherant()!=null && adherant) {
                vo.setAdherantVo(adherantConverter.toVo(item.getAdherant()));
            }
            if (item.getComptable()!=null && comptable) {
                vo.setComptableVo(comptableConverter.toVo(item.getComptable()));
            }

return vo;
 }
 }
public void init() { 

factureFournisseurs = true; 
}

  public Boolean getAdherant() {
   return adherant;
  }

  public void setAdherant(Boolean adherant) {
   this.adherant = adherant;
  }

  public Boolean getComptable() {
   return comptable;
  }

  public void setComptable(Boolean comptable) {
   this.comptable = comptable;
  }
 }
