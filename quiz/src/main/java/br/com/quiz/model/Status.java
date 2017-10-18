package br.com.quiz.model;

public enum Status {

	ATIVO("Ativo"), INATIVO("Inativo");

	private Status(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static Status negarStatus(Status status) {
		if (ATIVO.equals(status)) {
			return INATIVO;
		} else {
			return ATIVO;
		}
	}

}
