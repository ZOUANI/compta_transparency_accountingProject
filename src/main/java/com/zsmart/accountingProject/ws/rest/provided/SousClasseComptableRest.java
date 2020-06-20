package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.SousClasseComptable;
import com.zsmart.accountingProject.service.facade.SousClasseComptableService;
import com.zsmart.accountingProject.ws.rest.converter.SousClasseComptableConverter;
import com.zsmart.accountingProject.ws.rest.vo.SousClasseComptableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/SousClasseComptable")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SousClasseComptableRest {

    @Autowired
    private SousClasseComptableService sousClasseComptableService;

    @Autowired
    private SousClasseComptableConverter sousClasseComptableConverter;

    @PreAuthorize("hasRole('ADMIN') ")
    @PostMapping("/")
    public SousClasseComptableVo save(@RequestBody SousClasseComptableVo sousClasseComptableVo) {
        SousClasseComptable sousClasseComptable = sousClasseComptableConverter.toItem(sousClasseComptableVo);
        return sousClasseComptableConverter.toVo(sousClasseComptableService.save(sousClasseComptable));
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        sousClasseComptableService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @GetMapping("/")
    public List<SousClasseComptableVo> findAll() {
        return sousClasseComptableConverter.toVo(sousClasseComptableService.findAll());
    }

    public SousClasseComptableConverter getSousClasseComptableConverter() {
        return sousClasseComptableConverter;
    }

    public void setSousClasseComptableConverter(SousClasseComptableConverter sousClasseComptableConverter) {
        this.sousClasseComptableConverter = sousClasseComptableConverter;
    }

    public SousClasseComptableService getSousClasseComptableService() {
        return sousClasseComptableService;
    }

    public void setSousClasseComptableService(SousClasseComptableService sousClasseComptableService) {
        this.sousClasseComptableService = sousClasseComptableService;
    }

}