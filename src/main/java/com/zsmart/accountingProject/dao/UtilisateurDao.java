package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {
    public Optional<Utilisateur> findByEmail(String email);

}
