package appSpringMongo.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import appSpringMongo.Directeur.DirecteurModel;
import appSpringMongo.Directeur.DirecteurRepository;

@Controller
@RequestMapping("")
public class LoginController {
	
	@Autowired
    private DirecteurRepository directeurRepository;
/*	
    @RequestMapping("/loginJSP")
    public String showLoginPageJSP() {
        return "LoginInterface";   // Le nom du fichier JSP (LoginInterface.jsp)
        // return "NewFile";       // src/main/resources/templates/JSP/NewFile.jsp
          // http://localhost:6060/loginJSP
    } */
     
    @RequestMapping("/loginHTML")
    public String showLoginPageWithHTML() {
        return "Login/LoginInterfaceHTML";   // Le nom du fichier HTML (LoginInterfaceHTML.html)
          // http://localhost:6060/loginHTML
    }
    
    @RequestMapping("/interface-principale")
    public String showPagePrincipale() {
        return "Directeur/InterfacePrincipaleDirecteur";
          // http://localhost:6060/interface-principale
    }
    
    @PostMapping("/login")  // ce chemin existe dans la formulaire action = "/login"
    public String VerifierLoginPassword(@RequestParam String loginForm, @RequestParam String passwordForm, Model model) {
        DirecteurModel directeur = directeurRepository.findByLoginAndPassword(loginForm, passwordForm);
        if (directeur != null) {
        	 return "redirect:/interface-principale"; // http://localhost:6060/interface-principale;
             // return "Directeur/InterfacePrincipaleDirecteur";
        } else {
            model.addAttribute("errorMessage", "Erreur d'authentification...! Veuillez réessayer.");
            return "Login/LoginInterfaceHTML";
        }
    }
    
/*  @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> VerifierLoginPassword(@RequestParam String loginForm, @RequestParam String passwordForm) {
        DirecteurModel directeur = directeurRepository.findByLoginAndPassword(loginForm, passwordForm);
        if (directeur != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le login ou le mot de passe que vous avez saisi est incorrect. Veuillez réessayer.");
        }
    } */

    
    
}
