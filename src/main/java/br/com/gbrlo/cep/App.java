import br.com.gbrlo.cep.model.Endereco;

void main() {

    Endereco endereco = new Endereco(
            "01001000",
            "Praça da Sé",
            null,
            "100A",
            "Sé",
            "São Paulo",
            "SP"
    );

    IO.println(endereco);
}
