package br.com.perfeitoAcorde.loja.Enums;

public enum EnumTipoDaConta {

    ADM(0),
    FUNCIONARIO(1),
    CLIENTE(2);

    int valor;

    EnumTipoDaConta(int valor){
        this.valor = valor;
    }

}
