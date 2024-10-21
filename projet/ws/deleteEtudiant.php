<?php
if($_SERVER["REQUEST_METHOD"] == "POST"){
    include_once '../racine.php';
    include_once RACINE.'/service/EtudiantService.php';
    delete();
}

function delete(){
    if (!isset($_POST['id'])) {
        echo json_encode(["error" => "Invalid input"]);
        return;
    }

    extract($_POST);
    $es = new EtudiantService();

    // Assuming $id is passed to delete the student
    if ($es->delete(new Etudiant($id, "", "", "", ""))) { // ID is enough to identify
        header('Content-type: application/json');
        echo json_encode($es->findAllApi());
    } else {
        echo json_encode(["error" => "Delete failed"]);
    }
}