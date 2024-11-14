package br.com.perfeitoAcorde.loja.Model;

public abstract class Musica {

    private String nomeDaMusica;
    private String banda;

    public Musica(String nomeDaMusica, String banda){
        this.nomeDaMusica = nomeDaMusica;
        this.banda = banda;
    }

    public String getNomeDaMusica(){
        return this.nomeDaMusica;
    }

    public void setNomeDaMusica(String nomeDaMusica) {
        this.nomeDaMusica = nomeDaMusica;
    }

    public String getBanda(){
        return this.banda;
    }

    public void setBanda(String banda){
        this.banda = banda;
    }

    @Override
    public String toString() {
        return String.format("Nome da Musica: %s \nNome da Banda: %s\n", this.nomeDaMusica, this.banda);
    }
}
