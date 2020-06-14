package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.Journal;

import java.util.Date;
import java.util.List;

public interface JournalService {
    public Journal save(Journal journal);
    public List<Journal> findall();
    public Journal generatejournal(Date datedebut, Date datefin );

}
