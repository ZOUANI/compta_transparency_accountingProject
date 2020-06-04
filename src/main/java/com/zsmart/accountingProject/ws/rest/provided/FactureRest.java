package com.zsmart.accountingProject.ws.rest.provided;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.zsmart.accountingProject.bean.DeclarationTva;
import com.zsmart.accountingProject.service.facade.DeclarationTvaService;
import com.zsmart.accountingProject.ws.rest.converter.DeclarationTvaConverter;
import com.zsmart.accountingProject.ws.rest.vo.DeclarationTvaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import com.zsmart.accountingProject.service.facade.FactureService;
import com.zsmart.accountingProject.bean.Facture;
import com.zsmart.accountingProject.ws.rest.vo.FactureVo;
import com.zsmart.accountingProject.ws.rest.converter.FactureConverter;
import com.zsmart.accountingProject.service.util.*;

@RestController
@RequestMapping("/accountingProject/Facture")
@CrossOrigin(origins ="http://localhost:4200")
public class FactureRest {
    @Autowired
    private FactureService factureService;
    @Autowired
    private FactureConverter factureConverter;

    @PostMapping("/")
    public FactureVo save(@RequestBody FactureVo factureVo) {
        Facture facture = factureConverter.toItem(factureVo);
        return factureConverter.toVo(factureService.save(facture));
    }

    @PostMapping("/saveWithOperations/")
    public FactureVo saveWithOperations(@RequestBody FactureVo factureVo) {
        factureConverter.setOperationComptable(true);
        factureConverter.getOperationComptableConverter().setTypeOperationComptable(true);
        factureConverter.getOperationComptableConverter().setCompteBanquaire(true);
        factureConverter.getOperationComptableConverter().setCaisse(true);
        Facture facture = factureConverter.toItem(factureVo);
        return factureConverter.toVo(factureService.saveWithOperationComptable(facture));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        factureService.deleteById(id);
    }

    @DeleteMapping("/DelFacWithAll/{id}")
    public void deleteWithOperationsComptable(@PathVariable Long id) {
        try{
        factureService.deleteWithAll(id);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{reference}")
    public void deleteByReference(@PathVariable String reference) {
        factureService.deleteByReference(reference);
    }

    @GetMapping("/")
    public List<FactureVo> findAll() {
        return factureConverter.toVo(factureService.findAll());
    }



    public FactureConverter getFactureConverter() {
        return factureConverter;
    }

    public void setFactureConverter(FactureConverter factureConverter) {
        this.factureConverter = factureConverter;
    }

    public FactureService getFactureService() {
        return factureService;
    }

    public void setFactureService(FactureService factureService) {
        this.factureService = factureService;
    }

}