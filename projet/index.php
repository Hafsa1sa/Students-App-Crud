<!DOCTYPE html>
<?php include_once './racine.php'; ?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gestion des étudiants</title>
    </head>
    <body>
        <!-- Formulaire pour ajouter un nouvel étudiant -->
        <form method="GET" action="controller/addEtudiant.php">
            <fieldset>
                <legend>Ajouter un nouveau étudiant</legend>
                <table border="0">
                    <tr>
                        <td>Nom :</td>
                        <td><input type="text" name="nom" required /></td>
                    </tr>
                    <tr>
                        <td>Prénom :</td>
                        <td><input type="text" name="prenom" required /></td>
                    </tr>
                    <tr>
                        <td>Ville :</td>
                        <td>
                            <select name="ville">
                                <option value="Marrakech">Marrakech</option>
                                <option value="Rabat">Rabat</option>
                                <option value="Agadir">Agadir</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Sexe :</td>
                        <td>
                            <label>M</label><input type="radio" name="sexe" value="homme" required />
                            <label>F</label><input type="radio" name="sexe" value="femme" required />
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <label for="image">Image :</label>
                        <input type="file" name="image" required>
                        </td> 
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" value="Envoyer" />
                            <input type="reset" value="Effacer" />
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>

        <!-- Tableau pour afficher la liste des étudiants -->
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Ville</th>
                    <th>Sexe</th>
                    <th>Image</th>
                    <th>Supprimer</th>
                    <th>Modifier</th>
                </tr>
            </thead>
            <tbody>
                <?php
                include_once RACINE . '/service/EtudiantService.php';
                $etudiantService = new EtudiantService();

                foreach ($etudiantService->findAll() as $etudiant) {
                    ?>
                    <tr>
                        <td><?php echo $etudiant->getId(); ?></td>
                        <td><?php echo $etudiant->getNom(); ?></td>
                        <td><?php echo $etudiant->getPrenom(); ?></td>
                        <td><?php echo $etudiant->getVille(); ?></td>
                        <td><?php echo $etudiant->getSexe(); ?></td>
                        <td><?php echo $etudiant->getImage(); ?></td>
                        <td>
                            <a href="controller/deleteEtudiant.php?id=<?php echo $etudiant->getId(); ?>">Supprimer</a>
                        </td>
                        <td>
                            <a href="updateEtudiant.php?id=<?php echo $etudiant->getId(); ?>">Modifier</a>
                        </td>
                    </tr>
                    <?php
                }
                ?>
            </tbody>
        </table>
    </body>
</html>
