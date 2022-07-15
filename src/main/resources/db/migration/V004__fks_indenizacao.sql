ALTER TABLE `indenizacao` ADD CONSTRAINT `FK_beneficiario_indenizado` FOREIGN KEY (`beneficiarios`) REFERENCES `beneficiario` (`id`);
ALTER TABLE `indenizacao` ADD CONSTRAINT `FK_processo_indenizado` FOREIGN KEY (`processos`) REFERENCES `processo` (`id`);
ALTER TABLE `indenizacao` ADD CONSTRAINT `FK_orcamento_indenizado` FOREIGN KEY (`orcamento`) REFERENCES `orcamento` (`id`);