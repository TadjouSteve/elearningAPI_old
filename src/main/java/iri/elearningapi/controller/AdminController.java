package iri.elearningapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iri.elearningapi.model.mediaModel.Article;
import iri.elearningapi.model.mediaModel.Rubrique;
import iri.elearningapi.model.userModel.Etudiant;
import iri.elearningapi.model.userModel.Professeur;
import iri.elearningapi.service.AdminService;
import iri.elearningapi.service.EtudiantService;
import iri.elearningapi.service.MediaService;
import iri.elearningapi.service.ProfesseurService;
import iri.elearningapi.utils.URLElearning;
import iri.elearningapi.utils.form.formInt.FormLink;
import iri.elearningapi.utils.form.formOut.FormViewEtudiant;
import iri.elearningapi.utils.form.formOut.FormViewProfesseur;
import iri.elearningapi.utils.form.formOut.UserAdminDashboard;

@RestController
@CrossOrigin
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ProfesseurService professeurService;
	
	@Autowired
	private EtudiantService etudiantService;
	
	@Autowired
	private MediaService mediaService;
	
	@GetMapping("/admin/dashboard")
	public UserAdminDashboard getAdminDashboard() {
		return adminService.getAdminDashboard();
	}
	
	@GetMapping("/admin/etudiants/{pageNumber}")
	public Page<Etudiant> getAllEtudiant(@PathVariable("pageNumber") int pageNumber){
		return etudiantService.getListEtudiants(null, pageNumber);
	}
	
	@GetMapping("/admin/etudiant/{matricule}")
	public FormViewEtudiant getFormViewEtudiantByAdmin(@PathVariable("matricule")String matriculeEtudiant) {
		return etudiantService.getFormViewEtudiantForAdmin(matriculeEtudiant, null);
	}
	
	@GetMapping("/admin/professeurs/{pageNumber}")
	public Page<Professeur> getAllProfesseur(@PathVariable("pageNumber") int pageNumber){
		return professeurService.getListProfesseurs(null, pageNumber);
	}
	
	@GetMapping("/admin/professeur/{matricule}")
	public FormViewProfesseur getFormProfesseur(@PathVariable("matricule")String matriculeProf) {
		return professeurService.getFormViewProfesseur(matriculeProf);
	}
	
	@PostMapping("/admin/professeur")
	public Professeur addProfesseur(@RequestBody Professeur professeur) {
		return professeurService.createProfesseur(professeur);
	}
	
	@PostMapping("/admin/linkprofesseurtomodule/{matricule}")
	public boolean linkProfesseurToMatricule(@PathVariable("matricule")String matriculeProf, @RequestBody List<FormLink> formLinks) {
		professeurService.linkProfesseurToModule(matriculeProf, formLinks);
		System.out.println("linked module to prof is Ok");
		return true;
	}
	
	@PostMapping("/admin/media/rubrique")
	public URLElearning creaRubrique(@RequestBody Rubrique rubrique) {
		Rubrique rubrique2= mediaService.createRubrique(rubrique);
		return new URLElearning("/rubrique/"+rubrique2.getId());
	}
	
	@PostMapping("/admin/media/article/{idRubrique}")
	public URLElearning creaRubrique(@PathVariable("idRubrique") int idRubrique,@RequestBody Article article) {
		Article article2=mediaService.createArticle(article, idRubrique);
		return  new URLElearning("/article/"+article2.getId());
	}
	
}
