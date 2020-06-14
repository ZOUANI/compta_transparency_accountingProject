package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface JournalDao extends JpaRepository<Journal,Long> {
    public Journal findByDatedebutAndDatefin (Date datedebut, Date datefin);
}
