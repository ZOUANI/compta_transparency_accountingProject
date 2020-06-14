package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.Facture;
import com.zsmart.accountingProject.bean.Journal;
import com.zsmart.accountingProject.service.facade.JournalService;
import com.zsmart.accountingProject.ws.rest.converter.JournalConverter;
import com.zsmart.accountingProject.ws.rest.vo.FactureVo;
import com.zsmart.accountingProject.ws.rest.vo.JournalVo;
import com.zsmart.accountingProject.ws.rest.vo.SocieteVo;
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

    @PostMapping("/")
    public JournalVo save(@RequestBody JournalVo journalVo) {
        Journal journal = journalConverter.toItem(journalVo);
        return journalConverter.toVo(journalService.save(journal));
    }
    @PostMapping("/generatejournal")
    public JournalVo generateJournal(@RequestPart(value = "datedebut", required = true) Date datedebut,
                                     @RequestPart(value = "datefin", required = true) Date datefin
                                     ) {

        return journalConverter.toVo(journalService.generatejournal(datedebut,datefin));
    }

}
