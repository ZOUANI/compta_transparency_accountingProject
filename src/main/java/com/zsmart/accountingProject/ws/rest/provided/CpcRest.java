package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.Cpc;
import com.zsmart.accountingProject.service.facade.CpcService;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.converter.CpcConverter;
import com.zsmart.accountingProject.ws.rest.vo.CpcVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/accountingProject/Cpc")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CpcRest {

    @Autowired
    private CpcService cpcService;

    @Autowired
    private CpcConverter cpcConverter;

    @PreAuthorize("hasRole('ADMIN')  or hasRole('COMPTABLE')")
    @PostMapping("/generateCpc/")
    public CpcVo findCpcClasseComptable(@RequestBody CpcVo cpcVo) {
        cpcConverter.getSocieteConverter().setFacture(false);
        cpcConverter.setCpcSousClasses(true);
        cpcConverter.getCpcSousClasseConverter().setCpcCompteComptables(true);
        cpcConverter.getCpcSousClasseConverter().setSousClasseComptable(true);
        cpcConverter.getCpcSousClasseConverter().getCpcCompteComptableConverter().setCompteComptable(true);
        Cpc cpc = cpcConverter.toItem(cpcVo);
        return cpcConverter.toVo(cpcService.findCpcClasseComptable(cpc.getDateDebut(), cpc.getDateFin(), cpc.getSociete().getId()));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE') or hasRole('ADHERENT')")
    @PostMapping("/excel/")
    public Resource excel(@RequestBody CpcVo cpcVo) {
        cpcConverter.setCpcSousClasses(true);
        cpcConverter.setAdherant(true);

        cpcConverter.getCpcSousClasseConverter().setSousClasseComptable(true);
        cpcConverter.getCpcSousClasseConverter().setCpcCompteComptables(true);
        cpcConverter.getCpcSousClasseConverter().getCpcCompteComptableConverter().setCompteComptable(true);

        Cpc cpc = cpcConverter.toItem(cpcVo);
        return cpcService.getExcel(cpc);

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE')")
    @PostMapping("/")
    public CpcVo save(@RequestBody CpcVo cpcVo) {
        cpcConverter.setCpcSousClasses(true);
        cpcConverter.setAdherant(true);

        cpcConverter.getCpcSousClasseConverter().setSousClasseComptable(true);
        cpcConverter.getCpcSousClasseConverter().setCpcCompteComptables(true);
        cpcConverter.getCpcSousClasseConverter().getCpcCompteComptableConverter().setCompteComptable(true);

        Cpc cpc = cpcConverter.toItem(cpcVo);
        return cpcConverter.toVo(cpcService.saveWithCpcSousClasses(cpc));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE')")
    @DeleteMapping("/delete/adherent/{adherentId}/id/{id}")
    public void deleteById(@PathVariable Long adherentId, @PathVariable Long id) {
        cpcService.deleteByAdherantIdAndId(adherentId, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE')")
    @PostMapping("/deleteAll/")
    public void deleteAllByAdherentId(@RequestBody List<CpcVo> cpcVos) {
        for (CpcVo cpcVo : cpcVos
        ) {
            if (cpcVo.getId() != null)
                cpcService.deleteByAdherantIdAndId(NumberUtil.toLong(cpcVo.getAdherantVo().getId()), NumberUtil.toLong(cpcVo.getId()));
        }

    }

    @PreAuthorize("hasRole('ADHERENT') or hasRole('ADMIN') or hasRole('COMPTABLE')")
    @PostMapping("/findByCriteria")
    public List<CpcVo> findByCriteria(@RequestBody CpcVo cpcVo) {
        cpcConverter.setAdherant(true);
        Cpc cpc = cpcConverter.toItem(cpcVo);
        return cpcConverter.toVo(cpcService.findByCriteria(cpc.getSociete().getId(), cpc.getAdherant().getId(), null, null,
                DateUtil.parse(cpcVo.getDateDebutMin()), DateUtil.parse(cpcVo.getDateDebutMax()), DateUtil.parse(cpcVo.getDateFinMin()), DateUtil.parse(cpcVo.getDateFinMax()), null, null, null, null, null, null));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE') or hasRole('ADHERENT')")
    @GetMapping("/adherent/{adherentId}/id/{id}")
    public CpcVo findByAdherentAndId(@PathVariable Long adherentId, @PathVariable Long id) {
        try {
            cpcConverter.setCpcSousClasses(true);
            cpcConverter.setAdherant(true);
            cpcConverter.getCpcSousClasseConverter().setSousClasseComptable(true);
            cpcConverter.getCpcSousClasseConverter().setCpcCompteComptables(true);
            cpcConverter.getCpcSousClasseConverter().getCpcCompteComptableConverter().setCompteComptable(true);
            return cpcConverter.toVo(cpcService.findCpcByAdherantIdAndId(adherentId, id));
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cpcService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/")
    public void delete(@RequestBody Cpc cpc) {
        cpcService.delete(cpc);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public List<CpcVo> findAll() {
        return cpcConverter.toVo(cpcService.findAll());
    }

    public CpcConverter getCpcConverter() {
        return cpcConverter;
    }

    public void setCpcConverter(CpcConverter cpcConverter) {
        this.cpcConverter = cpcConverter;
    }

    public CpcService getCpcService() {
        return cpcService;
    }

    public void setCpcService(CpcService cpcService) {
        this.cpcService = cpcService;
    }

}