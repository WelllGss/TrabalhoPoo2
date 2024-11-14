package br.com.perfeitoAcorde.loja.Model;

import br.com.perfeitoAcorde.loja.Enums.EnumTipoDaConta;

public abstract class Conta {

    private String nomeDeUsuario;
    private String senha;
    private EnumTipoDaConta tipoDaConta;

    public Conta(String nomeDeUsuario, String senha, EnumTipoDaConta tipoDaConta){
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.tipoDaConta = tipoDaConta;
    }

    public EnumTipoDaConta getTipoDaConta(){
        return this.tipoDaConta;
    }

    public String getNomeDeUsuario() {
        return this.nomeDeUsuario;
    }

    public String getSenha() {
        return this.senha;
    }

    @Override
    public String toString() {
        return String.format("Nome de usuário: %s\n", this.nomeDeUsuario);
    }
}
