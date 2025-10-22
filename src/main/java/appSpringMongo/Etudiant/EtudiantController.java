package appSpringMongo.Etudiant;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import appSpringMongo.Directeur.DirecteurModel;

@Controller
// @RestController
//@RequestMapping("")
public class EtudiantController {
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
    @RequestMapping("/ajouter-etudiant")
    public String showPageAjouterEtudiant() {
        return "Etudiant/AjouterEtudiant"; // le fichier HTML AjouterEtudiant.html
          // http://localhost:6060/ajouter-etudiant
    }
    
// --------------------------------------------------------------------------------------
 
// L'annotation '@ResponseBody' est nécessaire, sans elle, Spring Boot interprète 'http://localhost:6060/etudiants' comme un template (fichier HTML...) au lieu de le traiter comme du format JSON.
// Ou on peut au debut utiliser l'annotation '@RestController' mais pour les fichiers HTML ne fonctionne pas 
    
// Pour récupérer tous les etudiants
    @ResponseBody  // cette annotation est obligatoire
    @GetMapping("/etudiants")
    public List<EtudiantModel> getAllEtudiants() {
        return etudiantRepository.findAll();
        // http://localhost:6060/etudiants
    }
    
 // Pour récupérer chaque etudiant par cin
    @ResponseBody
    @GetMapping("/etudiant/{cinEtud}")
    public EtudiantModel getEtudiantById(@PathVariable String cinEtud) {
        // return etudiantRepository.findById(cin).orElse(null);
    	return etudiantRepository.findByCin(cinEtud);
    }
    
    @PostMapping(value = "/AjouterEtudiantController", consumes = "application/x-www-form-urlencoded")
    public void ajouterEtudiant(@ModelAttribute EtudiantModel etudiant, HttpServletResponse response) throws IOException { 	
    	// Vérifier si l'étudiant existe déjà par CIN
    	EtudiantModel etudiantExiste = etudiantRepository.findByCin(etudiant.getCin());
    	if (etudiantExiste != null) {
    		// Afficher un message d'erreur avec SweetAlert
            sendSweetAlert(response, "error", null, "Étudiant existe déjà avec CIN : " + etudiantExiste.getCin());
        } else {
    	  etudiantRepository.save(etudiant);
	    	// Afficher un message de succès avec SweetAlert
	          sendSweetAlert(response, "success", "ajouter", "Étudiant ajouté avec succès.");
        }
    }
    
    private void sendSweetAlert(HttpServletResponse response, String icon, String action, String message) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
        out.println("<link rel='stylesheet' href='https://unpkg.com/sweetalert/dist/sweetalert.css'>");
        out.println("</head><body>");
        out.println("<script>");
        out.println("swal('" + message + "', { icon: '" + icon + "'});");
        
        if(icon == "success") {
          if(action == "ajouter")
        	 out.println("setTimeout(function(){ location.replace('http://localhost:6060/interface-principale'); }, 3000);");  // Redirection après 3 secondes
          if(action == "modifier" || action == "supprimer")
         	 out.println("setTimeout(function(){ location.replace('http://localhost:6060/consulter-etudiant'); }, 3000);"); 
       // if(action == "supprimer")
      // 	 out.println("setTimeout(function(){ location.replace('http://localhost:6060/consulter-etudiant'); }, 3000);");
        } else if (icon.equals("error")) 
        	out.println("setTimeout(function(){ history.back(); }, 3000);"); // Redirection après 3 secondes
        
        out.println("</script>");
        out.println("</body></html>");
    }
    
 //------------------- Consulter Etudiant -------------------------------------------
    
    @RequestMapping("/consulter-etudiant")
    public String showPageConsulterEtudiant(Model model) {
    	List<EtudiantModel> etudiants = etudiantRepository.findAll();
    	model.addAttribute("myListeEtudiants", etudiants);
        return "Etudiant/ConsulterEtudiant"; 
          // http://localhost:6060/consulter-etudiant
    }
    
//--------------------- Modifier Etudiant ----------------------------------------
    
    @GetMapping("/modifier-etudiant/{cin}")
    public String showModifierEtudiantPage(@PathVariable String cin, Model model) {
        // Récupérez l'étudiant à modifier par son CIN et ajoutez-le au modèle
        EtudiantModel etudiant = etudiantRepository.findByCin(cin);
        model.addAttribute("etudiantByCIN", etudiant);
        return "Etudiant/ModifierEtudiant";  // Assurez-vous d'avoir une vue "ModifierEtudiant.html"
    }
    
    @PostMapping("/ModifierEtudiantController")
    public void modifierEtudiant(@ModelAttribute EtudiantModel etudiant, HttpServletResponse reponse) throws IOException {
        // Récupérez l'étudiant existant par son CIN
        EtudiantModel existingEtudiant = etudiantRepository.findByCin(etudiant.getCin());
        
        if (existingEtudiant != null) {
            // Si l'étudiant existe, mettez à jour ses propriétés
        	Update update = new Update();
        	update.set("nom", etudiant.getNom());
            update.set("prenom", etudiant.getPrenom());
            update.set("dateNaissance", etudiant.getDateNaissance());
            update.set("telephone", etudiant.getTelephone());
            update.set("email", etudiant.getEmail());
            
         // Créez un objet Query pour rechercher le document à mettre à jour
            Query query = new Query(Criteria.where("cin").is(etudiant.getCin()));   

         // Effectuez la mise à jour du document correspondant à la requête
            mongoTemplate.updateFirst(query, update, EtudiantModel.class);
                      
            sendSweetAlert(reponse, "success", "modifier", "Étudiant modifié avec succès.");
        } else {
            // Si l'étudiant n'existe pas, affichez un message d'erreur
        	sendSweetAlert(reponse, "error", null, "Étudiant non trouvé avec CIN : " + etudiant.getCin());
        }
    }
    
//------------------- Supprimer Etudiant ----------------------------------------
    
    @PostMapping("/supprimer-etudiant/{cinStudent}")
    public void supprimerEtudiant(@PathVariable String cinStudent, HttpServletResponse response) throws IOException {
        EtudiantModel etudiant = etudiantRepository.findByCin(cinStudent);
        if (etudiant != null) {
            etudiantRepository.deleteByCin(cinStudent);
            // Afficher un message de succès avec SweetAlert
            sendSweetAlert(response, "success", "supprimer", "Étudiant supprimé avec succès.");
        } else {
            // Afficher un message d'erreur avec SweetAlert
            sendSweetAlert(response, "error", null, "Étudiant introuvable avec CIN : " + cinStudent);
        }
    }

}
