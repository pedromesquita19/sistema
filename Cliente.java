import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String contato;
    private String email;
    private String senha;
    private List<Agendamento> historicoAgendamentos;

    public Cliente(int id, String nome, String contato, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.email = email;
        this.senha = senha;
        this.historicoAgendamentos = new ArrayList<>();
    }

    public void agendarServico(Agendamento agendamento) {
        historicoAgendamentos.add(agendamento);
    }

    public void cancelarAgendamento(Agendamento agendamento) {
        historicoAgendamentos.remove(agendamento);
    }

    public void alterarAgendamento(Agendamento antigo, Agendamento novo) {
        cancelarAgendamento(antigo);
        agendarServico(novo);
    }

    public boolean autenticar(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Agendamento> getHistoricoAgendamentos() {
        return historicoAgendamentos;
    }

    public void setHistoricoAgendamentos(List<Agendamento> historicoAgendamentos) {
        this.historicoAgendamentos = historicoAgendamentos;
    }

}
