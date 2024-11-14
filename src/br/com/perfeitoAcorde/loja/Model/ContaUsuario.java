package br.com.perfeitoAcorde.loja.Model;

import br.com.perfeitoAcorde.loja.Contracts.IContaUsuario;
import br.com.perfeitoAcorde.loja.Enums.EnumTipoDaConta;

import java.util.ArrayList;
import java.util.Arrays;

public class ContaUsuario extends Conta implements IContaUsuario {

    int controleDeInventario;
    private ArrayList<Disco> inventarioDeDiscos = new ArrayList<>();
    float dinheiro = 0;

    public ContaUsuario (String nomeDeUsuario, String senha, EnumTipoDaConta tipoDaConta){
        super(nomeDeUsuario, senha, tipoDaConta);
    }

    public float getDinheiro() {
        return dinheiro;
    }

    public ArrayList<Disco> getInventarioDeDiscos(){
        return inventarioDeDiscos;
    }

    @Override
    public boolean depositar(float valor){
        if (valor < 0){
            return false;
        }

        this.dinheiro += valor;
        return true;
    }

    @Override
    public boolean comprar(ArrayList<Disco> discos, int id) {
        int valorIndex;
        for (Disco disco : discos) {
            if (disco.getIdDisco() == id && this.dinheiro >= disco.getValorDoDisco() && disco.getQuantidade() > 0) {
                // Verificando se o disco já está no inventário
                for (Disco invDisco : inventarioDeDiscos) {
                    if (disco.getIdDisco() == invDisco.getIdDisco()) {
                        invDisco.setQuantidade(invDisco.getQuantidade() + 1);

                        disco.setQuantidade(disco.getQuantidade() - 1);
                        valorIndex = disco.getIdDisco()-1;
                        discos.set(valorIndex, disco);

                        this.dinheiro -= disco.getValorDoDisco();
                        System.out.println("Disco comprado com sucesso e quantidade atualizada no inventário.");
                        return true;
                    }
                }

                // Caso o disco não esteja no iventario
                int quantidadeEstoque = disco.getQuantidade();
                //setta o valor de 1 para ser adicionado no estoque de forma correta
                disco.setQuantidade(1);
                //adiciona o disco com 1 no inventario
                inventarioDeDiscos.add(disco);
                //devolve o valor guardado menos 1
                disco.setQuantidade(quantidadeEstoque-1);
                valorIndex = disco.getIdDisco()-1;
                //atualiza o disco na lista
                discos.set(valorIndex, disco);
                this.dinheiro -= disco.getValorDoDisco();
                System.out.println("Disco comprado com sucesso e adicionado ao inventário.");
                return true;
            }
        }

        System.out.println("Compra não realizada. Verifique o saldo, estoque ou disco inválido.");
        return false;
    }


    @Override
    public void verInventario(){
        if (this.inventarioDeDiscos.isEmpty()){
            System.out.println("Inventario vazio");
        } else {
            System.out.println("===Iventario de discos===");
            for (Disco disco : inventarioDeDiscos){
                System.out.println(disco);
                System.out.println("===========");
            }
            System.out.println("\n");
        }
    }
}
