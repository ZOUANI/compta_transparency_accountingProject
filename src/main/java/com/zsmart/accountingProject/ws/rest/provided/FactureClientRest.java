package com.zsmart.accountingProject.ws.rest.provided;


import java.math.BigDecimal;
import java.util.ArrayList;

import com.zsmart.accountingProject.bean.FactureFournisseur;
import com.zsmart.accountingProject.ws.rest.converter.FactureConverter;
import com.zsmart.accountingProject.ws.rest.vo.FactureFournisseurVo;
import com.zsmart.accountingProject.ws.rest.vo.FactureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.zsmart.accountingProject.service.facade.FactureClientService;
import com.zsmart.accountingProject.bean.FactureClient;
import com.zsmart.accountingProject.ws.rest.vo.FactureClientVo;
import com.zsmart.accountingProject.ws.rest.converter.FactureClientConverter;
import com.zsmart.accountingProject.service.util.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/saveWithOperationsAndFactureItems/")
    public FactureClientVo saveWithOperationsAndFactureItems(@RequestPart(value = "file",required = true) MultipartFile file, @RequestPart(value = "facture",required = true) FactureClientVo factureClientVo){
        factureClientConverter.setClient(true);
        factureClientConverter.setOperationComptable(true);
        factureClientConverter.setFactureItems(true);
        factureClientConverter.getOperationComptableConverter().setTypeOperationComptable(true);
        factureClientConverter.getOperationComptableConverter().setCompteBanquaire(true);
        factureClientConverter.getOperationComptableConverter().setCaisse(true);
        FactureClient factureClient= factureClientConverter.toItem(factureClientVo);
        factureClientService.uploadScan(file,factureClient);
        return factureClientConverter.toVo(factureClientService.saveWithOperationsAndFactureItems(factureClient));
    }
    @GetMapping(value = "/scan/{id}")
    public Resource getScan(@PathVariable Long id){
        return factureClientService.getScan(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        factureClientService.deleteById(id);
    }

    @GetMapping("/")
    public List<FactureClientVo> findAll() {
        return factureClientConverter.toVo(factureClientService.findAll());
    }

    @GetMapping("/calculateGainByAnneeAndSocieteId/{annee}/{id}")
    public List<BigDecimal> calculateChargeByAnneeAndRefSoc(@PathVariable int annee, @PathVariable Long id){
        return factureClientService.calculateGainParAnneeEtSocieteId(annee,id);
    }

    @GetMapping("/findByRefAndSocieteId/{id}/{ref}")
    public FactureClientVo findByReferenceAndReferenceSociete(@PathVariable Long id, @PathVariable String ref) {
        factureClientConverter.setClient(true);
        factureClientConverter.setOperationComptable(true);
        factureClientConverter.getOperationComptableConverter().setTypeOperationComptable(true);
        return factureClientConverter.toVo(factureClientService.findBySocieteIdAndReference(id,ref));
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