package appSpringMongo.Directeur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directeurs")
public class DirecteurController {
	
    @Autowired
    private DirecteurRepository directeurRepository;
 
// Pour creer un nouveau directeur dans la base de donnees de MongoDB
    //@PostMapping
    @PostMapping("")
    public String createDirecteur(@RequestBody DirecteurModel directeur) {
           directeurRepository.save(directeur);
        return "My name is khan !";
     // http://localhost:7070/directeurs
    }

// Endpoint pour récupérer tous les directeurs
    @GetMapping
    public List<DirecteurModel> getAllDirecteurs() {
        return directeurRepository.findAll();
           // http://localhost:7070/directeurs
    }

// Pour récupérer chaque directeur par id
    @GetMapping("/{id}")
    public DirecteurModel getDirecteurById(@PathVariable String id) {
        return directeurRepository.findById(id).orElse(null);
    }

}
