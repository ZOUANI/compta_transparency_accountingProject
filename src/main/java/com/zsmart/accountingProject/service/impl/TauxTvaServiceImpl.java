package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.TauxTva;
import com.zsmart.accountingProject.dao.TauxTvaDao;
import com.zsmart.accountingProject.service.facade.TauxTvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TauxTvaServiceImpl implements TauxTvaService {
    @Autowired
    private TauxTvaDao tauxTvaDao;


    @Override
    public TauxTva save(TauxTva tauxTva) {
        if (tauxTva == null) {
            return null;
        } else {
            tauxTvaDao.save(tauxTva);
            return tauxTva;
        }
    }

    @Override
    public List<TauxTva> findAll() {
        return tauxTvaDao.findAll();
    }

    @Override
    public TauxTva findById(Long id) {
        return tauxTvaDao.getOne(id);
    }

    @Override
    public int delete(TauxTva tauxTva) {
        if (tauxTva == null) {
            return -1;
        } else {
            tauxTvaDao.delete(tauxTva);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        tauxTvaDao.deleteById(id);
    }

    @Override
    public List<TauxTva> findByAdherantIdAndId(Long adherentId, Long id) {
        return tauxTvaDao.findByAdherantIdAndId(adherentId, id);
    }

    @Override
    public List<TauxTva> findByAdherantId(Long id) {
        return tauxTvaDao.findByAdherantId(id);
    }
}
