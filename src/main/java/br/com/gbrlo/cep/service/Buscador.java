package br.com.gbrlo.cep.service;

import br.com.gbrlo.cep.model.Endereco;

public interface Buscador {
    Endereco buscar(String cep);
}
