package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Comptable;
import com.zsmart.accountingProject.bean.CompteComptable;
import com.zsmart.accountingProject.bean.CpcCompteComptable;
import com.zsmart.accountingProject.bean.Utilisateur;
import jdk.jshell.execution.Util;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur,Long> {
    public Utilisateur findByEmail(String email);
    public Utilisateur findByEmailAndMdp(String email, String mdp);
    public Utilisateur findByComptable(Comptable comptable);

}
