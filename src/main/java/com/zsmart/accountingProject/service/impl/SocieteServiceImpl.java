package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.dao.SocieteDao;
import com.zsmart.accountingProject.service.facade.SocieteService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocieteServiceImpl implements SocieteService {
    @Autowired
    private SocieteDao societeDao;
    @Override
    public List<Societe> findAll() {
        return societeDao.findAll();
    }

    @Override
    public Societe findById(Long id) {
        if (id!=null)return societeDao.getOne(id);
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
}
