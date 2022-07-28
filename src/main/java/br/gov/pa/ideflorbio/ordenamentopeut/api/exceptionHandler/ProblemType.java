package br.gov.pa.ideflorbio.ordenamentopeut.api.exceptionHandler;


public enum ProblemType {
	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encotrada", "Entidade não encontrada");
	
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
