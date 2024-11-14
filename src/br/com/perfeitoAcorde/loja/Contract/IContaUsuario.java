package br.com.perfeitoAcorde.loja.Contracts;

import br.com.perfeitoAcorde.loja.Model.Disco;

import java.util.ArrayList;

public interface IContaUsuario {

    boolean depositar(float valor);
    boolean comprar(ArrayList<Disco> discos, int id);
    void verInventario();

}
