import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.time.ZoneId;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Barbeiro> barbeiros = new ArrayList<>();
    private static Gerente gerente = new Gerente(1, "Carlos");

    public static void main(String[] args) {
       
        inicializarDados();

       
        exibirTelaLogin();
    }

    
    public static void inicializarDados() {
       
        Barbeiro barbeiro1 = new Barbeiro(1, "Pedro", "Corte de Cabelo e Barba");
        Barbeiro barbeiro2 = new Barbeiro(2, "Lucas", "Corte de Cabelo");
        barbeiros.add(barbeiro1);
        barbeiros.add(barbeiro2);

        
        Cliente cliente1 = new Cliente(1, "João", "9999-9999", "joao@email.com", "senha123");
        Cliente cliente2 = new Cliente(2, "Maria", "8888-8888", "maria@email.com", "senha456");
        clientes.add(cliente1);
        clientes.add(cliente2);
    }

   
    public static void exibirTelaLogin() {
        System.out.println("===== Bem-vindo ao Sistema de Barbearia =====");
        System.out.println("Selecione seu tipo de usuário:");
        System.out.println("1 - Cliente");
        System.out.println("2 - Barbeiro");
        System.out.println("3 - Gerente");
        System.out.println("0 - Sair");

        int opcao = scanner.nextInt();
        scanner.nextLine();  

        switch (opcao) {
            case 1:
                menuCliente();
                break;
            case 2:
                menuBarbeiro();
                break;
            case 3:
                menuGerente();
                break;
            case 0:
                System.out.println("Saindo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                exibirTelaLogin();
                break;
        }
    }

    
    public static void menuCliente() {
        System.out.println("===== Menu Cliente =====");
        Cliente cliente = autenticarCliente();
        if (cliente == null) {
            System.out.println("Autenticação falhou. Tente novamente.");
            exibirTelaLogin();
            return;
        }

        while (true) {
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Agendar Serviço");
            System.out.println("2 - Cancelar Agendamento");
            System.out.println("3 - Alterar Agendamento");
            System.out.println("4 - Fazer Pagamento");
            System.out.println("0 - Logout");

            int opcao = scanner.nextInt();
            scanner.nextLine();  

            switch (opcao) {
                case 1:
                    realizarAgendamento(cliente);
                    break;
                case 2:
                    cancelarAgendamento(cliente);
                    break;
                case 3:
                    alterarAgendamento(cliente);
                    break;
                case 4:
                    realizarPagamento(cliente);
                    break;
                case 0:
                    exibirTelaLogin();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }

    
    public static void menuBarbeiro() {
        System.out.println("===== Menu Barbeiro =====");
        Barbeiro barbeiro = autenticarBarbeiro();
        if (barbeiro == null) {
            System.out.println("Autenticação falhou. Tente novamente.");
            exibirTelaLogin();
            return;
        }

        while (true) {
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Visualizar Agendamentos");
            System.out.println("0 - Logout");

            int opcao = scanner.nextInt();
            scanner.nextLine();  

            switch (opcao) {
                case 1:
                    visualizarAgendamentos(barbeiro);
                    break;
                case 0:
                    exibirTelaLogin();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }


    public static void menuGerente() {
        System.out.println("===== Menu Gerente =====");

        while (true) {
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Gerenciar Agendamentos");
            System.out.println("3 - Gerar Relatórios Detalhados");
            System.out.println("0 - Logout");

            int opcao = scanner.nextInt();
            scanner.nextLine();  

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    gerenciarAgendamentos();
                    break;
                case 3:
                    gerarRelatoriosDetalhados();
                    break;
                case 0:
                    exibirTelaLogin();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }

    
    public static Cliente autenticarCliente() {
        System.out.println("Login: ");
        String email = scanner.nextLine();
        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        for (Cliente cliente : clientes) {
            if (cliente.autenticar(email, senha)) {
                System.out.println("Autenticação bem-sucedida!");
                return cliente;
            }
        }
        return null;
    }

    public static void realizarAgendamento(Cliente cliente) {
        System.out.println("Selecione o barbeiro:");
        for (int i = 0; i < barbeiros.size(); i++) {
            System.out.println((i + 1) + " - " + barbeiros.get(i).getNome() + " (" + barbeiros.get(i).getEspecializacoes() + ")");
        }
        int escolhaBarbeiro = scanner.nextInt();
        scanner.nextLine();  

        Barbeiro barbeiroEscolhido = barbeiros.get(escolhaBarbeiro - 1);
        System.out.println("Digite o serviço que deseja agendar: ");
        String servico = scanner.nextLine();

        Agendamento agendamento = new Agendamento(clientes.size() + 1, cliente, barbeiroEscolhido, servico, new Date(), "Pendente");
        cliente.agendarServico(agendamento);
        gerente.adicionarAgendamento(agendamento);
        System.out.println("Agendamento realizado com sucesso!");
    }

    public static void cancelarAgendamento(Cliente cliente) {
        List<Agendamento> agendamentos = cliente.getHistoricoAgendamentos();
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento disponível para cancelar.");
            return;
        }

        System.out.println("Selecione o agendamento que deseja cancelar:");
        for (int i = 0; i < agendamentos.size(); i++) {
            System.out.println((i + 1) + " - " + agendamentos.get(i));
        }
        int escolha = scanner.nextInt();
        scanner.nextLine();  

        Agendamento agendamento = agendamentos.get(escolha - 1);
        gerente.gerenciarAgendamento(agendamento, "cancelar", null);
        System.out.println("Agendamento cancelado com sucesso!");
    }

    public static void alterarAgendamento(Cliente cliente) {
        List<Agendamento> agendamentos = cliente.getHistoricoAgendamentos();
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento disponível para alterar.");
            return;
        }

        System.out.println("Selecione o agendamento que deseja alterar:");
        for (int i = 0; i < agendamentos.size(); i++) {
            System.out.println((i + 1) + " - " + agendamentos.get(i));
        }
        int escolha = scanner.nextInt();
        scanner.nextLine();  

        Agendamento agendamento = agendamentos.get(escolha - 1);

       
        System.out.println("Digite a nova data e hora no formato yyyy/MM/dd HH:mm:");
        String novaDataHoraStr = scanner.nextLine();

        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime novaDataHora = LocalDateTime.parse(novaDataHoraStr, formatter);

        
        Date novaDataHoraDate = Date.from(novaDataHora.atZone(ZoneId.systemDefault()).toInstant());

       
        gerente.gerenciarAgendamento(agendamento, "mudar", novaDataHoraDate);
        System.out.println("Agendamento alterado com sucesso!");
    }

    public static void realizarPagamento(Cliente cliente) {
        List<Agendamento> agendamentos = cliente.getHistoricoAgendamentos();
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento disponível para pagamento.");
            return;
        }

        System.out.println("Selecione o agendamento que deseja pagar:");
        for (int i = 0; i < agendamentos.size(); i++) {
            System.out.println((i + 1) + " - " + agendamentos.get(i));
        }
        int escolha = scanner.nextInt();
        scanner.nextLine();  

        Agendamento agendamento = agendamentos.get(escolha - 1);

        System.out.println("Selecione o método de pagamento:");
        System.out.println("1 - Cartão de Crédito");
        System.out.println("2 - Cartão de Débito");
        System.out.println("3 - Pix");
        int metodoPagamento = scanner.nextInt();
        scanner.nextLine();  

        String tipoPagamento = "";
        switch (metodoPagamento) {
            case 1:
                tipoPagamento = "Cartão de Crédito";
                break;
            case 2:
                tipoPagamento = "Cartão de Débito";
                break;
            case 3:
                tipoPagamento = "Pix";
                break;
            default:
                System.out.println("Método de pagamento inválido.");
                return;
        }

        Pagamento pagamento = new Pagamento(agendamentos.size() + 1, agendamento, 50.0, tipoPagamento);
        pagamento.processarPagamento();
        System.out.println("Pagamento realizado com sucesso!");
    }

    
    public static Barbeiro autenticarBarbeiro() {
        System.out.println("Digite seu nome de usuário (nome do barbeiro):");
        String nome = scanner.nextLine();

        for (Barbeiro barbeiro : barbeiros) {
            if (barbeiro.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Autenticação bem-sucedida!");
                return barbeiro;
            }
        }
        return null;
    }

    public static void visualizarAgendamentos(Barbeiro barbeiro) {
        System.out.println("Agendamentos do barbeiro " + barbeiro.getNome() + ":");
        List<Agendamento> agendamentos = new ArrayList<>(); 
        for (Agendamento agendamento : gerente.getAgendamentos()) {
            if (agendamento.getBarbeiro().equals(barbeiro)) {
                agendamentos.add(agendamento);
                System.out.println(agendamento);
            }
        }
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado para este barbeiro.");
        }
    }

    
    public static void cadastrarCliente() {
        System.out.println("Digite o nome do novo cliente: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o contato do novo cliente: ");
        String contato = scanner.nextLine();
        System.out.println("Digite o e-mail do novo cliente: ");
        String email = scanner.nextLine();
        System.out.println("Digite a senha do novo cliente: ");
        String senha = scanner.nextLine();

        Cliente novoCliente = new Cliente(clientes.size() + 1, nome, contato, email, senha);
        gerente.cadastrarCliente(clientes, novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public static void gerenciarAgendamentos() {
        List<Agendamento> agendamentos = gerente.getAgendamentos();
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento disponível para gerenciar.");
            return;
        }

        System.out.println("Selecione o agendamento que deseja gerenciar:");
        for (int i = 0; i < agendamentos.size(); i++) {
            System.out.println((i + 1) + " - " + agendamentos.get(i));
        }
        int escolha = scanner.nextInt();
        scanner.nextLine();  

        Agendamento agendamento = agendamentos.get(escolha - 1);

        System.out.println("Selecione uma ação:");
        System.out.println("1 - Alterar Agendamento");
        System.out.println("2 - Cancelar Agendamento");
        int acao = scanner.nextInt();
        scanner.nextLine();  

        if (acao == 1) {
            System.out.println("Digite a nova data e hora no formato yyyy/MM/dd HH:mm:");
            String novaDataHoraStr = scanner.nextLine();

            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            LocalDateTime novaDataHora = LocalDateTime.parse(novaDataHoraStr, formatter);

           
            Date novaDataHoraDate = Date.from(novaDataHora.atZone(ZoneId.systemDefault()).toInstant());
            gerente.gerenciarAgendamento(agendamento, "mudar", novaDataHoraDate);
            System.out.println("Agendamento alterado com sucesso!");
        } else if (acao == 2) {
            gerente.gerenciarAgendamento(agendamento, "cancelar", null);
            System.out.println("Agendamento cancelado com sucesso!");
        } else {
            System.out.println("Ação inválida!");
        }
    }

    public static void gerarRelatoriosDetalhados() {
        System.out.println("===== Relatório Detalhado de Agendamentos =====");
        for (Agendamento agendamento : gerente.getAgendamentos()) {
            System.out.println(agendamento);
        }
    }
}
