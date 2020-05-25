package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.DeclarationTva;
import com.zsmart.accountingProject.dao.DeclarationTvaDao;

import java.util.List;

public interface DeclarationTvaService {
    public DeclarationTva findById(Long id);
    public DeclarationTva save(DeclarationTva declarationTva);
    public List<DeclarationTva> findAll();



}
