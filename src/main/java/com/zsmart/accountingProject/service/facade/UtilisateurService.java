package com.zsmart.accountingProject.service.facade;


import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Comptable;
import com.zsmart.accountingProject.bean.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    public Utilisateur save(Utilisateur utilisateur);
    public Utilisateur findByEmail(Utilisateur utilisateur);
    public int  delete(Utilisateur utilisateur);
    public Utilisateur login(Utilisateur utilisateur);
    public Utilisateur  findByComptable(Comptable comptable);
}
