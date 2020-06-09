package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Comptable;

import java.util.List;

public interface AdherantService {
    public List<Adherant> findByComptable(Comptable comptable);

}
