package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.BeneficiarioNaoEncontradoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.BeneficiarioRepository;

@Service
public class CadastroBeneficiarioService {

	private static final String ENTIDADE_EM_USO = "Beneficiario de código %d não pode ser removido, pois está em uso";

	
	@Autowired
	private BeneficiarioRepository beneficiarios;
	
	//------MÉTODOS------//
	
	public Beneficiario salvar(Beneficiario beneficiario) {
		return beneficiarios.save(beneficiario);
	}
	
	public Beneficiario localizarEntidade(Long id) {
		Beneficiario  beneficiario = beneficiarios.findById(id).
				orElseThrow(()-> new BeneficiarioNaoEncontradoException(id));
		return beneficiario;
	}
	
	public void remover(Long id) {
		try {	
			beneficiarios.deleteById(id);
		}catch(EmptyResultDataAccessException e){
			throw new BeneficiarioNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format(ENTIDADE_EM_USO, id));
		}
	}
}
