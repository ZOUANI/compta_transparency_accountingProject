package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.CompteBanquaire;
import com.zsmart.accountingProject.service.facade.CompteBanquaireService;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.converter.CompteBanquaireConverter;
import com.zsmart.accountingProject.ws.rest.vo.CompteBanquaireVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/accountingProject/CompteBanquaire")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CompteBanquaireRest {

    @Autowired
    private CompteBanquaireService compteBanquaireService;

    @Autowired
    private CompteBanquaireConverter compteBanquaireConverter;

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/")
    public CompteBanquaireVo save(@RequestBody CompteBanquaireVo compteBanquaireVo) {
        compteBanquaireConverter.setBanque(true);
        CompteBanquaire compteBanquaire = compteBanquaireConverter.toItem(compteBanquaireVo);
        return compteBanquaireConverter.toVo(compteBanquaireService.save(compteBanquaire));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        compteBanquaireService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public List<CompteBanquaireVo> findAll() {
        return compteBanquaireConverter.toVo(compteBanquaireService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @GetMapping("/findByAdherent/{id}")
    public List<CompteBanquaireVo> findbyAdherentId(@PathVariable Long id) {
        compteBanquaireConverter.setBanque(true);
        compteBanquaireConverter.setAdherant(true);
        return compteBanquaireConverter.toVo(compteBanquaireService.findCompteBanquairesByAdherantId(id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @DeleteMapping("/delete/adherent/{adherentId}/id/{id}")
    public void deleteById(@PathVariable Long adherentId, @PathVariable Long id) {
        compteBanquaireService.deleteByAdherantIdAndId(adherentId, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/deleteAll/")
    public void deleteAllByAdherentId(@RequestBody List<CompteBanquaireVo> compteBanquaireVos) {
        for (CompteBanquaireVo compteBanquaireVo : compteBanquaireVos
        ) {
            if (compteBanquaireVo.getId() != null)
                compteBanquaireService.deleteByAdherantIdAndId(NumberUtil.toLong(compteBanquaireVo.getAdherantVo().getId()), NumberUtil.toLong(compteBanquaireVo.getId()));
        }

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @GetMapping("/adherent/{adherentId}/id/{id}")
    public CompteBanquaireVo findByAdherentAndId(@PathVariable Long adherentId, @PathVariable Long id) {
        try {
            compteBanquaireConverter.setBanque(true);
            return compteBanquaireConverter.toVo(compteBanquaireService.findByAdherantIdAndId(adherentId, id));
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    @PreAuthorize("hasRole('ADHERENT') or hasRole('ADMIN')")
    @PostMapping("/findByCriteria")
    public List<CompteBanquaireVo> findByCriteria(@RequestBody CompteBanquaireVo compteBanquaireVo) {
        compteBanquaireConverter.setAdherant(true);
        compteBanquaireConverter.setBanque(true);

        CompteBanquaire compteBanquaire = compteBanquaireConverter.toItem(compteBanquaireVo);
        String banqueName = compteBanquaire.getBanque() == null ? null : compteBanquaire.getBanque().getLibelle();
        return compteBanquaireConverter.toVo(compteBanquaireService.findByCriteria(compteBanquaire.getLibelle(), compteBanquaire.getCode(), banqueName, compteBanquaire.getAdherant().getId(), null, null, null, null));
    }

    public CompteBanquaireConverter getCompteBanquaireConverter() {
        return compteBanquaireConverter;
    }

    public void setCompteBanquaireConverter(CompteBanquaireConverter compteBanquaireConverter) {
        this.compteBanquaireConverter = compteBanquaireConverter;
    }

    public CompteBanquaireService getCompteBanquaireService() {
        return compteBanquaireService;
    }

    public void setCompteBanquaireService(CompteBanquaireService compteBanquaireService) {
        this.compteBanquaireService = compteBanquaireService;
    }

}