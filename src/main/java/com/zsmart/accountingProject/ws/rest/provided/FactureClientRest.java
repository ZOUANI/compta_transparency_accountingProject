package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.FactureClient;
import com.zsmart.accountingProject.service.facade.FactureClientService;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.converter.FactureClientConverter;
import com.zsmart.accountingProject.ws.rest.converter.FactureConverter;
import com.zsmart.accountingProject.ws.rest.vo.FactureClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public FactureClientVo save(@RequestBody FactureClientVo factureClientVo) {
        FactureClient factureClient = factureClientConverter.toItem(factureClientVo);
        return factureClientConverter.toVo(factureClientService.save(factureClient));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
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

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/saveWithOperationsAndFactureItems/")
    public FactureClientVo saveWithOperationsAndFactureItems(@RequestPart(value = "file", required = true) MultipartFile file, @RequestPart(value = "facture", required = true) FactureClientVo factureClientVo) {
        factureClientConverter.setClient(true);
        factureClientConverter.setOperationComptable(true);
        factureClientConverter.setFactureItems(true);
        factureClientConverter.setSociete(true);
        factureClientConverter.setAdherant(true);
        FactureClient factureClient = factureClientConverter.toItem(factureClientVo);
        factureClientService.uploadScan(file, factureClient);
        return factureClientConverter.toVo(factureClientService.saveWithOperationsAndFactureItems(factureClient));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @GetMapping(value = "/scan/{id}")
    public Resource getScan(@PathVariable Long id) {
        return factureClientService.getScan(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @PostMapping("/findByCriteria/")
    public List<FactureClientVo> findByCriteria(@RequestBody FactureClientVo factureVo) {
        factureClientConverter.setSociete(true);
        factureClientConverter.getSocieteConverter().setFacture(false);
        factureClientConverter.setEtatFacture(true);
        factureClientConverter.setClient(true);
        factureClientConverter.setAdherant(true);
        FactureClient facture = factureClientConverter.toItem(factureVo);
        return factureClientConverter.toVo(factureClientService.findByCriteria(facture, DateUtil.parse(factureVo.getDateFactureMin()), DateUtil.parse(factureVo.getDateFactureMax()), DateUtil.parse(factureVo.getDateSaisieMin()), DateUtil.parse(factureVo.getDateSaisieMax())));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @PostMapping("/ExportExcel/")
    public Resource export(@RequestBody List<FactureClientVo> factureVos) {
        factureClientConverter.setSociete(true);
        factureClientConverter.getSocieteConverter().setFacture(false);
        factureClientConverter.setEtatFacture(true);
        factureClientConverter.setClient(true);
        List<FactureClient> factures = factureClientConverter.toItem(factureVos);
        return factureClientService.exportExcelFile(factures);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @GetMapping("/adherent/{adherentId}/id/{id}")
    public FactureClientVo findByAdherentAndId(@PathVariable Long adherentId, @PathVariable Long id) {
        try {
            factureClientConverter.setFactureItems(true);
            factureClientConverter.setOperationComptable(true);
            factureClientConverter.setSociete(true);
            factureClientConverter.setClient(true);
            factureClientConverter.setEtatFacture(true);
            return factureClientConverter.toVo(factureClientService.findByAdherantIdAndId(adherentId, id));
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        factureClientService.deleteById(id);
    }

    @GetMapping("/")
    public List<FactureClientVo> findAll() {
        return factureClientConverter.toVo(factureClientService.findAll());
    }

    @GetMapping("/calculateGainByAnneeAndSocieteId/{annee}/{id}")
    public List<BigDecimal> calculateChargeByAnneeAndRefSoc(@PathVariable int annee, @PathVariable Long id) {
        return factureClientService.calculateGainParAnneeEtSocieteId(annee, id);
    }

    @GetMapping("/findByRefAndSocieteId/{id}/{ref}")
    public FactureClientVo findByReferenceAndReferenceSociete(@PathVariable Long id, @PathVariable String ref) {
        factureClientConverter.setClient(true);
        factureClientConverter.setOperationComptable(true);
        factureClientConverter.getOperationComptableConverter().setFacture(false);
        factureClientConverter.setFactureItems(true);
        factureClientConverter.getOperationComptableConverter().setTypeOperationComptable(true);
        factureClientConverter.getOperationComptableConverter().setSociete(true);
        factureClientConverter.getOperationComptableConverter().getSocieteConverter().setFacture(false);
        return factureClientConverter.toVo(factureClientService.findBySocieteIdAndReference(id, ref));
    }

    @GetMapping("/{id}")
    public FactureClientVo findById(@PathVariable Long id) {
        try {
            factureClientConverter.setFactureItems(true);
            factureClientConverter.setOperationComptable(true);
            factureClientConverter.setClient(true);
            factureClientConverter.setSociete(true);
            return factureClientConverter.toVo(factureClientService.findById(id));
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @DeleteMapping("/delete/adherent/{adherentId}/id/{id}")
    public void deleteById(@PathVariable Long adherentId, @PathVariable Long id) throws IOException {
        factureClientService.deleteFacWithAll(adherentId, id);
    }

    @PreAuthorize("hasRole('ADMIN')  or hasRole('COMPTABLE')")
    @GetMapping("/setEtat/etat/{etatId}/adherent/{adhrentId}/id/{id}")
    public int setEtatFacture(@PathVariable Long etatId, @PathVariable Long adhrentId, @PathVariable Long id) {
        return factureClientService.setEtatFacture(etatId, adhrentId, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/deleteAll/")
    public void deleteAllByAdherentId(@RequestBody List<FactureClientVo> factureClientVos) throws IOException {
        for (FactureClientVo factureClientVo : factureClientVos
        ) {
            if (factureClientVo.getId() != null)
                factureClientService.deleteFacWithAll(NumberUtil.toLong(factureClientVo.getAdherantVo().getId()), NumberUtil.toLong(factureClientVo.getId()));
        }

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