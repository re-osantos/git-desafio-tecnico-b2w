package br.com.filmes.starwars.controller;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.filmes.starwars.dtos.PlanetaDTO;
import br.com.filmes.starwars.entities.Planeta;
import br.com.filmes.starwars.responses.Response;
import br.com.filmes.starwars.services.PlanetaServices;


@RestController
@RequestMapping("/api/planetas")
public class GerenciadorPlanetasController {

	@Autowired
	private PlanetaServices planetaService;
	
	@PostMapping(path = "/new")
	public ResponseEntity<Response<Planeta>> cadastrar(@Valid @RequestBody PlanetaDTO planetaDTO, BindingResult result) {
		Response<Planeta> response = new Response<Planeta>();

		if (result.hasErrors()) {
		  Iterator<ObjectError> it = result.getAllErrors().iterator();
          while (it.hasNext()){ 
		    response.getErrors().add(it.next().toString());
          }
		  return ResponseEntity.badRequest().body(response);
		}
		Planeta planetaSalvo = null;
		try{
		  planetaSalvo = this.planetaService.criar(planetaDTO);
		}catch(Exception e){
          response.getErrors().add(e.getMessage());	
          response.setData(planetaSalvo);
		  return ResponseEntity.badRequest().body(response);
		}
		System.out.println("Planeta inserido - "+planetaSalvo.getNome());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planetaDTO.getId())
				.toUri();
		response.setData(planetaSalvo);
		return ResponseEntity.created(location).body(response);
	}
	
	@PostMapping(path = "/remove/{id}")
	public ResponseEntity<Response<Planeta>> remover(@PathVariable("id") Long id) {
		Response<Planeta> response = new Response<Planeta>();
		try{
		  this.planetaService.delete(id);
		}catch(Exception e){
          response.getErrors().add(e.getMessage());	
		  return ResponseEntity.badRequest().body(response);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id)
				.toUri();
		response.setData(new Planeta());
		return ResponseEntity.created(location).body(response);
	}

	@GetMapping
	public ResponseEntity<List<Planeta>> listar() {
		List<Planeta> planetas = planetaService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(planetas);
	}

	@GetMapping(path = "/id/{id}")
	public ResponseEntity<Response<Planeta>> buscar(@PathVariable("id") Long id) {
  
		Planeta planeta = planetaService.buscarPorId(id);
		Response<Planeta> response = new Response<Planeta>();
		response.setData(planeta);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}	

	@GetMapping(path = "/nome/{nome}")
	public ResponseEntity<Response<Planeta>> buscar(@PathVariable("nome") String nome) {
  
		Planeta planeta = planetaService.buscarPorNome(nome);
		Response<Planeta> response = new Response<Planeta>();
		response.setData(planeta);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}	
}
