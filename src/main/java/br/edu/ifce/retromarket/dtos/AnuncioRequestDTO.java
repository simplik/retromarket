package br.edu.ifce.retromarket.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AnuncioRequestDTO {

  @NotBlank(message = "O título é obrigatório.")
  @Size(max = 150, message = "O título deve possuir no máximo 150 caracteres.")
  private String titulo;

  @NotNull(message = "O preço é obrigatório.")
  @Positive(message = "O preço deve ser maior que zero.")
  private BigDecimal preco;

  @NotBlank(message = "A localização é obrigatória.")
  private String localizacao;

  @NotBlank(message = "A descrição é obrigatória.")
  private String descricao;

  private List<String> urlsFotos;

  @NotNull (message = "A categoria é obrigatória.")
  private Long idCategoria;

  @NotNull(message = "A plataforma é obrigatória.")
  private Long idPlataforma;

  @NotBlank(message = "A completude é obrigatória.")
  private String codigoCompletude;

  @NotBlank(message = "A condição é obrigatória.")
  private String codigoCondicao;

  @NotBlank(message = "O status é obrigatório.")
  private String codigoStatus;

  private Long idUsuario;
  
  public AnuncioRequestDTO() {
    this.urlsFotos = new ArrayList<>();
  }

  public AnuncioRequestDTO(String titulo, BigDecimal preco, String localizacao, String descricao,
      List<String> urlsFotos, Long idCategoria, Long idPlataforma, String codigoCompletude, String codigoCondicao,
      String codigoStatus, Long idUsuario) {
    this.titulo = titulo;
    this.preco = preco;
    this.localizacao = localizacao;
    this.descricao = descricao;
    this.urlsFotos = urlsFotos;
    this.idCategoria = idCategoria;
    this.idPlataforma = idPlataforma;
    this.codigoCompletude = codigoCompletude;
    this.codigoCondicao = codigoCondicao;
    this.codigoStatus = codigoStatus;
    this.idUsuario = idUsuario;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public String getLocalizacao() {
    return localizacao;
  }

  public void setLocalizacao(String localizacao) {
    this.localizacao = localizacao;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public List<String> getUrlsFotos() {
    return urlsFotos;
  }

  public void setUrlsFotos(List<String> urlsFotos) {
    this.urlsFotos = urlsFotos;
  }

  public Long getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(Long idCategoria) {
    this.idCategoria = idCategoria;
  }

  public Long getIdPlataforma() {
    return idPlataforma;
  }

  public void setIdPlataforma(Long idPlataforma) {
    this.idPlataforma = idPlataforma;
  }

  public String getCodigoCompletude() {
    return codigoCompletude;
  }

  public void setCodigoCompletude(String codigoCompletude) {
    this.codigoCompletude = codigoCompletude;
  }

  public String getCodigoCondicao() {
    return codigoCondicao;
  }

  public void setCodigoCondicao(String codigoCondicao) {
    this.codigoCondicao = codigoCondicao;
  }

  public String getCodigoStatus() {
    return codigoStatus;
  }

  public void setCodigoStatus(String codigoStatus) {
    this.codigoStatus = codigoStatus;
  }

  public Long getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Long idUsuario) {
    this.idUsuario = idUsuario;
  }

}