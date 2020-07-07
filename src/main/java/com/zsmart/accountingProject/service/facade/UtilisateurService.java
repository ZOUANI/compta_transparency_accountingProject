package com.zsmart.accountingProject.service.facade;


import com.zsmart.accountingProject.bean.Utilisateur;
import com.zsmart.accountingProject.ws.rest.vo.AuthResponse;
import com.zsmart.accountingProject.ws.rest.vo.SignupRequest;

public interface UtilisateurService {
    public Utilisateur save(Utilisateur utilisateur);

    public Utilisateur findById(Long id);

    public int delete(Utilisateur utilisateur);

    public int saveWithRoles(Utilisateur user);

    public AuthResponse authentificate(String login, String pass);

    public int signUp(SignupRequest user);

    public int adminsignUp(SignupRequest user);
}
