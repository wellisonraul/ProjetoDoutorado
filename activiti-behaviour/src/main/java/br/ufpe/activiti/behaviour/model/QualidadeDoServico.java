package br.ufpe.activiti.behaviour.model;

import java.util.Map;
public class QualidadeDoServico {
	
	private Map<Atributo, Double> qualidade;

	public Map<Atributo, Double> getQualidade() {
		return qualidade;
	}
	
	public void setQualidade(Map<Atributo, Double> qualidade) {
		this.qualidade = qualidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qualidade == null) ? 0 : qualidade.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof QualidadeDoServico))
			return false;
		QualidadeDoServico other = (QualidadeDoServico) obj;
		if (qualidade == null) {
			if (other.qualidade != null)
				return false;
		} else if (!qualidade.equals(other.qualidade))
			return false;
		return true;
	}
}
