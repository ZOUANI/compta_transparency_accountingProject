package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Comptable;
import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.bean.Utilisateur;
import com.zsmart.accountingProject.dao.SocieteDao;
import com.zsmart.accountingProject.service.facade.SocieteService;
import com.zsmart.accountingProject.service.facade.UtilisateurService;
import com.zsmart.accountingProject.service.util.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocieteServiceImpl implements SocieteService {
    @Autowired
    private SocieteDao societeDao;
    @Autowired
    private UtilisateurService utilisateurService;
    private final Path root = Paths.get("src\\main\\resources\\uploads");

    @Override
    public List<Societe> findAll() {
        return societeDao.findAll();
    }

    @Override
    public Societe findById(Long id) {
        if (id != null) return societeDao.getOne(id);
        else return null;
    }

    @Override
    public Societe save(Societe societe) {

        if (societe==null){
            return null;
        }else{
            return societeDao.save(societe);
        }
    }

    @Override
    public void deleteById(Long id) {
        societeDao.deleteById(id);
    }

    @Override
    public Societe findbyscraping(String ice) {
        if (ice!=null) {
            Document dc = null
                    ;
            try {
                dc = Jsoup.connect("https://simpl-recherche.tax.gov.ma/RechercheEntreprise/result")
                        .data("param['type']", "ICE")
                        .data("param['criteria']",ice)

                        .data("param['codeRC']", "552")
                        .data("param['btnType']", "Rechercher")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:77.0) Gecko/20100101 Firefox/77.0")
                        .post();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Societe societe = new Societe();
            ArrayList<String> list = new ArrayList();
            ArrayList<String> list1 = new ArrayList();

            for (Element row :dc.select("div.col-md-12.margin-bottom-30 div.panel.panel-default div.panel-body div.row.mrw div.col-md-8")){
                String rowing= row.select("textarea.form-control").text();
                String rowing1= row.select("input.form-control").val();

                list.add(rowing);
                list1.add(rowing1);
                }
            societe.setRaisonSocial(list.get(0));
            societe.setIce(list1.get(2));
            societe.setIdentifiantFiscal(list1.get(3));
            societe.setJuridiction(list1.get(4));
            societe.setRegistreComerce(list1.get(5));
            societe.setAdresse(list.get(6));


                return societe;
        }
      else   return null;
    }

    @Override
    public void uploadfiles(MultipartFile contratBail,
                            MultipartFile certificatnegatif,
                            MultipartFile registreComercialImage,
                            MultipartFile patente,
                            MultipartFile statue,
                            MultipartFile releverBanquaire,
                            MultipartFile publicationCreationBO,
                            Societe societe)
    {
        if (societe!=null){
            try {
                if (societe.getId()!=null){
                    Files.delete(this.root.resolve(societe.getIce() + "contratBail" + societe.getJuridiction()));
                    Files.delete(this.root.resolve(societe.getIce() + "certificatnegatif" + societe.getJuridiction()));
                    Files.delete(this.root.resolve(societe.getIce() + "registreComercialImage" + societe.getJuridiction()));
                    Files.delete(this.root.resolve(societe.getIce() + "patente" + societe.getJuridiction()));
                    Files.delete(this.root.resolve(societe.getIce() + "statue" + societe.getJuridiction()));
                    Files.delete(this.root.resolve(societe.getIce() + "releverBanquaire" + societe.getJuridiction()));
                    Files.delete(this.root.resolve(societe.getIce() + "publicationCreationBO" + societe.getJuridiction()));
                }

                if (contratBail!=null){
                    Files.copy(contratBail.getInputStream(), this.root.resolve(societe.getIce() + "contratBail" + societe.getJuridiction()));
                    societe.setContratBail(societe.getIce() + "contratBail" + societe.getJuridiction());
                }

                if (certificatnegatif!=null){
                    Files.copy(certificatnegatif.getInputStream(), this.root.resolve(societe.getIce() + "certificatnegatif" + societe.getJuridiction()));
                    societe.setCertificatNegatif(societe.getIce() + "certificatnegatif" + societe.getJuridiction());
                }
                if (registreComercialImage!=null){
                    Files.copy(registreComercialImage.getInputStream(), this.root.resolve(societe.getIce() + "registreComercialImage" + societe.getJuridiction()));
                    societe.setRegistreCommerceimage(societe.getIce() + "registreComercialImage" + societe.getJuridiction());
                }
                if (patente!=null){
                    Files.copy(patente.getInputStream(), this.root.resolve(societe.getIce() + "patente" + societe.getJuridiction()));
                    societe.setPatente(societe.getIce() + "patente" + societe.getJuridiction());
                }
                if (statue!=null){
                    Files.copy(statue.getInputStream(), this.root.resolve(societe.getIce() + "statue" + societe.getJuridiction()));
                    societe.setStatuet(societe.getIce() + "statue" + societe.getJuridiction());
                }
                if (releverBanquaire != null) {
                    Files.copy(releverBanquaire.getInputStream(), this.root.resolve(societe.getIce() + "releverBanquaire" + societe.getJuridiction()));
                    societe.setReleverBanquaire(societe.getIce() + "releverBanquaire" + societe.getJuridiction());
                }
                if (publicationCreationBO != null) {
                    Files.copy(publicationCreationBO.getInputStream(), this.root.resolve(societe.getIce() + "publicationCreationBO" + societe.getJuridiction()));
                    societe.setPublicationCreationBo(societe.getIce() + "publicationCreationBO" + societe.getJuridiction());
                }
            } catch (Exception e) {
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Societe> findByUtilisateurId(Long id) {

        Utilisateur user = utilisateurService.findById(id);

        if (Util.instanceOf(user, Adherant.class)) {
            return societeDao.findByAdherantId(user.getId());
        } else if (Util.instanceOf(user, Comptable.class)) {

            return societeDao.findByComptableId(id);
        } else {

            return null;
        }
    }

}
