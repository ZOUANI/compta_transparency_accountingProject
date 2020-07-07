package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.FactureFournisseur;
import com.zsmart.accountingProject.service.facade.FactureFournisseurService;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.converter.FactureFournisseurConverter;
import com.zsmart.accountingProject.ws.rest.vo.FactureFournisseurVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/accountingProject/FactureFournisseur")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FactureFournisseurRest {

    @Autowired
    private FactureFournisseurService factureFournisseurService;

    @Autowired
    private FactureFournisseurConverter factureFournisseurConverter;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public FactureFournisseurVo save(@RequestBody FactureFournisseurVo factureFournisseurVo) {
        FactureFournisseur factureFournisseur = factureFournisseurConverter.toItem(factureFournisseurVo);
        return factureFournisseurConverter.toVo(factureFournisseurService.save(factureFournisseur));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @PostMapping("/pdf")
    public Resource pdf(@RequestBody FactureFournisseurVo factureFournisseurVo) throws IOException {
        FactureFournisseur factureFournisseur = factureFournisseurConverter.toItem(factureFournisseurVo);
        return factureFournisseurService.toPdf(factureFournisseur);

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/saveWithOperationsAndFactureItems/")
    public FactureFournisseurVo saveWithOperationsAndFactureItems(@RequestPart(value = "file", required = true) MultipartFile file, @RequestPart(value = "facture", required = true) FactureFournisseurVo factureFournisseurVo) {
        factureFournisseurConverter.setFournisseur(true);
        factureFournisseurConverter.setOperationComptable(true);
        factureFournisseurConverter.setFactureItems(true);
        factureFournisseurConverter.setSociete(true);
        factureFournisseurConverter.setAdherant(true);
        FactureFournisseur factureFournisseur = factureFournisseurConverter.toItem(factureFournisseurVo);
        factureFournisseurService.uploadScan(file, factureFournisseur);
        return factureFournisseurConverter.toVo(factureFournisseurService.saveWithOperationsAndFactureItems(factureFournisseur));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @PostMapping("/findByCriteria/")
    public List<FactureFournisseurVo> findByCriteria(@RequestBody FactureFournisseurVo factureVo) {
        factureFournisseurConverter.setSociete(true);
        factureFournisseurConverter.getSocieteConverter().setFacture(false);
        factureFournisseurConverter.setEtatFacture(true);
        factureFournisseurConverter.setFournisseur(true);
        factureFournisseurConverter.setAdherant(true);
        FactureFournisseur facture = factureFournisseurConverter.toItem(factureVo);
        return factureFournisseurConverter.toVo(factureFournisseurService.findByCriteria(facture, DateUtil.parse(factureVo.getDateFactureMin()), DateUtil.parse(factureVo.getDateFactureMax()), DateUtil.parse(factureVo.getDateSaisieMin()), DateUtil.parse(factureVo.getDateSaisieMax())));
    }

    @PreAuthorize("hasRole('ADMIN')  or hasRole('COMPTABLE')")
    @GetMapping("/setEtat/etat/{etatId}/adherent/{adhrentId}/id/{id}")
    public int setEtatFacture(@PathVariable Long etatId, @PathVariable Long adhrentId, @PathVariable Long id) {
        return factureFournisseurService.setEtatFacture(etatId, adhrentId, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @PostMapping("/ExportExcel/")
    public Resource export(@RequestBody List<FactureFournisseurVo> factureVos) {
        factureFournisseurConverter.setSociete(true);
        factureFournisseurConverter.getSocieteConverter().setFacture(false);
        factureFournisseurConverter.setEtatFacture(true);
        factureFournisseurConverter.setFournisseur(true);
        List<FactureFournisseur> factures = factureFournisseurConverter.toItem(factureVos);
        return factureFournisseurService.exportExcelFile(factures);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @GetMapping(value = "/scan/{id}")
    public Resource getScan(@PathVariable Long id) {
        return factureFournisseurService.getScan(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public FactureFournisseurVo findById(@PathVariable Long id) {
        try {
            factureFournisseurConverter.setFactureItems(true);
            factureFournisseurConverter.setOperationComptable(true);
            factureFournisseurConverter.setSociete(true);
            factureFournisseurConverter.setFournisseur(true);
            return factureFournisseurConverter.toVo(factureFournisseurService.findById(id));
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @GetMapping("/adherent/{adherentId}/id/{id}")
    public FactureFournisseurVo findByAdherentAndId(@PathVariable Long adherentId, @PathVariable Long id) {
        try {
            factureFournisseurConverter.setFactureItems(true);
            factureFournisseurConverter.setOperationComptable(true);
            factureFournisseurConverter.setSociete(true);
            factureFournisseurConverter.setEtatFacture(true);
            factureFournisseurConverter.setFournisseur(true);
            return factureFournisseurConverter.toVo(factureFournisseurService.findByAdherantIdAndId(adherentId, id));
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @GetMapping("/adherent/{adherentId}/id/{id}/societe/{socId}")
    public FactureFournisseurVo findByAdherantIdAndIdAndSocieteId(@PathVariable Long adherentId, @PathVariable Long id, @PathVariable Long socId) {
        try {
            factureFournisseurConverter.setFactureItems(true);
            factureFournisseurConverter.setOperationComptable(true);
            factureFournisseurConverter.setSociete(true);
            factureFournisseurConverter.setEtatFacture(true);
            factureFournisseurConverter.setFournisseur(true);
            return factureFournisseurConverter.toVo(factureFournisseurService.findByAdherantIdAndIdAndSocieteId(adherentId, id, socId));
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @GetMapping("/adherent/{adherentId}/societe/{socId}/dateMin/{dateMin}/dateMax/{dateMax}")
    public BigDecimal findByAdherentAndId(@PathVariable Long adherentId, @PathVariable Long socId, @PathVariable Date dateMin, @PathVariable Date dateMax) {

        return factureFournisseurService.getTotalExpense(adherentId, socId, dateMin, dateMax);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') ")
    @DeleteMapping("/delete/adherent/{adherentId}/id/{id}")
    public void deleteById(@PathVariable Long adherentId, @PathVariable Long id) throws IOException {
        factureFournisseurService.deleteFacWithAll(adherentId, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/deleteAll/")
    public void deleteAllByAdherentId(@RequestBody List<FactureFournisseurVo> factureFournisseurVos) throws IOException {
        for (FactureFournisseurVo factureFournisseurVo : factureFournisseurVos
        ) {
            if (factureFournisseurVo.getId() != null)
                factureFournisseurService.deleteFacWithAll(NumberUtil.toLong(factureFournisseurVo.getAdherantVo().getId()), NumberUtil.toLong(factureFournisseurVo.getId()));
        }

    }

    @PreAuthorize("hasRole('ADMIN')")

    @GetMapping("/")
    public List<FactureFournisseurVo> findAll() {
        return factureFournisseurConverter.toVo(factureFournisseurService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') ")

    @GetMapping("/calculateChargeByAnneeAndSocieteId/{annee}/{id}")
    public List<BigDecimal> calculateChargeByAnneeAndRefSoc(@PathVariable int annee, @PathVariable Long id) {
        return factureFournisseurService.calculateChargeParAnneeEtSocieteId(annee, id);
    }

    @GetMapping("/findByRefAndSocieteId/{id}/{ref}")
    public FactureFournisseurVo findByReferenceAndReferenceSociete(@PathVariable Long id, @PathVariable String ref) {
        factureFournisseurConverter.setFournisseur(true);
        factureFournisseurConverter.setOperationComptable(true);
        factureFournisseurConverter.getOperationComptableConverter().setFacture(false);
        factureFournisseurConverter.setFactureItems(true);
        factureFournisseurConverter.getOperationComptableConverter().setTypeOperationComptable(true);
        factureFournisseurConverter.getOperationComptableConverter().setSociete(true);
        factureFournisseurConverter.getOperationComptableConverter().getSocieteConverter().setFacture(false);
        return factureFournisseurConverter.toVo(factureFournisseurService.findBySocieteIdAndReference(id, ref));
    }


    public FactureFournisseurConverter getFactureFournisseurConverter() {
        return factureFournisseurConverter;
    }

    public void setFactureFournisseurConverter(FactureFournisseurConverter factureFournisseurConverter) {
        this.factureFournisseurConverter = factureFournisseurConverter;
    }

    public FactureFournisseurService getFactureFournisseurService() {
        return factureFournisseurService;
    }

    public void setFactureFournisseurService(FactureFournisseurService factureFournisseurService) {
        this.factureFournisseurService = factureFournisseurService;
    }

}