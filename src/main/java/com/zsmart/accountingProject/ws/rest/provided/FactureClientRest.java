package com.zsmart.accountingProject.ws.rest.provided;


import java.math.BigDecimal;
import java.util.ArrayList;

import com.zsmart.accountingProject.ws.rest.converter.FactureConverter;
import com.zsmart.accountingProject.ws.rest.vo.FactureVo;
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

import com.zsmart.accountingProject.service.facade.FactureClientService;
import com.zsmart.accountingProject.bean.FactureClient;
import com.zsmart.accountingProject.ws.rest.vo.FactureClientVo;
import com.zsmart.accountingProject.ws.rest.converter.FactureClientConverter;
import com.zsmart.accountingProject.service.util.*;

@RestController
@RequestMapping("/accountingProject/FactureClient")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FactureClientRest {

    @Autowired
    private FactureClientService factureClientService;

    @Autowired
    private FactureClientConverter factureClientConverter;

    @Autowired
    private FactureConverter factureConverter;

    @PostMapping("/")
    public FactureClientVo save(@RequestBody FactureClientVo factureClientVo) {
        FactureClient factureClient = factureClientConverter.toItem(factureClientVo);
        return factureClientConverter.toVo(factureClientService.save(factureClient));
    }

    @PostMapping("/saveWithOperations/")
    public FactureClientVo saveWithOperations(@RequestBody FactureClientVo factureClientVo) {
     factureClientConverter.setClient(true);
     factureClientConverter.setOperationComptable(true);
     factureClientConverter.getOperationComptableConverter().setTypeOperationComptable(true);
     factureClientConverter.getOperationComptableConverter().setCompteBanquaire(true);
     factureClientConverter.getOperationComptableConverter().setCaisse(true);
        FactureClient factureClient = factureClientConverter.toItem(factureClientVo);
        return factureClientConverter.toVo(factureClientService.saveWithOperations(factureClient));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        factureClientService.deleteById(id);
    }

    @GetMapping("/")
    public List<FactureClientVo> findAll() {
        return factureClientConverter.toVo(factureClientService.findAll());
    }

    @GetMapping("/calculateGainByAnneeAndRefSoc/{annee}/{refSoc}")
    public List<BigDecimal> calculateChargeByAnneeAndRefSoc(@PathVariable int annee, @PathVariable String refSoc){
        return factureClientService.calculateGainParAnneeEtRefSociete(annee,refSoc);
    }

    @GetMapping("/findByRefAndRefSociete/{refsoc}/{ref}")
    public FactureClientVo findByReferenceAndReferenceSociete(@PathVariable String refsoc, @PathVariable String ref) {
        factureClientConverter.setClient(true);
        factureClientConverter.setOperationComptable(true);
        factureClientConverter.getOperationComptableConverter().setTypeOperationComptable(true);
        return factureClientConverter.toVo(factureClientService.findByReferenceSocieteAndReference(refsoc,ref));
    }

    public FactureClientConverter getFactureClientConverter() {
        return factureClientConverter;
    }

    public void setFactureClientConverter(FactureClientConverter factureClientConverter) {
        this.factureClientConverter = factureClientConverter;
    }

    public FactureClientService getFactureClientService() {
        return factureClientService;
    }

    public void setFactureClientService(FactureClientService factureClientService) {
        this.factureClientService = factureClientService;
    }

}