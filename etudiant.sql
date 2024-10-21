-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 22 oct. 2024 à 00:58
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `school1`
--

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `ville` varchar(50) NOT NULL,
  `sexe` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `nom`, `prenom`, `ville`, `sexe`) VALUES
(6, 'Sabrou', 'Othmane', 'Marrakesh', 'M'),
(7, 'Fathallah', 'Amal', 'Sale', 'M'),
(11, 'Maghni', 'manal', 'Rabat', 'femme'),
(12, 'Maghni', 'manal', 'Rabat', 'femme'),
(13, 'amerga', 'youness', 'Rabat', 'homme'),
(14, 'chouay', 'walid', 'Eljadida', 'homme'),
(15, 'Rmili', 'siham', 'Settat', 'femme'),
(16, 'mourid', 'hajar', 'Eljadida', 'femme'),
(18, 'jorf', 'abdellah', 'Marrakech', 'homme'),
(23, 'alex', 'alaoui', 'Casablanca', 'homme'),
(24, 'alex', 'alaoui', 'Casablanca', 'homme'),
(25, 'alex', 'alaoui', 'Casablanca', 'homme'),
(26, 'salima', 'hakima', 'Eljadida', 'femme'),
(27, 'alexa', 'demi', 'Eljadida', 'femme'),
(28, 'sabro', 'brahim', 'Casablanca', 'homme'),
(30, 'Laghdaf', 'hanane', 'Rabat', 'femme'),
(31, 'Idtnain', 'mohamed', 'Agadir', 'homme'),
(32, 'Hafsa', 'Salim', 'Rabat', 'femme');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
