import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SistemaGerenciamento {
    public static void main(String[] args) {
        
        List<Cliente> clientes = new ArrayList<>();
        List<Barbeiro> barbeiros = new ArrayList<>();
        Gerente gerente = new Gerente(1, "Carlos");

        Barbeiro barbeiro = new Barbeiro(1, "Pedro", "Corte de Cabelo");
        barbeiros.add(barbeiro);

        Cliente cliente = new Cliente(1, "João", "9999-9999", "joao@email.com", "senha123");
        clientes.add(cliente);

        Agendamento agendamento = new Agendamento(1, cliente, barbeiro, "Corte de Cabelo", new Date(), "Pendente");

        gerente.adicionarAgendamento(agendamento);
        
        cliente.agendarServico(agendamento);
        agendamento.marcar();
        System.out.println("Agendamento realizado: " + agendamento);
        
        Pagamento pagamento = new Pagamento(1, agendamento, 50.0, "Cartão de Crédito");
        pagamento.processarPagamento();
        
        Date novaDataHora = new Date(System.currentTimeMillis() + 3600000);  
        gerente.gerenciarAgendamento(agendamento, "mudar", novaDataHora);
        
        gerente.gerarRelatorios();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login: ");
        String email = scanner.next();
        System.out.println("Senha: ");
        String senha = scanner.next();
        
        if (cliente.autenticar(email, senha)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Credenciais incorretas!");
        }
        
        Cliente novoCliente = new Cliente(2, "Maria", "8888-8888", "maria@email.com", "senha456");
        gerente.cadastrarCliente(clientes, novoCliente);
    }
}
