package com.zsmart.accountingProject.service.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Date; 
import java.math.BigDecimal; 
import com.zsmart.accountingProject.bean.TypeOperationComptable;
public interface TypeOperationComptableService {

public TypeOperationComptable save(TypeOperationComptable typeoperationcomptable); 
public List<TypeOperationComptable>  findAll();
public TypeOperationComptable findById(Long id);
public int delete(TypeOperationComptable typeoperationcomptable);
public void  deleteById(Long id);
public void clone(TypeOperationComptable typeoperationcomptable,TypeOperationComptable typeoperationcomptableClone);
public TypeOperationComptable clone(TypeOperationComptable typeoperationcomptable);
public List<TypeOperationComptable> clone(List<TypeOperationComptable>typeoperationcomptables);
 public List<TypeOperationComptable>  findByCriteria(String libelle,String code,Long idMin,Long idMax);

}
