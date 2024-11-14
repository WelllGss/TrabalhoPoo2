package br.com.perfeitoAcorde.loja.Contracts;

import br.com.perfeitoAcorde.loja.Enums.EnumEstiloDaMusica;
import br.com.perfeitoAcorde.loja.Enums.EnumTipoDoDisco;
import br.com.perfeitoAcorde.loja.Model.Disco;

import java.util.ArrayList;

public interface IContaAdm {

    Disco cadastrarDisco(String nomeDaMusica, String nomeDaBanda, EnumTipoDoDisco tipoDoDisco, EnumEstiloDaMusica estiloMusica, float valorDoDisco, int quantidade, int idDisco);
    void alterarDiscoNomeDaBanda(Disco disco, String nomeDaBanda);
    void alterarDiscoNomeDaMusica(Disco disco, String nomeDaBanda);
    void alterarValorDoDisco(Disco disco, float valor);


}
