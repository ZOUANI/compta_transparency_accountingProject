package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.TypeOperationComptable;
import com.zsmart.accountingProject.service.facade.TypeOperationComptableService;
import com.zsmart.accountingProject.ws.rest.converter.TypeOperationComptableConverter;
import com.zsmart.accountingProject.ws.rest.vo.TypeOperationComptableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/TypeOperationComptable")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TypeOperationComptableRest {

    @Autowired
    private TypeOperationComptableService typeOperationComptableService;

    @Autowired
    private TypeOperationComptableConverter typeOperationComptableConverter;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('COMPTABLE') ")

    @PostMapping("/")
    public TypeOperationComptableVo save(@RequestBody TypeOperationComptableVo typeOperationComptableVo) {
        TypeOperationComptable typeOperationComptable = typeOperationComptableConverter.toItem(typeOperationComptableVo);
        return typeOperationComptableConverter.toVo(typeOperationComptableService.save(typeOperationComptable));
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('COMPTABLE') ")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        typeOperationComptableService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('COMPTABLE') ")
    @GetMapping("/")
    public List<TypeOperationComptableVo> findAll() {
        return typeOperationComptableConverter.toVo(typeOperationComptableService.findAll());
    }

    public TypeOperationComptableConverter getTypeOperationComptableConverter() {
        return typeOperationComptableConverter;
    }

    public void setTypeOperationComptableConverter(TypeOperationComptableConverter typeOperationComptableConverter) {
        this.typeOperationComptableConverter = typeOperationComptableConverter;
    }

    public TypeOperationComptableService getTypeOperationComptableService() {
        return typeOperationComptableService;
    }

    public void setTypeOperationComptableService(TypeOperationComptableService typeOperationComptableService) {
        this.typeOperationComptableService = typeOperationComptableService;
    }

}