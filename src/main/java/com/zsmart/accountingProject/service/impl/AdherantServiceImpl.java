package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Comptable;
import com.zsmart.accountingProject.dao.AdherantDao;
import com.zsmart.accountingProject.service.facade.AdherantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdherantServiceImpl implements AdherantService {
    @Autowired
    AdherantDao adherantDao;
    @Override
    public List<Adherant> findByComptable(Comptable comptable) {
        return adherantDao.findByComptable(comptable);
    }

}
