import java.util.ArrayList;
import java.util.List;

public class Barbeiro {
    private int id;
    private String nome;
    private String especializacoes;
    private List<Agendamento> agendamentos;

    public Barbeiro(int id, String nome, String especializacoes) {
        this.id = id;
        this.nome = nome;
        this.especializacoes = especializacoes;
        this.agendamentos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void visualizarAgendamentos() {
        for (Agendamento agendamento : agendamentos) {
            System.out.println(agendamento);
        }
    }

    public void adicionarAgendamento(Agendamento agendamento) {
        this.agendamentos.add(agendamento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecializacoes() {
        return especializacoes;
    }

    public void setEspecializacoes(String especializacoes) {
        this.especializacoes = especializacoes;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    } 
}
