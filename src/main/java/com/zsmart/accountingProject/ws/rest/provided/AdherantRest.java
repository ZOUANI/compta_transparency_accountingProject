package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.service.facade.AdherantService;
import com.zsmart.accountingProject.ws.rest.converter.AdherantConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountingProject/Adherant")
@CrossOrigin(origins = "http://localhost:4200")

public class AdherantRest {

    @Autowired
    private AdherantService adherantService;


    @Autowired
    private AdherantConverter adherantConverter;


}
