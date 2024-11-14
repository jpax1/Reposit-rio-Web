package Model;

public class Jogos {
	
	// Declarando as variáveis de acordo com o banco de dados \\
	private int id;
	private String titulo;
	private String descriçao;
	private String data_publicaçao;
	private double preço;
	
	/*Construtor sem parâmetros: Não define valores iniciais.
	Util para criar os objetos vazios e definir os valores depois*/
	public Jogos() {}
	
	// Construtot com parâmetros: Simplifica a criação de objetos com valores específicos \\
	public Jogos(String titulo, String descriçao, String data_publicaçao, double preço) {
		this.titulo = titulo;
		this.descriçao = descriçao;
		this.data_publicaçao = data_publicaçao;
		this.preço = preço;
	}
	
	//Getters e Setters\\
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescriçao() {
		return descriçao;
	}

	public void setDescriçao(String descriçao) {
		this.descriçao = descriçao;
	}

	public String getData_publicaçao() {
		return data_publicaçao;
	}

	public void setData_publicaçao(String data_publicaçao) {
		this.data_publicaçao = data_publicaçao;
	}

	public double getPreço() {
		return preço;
	}

	public void setPreço(double preço) {
		this.preço = preço;
	}
	
	//Compara se dois objetos são iguais\\
	public boolean equals(Object compared) {
		if(this == compared) {
			return true;
		}
		
		//Primeiro verifica se ambos tem a mesma instância, depois faz uma comparação campo a campo vendo se os valores são iguais\\
		Jogos jogo = (Jogos) compared;
		return this.getId() == jogo.getId() && this.getTitulo().equals(jogo.getTitulo()) && this.getDescriçao().equals(jogo.getDescriçao()) && this.getData_publicaçao().equals(jogo.getData_publicaçao()) && this.getPreço() == jogo.getPreço();
	}
}
