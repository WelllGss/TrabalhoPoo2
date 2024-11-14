package br.com.perfeitoAcorde.loja.Model;

import br.com.perfeitoAcorde.loja.Contracts.IContaFuncionario;
import br.com.perfeitoAcorde.loja.Enums.EnumEstiloDaMusica;
import br.com.perfeitoAcorde.loja.Enums.EnumTipoDaConta;
import br.com.perfeitoAcorde.loja.Enums.EnumTipoDoDisco;

import java.util.ArrayList;

public class ContaFuncionario extends Conta implements IContaFuncionario {

    public ContaFuncionario (String nomeDeUsuario, String senha, EnumTipoDaConta tipoDaConta){
        super(nomeDeUsuario, senha, tipoDaConta);
    }

    @Override
    public Disco cadastrarDisco(String nomeDaMusica, String nomeDaBanda, EnumTipoDoDisco tipoDoDisco, EnumEstiloDaMusica estiloMusica, float valorDoDisco, int quantidade, int idDisco){
        if (valorDoDisco > 0 && nomeDaBanda.length() > 3){
            Disco disco = new Disco(nomeDaMusica, nomeDaBanda, tipoDoDisco, estiloMusica, valorDoDisco, quantidade, idDisco);
            return disco;
        }
        return null;
    }
}
