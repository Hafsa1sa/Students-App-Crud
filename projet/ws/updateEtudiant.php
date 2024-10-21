<?php
if($_SERVER["REQUEST_METHOD"] == "POST"){
    include_once '../racine.php';
    include_once RACINE.'/service/EtudiantService.php';
    update();
}

function update(){
    if (!isset($_POST['id'], $_POST['nom'], $_POST['prenom'], $_POST['ville'], $_POST['sexe'])) {
        echo json_encode(["error" => "Invalid input"]);
        return;
    }

    extract($_POST);
    $es = new EtudiantService();

    // Assuming $id is passed and used to find the correct student
    $etudiant = new Etudiant($id, $nom, $prenom, $ville, $sexe);
    if ($es->update($etudiant)) {
        header('Content-type: application/json');
        echo json_encode($es->findAllApi());
    } else {
        echo json_encode(["error" => "Update failed"]);
    }
}