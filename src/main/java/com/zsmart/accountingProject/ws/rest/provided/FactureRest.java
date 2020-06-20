package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.Facture;
import com.zsmart.accountingProject.bean.PaiementFacture;
import com.zsmart.accountingProject.service.facade.FactureService;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.ws.rest.converter.FactureConverter;
import com.zsmart.accountingProject.ws.rest.converter.PaiementFactureConverter;
import com.zsmart.accountingProject.ws.rest.vo.FactureVo;
import com.zsmart.accountingProject.ws.rest.vo.PaiementFactureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/accountingProject/Facture")
@CrossOrigin(origins = "http://localhost:4200")
public class FactureRest {
    @Autowired
    private FactureService factureService;
    @Autowired
    private PaiementFactureConverter paiementFactureConverter;
    @Autowired
    private FactureConverter factureConverter;

    @PreAuthorize("hasRole('ADMIN') ")
    @PostMapping("/")
    public FactureVo save(@RequestBody FactureVo factureVo) {
        Facture facture = factureConverter.toItem(factureVo);
        return factureConverter.toVo(factureService.save(facture));
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @PostMapping("/saveWithOperations/")
    public FactureVo saveWithOperations(@RequestBody FactureVo factureVo) {
        factureConverter.setOperationComptable(true);
        factureConverter.getOperationComptableConverter().setTypeOperationComptable(true);
        factureConverter.getOperationComptableConverter().setCompteBanquaire(true);
        factureConverter.getOperationComptableConverter().setCaisse(true);
        Facture facture = factureConverter.toItem(factureVo);
        return factureConverter.toVo(factureService.saveWithOperationComptable(facture));
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @PostMapping("/ExportExcel/")
    public Resource export(@RequestBody List<FactureVo> factureVos) {
        factureConverter.setSociete(true);
        factureConverter.getSocieteConverter().setFacture(false);
        factureConverter.setEtatFacture(true);
        List<Facture> factures = factureConverter.toItem(factureVos);
        return factureService.exportExcelFile(factures);
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @PostMapping("/findByCriteria/")
    public List<FactureVo> findByCriteria(@RequestBody FactureVo factureVo) {
        factureConverter.setSociete(true);
        factureConverter.getSocieteConverter().setFacture(false);
        factureConverter.setFactureItems(true);
        factureConverter.setOperationComptable(true);
        factureConverter.getOperationComptableConverter().setCompteBanquaire(true);
        factureConverter.getOperationComptableConverter().setCaisse(true);
        factureConverter.getOperationComptableConverter().setCompteComptable(true);
        factureConverter.getOperationComptableConverter().setTypeOperationComptable(true);
        factureConverter.setEtatFacture(true);
        Facture facture = factureConverter.toItem(factureVo);
        return factureConverter.toVo(factureService.findByCriteria(facture, DateUtil.parse(factureVo.getDateFactureMin()), DateUtil.parse(factureVo.getDateFactureMax()), DateUtil.parse(factureVo.getDateSaisieMin()), DateUtil.parse(factureVo.getDateSaisieMax())));
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        factureService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @DeleteMapping("/DelFacWithAll/{id}")
    public void deleteWithOperationsComptable(@PathVariable Long id) {
        try {
            factureService.deleteWithAll(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @DeleteMapping("/{reference}")
    public void deleteByReference(@PathVariable String reference) {
        factureService.deleteByReference(reference);
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @GetMapping("/")
    public List<FactureVo> findAll() {
        factureConverter.setSociete(true);
        factureConverter.getSocieteConverter().setFacture(false);
        factureConverter.setEtatFacture(true);
        factureConverter.setOperationComptable(true);
        factureConverter.setFactureItems(true);

        return factureConverter.toVo(factureService.findAll());
    }

    @GetMapping("/AdherentId/{adherentId}/Ref/{ref}/SocId/{societeId}")
    public boolean isRefExists(@PathVariable Long adherentId, @PathVariable String ref, @PathVariable Long societeId) {


        return factureService.existsByAdherantIdAndReferenceAndSocieteId(adherentId, ref, societeId);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @GetMapping("/adherent/{adherentId}/id/{id}")
    public FactureVo findByAdherentAndId(@PathVariable Long adherentId, @PathVariable Long id) {
        factureConverter.setPaimentFactures(true);
        factureConverter.setEtatFacture(true);
        factureConverter.getPaiementFactureConverter().setFacture(false);
        factureConverter.getPaiementFactureConverter().setTypePaiment(true);
        factureConverter.getPaiementFactureConverter().setOperationComptable(true);
        try {
            return factureConverter.toVo(factureService.findByAdherantIdAndId(adherentId, id));
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/pay")
    public int pay(@RequestBody PaiementFactureVo paiementFactureVo) {
        paiementFactureConverter.setTypePaiment(true);
        paiementFactureConverter.setFacture(true);
        paiementFactureConverter.setCaisse(true);
        paiementFactureConverter.setCompteBanquaire(true);
        PaiementFacture paiementFacture = paiementFactureConverter.toItem(paiementFactureVo);

        return factureService.pay(paiementFacture);

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/deletePaiement")
    public int deletePaiement(@RequestBody PaiementFactureVo paiementFactureVo) {
        paiementFactureConverter.setTypePaiment(true);
        paiementFactureConverter.setFacture(true);
        paiementFactureConverter.setCaisse(true);
        paiementFactureConverter.setCompteBanquaire(true);
        paiementFactureConverter.setOperationComptable(true);
        PaiementFacture paiementFacture = paiementFactureConverter.toItem(paiementFactureVo);

        return factureService.deletePaiement(paiementFacture);

    }

    @GetMapping("/test/{id}")
    public boolean isClient(@PathVariable Long id) {
        return factureService.isClient(id);
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