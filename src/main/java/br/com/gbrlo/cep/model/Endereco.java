package br.com.gbrlo.cep.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Endereco {
    private String cep;
    private String logradouro;
    private String complemento;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String cep,
                    String logradouro,
                    String complemento,
                    String numero,
                    String bairro,
                    String cidade,
                    String estado) {

        setCep(cep);
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        Pattern pattern = Pattern.compile("^(\\d{2})\\.?(\\d{3})-?(\\d{3})$");
        Matcher matcher = pattern.matcher(cep);
        StringBuilder builder = new StringBuilder();

        if (matcher.matches()) {
            builder.append(matcher.group(1)).append('.');
            builder.append(matcher.group(2)).append('-');
            builder.append(matcher.group(3));
        } else {
            throw new IllegalArgumentException("CEP informado inválido.");
        }
        this.cep = builder.toString();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(logradouro);

        if (numero == null || numero.isBlank()) {
            sb.append(", sem número");
        } else {
            sb.append(", número ").append(numero);
        }

        if (complemento != null && !complemento.isBlank()) {
            sb.append(", ").append(complemento);
        }

        sb.append(", bairro ").append(bairro);
        sb.append(", ").append(cidade).append('/').append(estado);
        sb.append(" - CEP ").append(cep);
        return sb.toString();
    }
}
