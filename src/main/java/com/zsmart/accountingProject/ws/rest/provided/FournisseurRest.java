package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Fournisseur;
import com.zsmart.accountingProject.service.facade.FournisseurService;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.converter.AdherantConverter;
import com.zsmart.accountingProject.ws.rest.converter.FournisseurConverter;
import com.zsmart.accountingProject.ws.rest.vo.AdherantVo;
import com.zsmart.accountingProject.ws.rest.vo.FournisseurVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/accountingProject/Fournisseur")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FournisseurRest {

    @Autowired
    private FournisseurService fournisseurService;

    @Autowired
    private FournisseurConverter fournisseurConverter;
    @Autowired
    private AdherantConverter adherantConverter;


    @PreAuthorize("hasRole('ADHERENT') or hasRole('ADMIN')")
    @PostMapping("/save")
    public FournisseurVo save(@RequestBody FournisseurVo fournisseurVo) {
        fournisseurConverter.setAdherant(true);
        Fournisseur fournisseur = fournisseurConverter.toItem(fournisseurVo);
        return fournisseurConverter.toVo(fournisseurService.save(fournisseur));
    }

    @PreAuthorize("hasRole('ADHERENT') or hasRole('ADMIN')")
    @PostMapping("/findByCriteria")
    public List<FournisseurVo> findByCriteria(@RequestBody FournisseurVo fournisseurVo) {
        fournisseurConverter.setAdherant(true);
        Fournisseur fournisseur = fournisseurConverter.toItem(fournisseurVo);
        return fournisseurConverter.toVo(fournisseurService.findByCriteria(fournisseur.getIce(), fournisseur.getIdentifiantFiscale(), fournisseur.getRc(), fournisseur.getLibelle(), fournisseur.getCode(), fournisseur.getAdherant().getId()));
    }

    @PreAuthorize("hasRole('ADHERENT') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        fournisseurService.deleteById(id);
    }

    @GetMapping("/aa")
    @PreAuthorize("hasRole('ADMIN')")
    public List<FournisseurVo> findAll() {
        fournisseurConverter.setAdherant(false);
        return fournisseurConverter.toVo(fournisseurService.findAll());
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    public List<FournisseurVo> findByAdherent(@RequestBody AdherantVo adherantVo) {
        Adherant adherant = adherantConverter.toItem(adherantVo);
        return fournisseurConverter.toVo(fournisseurService.findByAdherent(adherant));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @DeleteMapping("/delete/adherent/{adherentId}/id/{id}")
    public void deleteById(@PathVariable Long adherentId, @PathVariable Long id) {
        fournisseurService.deleteByAdherantIdAndId(adherentId, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/deleteAll/")
    public void deleteAllByAdherentId(@RequestBody List<FournisseurVo> fournisseurVos) {
        for (FournisseurVo fournisseurVo : fournisseurVos
        ) {
            if (fournisseurVo.getId() != null)
                fournisseurService.deleteByAdherantIdAndId(NumberUtil.toLong(fournisseurVo.getAdherantVo().getId()), NumberUtil.toLong(fournisseurVo.getId()));
        }

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @GetMapping("/adherent/{adherentId}/id/{id}")
    public FournisseurVo findByAdherentAndId(@PathVariable Long adherentId, @PathVariable Long id) {
        try {
            return fournisseurConverter.toVo(fournisseurService.findByAdherantIdAndId(adherentId, id));
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    public FournisseurConverter getFournisseurConverter() {
        return fournisseurConverter;
    }

    public void setFournisseurConverter(FournisseurConverter fournisseurConverter) {
        this.fournisseurConverter = fournisseurConverter;
    }

    public FournisseurService getFournisseurService() {
        return fournisseurService;
    }

    public void setFournisseurService(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

}