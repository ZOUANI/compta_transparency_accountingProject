package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.DeclarationTva;
import com.zsmart.accountingProject.dao.DeclarationTvaDao;
import com.zsmart.accountingProject.service.facade.DeclarationTvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeclarationTvaServiceImpl implements DeclarationTvaService {
    @Autowired
    private DeclarationTvaDao declarationTvaDao;


    @Override
    public DeclarationTva findById(Long id) {
        return null;
    }

    @Override
    public DeclarationTva save(DeclarationTva declarationTva) {
    if (declarationTva==null) return null;
        else
    {
        declarationTvaDao.save(declarationTva);
    return declarationTva;}
    }

    @Override
    public List<DeclarationTva> findAll() {
        return declarationTvaDao.findAll();
    }
}
