package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.ClasseComptable;
import com.zsmart.accountingProject.service.facade.ClasseComptableService;
import com.zsmart.accountingProject.ws.rest.converter.ClasseComptableConverter;
import com.zsmart.accountingProject.ws.rest.vo.ClasseComptableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/ClasseComptable")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ClasseComptableRest {

    @Autowired
    private ClasseComptableService classeComptableService;

    @Autowired
    private ClasseComptableConverter classeComptableConverter;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ClasseComptableVo save(@RequestBody ClasseComptableVo classeComptableVo) {
        ClasseComptable classeComptable = classeComptableConverter.toItem(classeComptableVo);
        return classeComptableConverter.toVo(classeComptableService.save(classeComptable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        classeComptableService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public List<ClasseComptableVo> findAll() {
        return classeComptableConverter.toVo(classeComptableService.findAll());
    }

    public ClasseComptableConverter getClasseComptableConverter() {
        return classeComptableConverter;
    }

    public void setClasseComptableConverter(ClasseComptableConverter classeComptableConverter) {
        this.classeComptableConverter = classeComptableConverter;
    }

    public ClasseComptableService getClasseComptableService() {
        return classeComptableService;
    }

    public void setClasseComptableService(ClasseComptableService classeComptableService) {
        this.classeComptableService = classeComptableService;
    }

}