package com.zsmart.accountingProject.ws.rest.provided ;


import java.util.ArrayList;
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
import com.zsmart.accountingProject.service.facade.TypeOperationComptableService;
import com.zsmart.accountingProject.bean.TypeOperationComptable;
import com.zsmart.accountingProject.ws.rest.vo.TypeOperationComptableVo;
import com.zsmart.accountingProject.ws.rest.converter.TypeOperationComptableConverter;
import com.zsmart.accountingProject.service.util.* ;
@RestController
@RequestMapping("/accountingProject/TypeOperationComptable")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TypeOperationComptableRest {

 @Autowired 
 private TypeOperationComptableService typeOperationComptableService;

 @Autowired 
private TypeOperationComptableConverter typeOperationComptableConverter ;

@PostMapping("/")
public TypeOperationComptableVo save(@RequestBody TypeOperationComptableVo typeOperationComptableVo){
TypeOperationComptable typeOperationComptable= typeOperationComptableConverter.toItem(typeOperationComptableVo);
return typeOperationComptableConverter.toVo(typeOperationComptableService.save(typeOperationComptable));
}
@DeleteMapping("/{id}")
public void deleteById(@PathVariable Long id){
typeOperationComptableService.deleteById(id);
}
@GetMapping("/")
public List<TypeOperationComptableVo> findAll(){
return typeOperationComptableConverter.toVo(typeOperationComptableService.findAll());
}

 public TypeOperationComptableConverter getTypeOperationComptableConverter(){
return typeOperationComptableConverter;
}
 
 public void setTypeOperationComptableConverter(TypeOperationComptableConverter typeOperationComptableConverter){
this.typeOperationComptableConverter=typeOperationComptableConverter;
}

 public TypeOperationComptableService getTypeOperationComptableService(){
return typeOperationComptableService;
}
 
 public void setTypeOperationComptableService(TypeOperationComptableService typeOperationComptableService){
this.typeOperationComptableService=typeOperationComptableService;
}

}