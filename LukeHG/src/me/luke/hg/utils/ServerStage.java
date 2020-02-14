package me.luke.hg.utils;

public enum ServerStage {

	INICIANDO("Iniciando"), INVENCIBILIDADE("Invencivel"), ANDAMENTO("Andamento"), ACABOU("Acabou");
	
	private String stageName;
	
	private ServerStage(String stageName) {
		this.stageName = stageName;
	}
	
	public String getStageName() {
		return stageName;
	}
	
}
