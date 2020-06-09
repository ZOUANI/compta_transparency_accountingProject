package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Comptable;
import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.bean.Utilisateur;
import com.zsmart.accountingProject.dao.UtilisateurDao;
import com.zsmart.accountingProject.service.facade.UtilisateurService;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    private UtilisateurDao utilisateurDao;
    @Autowired
    private UtilisateurService utilisateurService;


    @Override
    public Utilisateur save(Utilisateur utilisateur) {

            Utilisateur res =utilisateurService.findByEmail(utilisateur);
            if (res==null){
                utilisateurDao.save(utilisateur);
                return utilisateur;
            }   else{
                return res;
            }
    }



    @Override
    public Utilisateur findByEmail(Utilisateur utilisateur) {
        return utilisateurDao.findByEmail(utilisateur.getEmail());
    }

    @Override
    public Utilisateur findByComptable(Comptable comptable) {
        return utilisateurDao.findByComptable(comptable);
    }

    @Override
    public int delete(Utilisateur utilisateur) {
if(utilisateur==null){
    return -1;

}else{
    utilisateurDao.delete(utilisateur);
    return 1;
}
    }

    @Override
    public Utilisateur login(Utilisateur utilisateur) {
   return utilisateurDao.findByEmailAndMdp(utilisateur.getEmail(),utilisateur.getMdp());
    }
}
