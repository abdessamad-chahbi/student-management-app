package appSpringMongo.Etudiant;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface EtudiantRepository extends MongoRepository<EtudiantModel, String> {
    // Ajoutez des méthodes personnalisées si nécessaire
	
	EtudiantModel findByCin(String cinEtudiant);
//	La méthode findByCin est générée automatiquement par Spring Data MongoDB en fonction du nom du champ dans votre modèle (cin dans ce cas).
//	EtudiantModel findByCIN(String cinEtudiant);
// ==> cette fontion "findByCIN" ne fonctionne pas car l'attribut "CIN" n'existe pas dans le modele "EtudiantModel" 

	void deleteByCin(String EtudCin);
}
