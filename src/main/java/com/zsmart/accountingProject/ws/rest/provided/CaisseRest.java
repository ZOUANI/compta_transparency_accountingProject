package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.Caisse;
import com.zsmart.accountingProject.service.facade.CaisseService;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.converter.CaisseConverter;
import com.zsmart.accountingProject.ws.rest.vo.CaisseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/accountingProject/Caisse")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CaisseRest {

    @Autowired
    private CaisseService caisseService;

    @Autowired
    private CaisseConverter caisseConverter;

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/")
    public CaisseVo save(@RequestBody CaisseVo caisseVo) {
        Caisse caisse = caisseConverter.toItem(caisseVo);
        return caisseConverter.toVo(caisseService.save(caisse));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        caisseService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public List<CaisseVo> findAll() {
        return caisseConverter.toVo(caisseService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @GetMapping("/findByAdherent/{id}")
    public List<CaisseVo> findbyAdherentId(@PathVariable Long id) {
        caisseConverter.setAdherent(true);
        return caisseConverter.toVo(caisseService.findCaissesByAdherantId(id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @DeleteMapping("/delete/adherent/{adherentId}/id/{id}")
    public void deleteById(@PathVariable Long adherentId, @PathVariable Long id) {
        caisseService.deleteByAdherantIdAndId(adherentId, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/deleteAll/")
    public void deleteAllByAdherentId(@RequestBody List<CaisseVo> caisseVos) {
        for (CaisseVo caisseVo : caisseVos
        ) {
            if (caisseVo.getId() != null)
                caisseService.deleteByAdherantIdAndId(NumberUtil.toLong(caisseVo.getAdherantVo().getId()), NumberUtil.toLong(caisseVo.getId()));
        }

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @GetMapping("/adherent/{adherentId}/id/{id}")
    public CaisseVo findByAdherentAndId(@PathVariable Long adherentId, @PathVariable Long id) {
        try {
            return caisseConverter.toVo(caisseService.findByAdherantIdAndId(adherentId, id));
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    @PreAuthorize("hasRole('ADHERENT') or hasRole('ADMIN')")
    @PostMapping("/findByCriteria")
    public List<CaisseVo> findByCriteria(@RequestBody CaisseVo caisseVo) {
        caisseConverter.setAdherent(true);
        Caisse caisse = caisseConverter.toItem(caisseVo);
        return caisseConverter.toVo(caisseService.findByCriteria(caisse.getLibelle(), caisse.getCode(), caisse.getAdherant().getId(), null, null, null, null));
    }

    public CaisseConverter getCaisseConverter() {
        return caisseConverter;
    }

    public void setCaisseConverter(CaisseConverter caisseConverter) {
        this.caisseConverter = caisseConverter;
    }

    public CaisseService getCaisseService() {
        return caisseService;
    }

    public void setCaisseService(CaisseService caisseService) {
        this.caisseService = caisseService;
    }

}