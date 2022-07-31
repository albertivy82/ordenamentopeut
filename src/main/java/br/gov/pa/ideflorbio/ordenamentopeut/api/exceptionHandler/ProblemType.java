package br.gov.pa.ideflorbio.ordenamentopeut.api.exceptionHandler;


public enum ProblemType {
	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encotrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso-por-outra-entidade", "Entidade em Uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");
	
	
	private String title;
	private String uri;
	
    ProblemType(String path, String title){
    	this.uri = "https://ideflorbio.pa.gov.br"+path;
    	this.title = title;
    }
    
	public String getTitle() {
		return title;
	}

	public String getUri() {
		return uri;
	}

}
