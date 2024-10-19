import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gerente {
    private int id;
    private String nome;
    private List<Agendamento> agendamentos;

    public Gerente(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.agendamentos = new ArrayList<>();  
    }

    public void gerenciarAgendamento(Agendamento agendamento, String acao, Date novaDataHora) {
        switch (acao.toLowerCase()) {  
            case "mudar":
                if (novaDataHora != null) {
                    agendamento.mudar(novaDataHora);  
                    System.out.println("Agendamento alterado para: " + novaDataHora);
                } else {
                    System.out.println("Erro: Data e hora inválidas para alteração.");
                }
                break;
            case "cancelar":
                agendamento.cancelar(); 
                System.out.println("Agendamento cancelado.");
                break;
            default:
                System.out.println("Ação desconhecida. Use 'mudar' ou 'cancelar'.");
                break;
        }
    }
 
    public void gerarRelatorios() {
        System.out.println("Relatórios de agendamentos:");
        for (Agendamento agendamento : agendamentos) {
            System.out.println(agendamento);
        }
    }

    public void adicionarAgendamento(Agendamento agendamento) {
        agendamentos.add(agendamento);
    }

    public void cadastrarCliente(List<Cliente> clientes, Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente cadastrado: " + cliente.getNome());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}
