package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.bean.Utilisateur;
import com.zsmart.accountingProject.service.facade.SocieteService;
import com.zsmart.accountingProject.ws.rest.converter.SocieteConverter;
import com.zsmart.accountingProject.ws.rest.converter.UtilisateurConverter;
import com.zsmart.accountingProject.ws.rest.vo.SocieteVo;
import com.zsmart.accountingProject.ws.rest.vo.UtilisateurVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/Societe")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SocieteRest {

    @Autowired
    private SocieteService societeService;
    @Autowired
    private SocieteConverter societeConverter;
    @Autowired
    private UtilisateurConverter utilisateurConverter;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @PostMapping("/")
    public SocieteVo save(@RequestPart(value = "societe", required = true) SocieteVo societeVo,
                          @RequestPart(value = "contratBail", required = false) MultipartFile contratBail,
                          @RequestPart(value = "certificatNegatif", required = false) MultipartFile certificatnegatif,
                          @RequestPart(value = "registreComercialImage", required = false) MultipartFile registreComercialImage,
                          @RequestPart(value = "patente", required = false) MultipartFile patente,
                          @RequestPart(value = "statue", required = false) MultipartFile statue,
                          @RequestPart(value = "releverBanquaire", required = false) MultipartFile releverBanquaire,
                          @RequestPart(value = "publicationCreationBO", required = false) MultipartFile publicationCreationBO
    ) {

        Societe societe = societeConverter.toItem(societeVo);
        societeService.uploadfiles(
                contratBail,
                certificatnegatif,
                registreComercialImage,
                patente,
                statue,
                releverBanquaire,
                publicationCreationBO,
                societe
        );
        return societeConverter.toVo(societeService.save(societe));
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        societeService.deleteById(id);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN') ")
    public List<SocieteVo> findAll() {
        societeConverter.setFacture(false);
        return societeConverter.toVo(societeService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @GetMapping("/{id}")
    public SocieteVo findById(@PathVariable Long id) {
        societeConverter.getFactureConverter().setOperationComptable(true);
        societeConverter.getFactureConverter().setFactureItems(true);
        societeConverter.getFactureConverter().getFactureItemConverter().setFacture(false);
        societeConverter.getFactureConverter().getOperationComptableConverter().setTypeOperationComptable(true);
        societeConverter.getFactureConverter().getOperationComptableConverter().setSociete(false);
        societeConverter.getFactureConverter().getOperationComptableConverter().setFacture(false);
        societeConverter.setFacture(true);
        societeConverter.getFactureConverter().setSociete(false);
        return societeConverter.toVo(societeService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @GetMapping("/findbyscraping/{ice}")
    public SocieteVo findbyscraping(@PathVariable String ice) {

        return societeConverter.toVo(societeService.findbyscraping(ice));
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @PostMapping("/findByUser")
    public List<SocieteVo> findByUtilisateur(@RequestBody UtilisateurVo utilisateurVo) {
        societeConverter.setAdherant(true);
        societeConverter.getAdherantConverter().setSociete(false);
        Utilisateur utilisateur = utilisateurConverter.toItem(utilisateurVo);
        return societeConverter.toVo(societeService.findByUtilisateurId(utilisateur.getId()));
    }

}
