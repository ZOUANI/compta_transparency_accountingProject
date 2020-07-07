package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Comptable;

import java.util.List;

public interface ComptableService {
    public Comptable findById(Long id);

    public Comptable updateAdherents(Comptable comptable);

    public List<Comptable> findByCriteria(String nom, String prenom, String email);

    public int remove(Adherant adherant);
}
