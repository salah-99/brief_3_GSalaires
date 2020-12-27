-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 27 déc. 2020 à 12:30
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestionpersonnel`
--

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE `employe` (
  `id_employer` int(11) NOT NULL,
  `nom` varchar(60) NOT NULL,
  `prenom` varchar(60) NOT NULL,
  `age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`id_employer`, `nom`, `prenom`, `age`) VALUES
(1, 'habib', 'salman', 25),
(2, 'NT1', 'PT1', 10),
(4, 'NT', 'PT3', 30),
(5, 'DD', 'DD', 22),
(8, 'S', 'S', 2),
(9, 'OUM', 'RH', 22),
(10, 'OUM12', 'RH2', 33),
(11, 'ZZ', 'MM', 11),
(12, 'SALOU', 'LBAYAD', 22),
(13, 'SSA', 'NNA', 22);

-- --------------------------------------------------------

--
-- Structure de la table `info_salaire`
--

CREATE TABLE `info_salaire` (
  `id_info_salaire` int(11) NOT NULL,
  `date` varchar(60) NOT NULL,
  `chifredaffire` double NOT NULL,
  `Salire_total` double NOT NULL,
  `id_poste_de_travail` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `info_salaire`
--

INSERT INTO `info_salaire` (`id_info_salaire`, `date`, `chifredaffire`, `Salire_total`, `id_poste_de_travail`) VALUES
(1, '2020-12-25', 100, 1520, 1),
(3, '2020-12-25', 20, 100, 6),
(4, '2020-12-25', 40, 200, 6),
(11, '2020-12-25', 66, 530, 14),
(16, '2020-12-25', 200, 1540, 25),
(17, '2020-12-25', 55, 475, 22),
(18, '2020-12-25', 33, 365, 22),
(19, '2020-12-25', 66, 3300, 21),
(20, '2020-12-25', 44, 220, 28);

-- --------------------------------------------------------

--
-- Structure de la table `poste_de_travail`
--

CREATE TABLE `poste_de_travail` (
  `id_post_de_travail` int(11) NOT NULL,
  `date_dentree_service` varchar(60) NOT NULL,
  `type` varchar(60) NOT NULL,
  `status` int(11) NOT NULL,
  `id_employer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `poste_de_travail`
--

INSERT INTO `poste_de_travail` (`id_post_de_travail`, `date_dentree_service`, `type`, `status`, `id_employer`) VALUES
(1, '2020-12-24', 'la_Vente', 1, 1),
(2, '2020-10-24', 'la_Vente', 0, 2),
(4, '2020-12-24', 'la_Manutention', 0, 4),
(6, '2020-12-24', 'la_Production', 0, 5),
(7, '2020-12-24', 'la_Manutention', 0, 2),
(14, '2020-12-25', 'la_Production_a_risques', 1, 2),
(21, '2020-12-25', 'la_Manutention', 0, 8),
(22, '2020-12-25', 'la_Production_a_risques', 1, 9),
(23, '2020-12-25', 'la_Manutention_a_risques', 1, 10),
(24, '2020-12-25', 'la_Representation', 1, 11),
(25, '2020-12-25', 'la_Vente', 1, 4),
(26, '2020-12-25', 'la_Representation', 1, 12),
(27, '2020-12-25', 'la_Representation', 1, 13),
(28, '2020-12-25', 'la_Production', 1, 8);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`id_employer`);

--
-- Index pour la table `info_salaire`
--
ALTER TABLE `info_salaire`
  ADD PRIMARY KEY (`id_info_salaire`),
  ADD KEY `id_poste_de_travail` (`id_poste_de_travail`);

--
-- Index pour la table `poste_de_travail`
--
ALTER TABLE `poste_de_travail`
  ADD PRIMARY KEY (`id_post_de_travail`),
  ADD KEY `id_employer` (`id_employer`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `employe`
--
ALTER TABLE `employe`
  MODIFY `id_employer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `info_salaire`
--
ALTER TABLE `info_salaire`
  MODIFY `id_info_salaire` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `poste_de_travail`
--
ALTER TABLE `poste_de_travail`
  MODIFY `id_post_de_travail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `info_salaire`
--
ALTER TABLE `info_salaire`
  ADD CONSTRAINT `info_salaire_ibfk_1` FOREIGN KEY (`id_poste_de_travail`) REFERENCES `poste_de_travail` (`id_post_de_travail`);

--
-- Contraintes pour la table `poste_de_travail`
--
ALTER TABLE `poste_de_travail`
  ADD CONSTRAINT `poste_de_travail_ibfk_1` FOREIGN KEY (`id_employer`) REFERENCES `employe` (`id_employer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
