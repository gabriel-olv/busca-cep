import br.com.gbrlo.cep.model.Endereco;
import br.com.gbrlo.cep.service.Buscador;
import br.com.gbrlo.cep.service.viacep.BuscadorViaCep;

import static java.lang.IO.readln;

void main() {

    String cep = readln("Digite seu CEP> ");
    String numero = readln("Digite o número> ");
    String complemento = readln("Digite o complemento (opcional)> ");

    try {
        Buscador buscador = new BuscadorViaCep();
        Endereco endereco = buscador.buscar(cep);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);

        IO.println("O seu endereço completo é:");
        IO.println(endereco);
    } catch (RuntimeException e) {
        IO.println(e.getMessage());
    }
}
