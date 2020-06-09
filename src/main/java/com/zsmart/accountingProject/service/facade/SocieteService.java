package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.Facture;
import com.zsmart.accountingProject.bean.Societe;

import java.io.IOException;
import java.util.List;

public interface SocieteService {
    public List<Societe> findAll();
    public Societe findById(Long id);
    public Societe save(Societe societe);
    public void deleteById(Long id);
    public Societe findbyscraping(String ice);

}
