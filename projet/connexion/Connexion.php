<?php
class Connexion {
    // Propriété privée pour stocker l'objet PDO
    private $connexion;

    // Constructeur pour initialiser la connexion à la base de données
    public function __construct() {
        // Paramètres de connexion à la base de données
        $host = 'localhost';
        $dbname = 'school1';
        $login = 'root';
        $password = '';

        try {
            // Création de la connexion PDO
            $this->connexion = new PDO("mysql:host=$host;dbname=$dbname", $login, $password);
            // Définir l'encodage UTF-8
            $this->connexion->query("SET NAMES UTF8");
        } catch (Exception $e) {
            // Gestion des erreurs de connexion
            die('Erreur : ' . $e->getMessage());
        }
    }

    // Méthode pour obtenir l'objet de connexion PDO
    public function getConnexion() {
        return $this->connexion;
    }
}
?>
