package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.CpcSousClasse;
import com.zsmart.accountingProject.service.facade.CpcSousClasseService;
import com.zsmart.accountingProject.ws.rest.converter.CpcSousClasseConverter;
import com.zsmart.accountingProject.ws.rest.vo.CpcSousClasseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/CpcSousClasse")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CpcSousClasseRest {

    @Autowired
    private CpcSousClasseService cpcSousClasseService;

    @Autowired
    private CpcSousClasseConverter cpcSousClasseConverter;


    @PostMapping("/")
    public CpcSousClasseVo save(@RequestBody CpcSousClasseVo cpcSousClasseVo) {
        CpcSousClasse cpcSousClasse = cpcSousClasseConverter.toItem(cpcSousClasseVo);
        return cpcSousClasseConverter.toVo(cpcSousClasseService.save(cpcSousClasse));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cpcSousClasseService.deleteById(id);
    }

    @GetMapping("/")
    public List<CpcSousClasseVo> findAll() {
        return cpcSousClasseConverter.toVo(cpcSousClasseService.findAll());
    }

    @GetMapping("/findAllCpcSousClasse/{numero}")
    public List<CpcSousClasseVo> findAllCpcSousClasse(@PathVariable int numero) {
        cpcSousClasseConverter.setCpc(false);
        cpcSousClasseConverter.setSousClasseComptable(true);
        cpcSousClasseConverter.setCpcCompteComptables(false);
        return cpcSousClasseConverter.toVo(cpcSousClasseService.findAllCpcSousClasse(numero));
    }


    public CpcSousClasseConverter getCpcSousClasseConverter() {
        return cpcSousClasseConverter;
    }

    public void setCpcSousClasseConverter(CpcSousClasseConverter cpcSousClasseConverter) {
        this.cpcSousClasseConverter = cpcSousClasseConverter;
    }

    public CpcSousClasseService getCpcSousClasseService() {
        return cpcSousClasseService;
    }

    public void setCpcSousClasseService(CpcSousClasseService cpcSousClasseService) {
        this.cpcSousClasseService = cpcSousClasseService;
    }

}