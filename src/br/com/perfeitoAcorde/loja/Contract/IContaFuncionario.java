package br.com.perfeitoAcorde.loja.Contracts;

import br.com.perfeitoAcorde.loja.Enums.EnumEstiloDaMusica;
import br.com.perfeitoAcorde.loja.Enums.EnumTipoDoDisco;
import br.com.perfeitoAcorde.loja.Model.Disco;

public interface IContaFuncionario {
    Disco cadastrarDisco(String nomeDaMusica, String nomeDaBanda, EnumTipoDoDisco tipoDoDisco, EnumEstiloDaMusica estiloMusica, float valorDoDisco, int quantidade, int idDisco);
}
