package appSpringMongo.Directeur;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DirecteurRepository extends MongoRepository<DirecteurModel, String> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées ici si nécessaire
	
	//Optional<DirecteurModel> findByLoginAndPassword(String loginForm, String passwordForm);
	DirecteurModel findByLoginAndPassword(String login, String password);
}
