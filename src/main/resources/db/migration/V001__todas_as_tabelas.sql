CREATE TABLE `beneficiario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(30) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `perfil` varchar(15) DEFAULT NULL,
  `rg` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `processo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `processo_pae` varchar(15) NOT NULL,
  `processo_original` varchar(15) NOT NULL,
  `numero_lote` varchar(10) NOT NULL,
  `status_lote` varchar(255) NOT NULL,
  `status_processo` varchar(255) NOT NULL,
  `localizacao` bigint(20) NOT NULL,
  `beneficiarios` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `dados_bancarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agencia` varchar(10) DEFAULT NULL,
  `banco` varchar(50) DEFAULT NULL,
  `conta_corrente` varchar(20) DEFAULT NULL,
  `op` varchar(10) DEFAULT NULL,
  `beneficiarios` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


CREATE TABLE `orcamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `elemento_despesa` varchar(100) DEFAULT NULL,
  `fonte` varchar(100) DEFAULT NULL,
  `ptres` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;



CREATE TABLE `indenizacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_acordo` date DEFAULT NULL,
  `data_ne` date DEFAULT NULL,
  `data_nl` date DEFAULT NULL,
  `data_ob` date DEFAULT NULL,
  `ne` varchar(15) DEFAULT NULL,
  `nl` varchar(15) DEFAULT NULL,
  `ob` varchar(15) DEFAULT NULL,
  `status_pagamento` varchar(100) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `beneficiarios` bigint(20),
  `processos` bigint(20),
  `orcamento` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `localizacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(60) DEFAULT NULL,
  `logradouro` varchar(100) DEFAULT NULL,
  `municiopio` varchar(50) DEFAULT NULL,
  `status_logradouro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE `imagens` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `imagem` longblob,
  `processos` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;