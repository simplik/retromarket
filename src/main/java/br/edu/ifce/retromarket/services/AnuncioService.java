package br.edu.ifce.retromarket.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifce.retromarket.dtos.AnuncioDetalhesDTO;
import br.edu.ifce.retromarket.dtos.AnuncioResponseDTO;
import br.edu.ifce.retromarket.dtos.CategoriaResumoDTO;
import br.edu.ifce.retromarket.dtos.CompletudeDTO;
import br.edu.ifce.retromarket.dtos.CondicaoDTO;
import br.edu.ifce.retromarket.dtos.PlataformaDTO;
import br.edu.ifce.retromarket.dtos.PlataformaResumoDTO;
import br.edu.ifce.retromarket.dtos.StatusDTO;
import br.edu.ifce.retromarket.entities.Anuncio;
import br.edu.ifce.retromarket.entities.Completude;
import br.edu.ifce.retromarket.repositories.AnuncioRepository;
import br.edu.ifce.retromarket.repositories.CompletudeRepository;
import org.springframework.data.domain.Page;
@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private CompletudeRepository completudeRepository;

  public Page<AnuncioResponseDTO> listarAnuncios(Pageable pageable) {
    Page<Anuncio> anuncios = anuncioRepository.findAll(pageable);

    Page<AnuncioResponseDTO> pageDTO = anuncios.map(this::toAnuncioResponseDTO);
    return pageDTO;
  }

  public Optional<AnuncioDetalhesDTO> buscarPorId(Long id) {
    Optional<Anuncio> anuncio = anuncioRepository.findById(id);
    return toOptionalAnuncioDTO(anuncio);
  }

    public Completude criarCompletude(Completude completude) {
        return completudeRepository.save(completude);
    }

    public List<Completude> buscarCompletudes() {
        return completudeRepository.findAll();
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

    private Optional<AnuncioDetalhesDTO> toOptionalAnuncioDTO(Optional<Anuncio> anuncio) {
        return anuncio.map(a -> {
        AnuncioDetalhesDTO anuncioDTO = new AnuncioDetalhesDTO();

        anuncioDTO.setId(a.getId());
        anuncioDTO.setTitulo(a.getTitulo());
        anuncioDTO.setDescricao(a.getDescricao());
        anuncioDTO.setPreco(a.getPreco());
        anuncioDTO.setLocalizacao(a.getLocalizacao());
        anuncioDTO.setDataPublicacao(a.getDataPublicacao().toLocalDate());

        anuncioDTO.setUrlsFotos(a.getFotos().stream().map(fontoAnuncio -> fontoAnuncio.getUrl()).toList());
        anuncioDTO.setCategoria(new CategoriaResumoDTO(a.getCategoria().getId(),a.getCategoria().getNome()));
        anuncioDTO.setPlataforma(new PlataformaDTO(a.getPlataforma().getId(),a.getPlataforma().getNome(),a.getPlataforma().getFabricante(),a.getPlataforma().getGeracao()));
        anuncioDTO.setCondicao(new CondicaoDTO(a.getCondicao().getCodigo(),a.getCondicao().getDescricao()));
        anuncioDTO.setStatus(new StatusDTO(a.getStatus().getCodigo(),a.getStatus().getDescricao()));
        anuncioDTO.setCompletude(new CompletudeDTO(a.getCompletude().getCodigo(),a.getCompletude().getDescricao()));

        return anuncioDTO;
    });
}
}
