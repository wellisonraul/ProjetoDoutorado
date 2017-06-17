package br.ufpe.activiti.behaviour.model;

public class Atributo {

	private String name;
	private boolean isGreaterValuesBetter;
	
	public Atributo() {
		super();
	}
	
	public Atributo(String name, boolean isGreaterValuesBetter) {
		super();
		this.name = name;
		this.isGreaterValuesBetter = isGreaterValuesBetter;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isGreaterValuesBetter() {
		return isGreaterValuesBetter;
	}
	public void setGreaterValuesBetter(boolean isGreaterValuesBetter) {
		this.isGreaterValuesBetter = isGreaterValuesBetter;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
