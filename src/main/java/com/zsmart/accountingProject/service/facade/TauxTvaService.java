package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.TauxTva;

import java.util.List;

public interface TauxTvaService {
    public TauxTva save(TauxTva tauxTva);

    public List<TauxTva> findAll();

    public TauxTva findById(Long id);

    public int delete(TauxTva tauxTva);

    public void deleteById(Long id);

    public List<TauxTva> findByAdherantIdAndId(Long adherentId, Long id);

    public List<TauxTva> findByAdherantId(Long id);
}
