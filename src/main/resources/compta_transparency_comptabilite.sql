-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 20, 2020 at 08:54 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `compta_transparency_comptabilite`
--

-- --------------------------------------------------------

--
-- Table structure for table `adherant`
--

CREATE TABLE `adherant` (
  `id` bigint(20) NOT NULL,
  `comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `banque`
--

CREATE TABLE `banque` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `banque`
--

INSERT INTO `banque` (`id`, `code`, `libelle`) VALUES
(1, 'code1', 'Banque1'),
(639, NULL, 'TEST'),
(640, NULL, NULL),
(641, NULL, 'atijarri');

-- --------------------------------------------------------

--
-- Table structure for table `caisse`
--

CREATE TABLE `caisse` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `solde` decimal(16,4) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `caisse`
--

INSERT INTO `caisse` (`id`, `code`, `libelle`, `solde`, `adherant`) VALUES
(1, 'code1', 'Caisse13332', '35150.0000', 486),
(2, 'code2', 'Caisse2', '52000.0000', 486),
(4, 'code4', 'Caisse4', '52000.0000', NULL),
(638, '0112335nn', 'TestCaisse', '150000.0000', 486);

-- --------------------------------------------------------

--
-- Table structure for table `classe_comptable`
--

CREATE TABLE `classe_comptable` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `classe_comptable`
--

INSERT INTO `classe_comptable` (`id`, `libelle`, `numero`) VALUES
(1, 'classe1', 1),
(2, 'classe2', 2),
(3, 'classe3', 3),
(4, 'classe4', 4),
(5, 'classe5', 5),
(6, 'classe6', 6),
(7, 'classe7', 7),
(8, 'classe8', 8);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `ice` varchar(255) DEFAULT NULL,
  `identifiant_fiscale` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `rc` varchar(255) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `code`, `ice`, `identifiant_fiscale`, `libelle`, `rc`, `adherant`, `comptable`) VALUES
(1, 'code1', 'ice1', 'id1', 'Client1', 'rc1', NULL, NULL),
(462, NULL, NULL, NULL, 'mohmad', NULL, NULL, NULL),
(555, '01020124663', 'ice', '0124513346fff', 'TESTrrddd', '124444466rc', 486, NULL),
(561, 'dsdsds', NULL, NULL, 'clien', NULL, 486, NULL),
(562, 'sdsdsd', NULL, NULL, 'sdsdsd', NULL, 486, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `comptable`
--

CREATE TABLE `comptable` (
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `compte_banquaire`
--

CREATE TABLE `compte_banquaire` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `solde` decimal(16,4) DEFAULT NULL,
  `banque` bigint(20) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `compte_banquaire`
--

INSERT INTO `compte_banquaire` (`id`, `code`, `libelle`, `solde`, `banque`, `adherant`, `comptable`) VALUES
(1, 'code1', 'Compte Banquaire 1', '4579504.0000', 1, 486, NULL),
(2, 'code2', 'Compte Banquaire 2', '45521251.0000', 1, NULL, NULL),
(3, 'code3', 'Compte Banquaire 3', '4564654.0000', 1, NULL, NULL),
(642, '100d23s54dsd1', 'plsWork', '150032.0000', 641, 486, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `compte_comptable`
--

CREATE TABLE `compte_comptable` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `sous_classe_comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `compte_comptable`
--

INSERT INTO `compte_comptable` (`id`, `code`, `libelle`, `sous_classe_comptable`) VALUES
(1, '11223', 'compte1', 1),
(2, '12455', 'compte2', 2),
(3, '21223', 'compte3', 3),
(4, '24542', 'compte4', 4),
(5, '31223', 'compte5', 5),
(6, '32154', 'compte6', 6),
(7, '41223', 'compte7', 7),
(8, '46599', 'compte8', 8),
(9, '51223', 'compte9', 9),
(10, '57854', 'compte10', 10),
(11, '61223', 'compte11', 11),
(12, '63245', 'compte12', 12),
(13, '71223', 'compte13', 13),
(14, '75453', 'compte14', 14),
(15, '81223', 'compte15', 15),
(16, '82146', 'compte16', 16),
(17, '75453', 'Compte17', 14);

-- --------------------------------------------------------

--
-- Table structure for table `cpc`
--

CREATE TABLE `cpc` (
  `id` bigint(20) NOT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `resultat` decimal(16,4) DEFAULT NULL,
  `total_charge` decimal(16,4) DEFAULT NULL,
  `total_produit` decimal(16,4) DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `cpc_compte_comptable`
--

CREATE TABLE `cpc_compte_comptable` (
  `id` bigint(20) NOT NULL,
  `montant` decimal(16,4) DEFAULT NULL,
  `compte_comptable` bigint(20) DEFAULT NULL,
  `cpc_sous_classe` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `cpc_sous_classe`
--

CREATE TABLE `cpc_sous_classe` (
  `id` bigint(20) NOT NULL,
  `montant` decimal(16,4) DEFAULT NULL,
  `cpc` bigint(20) DEFAULT NULL,
  `sous_classe_comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `declaration_tva`
--

CREATE TABLE `declaration_tva` (
  `id` bigint(20) NOT NULL,
  `cotisation` decimal(16,4) DEFAULT NULL,
  `difference_charge_gain` decimal(16,4) DEFAULT NULL,
  `total_tva_charge` decimal(16,4) DEFAULT NULL,
  `total_tva_gain` decimal(16,4) DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `etat_facture`
--

CREATE TABLE `etat_facture` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `etat_facture`
--

INSERT INTO `etat_facture` (`id`, `code`, `libelle`) VALUES
(3, 'primary', 'Partial'),
(2, 'info', 'Approved'),
(1, 'warning', 'Draft'),
(4, 'success', 'Payed'),
(5, 'danger', 'refused');

-- --------------------------------------------------------

--
-- Table structure for table `facture`
--

CREATE TABLE `facture` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) DEFAULT NULL,
  `date_facture` date DEFAULT NULL,
  `date_saisie` date DEFAULT NULL,
  `mois` int(11) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `total_ht` decimal(16,4) DEFAULT NULL,
  `total_payer_ht` decimal(16,4) DEFAULT NULL,
  `total_restant_ht` decimal(16,4) DEFAULT NULL,
  `total_ttc` decimal(16,4) DEFAULT NULL,
  `trimester` int(11) DEFAULT NULL,
  `tva` decimal(16,4) DEFAULT NULL,
  `type_facture` varchar(255) DEFAULT NULL,
  `etat_facture` bigint(20) DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `scan_path` varchar(255) DEFAULT NULL,
  `declaration_tva` bigint(20) DEFAULT NULL,
  `traiter` bit(1) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL,
  `taux_tva` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `facture_client`
--

CREATE TABLE `facture_client` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) DEFAULT NULL,
  `date_facture` date DEFAULT NULL,
  `date_saisie` date DEFAULT NULL,
  `mois` int(11) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `total_ht` decimal(16,4) DEFAULT NULL,
  `total_payer_ht` decimal(16,4) DEFAULT NULL,
  `total_restant_ht` decimal(16,4) DEFAULT NULL,
  `total_ttc` decimal(16,4) DEFAULT NULL,
  `trimester` int(11) DEFAULT NULL,
  `tva` decimal(16,4) DEFAULT NULL,
  `type_facture` varchar(255) DEFAULT NULL,
  `etat_facture` bigint(20) DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `client` bigint(20) DEFAULT NULL,
  `scan_path` varchar(255) DEFAULT NULL,
  `declaration_tva` bigint(20) DEFAULT NULL,
  `traiter` bit(1) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL,
  `taux_tva` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `facture_fournisseur`
--

CREATE TABLE `facture_fournisseur` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) DEFAULT NULL,
  `date_facture` date DEFAULT NULL,
  `date_saisie` date DEFAULT NULL,
  `mois` int(11) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `total_ht` decimal(16,4) DEFAULT NULL,
  `total_payer_ht` decimal(16,4) DEFAULT NULL,
  `total_restant_ht` decimal(16,4) DEFAULT NULL,
  `total_ttc` decimal(16,4) DEFAULT NULL,
  `trimester` int(11) DEFAULT NULL,
  `tva` decimal(16,4) DEFAULT NULL,
  `type_facture` varchar(255) DEFAULT NULL,
  `etat_facture` bigint(20) DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `fournisseur` bigint(20) DEFAULT NULL,
  `scan_path` varchar(255) DEFAULT NULL,
  `declaration_tva` bigint(20) DEFAULT NULL,
  `traiter` bit(1) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL,
  `taux_tva` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `facture_item`
--

CREATE TABLE `facture_item` (
  `id` bigint(20) NOT NULL,
  `montant` decimal(16,4) DEFAULT NULL,
  `produit` varchar(255) DEFAULT NULL,
  `quantite` decimal(16,4) DEFAULT NULL,
  `facture` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `ice` varchar(255) DEFAULT NULL,
  `identifiant_fiscale` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `rc` varchar(255) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fournisseur`
--

INSERT INTO `fournisseur` (`id`, `code`, `ice`, `identifiant_fiscale`, `libelle`, `rc`, `adherant`, `comptable`) VALUES
(1, 'code1', 'ice1', 'id1', 'Fournisseur1', 'rc1', NULL, NULL),
(2, '0000000000000000', 'ice2', 'id2', 'Fournisseur2', 'rc2', 486, NULL),
(3, 'code3', 'ice3', 'id3', 'Fournisseur3', 'rc3', NULL, NULL),
(460, NULL, NULL, NULL, 'true', NULL, 486, NULL),
(461, NULL, NULL, NULL, 'fff', NULL, 486, NULL),
(643, 'test', NULL, NULL, 'test', NULL, 486, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670),
(670);

-- --------------------------------------------------------

--
-- Table structure for table `journal`
--

CREATE TABLE `journal` (
  `id` bigint(20) NOT NULL,
  `datedebut` date DEFAULT NULL,
  `datefin` date DEFAULT NULL,
  `libele` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `operation_comptable`
--

CREATE TABLE `operation_comptable` (
  `id` bigint(20) NOT NULL,
  `date_operation_comptable` datetime DEFAULT NULL,
  `date_saisie` datetime DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `montant` decimal(16,4) DEFAULT NULL,
  `reference_facture` varchar(255) DEFAULT NULL,
  `caisse` bigint(20) DEFAULT NULL,
  `compte_banquaire` bigint(20) DEFAULT NULL,
  `compte_comptable` bigint(20) DEFAULT NULL,
  `facture` bigint(20) DEFAULT NULL,
  `operation_comptable_groupe` bigint(20) DEFAULT NULL,
  `societe` bigint(20) DEFAULT NULL,
  `type_operation_comptable` bigint(20) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `operation_comptable_groupe`
--

CREATE TABLE `operation_comptable_groupe` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL,
  `date_saisie` datetime DEFAULT NULL,
  `journal` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `paiement_facture`
--

CREATE TABLE `paiement_facture` (
  `id` bigint(20) NOT NULL,
  `date_paiment` date DEFAULT NULL,
  `date_saisie` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `montant` decimal(16,4) DEFAULT NULL,
  `scan` varchar(255) DEFAULT NULL,
  `facture` bigint(20) DEFAULT NULL,
  `type_paiment` bigint(20) DEFAULT NULL,
  `caisse` bigint(20) DEFAULT NULL,
  `compte_banquaire` bigint(20) DEFAULT NULL,
  `operation_comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `authority`) VALUES
(1, 'ROLE_ADHERENT'),
(2, 'ROLE_COMPTABLE');

-- --------------------------------------------------------

--
-- Table structure for table `societe`
--

CREATE TABLE `societe` (
  `id` bigint(20) NOT NULL,
  `ice` varchar(255) DEFAULT NULL,
  `identifiant_fiscal` varchar(255) DEFAULT NULL,
  `raison_social` varchar(255) DEFAULT NULL,
  `authentification_cnss` varchar(255) DEFAULT NULL,
  `juridiction` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `registre_comerce` varchar(255) DEFAULT NULL,
  `statuejuridique` varchar(255) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `certificat_negatif` varchar(255) DEFAULT NULL,
  `contrat_bail` varchar(255) DEFAULT NULL,
  `patente` varchar(255) DEFAULT NULL,
  `publication_creation_bo` varchar(255) DEFAULT NULL,
  `registre_commerceimage` varchar(255) DEFAULT NULL,
  `relever_banquaire` varchar(255) DEFAULT NULL,
  `statuet` varchar(255) DEFAULT NULL,
  `utilisateur` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `societe`
--

INSERT INTO `societe` (`id`, `ice`, `identifiant_fiscal`, `raison_social`, `authentification_cnss`, `juridiction`, `nom`, `prenom`, `registre_comerce`, `statuejuridique`, `adherant`, `comptable`, `adresse`, `certificat_negatif`, `contrat_bail`, `patente`, `publication_creation_bo`, `registre_commerceimage`, `relever_banquaire`, `statuet`, `utilisateur`) VALUES
(1, 'ice1', 'id1', 'Bmce', NULL, NULL, NULL, NULL, NULL, NULL, 486, 485, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 486),
(2, 'ice2', 'id2', 'Fstg', NULL, NULL, NULL, NULL, NULL, NULL, 565, 485, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'ice3', 'id3', 'soc3', NULL, NULL, NULL, NULL, NULL, NULL, 486, 485, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 486);

-- --------------------------------------------------------

--
-- Table structure for table `sous_classe_comptable`
--

CREATE TABLE `sous_classe_comptable` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `classe_comptable` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sous_classe_comptable`
--

INSERT INTO `sous_classe_comptable` (`id`, `libelle`, `numero`, `classe_comptable`) VALUES
(1, 'souclasse', 11223, 1),
(2, 'souclasse', 12455, 1),
(3, 'souclasse', 21223, 2),
(4, 'souclasse', 24542, 2),
(5, 'souclasse', 31223, 3),
(6, 'souclasse', 32154, 3),
(7, 'souclasse', 41223, 4),
(8, 'souclasse', 46599, 4),
(9, 'souclasse', 51223, 5),
(10, 'souclasse', 57854, 5),
(11, 'souclasse', 61223, 6),
(12, 'souclasse', 63245, 6),
(13, 'souclasse', 71223, 7),
(14, 'souclasse', 75453, 7),
(15, 'souclasse', 81223, 8),
(16, 'souclasse', 82146, 8);

-- --------------------------------------------------------

--
-- Table structure for table `taux_tva`
--

CREATE TABLE `taux_tva` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `taux` int(11) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `taux_tva`
--

INSERT INTO `taux_tva` (`id`, `libelle`, `taux`, `adherant`) VALUES
(427, 'taux1', 7, NULL),
(458, 'taux2', 15, NULL),
(463, 'taxe ', 21, NULL),
(524, 'TAUX', 8, 486);

-- --------------------------------------------------------

--
-- Table structure for table `type_operation_comptable`
--

CREATE TABLE `type_operation_comptable` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `type_operation_comptable`
--

INSERT INTO `type_operation_comptable` (`id`, `code`, `libelle`) VALUES
(1, 'code1', 'Debit'),
(2, 'code2', 'Credit');

-- --------------------------------------------------------

--
-- Table structure for table `type_paiement`
--

CREATE TABLE `type_paiement` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `type_paiement`
--

INSERT INTO `type_paiement` (`id`, `code`, `libelle`) VALUES
(1, 'code1', 'Cheque'),
(2, 'code2', 'Espece');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `datenaissance` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mdp` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `adherant` bigint(20) DEFAULT NULL,
  `comptable` bigint(20) DEFAULT NULL,
  `dtype` varchar(31) NOT NULL,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `adresse`, `datenaissance`, `email`, `mdp`, `nom`, `prenom`, `role`, `ville`, `adherant`, `comptable`, `dtype`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`, `password`) VALUES
(483, NULL, NULL, 'aitdaoudelhoussein@gmail.com', NULL, 'houssein', 'aitdaoud', NULL, NULL, NULL, NULL, 'Comptable', b'1', b'1', b'1', b'1', '$2a$10$WifzMBFOlYRuSAY4P708MOfdPDtkTlHxXq10tqf260pZ2CK5XBFua'),
(484, NULL, NULL, 'YASSIR@gmail.com', NULL, 'YASSIR', 'aitdaoud', NULL, NULL, NULL, NULL, 'Comptable', b'1', b'1', b'1', b'1', '$2a$10$UP25pCbg3HOJ7Nj2VkuEQeQue5mFqV3Vyh6yJu4.BBZWEo2PahPMa'),
(485, NULL, NULL, 'YASSIR2@gmail.com', NULL, 'YASSIR2', 'aitdaoud', NULL, NULL, NULL, NULL, 'Comptable', b'1', b'1', b'1', b'1', '$2a$10$Jaxb6V.JsGa5cbrJr.hAt.M/C6xHr4dgpPuuRS6JGps0CPFzDLvCm'),
(486, NULL, NULL, 'YASSIR23@gmail.com', NULL, 'YASSIR32', 'aitdaoud', NULL, NULL, NULL, 485, 'Adherant', b'1', b'1', b'1', b'1', '$2a$10$6Qxift5p8yVsi2727Czz6Otu1g6z0A9yHYAm1cFVhmAICalRjkmJK'),
(563, NULL, NULL, 'plswwww@gmail.com', NULL, 'dddd', 'ddddd', NULL, NULL, NULL, NULL, 'Comptable', b'1', b'1', b'1', b'1', '$2a$10$WG5KmOKZXhqMSUd5zs1euuJD8yQsImbUw6WmDUaSajWpcewTSL1Fm'),
(564, NULL, NULL, 'TST@gmail.com', NULL, 'plswork', '3afak', NULL, NULL, NULL, NULL, 'Comptable', b'1', b'1', b'1', b'1', '$2a$10$hbU39VSNvIuKNX4QdVKKR.FfLPFRC7Li2gaMUENE/3vgj5h7r2tv.'),
(565, NULL, NULL, 'plswwwwDDD@gmail.com', NULL, 'LOL', 'LOL', NULL, NULL, NULL, 485, 'Adherant', b'1', b'1', b'1', b'1', '$2a$10$Giax1Z7k9QKkiVq7fWqN/ePmWVhO.c0vr5qnKzE2OOZ2VDHhQ/nry'),
(669, NULL, NULL, 'hussein@gmail.com', NULL, 'ffff', 'ffff', NULL, NULL, NULL, NULL, 'Adherant', b'1', b'1', b'1', b'1', '$2a$10$veHHIGf47Aa8LFq7YfpTzeQvqqPUoccijjUEJq70cYPR72T7WpFeW');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur_adherants`
--

CREATE TABLE `utilisateur_adherants` (
  `admin_id` bigint(20) NOT NULL,
  `adherants` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur_authorities`
--

CREATE TABLE `utilisateur_authorities` (
  `utilisateur_id` bigint(20) NOT NULL,
  `authorities` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `utilisateur_authorities`
--

INSERT INTO `utilisateur_authorities` (`utilisateur_id`, `authorities`) VALUES
(484, 2),
(485, 2),
(486, 1),
(563, 2),
(564, 2),
(565, 1),
(669, 1);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur_comptables`
--

CREATE TABLE `utilisateur_comptables` (
  `admin_id` bigint(20) NOT NULL,
  `comptables` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adherant`
--
ALTER TABLE `adherant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc2bk51o4knlkg4kj004kvl89j` (`comptable`);

--
-- Indexes for table `banque`
--
ALTER TABLE `banque`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `caisse`
--
ALTER TABLE `caisse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgp6qekorwl435nxm4ps686t7` (`adherant`);

--
-- Indexes for table `classe_comptable`
--
ALTER TABLE `classe_comptable`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhu2s6arvri42ybklvo54n01s0` (`comptable`),
  ADD KEY `FKm461vuc36wkk7iregec3s3eno` (`adherant`);

--
-- Indexes for table `comptable`
--
ALTER TABLE `comptable`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `compte_banquaire`
--
ALTER TABLE `compte_banquaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7qu9kpq0fxn2ugp65olpv4nr2` (`comptable`),
  ADD KEY `FK4e2chaqx6d4xcfxlbs4hagdm7` (`adherant`),
  ADD KEY `FK5h83te6mo1kbviol8f9wvltbe` (`banque`);

--
-- Indexes for table `compte_comptable`
--
ALTER TABLE `compte_comptable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKajf6wdmvrh66jg39vg1aryt4m` (`sous_classe_comptable`);

--
-- Indexes for table `cpc`
--
ALTER TABLE `cpc`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfmmudabi098ss8sy88o4vws6g` (`comptable`),
  ADD KEY `FKpbx8ce6xl7cl870x6mt8psfb` (`adherant`),
  ADD KEY `FKexgupd0akd2lkq0decymbd9w9` (`societe`);

--
-- Indexes for table `cpc_compte_comptable`
--
ALTER TABLE `cpc_compte_comptable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf1wf1ifmsrolsceomy54w6i97` (`compte_comptable`),
  ADD KEY `FKkij6x10oun6hlemsc057d9to6` (`cpc_sous_classe`);

--
-- Indexes for table `cpc_sous_classe`
--
ALTER TABLE `cpc_sous_classe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6lifo2d4w03lbmnpnrq5et6i5` (`cpc`),
  ADD KEY `FKgdomukqski5reuy78v43alxpm` (`sous_classe_comptable`);

--
-- Indexes for table `declaration_tva`
--
ALTER TABLE `declaration_tva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhejhbpio6ch1c9xl70luj57a3` (`comptable`),
  ADD KEY `FK341y396rb5aekjue3rsrqkyog` (`adherant`),
  ADD KEY `FKgwbagwpqe8jdr7jtjbks2ybcm` (`societe`);

--
-- Indexes for table `etat_facture`
--
ALTER TABLE `etat_facture`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK52nvfwxnktqxb1hrd0hsmxxo7` (`comptable`),
  ADD KEY `FKgwgpn1p4mwli0ceasaw9f0bfn` (`adherant`),
  ADD KEY `FKsburti4kj44462x058oi43eae` (`declaration_tva`),
  ADD KEY `FKkr3ksts74gennv8v3sftugtj8` (`etat_facture`),
  ADD KEY `FKl8ruald7hboyv2n6ovtp35grw` (`societe`),
  ADD KEY `FK9m4ubag6gj28q3qaioguwug0d` (`taux_tva`);

--
-- Indexes for table `facture_client`
--
ALTER TABLE `facture_client`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_i6na537nan7u1u8hprvd23lvs` (`comptable`),
  ADD KEY `FK36tkxh2lf6npu23onmjudndf4` (`client`),
  ADD KEY `FK_kglq6fv34bybpy3paoe4sljfb` (`adherant`),
  ADD KEY `FK_1xdvntprqb5t63apy68sd7s6l` (`declaration_tva`),
  ADD KEY `FK_r5ih2egwqamtbus5f9j0ois9n` (`etat_facture`),
  ADD KEY `FK_aw966bq3yxgyekaauraqo8mv7` (`societe`),
  ADD KEY `FK_6g2jfv8s0ev0rqrt84ssexuug` (`taux_tva`);

--
-- Indexes for table `facture_fournisseur`
--
ALTER TABLE `facture_fournisseur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_bvamhwl4dmpdi6po2fphh8ean` (`comptable`),
  ADD KEY `FKfe61pimknjlt0damatulcmags` (`fournisseur`),
  ADD KEY `FK_iyosu9dxhn49d7u3p16rip5gr` (`adherant`),
  ADD KEY `FK_jiy1u2buf3uy6j21ljhsskada` (`declaration_tva`),
  ADD KEY `FK_joh92jjpu3aowya8kg57gluk8` (`etat_facture`),
  ADD KEY `FK_h7bw9x0gbxnkb33e5su7ujc4a` (`societe`),
  ADD KEY `FK_npwi4uar02pdeu1pe9rnf0h5e` (`taux_tva`);

--
-- Indexes for table `facture_item`
--
ALTER TABLE `facture_item`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmsfh4s70lbxwjl34m0woq6iak` (`comptable`),
  ADD KEY `FK2ovelmlpifwkoka0n0rvq6ckx` (`adherant`);

--
-- Indexes for table `journal`
--
ALTER TABLE `journal`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `operation_comptable`
--
ALTER TABLE `operation_comptable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKaa0oi615im0pa23xh085462ov` (`adherant`),
  ADD KEY `FKkt5dw93eim2uotngttopfiexv` (`caisse`),
  ADD KEY `FK94bwi32777cshjsmg9i99empl` (`compte_banquaire`),
  ADD KEY `FKj0grkgxvo0dmltj7g30hdnrlr` (`compte_comptable`),
  ADD KEY `FKgo5detp1mbnxksi92yn11pjde` (`operation_comptable_groupe`),
  ADD KEY `FKauu981hqjevfwau4h1dcnmfmx` (`societe`),
  ADD KEY `FKtnkg37gw0d4tym9it150ripni` (`type_operation_comptable`);

--
-- Indexes for table `operation_comptable_groupe`
--
ALTER TABLE `operation_comptable_groupe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKabeatf2lntr8tnuvyi3v39gm5` (`comptable`),
  ADD KEY `FKg1ak7y9eciad6wrc9yrrhr926` (`adherant`),
  ADD KEY `FK97iquw8rv5o4jvsdbaqboss` (`journal`);

--
-- Indexes for table `paiement_facture`
--
ALTER TABLE `paiement_facture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6tbrlld2fu4kvpy042emw7en1` (`caisse`),
  ADD KEY `FKdppch8pkjfxkainfjsb4r04ae` (`compte_banquaire`),
  ADD KEY `FK8w91cb0mdvjweq9himcmwyho` (`operation_comptable`),
  ADD KEY `FKj6gmcr8in3w6y5qxahunjufvp` (`type_paiment`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `societe`
--
ALTER TABLE `societe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpoiub9fmn4ryp2ecmbbwep2vw` (`utilisateur`),
  ADD KEY `FKbjcld9rfa7txsaga6xl1chxyx` (`adherant`),
  ADD KEY `FKsa6e3dodh2wnpi5cus23d57wp` (`comptable`);

--
-- Indexes for table `sous_classe_comptable`
--
ALTER TABLE `sous_classe_comptable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhacjc0ryy5m37e5fb4cftpsia` (`classe_comptable`);

--
-- Indexes for table `taux_tva`
--
ALTER TABLE `taux_tva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7ibs2piuae3px0oxek7aed6pl` (`adherant`);

--
-- Indexes for table `type_operation_comptable`
--
ALTER TABLE `type_operation_comptable`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `type_paiement`
--
ALTER TABLE `type_paiement`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKaewosd2vb2xdy96f9pi8x2399` (`adherant`),
  ADD KEY `FKdck8erk3dotn9rjgeia7ib8hr` (`comptable`);

--
-- Indexes for table `utilisateur_adherants`
--
ALTER TABLE `utilisateur_adherants`
  ADD UNIQUE KEY `UK_515v2ogygg4eeu1oslt37c7w3` (`adherants`),
  ADD KEY `FKc4chaieu5sxm1jels96qtj0si` (`admin_id`);

--
-- Indexes for table `utilisateur_authorities`
--
ALTER TABLE `utilisateur_authorities`
  ADD KEY `FKryv9eqdlt5qhxo2wvn0v55t6f` (`authorities`),
  ADD KEY `FKivke62d2m4a8gc75k24o4y4t4` (`utilisateur_id`);

--
-- Indexes for table `utilisateur_comptables`
--
ALTER TABLE `utilisateur_comptables`
  ADD UNIQUE KEY `UK_k6qnbhcismffio40k040u7bw7` (`comptables`),
  ADD KEY `FK9c8qnyu89bxsu4kflmfinurrr` (`admin_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
