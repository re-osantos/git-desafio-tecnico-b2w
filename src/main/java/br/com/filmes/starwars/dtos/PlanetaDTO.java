package br.com.filmes.starwars.dtos;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class PlanetaDTO implements Serializable {

	private static final long serialVersionUID = -8345321933692077649L;

	private Long id;
	private String nome;
	private String clima;
	private String terreno;
	private Long aparicaoEmFilme;

	public PlanetaDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull(message = "Nome é uma informação obrigatória")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull(message = "Clima é uma informação obrigatória")
	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	@NotNull(message = "Terreno é uma informação obrigatória")
	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public Long getAparicaoEmFilme() {
		return aparicaoEmFilme;
	}

	public void setAparicaoEmFilme(Long aparicaoEmFilme) {
		this.id = aparicaoEmFilme;
	}

	@Override
	public String toString() {
		return "ViagemDto [id=" + id + ", nome=" + nome + ", clima=" + clima
				+ ", terreno=" + terreno + ", aparicaoEmFilme=" + aparicaoEmFilme + "]";
	}	
}
