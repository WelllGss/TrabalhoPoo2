package br.com.perfeitoAcorde.loja.Main;

import br.com.perfeitoAcorde.loja.Enums.EnumEstiloDaMusica;
import br.com.perfeitoAcorde.loja.Enums.EnumTipoDaConta;
import br.com.perfeitoAcorde.loja.Enums.EnumTipoDoDisco;
import br.com.perfeitoAcorde.loja.Model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    //Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    private static int CurrentIdDisco = 1;
    private static ContaAdm contaAdmLogada;
    private static ContaFuncionario contaLogadaFuncionario;
    private static ContaUsuario contaLogadaUsuario;
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<ContaAdm> contasAdm = new ArrayList<>();
    private static ArrayList<ContaFuncionario> contasFuncionario = new ArrayList<>();
    private static ArrayList<ContaUsuario> contasUsuario = new ArrayList<>();
    private static ArrayList<Disco> discos = new ArrayList<>();
    private static String senhaAdm = "123";

    public static void main(String[] args){

        int opcao;
        do {

            mainMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao){
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    switch (logarConta()){
                        case 0:
                            criarMenuAdm();
                            break;
                        case 1:
                            criarMenuFunc();
                            break;
                        case 2:
                            criarMenuUser();
                            break;
                        default:
                            System.out.println("Alguma informação foi preenchida errada");
                            break;
                    }
                    break;
                case 0:
                    System.out.println("Fechando o sistema");
                default:
                    System.out.println("Opção invalida, tente novamente");
            }

        } while (opcao != 0);

    }

    // cria o menu principal
    private static void mainMenu(){
        System.out.println("======== MENU ========");
        System.out.println("[1] - Cadastrar-se");
        System.out.println("[2] - Logar");
        System.out.println("[0] - Sair");
        System.out.println("======================");
        System.out.println("Escolha uma opção: ");
    }

    // cria o menu do adm
    private static void criarMenuAdm() {
        int opcaoMenu;
        do {
            System.out.println("======== MENU - ADM ========");
            System.out.println("[1] - Cadastrar Disco");
            System.out.println("[2] - Alterar Disco");
            System.out.println("[3] - Visualizar Discos");
            System.out.println("[4] - Deletar Discos");
            System.out.println("[0] - Sair");
            System.out.println("============================");
            System.out.println("Escolha uma opção: ");

            opcaoMenu = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoMenu) {
                case 1:
                    cadastrarDisco();
                    break;
                case 2:
                    visualizarDiscos();
                    if (!discos.isEmpty()){
                        alterarDisco();
                    }
                    break;
                case 3:
                    visualizarDiscos();
                    break;
                case 4:
                    boolean deleted = false;
                    visualizarDiscos();
                    if (!discos.isEmpty()) {
                        System.out.println("Digite o id do disco que deseja remover: ");
                        int idDelete = scanner.nextInt();
                        for (Disco disco : discos) {
                            if (disco.getIdDisco() == idDelete) {
                                idDelete -= 1;
                                discos.remove(idDelete);
                                System.out.println("Disco deletado com sucesso");
                                deleted = true;
                                break;
                            }
                        }
                        if (!deleted) {
                            System.out.println("Id não encontrado");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção selecionada errada!");
            }
        } while (opcaoMenu != 0);
    }

    private static void criarMenuFunc(){
        int opcaoMenu;
        do {
            System.out.println("======== MENU - FUNCIONARIO ========");
            System.out.println("[1] - Cadastrar Disco");
            System.out.println("[2] - Visualizar Discos");
            System.out.println("[0] - Sair");
            System.out.println("============================");
            System.out.println("Escolha uma opção: ");

            opcaoMenu = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoMenu) {
                case 1:
                    cadastrarDiscoFunc();
                    break;
                case 2:
                    visualizarDiscos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção selecionada errada!");
            }
        } while (opcaoMenu != 0);
    }

    // cria o menu do usuario
    private static void criarMenuUser(){
        int opcaoMenu;
        do{
            System.out.println("======== MENU =========");
            System.out.printf("Seu Saldo: %s\n", contaLogadaUsuario.getDinheiro());
            System.out.println("[1] - Comprar Discos");
            System.out.println("[2] - Depositar");
            System.out.println("[3] - Inventario");
            System.out.println("[0] - Sair");
            System.out.println("=======================");
            System.out.println("Escolha uma opção: ");
            opcaoMenu = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoMenu) {
                case 1:
                    if (contaLogadaUsuario.getDinheiro() == 0){
                        System.out.println("Voce não pode olhar os discos se estiver com saldo em 0");
                        break;
                    }
                    visualizarDiscos();
                    // pega id para a compra
                    if (!discos.isEmpty()) {
                        System.out.println("Informe o id do disco que deseja comprar: ");
                        int idCompra = scanner.nextInt();
                        scanner.nextLine();

                        // verifica o status da compra
                        if (!contaLogadaUsuario.comprar(discos, idCompra)) {
                            System.out.println("Voce informou o id errado ou esta sem saldo para a compra");
                            break;
                        }
                        atualizarDisco();
                    }
                    break;
                case 2:
                    System.out.println("Informe o valor que deseja depositar: ");
                    float valor = scanner.nextFloat();
                    if(contaLogadaUsuario.depositar(valor)){
                        System.out.println("Deposito realizado com sucesso!");
                    } else {
                        System.out.println("Houve algum erro");
                    }
                    break;
                case 3:
                    contaLogadaUsuario.verInventario();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção selecionada errada!");
            }
        } while (opcaoMenu !=0);
    }

    // Menu para criar a conta
    //
    private static void cadastrarConta(){
        //condição do loop
        boolean condition = true;
        System.out.println("Digite o nome do usuario: ");
        String nome = scanner.nextLine();

        System.out.println("Digite a senha que deseja: ");
        String senha = scanner.nextLine();
        //loop de escolha do usuario
        while (condition){
            System.out.println("Escolha o tipo da conta: ");
            System.out.println("[1] - ADM");
            System.out.println("[2] - FUNCIONARIO");
            System.out.println("[3] - USUARIO");
            EnumTipoDaConta tipoDaConta;
            int opcao = scanner.nextInt();
            scanner.nextLine(); //consome o buff

            switch(opcao){
                case 1:
                    System.out.println("Digite a senha de ADM");
                    String tentativa = scanner.nextLine();

                    if (tentativa.equals(senhaAdm)){
                        tipoDaConta = EnumTipoDaConta.ADM;
                        ContaAdm adm = new ContaAdm(nome, senha, tipoDaConta);
                        contasAdm.add(adm);
                        condition = false;
                        break;
                    }
                    System.out.println("Senha invalida");
                    break;
                case 2:
                    tipoDaConta = EnumTipoDaConta.FUNCIONARIO;
                    ContaFuncionario funcionario = new ContaFuncionario(nome, senha, tipoDaConta);
                    contasFuncionario.add(funcionario);
                    condition = false;
                    break;
                case 3:
                    tipoDaConta = EnumTipoDaConta.CLIENTE;
                    ContaUsuario usuario = new ContaUsuario(nome, senha, tipoDaConta);
                    contasUsuario.add(usuario);
                    condition = false;
                    break;
                default:
                    System.out.println("Opção invalida!");
            }
        }
        System.out.println("Usuario Cadastrado com sucesso!");
    }

    private static int logarConta(){
        System.out.println("Digite o usuario: ");
        String user = scanner.nextLine();
        System.out.println("Digite a senha: ");
        String senha = scanner.nextLine();
        System.out.println("Qual tipo de login: ");
        System.out.println("[1] - ADM");
        System.out.println("[2] - FUNCIONARIO");
        System.out.println("[3] - USUARIO");
        int tipoLogin = scanner.nextInt();
        scanner.nextLine();

        if (tipoLogin == 1){
            for (ContaAdm contas : contasAdm){
                if (contas.getNomeDeUsuario().equals(user) && contas.getSenha().equals(senha)){
                    contaAdmLogada = contas;
                    return 0;
                }
            }
        } else if (tipoLogin == 2) {
            for (ContaFuncionario contas : contasFuncionario){
                if (contas.getNomeDeUsuario().equals(user) && contas.getSenha().equals(senha)){
                    contaLogadaFuncionario = contas;
                    return 1;
                }
            }
        } else if (tipoLogin == 3) {
            for (ContaUsuario contas : contasUsuario){
                if (contas.getNomeDeUsuario().equals(user) && contas.getSenha().equals(senha)){
                    contaLogadaUsuario = contas;
                    return 2;
                }
            }
        } else {
            System.out.println("Opção invalida!");
        }
        return -1;
    }

    private static void visualizarDiscos(){
        if (discos.isEmpty()){
            System.out.println("Não há discos cadastrados");
        } else {
            System.out.println("\n======Lista de Discos======");
            for (Disco disco : discos){
                System.out.println(disco);
            }
        }
    }

    private static void cadastrarDisco(){
        EnumTipoDoDisco tipoDoDisco;
        EnumEstiloDaMusica tipoDaMusica;
        System.out.println("Digite o nome da Musica: ");
        String nomeDaMusica = scanner.nextLine();
        System.out.println("Digite o nome da banda: ");
        String nomeDaBanda = scanner.nextLine();
        System.out.println("Tipo do disco:");
        System.out.println("[1] - VINIL");
        System.out.println("[2] - CD");
        int opcaoTipoDoDisco = scanner.nextInt();
        scanner.nextLine();

        // verificação do tipo do disco
        if (opcaoTipoDoDisco == 1) {
            tipoDoDisco = EnumTipoDoDisco.VINIL;
        } else {
            tipoDoDisco = EnumTipoDoDisco.CD;
        }
        // verifica o estilo da musica
        System.out.println("Tipo da musica:");
        System.out.println("[1] - ROCK");
        System.out.println("[2] - METAL");
        int opcaoTipoDaMusica = scanner.nextInt();
        scanner.nextLine();
        if (opcaoTipoDoDisco == 1) {
            tipoDaMusica = EnumEstiloDaMusica.ROCK;
        } else {
            tipoDaMusica = EnumEstiloDaMusica.METAL;
        }

        System.out.println("Digite o valor do disco: ");
        float valorDoDisco = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Digite a quantidade de discos: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        int idDisco = CurrentIdDisco;

        Disco disc = contaAdmLogada.cadastrarDisco(nomeDaMusica, nomeDaBanda, tipoDoDisco, tipoDaMusica, valorDoDisco, quantidade, idDisco);
        if (disc != null) {
            System.out.println("Disco cadastrado");
            CurrentIdDisco += 1;
            discos.add(disc);
        } else {
            System.out.println("Disco não cadastrado");
        }
    }

    private static void cadastrarDiscoFunc(){
        Disco disc;
        EnumTipoDoDisco tipoDoDisco;
        EnumEstiloDaMusica tipoDaMusica;
        System.out.println("Digite o nome da Musica: ");
        String nomeDaMusica = scanner.nextLine();
        System.out.println("Digite o nome da banda: ");
        String nomeDaBanda = scanner.nextLine();
        System.out.println("Tipo do disco:");
        System.out.println("[1] - VINIL");
        System.out.println("[2] - CD");
        int opcaoTipoDoDisco = scanner.nextInt();
        scanner.nextLine();

        // verificação do tipo do disco
        if (opcaoTipoDoDisco == 1) {
            tipoDoDisco = EnumTipoDoDisco.VINIL;
        } else {
            tipoDoDisco = EnumTipoDoDisco.CD;
        }
        // verifica o estilo da musica
        System.out.println("Tipo da musica:");
        System.out.println("[1] - ROCK");
        System.out.println("[2] - METAL");
        int opcaoTipoDaMusica = scanner.nextInt();
        scanner.nextLine();
        if (opcaoTipoDaMusica == 1) {
            tipoDaMusica = EnumEstiloDaMusica.ROCK;
        } else {
            tipoDaMusica = EnumEstiloDaMusica.METAL;
        }

        System.out.println("Digite o valor do disco: ");
        float valorDoDisco = scanner.nextFloat();
        System.out.println("Digite a quantidade de discos: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        int idDisco = CurrentIdDisco;

        disc = contaLogadaFuncionario.cadastrarDisco(nomeDaMusica, nomeDaBanda, tipoDoDisco, tipoDaMusica, valorDoDisco, quantidade, idDisco);
        if (disc != null) {
            System.out.println("Disco cadastrado");
            CurrentIdDisco += 1;
            discos.add(disc);
        } else {
            System.out.println("Disco não cadastrado");
        }
    }

    private static void alterarDisco(){
        System.out.println("Digite o id do disco que deseja alterar a informação: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Disco disco : discos) {
            if (disco.getIdDisco() == id) {
                System.out.println("[1] - Nome da banda");
                System.out.println("[2] - Nome da musica");
                System.out.println("[3] - Valor do Disco");
                System.out.println("Qual informação deseja alterar: ");
                int opcaoAlteracao = scanner.nextInt();
                scanner.nextLine();

                if (opcaoAlteracao == 1) {
                    System.out.println("Digite o nome da banda correto");
                    String nomeBanda = scanner.nextLine();
                    contaAdmLogada.alterarDiscoNomeDaBanda(disco, nomeBanda);
                    System.out.println("Nome alterado");
                } else if (opcaoAlteracao == 2) {
                    System.out.println("Digite o nome da musica correto: ");
                    String nomeMusica = scanner.nextLine();
                    contaAdmLogada.alterarDiscoNomeDaBanda(disco, nomeMusica);
                    System.out.println("Banda alterado");
                } else if (opcaoAlteracao == 3) {
                    System.out.println("Digite o valor do disco: ");
                    float valorDisco = scanner.nextFloat();
                    contaAdmLogada.alterarValorDoDisco(disco, valorDisco);
                    System.out.println("valor alterado");
                }
            }
        }
    }

    private static void atualizarDisco() {
        // usei o iterator como forma de remover os elementos da minha lista quando a quantidade chega a 0 de forma mais segura
        // pois ele fornece um metodo remove onde posso remover de forma mais eficiente enquanto intero sobre a lista
        Iterator<Disco> iterator = discos.iterator();
        while (iterator.hasNext()) {
            Disco disco = iterator.next();
            if (disco.getQuantidade() == 0) {
                iterator.remove();
            }
        }
    }
}
