package com.zsmart.accountingProject.ws.rest.converter;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zsmart.accountingProject.service.util.*;
import com.zsmart.accountingProject.bean.CompteBanquaire; 
import com.zsmart.accountingProject.ws.rest.vo.CompteBanquaireVo; 

 @Component 
public class CompteBanquaireConverter extends AbstractConverter<CompteBanquaire,CompteBanquaireVo>{ 

private boolean banque; 

 @Autowired
 private BanqueConverter banqueConverter ; 
private boolean operationComptables; 

 @Autowired
 private OperationComptableConverter operationComptableConverter ;
  @Autowired
  private AdherantConverter adherantConverter;
  @Autowired
  private ComptableConverter comptableConverter;
  private Boolean adherant;
  private Boolean comptable;
 @Override 
 public CompteBanquaire toItem(CompteBanquaireVo vo) {
 if (vo == null) {
    return null;
      } else {
CompteBanquaire item = new CompteBanquaire();

 if(banque&& vo.getBanqueVo() != null) {
 item.setBanque(banqueConverter.toItem(vo.getBanqueVo()));
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

 if (vo.getSolde() != null) {
 item.setSolde(NumberUtil.toBigDecimal(vo.getSolde()));
} 

 if (ListUtil.isNotEmpty(vo.getOperationComptablesVo ()) && operationComptables) {
 item.setOperationComptables(operationComptableConverter.toItem(vo.getOperationComptablesVo())); 
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
 public CompteBanquaireVo toVo(CompteBanquaire item) {
 if (item == null) {
    return null;
      } else {
CompteBanquaireVo vo = new CompteBanquaireVo();

 if(banque&& item.getBanque() != null) {
 vo.setBanqueVo(banqueConverter.toVo(item.getBanque()));
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

 if (item.getSolde() != null) {
 vo.setSolde(NumberUtil.toString(item.getSolde()));
} 

 if(ListUtil.isNotEmpty(item.getOperationComptables()) && operationComptables) {
 vo.setOperationComptablesVo(operationComptableConverter.toVo(item.getOperationComptables()));
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

banque = true; 

operationComptables = true; 
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
