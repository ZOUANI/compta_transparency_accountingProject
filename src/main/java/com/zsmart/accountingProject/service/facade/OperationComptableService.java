package com.zsmart.accountingProject.service.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Date; 
import java.math.BigDecimal; 
import com.zsmart.accountingProject.bean.OperationComptable;
import com.zsmart.accountingProject.bean.Caisse; 
import com.zsmart.accountingProject.bean.TypeOperationComptable; 
import com.zsmart.accountingProject.bean.CompteBanquaire; 
import com.zsmart.accountingProject.bean.CompteComptable; 
import com.zsmart.accountingProject.bean.OperationComptableGroupe; 
import com.zsmart.accountingProject.bean.Facture; 

public interface OperationComptableService {

public OperationComptable save(OperationComptable operationcomptable); 
public List<OperationComptable>  findAll();
public OperationComptable findById(Long id);
public List<OperationComptable> findBySocId(Long id);
public int delete(OperationComptable operationcomptable);
public void  deleteById(Long id);
public void clone(OperationComptable operationcomptable,OperationComptable operationcomptableClone);
public OperationComptable clone(OperationComptable operationcomptable);
public List<OperationComptable> clone(List<OperationComptable>operationcomptables);
 public List<OperationComptable>  findByCriteria(String libelle,String referenceSociete,String referenceFacture,Long idMin,Long idMax,BigDecimal montantMin,BigDecimal montantMax,Date dateOperationComptableMin,Date dateOperationComptableMax,Date dateSaisieMin,Date dateSaisieMax,String codeCompteComptable);
 public List<OperationComptable> findOperationComptable(Date dateDebut, Date dateFin, String code) ;


List<Object[]> findGroupeBySousClasseCompteComptable(Date dateDebut, Date dateFin, String code);
List<Object[]> findGroupeByClasseCompteComptable(Date dateDebut, Date dateFin, String code);
List<Object[]> findGroupeByCompteComptable(Date dateDebut, Date dateFin, int code);
}
