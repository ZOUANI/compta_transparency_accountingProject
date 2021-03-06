package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.Societe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SocieteService {
    public List<Societe> findAll();
    public Societe findById(Long id);
    public Societe save(Societe societe);
    public void deleteById(Long id);
    public Societe findbyscraping(String ice);

    public void uploadfiles(MultipartFile contratBail,
                            MultipartFile certificatnegatif,
                            MultipartFile registreComercialImage,
                            MultipartFile patente,
                            MultipartFile statue,
                            MultipartFile releverBanquaire,
                            MultipartFile publicationCreationBO,
                            Societe societe
    );

    public List<Societe> findByUtilisateurId(Long id);

}
