package br.edu.ifce.retromarket.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.retromarket.dtos.AnuncioResponseDTO;
import br.edu.ifce.retromarket.dtos.CategoriaResumoDTO;
import br.edu.ifce.retromarket.dtos.PlataformaResumoDTO;
import br.edu.ifce.retromarket.entities.Anuncio;
import br.edu.ifce.retromarket.entities.Completude;
import br.edu.ifce.retromarket.repositories.AnuncioRepository;
import br.edu.ifce.retromarket.repositories.CompletudeRepository;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private CompletudeRepository repository;

    public List<AnuncioResponseDTO> listarAnuncios() {
      List<Anuncio> anuncios = anuncioRepository.findAll();

      List<AnuncioResponseDTO> anunciosDTO = new ArrayList<>();

    for (Anuncio anuncio : anuncios) {
        anunciosDTO.add(toAnuncioResponseDTO(anuncio));
}
return anunciosDTO;
    }

    public Completude criarCompletude(Completude completude) {
        return repository.save(completude);
    }

    public List<Completude> buscarCompletudes() {
        return repository.findAll();
  }

    private AnuncioResponseDTO toAnuncioResponseDTO(Anuncio anuncio){
        AnuncioResponseDTO anuncioResponseDTO = new AnuncioResponseDTO();

        anuncioResponseDTO.setId(anuncio.getId());
        anuncioResponseDTO.setTitulo(anuncio.getTitulo());
        anuncioResponseDTO.setPreco(anuncio.getPreco());
        anuncioResponseDTO.setLocalizacao(anuncio.getLocalizacao());

        anuncioResponseDTO.setCondicao(anuncio.getCondicao().getDescricao());
        anuncioResponseDTO.setCompletude(anuncio.getCompletude().getDescricao());
        anuncioResponseDTO.setStatus(anuncio.getStatus().getDescricao());

        if (!anuncio.getFotos().isEmpty()) {
            anuncioResponseDTO.setUrlFotoPrincipal(anuncio.getFotos().getFirst().getUrl());
}

        anuncioResponseDTO
            .setPlataforma(new PlataformaResumoDTO(anuncio.getPlataforma().getId(), anuncio.getPlataforma().getNome()));

        anuncioResponseDTO
            .setCategoria(new CategoriaResumoDTO(anuncio.getCategoria().getId(), anuncio.getCategoria().getNome()));

        return anuncioResponseDTO;
    }
}
