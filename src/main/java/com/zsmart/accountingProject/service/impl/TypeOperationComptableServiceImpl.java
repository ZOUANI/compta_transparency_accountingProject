
package com.zsmart.accountingProject.service.impl ;
import com.zsmart.accountingProject.service.facade.TypeOperationComptableService ; 
import com.zsmart.accountingProject.dao.TypeOperationComptableDao ;
import com.zsmart.accountingProject.service.util.SearchUtil;
import com.zsmart.accountingProject.bean.TypeOperationComptable;
import org.springframework.beans.factory.annotation.Autowired; 
import java.util.ArrayList; 
import java.math.BigDecimal; 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date; 
import org.springframework.stereotype.Service; 
import java.util.List; 

 @Service  

 public class TypeOperationComptableServiceImpl implements TypeOperationComptableService  {


 @Autowired 

 private TypeOperationComptableDao typeoperationcomptableDao;

 @Autowired 

 private EntityManager entityManager; 

 @Override 
public TypeOperationComptable  save (TypeOperationComptable typeoperationcomptable){

if(typeoperationcomptable== null){ 
 return null; 
}else {
 typeoperationcomptableDao.save(typeoperationcomptable);
return typeoperationcomptable;
}
}

 @Override 
public List< TypeOperationComptable>  findAll(){
 return typeoperationcomptableDao.findAll();
}

 @Override 
public TypeOperationComptable findById(Long id){
 return typeoperationcomptableDao.getOne(id);
}

 @Override 
public int delete(TypeOperationComptable typeoperationcomptable){
if(typeoperationcomptable== null){ 
  return -1; 
}else {
 typeoperationcomptableDao.delete(typeoperationcomptable);
return 1 ;
}
}

 @Override 
public void deleteById(Long id){
       typeoperationcomptableDao.deleteById(id);
}
public void clone(TypeOperationComptable typeoperationcomptable,TypeOperationComptable typeoperationcomptableClone){
if(typeoperationcomptable!= null && typeoperationcomptableClone != null){
typeoperationcomptableClone.setId(typeoperationcomptable.getId());
typeoperationcomptableClone.setLibelle(typeoperationcomptable.getLibelle());
typeoperationcomptableClone.setCode(typeoperationcomptable.getCode());
}
}
public TypeOperationComptable clone(TypeOperationComptable typeoperationcomptable){
if(typeoperationcomptable== null){       return null ;
}else{TypeOperationComptable typeoperationcomptableClone= new TypeOperationComptable();
 clone(typeoperationcomptable,typeoperationcomptableClone);
return typeoperationcomptableClone;
}
}
public List<TypeOperationComptable> clone(List<TypeOperationComptable>typeoperationcomptables){
if(typeoperationcomptables== null){
       return null ;
}else{List<TypeOperationComptable> typeoperationcomptablesClone = new ArrayList();
	 	 	 typeoperationcomptables.forEach((typeoperationcomptable)->{typeoperationcomptablesClone.add(clone(typeoperationcomptable));
});return typeoperationcomptablesClone;
}
}
 @Override 
 public List< TypeOperationComptable>  findByCriteria(String libelle,String code,Long idMin,Long idMax){
 return entityManager.createQuery(constructQuery(libelle,code,idMin,idMax)).getResultList(); 
 }
private String constructQuery(String libelle,String code,Long idMin,Long idMax){
String query = "SELECT t FROM TypeOperationComptable t where 1=1 ";
query += SearchUtil.addConstraint( "t", "libelle","=",libelle);
query += SearchUtil.addConstraint( "t", "code","=",code);
query += SearchUtil.addConstraintMinMax("t", "id", idMin, idMax);

  return query; 
}
}
