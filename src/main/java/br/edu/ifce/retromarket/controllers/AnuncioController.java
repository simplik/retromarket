package br.edu.ifce.retromarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifce.retromarket.dtos.AnuncioResponseDTO;
import br.edu.ifce.retromarket.entities.Anuncio;
import br.edu.ifce.retromarket.entities.Completude;
import br.edu.ifce.retromarket.repositories.CompletudeRepository;
import br.edu.ifce.retromarket.services.AnuncioService;

@RestController
@RequestMapping(value = "/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService service;

  // Método para retornar a lista de
  @GetMapping("/completudes")
  public List<Completude> buscarCompletudes() {
    return service.buscarCompletudes();
  }

  @PostMapping("/completudes")
  public Completude criarCompletude(@RequestBody Completude completude) {
    return service.criarCompletude(completude);
  }

  @GetMapping
  public List<AnuncioResponseDTO> listarAnuncios() {
    return service.listarAnuncios();
  }

}
