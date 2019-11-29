package br.com.filmes.starwars.services;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.filmes.starwars.dtos.PlanetaDTO;
import br.com.filmes.starwars.entities.Planeta;
import br.com.filmes.starwars.services.PlanetaServices;
import br.com.filmes.starwars.dao.PlanetaDAO;

@Service
public class PlanetaServices {

	//@Autowired
	private PlanetaDAO planetaDAO = new PlanetaDAO();
	
	public Planeta criar(PlanetaDTO planetaDTO) throws Exception {
	  Planeta planeta = new Planeta();
	  planeta.setId(planetaDTO.getId());
	  planeta.setNome(planetaDTO.getNome());
	  planeta.setClima(planetaDTO.getClima());
	  planeta.setTerreno(planetaDTO.getTerreno());
	  return planetaDAO.create(planeta);
	}	

	public void delete(Long id) throws Exception {
	  planetaDAO.delete(id);
	}	
	
	public List<Planeta> listar() {
		return planetaDAO.list();
	}
	
	public Planeta buscarPorId(Long id) {
		return planetaDAO.getById(id);
	}	

	public Planeta buscarPorNome(String nome) {
		return planetaDAO.getByNome(nome);
	}

}

