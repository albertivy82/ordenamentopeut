package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.NegocioException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.ProcessoNaoEncontradoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ProcessoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroProcessoService;



@RestController
@RequestMapping("/processos")
public class ProcessoController {
	
	@Autowired
	private ProcessoRepository processos;
	
	@Autowired
	private CadastroProcessoService cadastroProcesso;
	
	@GetMapping
	public List<Processo> listar(){
		return processos.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Processo Buscar(@PathVariable Long id){
		return cadastroProcesso.localizarEntidade(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Processo adicionar(@RequestBody Processo processo) {
		try{
			return cadastroProcesso.salvar(processo);
		}catch (ProcessoNaoEncontradoException e){
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Processo atualizar(@PathVariable Long id, @RequestBody Processo processo){
		 	try {
				Processo processoProcurado = cadastroProcesso.localizarEntidade(id);
				BeanUtils.copyProperties(processo, processoProcurado, "id");
			return cadastroProcesso.salvar(processoProcurado);
		 	}catch(EntidadeNaoEncontradaException e) {
		 		throw new NegocioException(e.getMessage(), e);	
		 	}
		 
	 }
	
		
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id){
		cadastroProcesso.remover(id);
	}

   
}
