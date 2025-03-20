package iset.master.spring.model;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import iset.master.spring.model.Categorie;
import jakarta.persistence.*;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Produit implements Serializable{
 private static final long serialVersionUID = 1L;
 @Id
 @GeneratedValue
 private Long id;

 @Column(length = 50)
 private String designation;

 private double prix;
 private int quantite;
 //@Column(nullable = true)

 //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
 private Date dateAchat;

 @ManyToOne
 private Categorie categorie;

 public Produit(String designation, double prix, int quantite, Date dateAchat,
Categorie categorie) {
  super();

  this.designation = designation;
  this.prix = prix;
  this.quantite = quantite;
  this.dateAchat = dateAchat;
  this.categorie = categorie;
 }

 public Categorie getCategorie() {
  return categorie;
 }

 public void setCategorie(Categorie categorie) {
  this.categorie = categorie;
 }

 public java.util.Date getDateAchat() {
  return dateAchat;
 }

 public void setDateAchat(java.util.Date dateAchat) {
  this.dateAchat = dateAchat;
 }

 public Produit(String designation, double prix, int quantite, Date dateAchat) {
  super();
  this.designation = designation;
  this.prix = prix;
  this.quantite = quantite;
  this.dateAchat = dateAchat;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public Produit(String designation, double prix, int quantite, Categorie categorie) {
  this.designation = designation;
  this.prix = prix;
  this.quantite = quantite;
  this.categorie = categorie;
 }

 public String getDesignation() {
  return designation;
 }

 public void setDesignation(String designation) {
  this.designation = designation;
 }

 public double getPrix() {
  return prix;
 }

 public void setPrix(double prix) {
  this.prix = prix;
 }

 public int getQuantite() {
  return quantite;
 }


 public void setQuantite(int quantite) {
  this.quantite = quantite;
 }

 public Produit() {
  super();
 }

 public Produit(String designation, double prix, int quantite) {
  super();
  this.designation = designation;
  this.prix = prix;
  this.quantite = quantite;
 }

 @Override
 public String toString() {
  return "Produit [id=" + id + ", designation=" + designation + ", prix=" +
prix + ", quantite=" + quantite
    + ", dateAchat=" + dateAchat + ", categorie=" + categorie +
"]";
 }

 public Produit(Long id, String designation, double prix, int quantite) {
  super();
  this.id = id;
  this.designation = designation;
  this.prix = prix;
  this.quantite = quantite;
 }

 public Produit(Long id, String designation, double prix, int quantite, Date dateAchat, Categorie categorie) {
  this.id = id;
  this.designation = designation;
  this.prix = prix;
  this.quantite = quantite;
  this.dateAchat = dateAchat;
  this.categorie = categorie;
 }

 public Produit(Long id, String designation, double prix, int quantite, Categorie categorie) {
  this.id = id;
  this.designation = designation;
  this.prix = prix;
  this.quantite = quantite;
  this.categorie = categorie;
 }

}