package com.zsmart.accountingProject.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Banque implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	private String libelle;
	private String code;
	@OneToMany(mappedBy = "banque")
	private List<CompteBanquaire> compteBanquaires;
	
	
	
	public List<CompteBanquaire> getCompteBanquaires() {
		return compteBanquaires;
	}
	public void setCompteBanquaires(List<CompteBanquaire> compteBanquaires) {
		this.compteBanquaires = compteBanquaires;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banque other = (Banque) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Banque [id=" + id + ", libelle=" + libelle + ", code=" + code + ", compteBanquaires=" + compteBanquaires
				+ "]";
	}
	
	

}
