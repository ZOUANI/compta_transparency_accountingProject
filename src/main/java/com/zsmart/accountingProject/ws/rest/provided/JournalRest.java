package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.Journal;
import com.zsmart.accountingProject.service.facade.JournalService;
import com.zsmart.accountingProject.ws.rest.converter.JournalConverter;
import com.zsmart.accountingProject.ws.rest.converter.OperationComptableConverter;
import com.zsmart.accountingProject.ws.rest.converter.OperationComptableGroupeConverter;
import com.zsmart.accountingProject.ws.rest.vo.JournalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
@RestController
@RequestMapping("/accountingProject/Journal")
@CrossOrigin(origins = {"http://localhost:4200"})
public class JournalRest {
    @Autowired
    private JournalConverter journalConverter;
    @Autowired
    private JournalService journalService;
    @Autowired
    private OperationComptableConverter operationComptableConverter;
    @Autowired
    private OperationComptableGroupeConverter operationComptableGroupeConverter;

    @PostMapping("/")
    public JournalVo save(@RequestBody JournalVo journalVo) {
        Journal journal = journalConverter.toItem(journalVo);
        return journalConverter.toVo(journalService.save(journal));
    }
    @PostMapping("/generatejournal/")
    public JournalVo generateJournal(@RequestPart(value = "datedebut", required = true) Date datedebut,
                                     @RequestPart(value = "datefin", required = true) Date datefin
 ) {
        operationComptableConverter.setFacture(true);
        operationComptableConverter.setCompteBanquaire(true);
        operationComptableConverter.setTypeOperationComptable(true);
        operationComptableConverter.setCaisse(true);
        operationComptableGroupeConverter.setOperationComptables(true);
        journalConverter.setOperationComptableGroupe(true);
        Journal journal = journalService.generatejournal(datedebut,datefin);
        return journalConverter.toVo(journal);
    }

}
