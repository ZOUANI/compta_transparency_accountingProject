package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.dao.SocieteDao;
import com.zsmart.accountingProject.service.facade.SocieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocieteServiceImpl implements SocieteService {
    @Autowired
    private SocieteDao societeDao;
    @Override
    public List<Societe> findAll() {
        return societeDao.findAll();
    }

    @Override
    public Societe findById(Long id) {
        return societeDao.getOne(id);
    }

    @Override
    public Societe save(Societe societe) {

        if (societe==null){
            return null;
        }else{
            return societeDao.save(societe);
        }
    }

    @Override
    public void deleteById(Long id) {
        societeDao.deleteById(id);
    }
}
