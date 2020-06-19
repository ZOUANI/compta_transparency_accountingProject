package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Journal;
import com.zsmart.accountingProject.bean.OperationComptable;
import com.zsmart.accountingProject.bean.OperationComptableGroupe;
import com.zsmart.accountingProject.dao.JournalDao;
import com.zsmart.accountingProject.service.facade.JournalService;
import com.zsmart.accountingProject.service.facade.OperationComptableGroupeService;
import com.zsmart.accountingProject.service.facade.OperationComptableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Element;
import java.util.Date;
import java.util.List;
@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    JournalDao journalDao;
    @Autowired
    OperationComptableGroupeService operationComptableGroupeService;
    @Override
    public Journal save(Journal journal) {
        if (journal!=null) {
            journalDao.save(journal);
            return journal;

        }
        else
            return null;
    }

    @Override
    public List<Journal> findall() {
        return journalDao.findAll();
    }

    @Override
    public Journal generatejournal(Date datedebut, Date datefin) {
        Journal journal=new Journal();
        journal.setDatedebut(datedebut);
        journal.setDatefin(datefin);

        List<OperationComptableGroupe> operationComptableGroupes= operationComptableGroupeService.findByCriteria(null,null,null, null, datedebut, datefin);
        journal.setOperationComptablesGroupe(operationComptableGroupes);
        journal.setLibele("Journal comptable du " + journal.getDatedebut().toString() + " au " + journal.getDatefin() +".");
        return journal;
    }
}
