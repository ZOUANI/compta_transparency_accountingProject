package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.EtatFacture;
import com.zsmart.accountingProject.service.facade.EtatFactureService;
import com.zsmart.accountingProject.ws.rest.converter.EtatFactureConverter;
import com.zsmart.accountingProject.ws.rest.vo.EtatFactureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/EtatFacture")
@CrossOrigin(origins = {"http://localhost:4200"})
public class EtatFactureRest {

    @Autowired
    private EtatFactureService etatFactureService;

    @Autowired
    private EtatFactureConverter etatFactureConverter;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public EtatFactureVo save(@RequestBody EtatFactureVo etatFactureVo) {
        EtatFacture etatFacture = etatFactureConverter.toItem(etatFactureVo);
        return etatFactureConverter.toVo(etatFactureService.save(etatFacture));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        etatFactureService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    @GetMapping("/")
    public List<EtatFactureVo> findAll() {
        return etatFactureConverter.toVo(etatFactureService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')  or hasRole('COMPTABLE')")
    @GetMapping("/findByLibelle/{libelle}")
    public EtatFactureVo findEtatFactureByLibelleLike(@PathVariable String libelle) {
        return etatFactureConverter.toVo(etatFactureService.findEtatFactureByLibelleLike(libelle));
    }

    public EtatFactureConverter getEtatFactureConverter() {
        return etatFactureConverter;
    }

    public void setEtatFactureConverter(EtatFactureConverter etatFactureConverter) {
        this.etatFactureConverter = etatFactureConverter;
    }

    public EtatFactureService getEtatFactureService() {
        return etatFactureService;
    }

    public void setEtatFactureService(EtatFactureService etatFactureService) {
        this.etatFactureService = etatFactureService;
    }

}