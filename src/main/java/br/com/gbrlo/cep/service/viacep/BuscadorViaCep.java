package br.com.gbrlo.cep.service.viacep;

import br.com.gbrlo.cep.model.Endereco;
import br.com.gbrlo.cep.service.Buscador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Pattern;

public class BuscadorViaCep implements Buscador {
    private static final String BASE_URL = "https://viacep.com.br/ws/";

    @Override
    public Endereco buscar(String cep) {
        cep = validarCep(cep);

        URI uriToRequest = URI.create(BASE_URL + cep + "/json");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriToRequest)
                .setHeader("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException();
            }

            String stringJson = response.body();

            EnderecoViaCep enderecoViaCep = new Gson().fromJson(stringJson, EnderecoViaCep.class);

            return enderecoViaCep.paraEndereco();
        } catch (Exception e) {
            throw new RuntimeException("Falha ao buscar CEP: %s".formatted(cep));
        }
    }

    private String validarCep(String cep) {
        cep = cep.replaceAll("[\\.-]", "");
        Pattern pattern = Pattern.compile("^\\d{8}$");
        if (!pattern.matcher(cep).matches()) {
            throw new RuntimeException("CEP informado inválido. Precisa ser 8 dígitos.");
        }
        return cep;
    }
}
