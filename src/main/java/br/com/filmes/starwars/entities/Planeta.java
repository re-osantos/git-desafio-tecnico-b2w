package br.com.filmes.starwars.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

import br.com.filmes.starwars.swapi.ArgumentSwitcher;

@Entity
@Component
/*
 * @Table(name = "planeta")
 */
public class Planeta implements Serializable {

	private static final long serialVersionUID = -6081542563201514002L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "clima", nullable = false)
	private String clima;
	
	@Column(name = "terreno", nullable = false)
	private String terreno;
	
	@Column(name = "aparicoesEmFilmes", nullable = false)
	private int aparicoesEmFilmes;
	
	public Planeta() {

	}

	public Planeta(Long id, String nome, String clima, String terreno, int aparicoesEmFilmes) {
		this.id = id;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.aparicoesEmFilmes = aparicoesEmFilmes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getAparicoesEmFilmes() {
		if (!"".equals(this.getNome())||this.getNome()!=null){
          ArgumentSwitcher argumentSwitcher = new ArgumentSwitcher();
	      return argumentSwitcher.getQtdAparicoesEmFilmes("planets", this.getNome().replace(" ", "&nbsp;"));
		}
		return 0;
	}

	public void setAparicoesEmFilmes(int aparicoesEmFilmes) {
		this.aparicoesEmFilmes = aparicoesEmFilmes;
	}	
	
	@Override
	public String toString() {
		return "Planeta [id=" + id + ", nome=" + nome + ", clima=" + clima
				+ ", terreno=" + terreno + ", aparicoesEmFilmes=" + aparicoesEmFilmes + "]";
	}	
	
}
