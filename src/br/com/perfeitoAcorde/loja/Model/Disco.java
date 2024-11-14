package br.com.perfeitoAcorde.loja.Model;

import br.com.perfeitoAcorde.loja.Enums.EnumEstiloDaMusica;
import br.com.perfeitoAcorde.loja.Enums.EnumTipoDoDisco;

public class Disco extends Musica{

    private EnumTipoDoDisco tipoDoDisco;
    private EnumEstiloDaMusica estiloMusica;
    private float valorDoDisco;
    private int idDisco;
    private int quantidade;

    public Disco(String nomeDaMusica, String nomeDaBanda, EnumTipoDoDisco tipoDoDisco, EnumEstiloDaMusica estiloMusica, float valorDoDisco, int quantidade, int idDisco){
        super(nomeDaMusica,nomeDaBanda);
        this.tipoDoDisco = tipoDoDisco;
        this.estiloMusica = estiloMusica;
        this.valorDoDisco = valorDoDisco;
        this.idDisco = idDisco;
        this.quantidade = quantidade;
    }

    public EnumTipoDoDisco getTipoDoDisco() {
        return tipoDoDisco;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public EnumEstiloDaMusica getEstiloMusica() {
        return estiloMusica;
    }

    public float getValorDoDisco() {
        return valorDoDisco;
    }

    public int getIdDisco(){
        return idDisco;
    }

    public void setTipoDoDisco(EnumTipoDoDisco tipoDoDisco) {
        this.tipoDoDisco = tipoDoDisco;
    }

    public void setEstiloMusica(EnumEstiloDaMusica estiloMusica) {
        this.estiloMusica = estiloMusica;
    }

    public void setValorDoDisco(float valorDoDisco) {
        this.valorDoDisco = valorDoDisco;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        String superString = super.toString();
        return String.format("%sTipo do Disco: %s \nTipo da Musica: %s \nValor do Disco: %.2f\nId: %d\nQuantidade: %d", superString, this.tipoDoDisco, this.estiloMusica, this.valorDoDisco, this.idDisco, this.quantidade);
    }
}
