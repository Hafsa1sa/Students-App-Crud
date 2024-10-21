<?php
    // Inclure les fichiers nécessaires
    include_once '../racine.php';
    include_once RACINE . '/service/EtudiantService.php';

    // Extraire les paramètres GET
    extract($_GET);

    // Initialiser le service d'étudiant
    $etudiantService = new EtudiantService();

    // Trouver l'étudiant par ID et le supprimer
    $etudiant = $etudiantService->findById($id);
    if ($etudiant) {
        $etudiantService->delete($etudiant);
    }

    // Redirection vers la page d'accueil après la suppression
    header("Location: ../index.php");
    exit(); // Toujours utiliser exit après un header pour arrêter l'exécution
?>
