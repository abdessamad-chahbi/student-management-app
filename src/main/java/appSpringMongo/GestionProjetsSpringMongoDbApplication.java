package appSpringMongo;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
//@EntityScan("appSpringMongo")
public class GestionProjetsSpringMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionProjetsSpringMongoDbApplication.class, args);
	}
		

    // Injectez la classe MongoTemplate pour vérifier la connexion à MongoDB
    private final MongoTemplate mongoTemplate;

    public GestionProjetsSpringMongoDbApplication(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void checkMongoDBConnection() {
        try {
            // Vérifiez la connexion à MongoDB en effectuant une opération simple
            mongoTemplate.executeCommand("{ ping: 1 }");

            // Affichez un message de réussite si la connexion est établie
            System.err.println("Connexion à MongoDB réussie.");
        } catch (Exception e) {
            System.err.println("Impossible d'établir une connexion à MongoDB : " + e.getMessage());
        }
    }




    
}
