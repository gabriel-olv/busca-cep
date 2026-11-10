package br.com.gbrlo.cep.service.viacep;

import br.com.gbrlo.cep.model.Endereco;

public record EnderecoViaCep(String cep,
                             String logradouro,
                             String complemento,
                             String bairro,
                             String localidade,
                             String uf) {

    public Endereco paraEndereco() {
        return new Endereco(cep, logradouro, complemento, null, bairro, localidade, uf);
    }
}
