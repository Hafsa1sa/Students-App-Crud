<?php
    // Inclure les fichiers nécessaires
    include_once '../racine.php';
    include_once RACINE . '/service/EtudiantService.php';

    // Extraire les paramètres GET
    extract($_GET);

    // Initialiser le service d'étudiant
    $etudiantService = new EtudiantService();

    // Créer un nouvel étudiant avec les données fournies
    $etudiantService->create(new Etudiant(1, $nom, $prenom, $ville, $sexe));

    // Redirection vers la page d'accueil après la création
    header("Location: ../index.php");
    exit(); // Toujours utiliser exit après un header pour arrêter l'exécution
?>
