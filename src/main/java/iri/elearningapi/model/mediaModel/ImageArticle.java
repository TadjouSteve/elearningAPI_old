package iri.elearningapi.model.mediaModel;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the image_article database table.
 * 
 */
@Entity
@Table(name="image_article")
@NamedQuery(name="ImageArticle.findAll", query="SELECT i FROM ImageArticle i")
public class ImageArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private byte[] image;

	@Lob
	private String nom;

	//bi-directional many-to-one association to Article
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_article")
	private Article article;

	public ImageArticle() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}