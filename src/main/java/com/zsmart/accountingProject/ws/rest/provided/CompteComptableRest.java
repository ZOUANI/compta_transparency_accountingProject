package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.CompteComptable;
import com.zsmart.accountingProject.service.facade.CompteComptableService;
import com.zsmart.accountingProject.ws.rest.converter.CompteComptableConverter;
import com.zsmart.accountingProject.ws.rest.vo.CompteComptableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/CompteComptable")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CompteComptableRest {

    @Autowired
    private CompteComptableService compteComptableService;

    @Autowired
    private CompteComptableConverter compteComptableConverter;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public CompteComptableVo save(@RequestBody CompteComptableVo compteComptableVo) {
        CompteComptable compteComptable = compteComptableConverter.toItem(compteComptableVo);
        return compteComptableConverter.toVo(compteComptableService.save(compteComptable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        compteComptableService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE') ")
    @GetMapping("/")
    public List<CompteComptableVo> findAll() {
        return compteComptableConverter.toVo(compteComptableService.findAll());
    }

    public CompteComptableConverter getCompteComptableConverter() {
        return compteComptableConverter;
    }

    public void setCompteComptableConverter(CompteComptableConverter compteComptableConverter) {
        this.compteComptableConverter = compteComptableConverter;
    }

    public CompteComptableService getCompteComptableService() {
        return compteComptableService;
    }

    public void setCompteComptableService(CompteComptableService compteComptableService) {
        this.compteComptableService = compteComptableService;
    }

}