package com.zsmart.accountingProject.ws.rest.converter;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zsmart.accountingProject.service.util.*;
import com.zsmart.accountingProject.bean.TypeOperationComptable; 
import com.zsmart.accountingProject.ws.rest.vo.TypeOperationComptableVo; 

 @Component 
public class TypeOperationComptableConverter extends AbstractConverter<TypeOperationComptable,TypeOperationComptableVo>{ 


 @Override 
 public TypeOperationComptable toItem(TypeOperationComptableVo vo) {
 if (vo == null) {
    return null;
      } else {
TypeOperationComptable item = new TypeOperationComptable();

 if (StringUtil.isNotEmpty(vo.getLibelle())) {
 item.setLibelle(vo.getLibelle());
} 

 if (StringUtil.isNotEmpty(vo.getCode())) {
 item.setCode(vo.getCode());
} 

 if (vo.getId() != null) {
 item.setId(NumberUtil.toLong(vo.getId()));
} 

return item;
 }
 }

  @Override 
 public TypeOperationComptableVo toVo(TypeOperationComptable item) {
 if (item == null) {
    return null;
      } else {
TypeOperationComptableVo vo = new TypeOperationComptableVo();

 if (StringUtil.isNotEmpty(item.getLibelle())) {
 vo.setLibelle(item.getLibelle());
} 

 if (StringUtil.isNotEmpty(item.getCode())) {
 vo.setCode(item.getCode());
} 

 if (item.getId() != null) {
 vo.setId(NumberUtil.toString(item.getId()));
} 

return vo;
 }
 }
public void init() { 
}
 } 
