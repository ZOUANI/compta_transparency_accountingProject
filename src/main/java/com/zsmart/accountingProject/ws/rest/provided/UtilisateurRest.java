package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.dao.FactureDao;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.ws.rest.converter.FactureConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/accountingProject/Utilisateur")
@CrossOrigin(origins = "http://localhost:4200")

public class UtilisateurRest {
    @Autowired
    private FactureDao factureDao;
    @Autowired
    private FactureConverter factureConverter;

    @GetMapping("/")
    public List<List<YearMonth>> findAll() {
        return DateUtil.splitDate(YearMonth.of(2015, 1), YearMonth.of(2015, 3), 12);
    }

}
