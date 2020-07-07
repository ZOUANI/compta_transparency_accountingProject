package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.service.facade.AdherantService;
import com.zsmart.accountingProject.ws.rest.converter.AdherantConverter;
import com.zsmart.accountingProject.ws.rest.vo.AdherantVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/Adherant")
@CrossOrigin(origins = "http://localhost:4200")

public class AdherantRest {

    @Autowired
    private AdherantService adherantService;


    @Autowired
    private AdherantConverter adherantConverter;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/findNonComptable")
    public List<AdherantVo> findNonComptable() {
        adherantConverter.getSocieteConverter().setAdherant(false);
        return adherantConverter.toVo(adherantService.findByComptable(null));
    }

}
