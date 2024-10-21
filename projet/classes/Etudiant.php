<?php
class Etudiant {
    // Déclaration des propriétés privées
    private $id;
    private $nom;
    private $prenom;
    private $ville;
    private $sexe;
   

    // Constructeur pour initialiser les propriétés de l'objet
    public function __construct($id, $nom, $prenom, $ville, $sexe) {
        $this->id = $id;
        $this->nom = $nom;
        $this->prenom = $prenom;
        $this->ville = $ville;
        $this->sexe = $sexe;
        
    }

    // Méthodes 'getter' pour accéder aux propriétés privées
    public function getId() {
        return $this->id;
    }

    public function getNom() {
        return $this->nom;
    }

    public function getPrenom() {
        return $this->prenom;
    }

    public function getVille() {
        return $this->ville;
    }

    public function getSexe() {
        return $this->sexe;
    }
   

    // Méthodes 'setter' pour modifier les propriétés privées
    public function setId($id) {
        $this->id = $id;
    }

    public function setNom($nom) {
        $this->nom = $nom;
    }

    public function setPrenom($prenom) {
        $this->prenom = $prenom;
    }

    public function setVille($ville) {
        $this->ville = $ville;
    }

    public function setSexe($sexe) {
        $this->sexe = $sexe;
    }
   

    // Méthode pour retourner une chaîne de caractères représentant l'objet
    public function __toString() {
        return $this->nom . " " . $this->prenom;
    }
}
?>
