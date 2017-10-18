package br.com.quiz.model;

public enum Cor {

	GREEN	("Verde", "#4CAF50", "background: #4CAF50;"),
	OLIVA_1	("Oliva 1", "#73b558", "background: #73b558;"),
	OLIVA_2	("Oliva 2", "#5f924a", "background: #5f924a;"),
	OLIVA_3	("Oliva 3", "#375a29", "background: #375a29;"),
	PURPLE_1	("Roxo 1", "#7d48dc", "background: #7d48dc;"),
	PURPLE_2	("Roxo 2", "#663ab5", "background: #663ab5;"),
	PURPLE_3	("Roxo 3", "#512d90", "background: #512d90;"),
	BLUE		("Azul", "#03A9F4", "background: #03A9F4;"),
	PURPLE	("Roxo 0", "#673AB7", "background: #673AB7;"),
	RED		("Vermelho", "#E53935", "background: #E53935;"),
	YELLOW	("Amarelo", "#FB8C00", "background: #FB8C00;"),
	GREY		("Cinza", "#d3d7cf", "background: #d3d7cf;");

	Cor(String descricao, String hexa, String css) {
		this.descricao = descricao;
		this.hexa = hexa;
		this.css = css;
	}

	private String descricao;
	private String hexa;
	private String css;

	public String getDescricao() {
		return descricao;
	}

	public String getHexa() {
		return hexa;
	}

	public String getCss() {
		return css;
	}
}
