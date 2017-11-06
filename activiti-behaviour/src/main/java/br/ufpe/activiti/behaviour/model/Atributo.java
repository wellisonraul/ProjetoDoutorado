package br.ufpe.activiti.behaviour.model;

public class Atributo {

	private String nome;
	private double valor;
	private boolean eMelhorOValorMaior;
	private int peso;
	
	public Atributo() {
		super();
	}
	
	public Atributo(String nome, boolean eMelhorOValorMaior, double valor, int peso) {
		super();
		this.nome = nome;
		this.eMelhorOValorMaior = eMelhorOValorMaior;
		this.valor = valor;
		this.peso = peso;
	}
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getEMelhorOValorMaior() {
		return eMelhorOValorMaior;
	}

	public void setEMelhorOValorMaior(boolean eMelhorOValorMaior) {
		this.eMelhorOValorMaior = eMelhorOValorMaior;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Atributo))
			return false;
		Atributo other = (Atributo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
