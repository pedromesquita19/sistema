
import java.util.Date;

public class Agendamento {
    private int id;
    private Cliente cliente;
    private Barbeiro barbeiro;
    private String servico;
    private Date dataHora;
    private String status;

    public Agendamento(int id, Cliente cliente, Barbeiro barbeiro, String servico, Date dataHora, String status) {
        this.id = id;
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.servico = servico;
        this.dataHora = dataHora;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Agendamento [Cliente=" + cliente.getNome() + 
                ", Barbeiro=" + barbeiro.getNome() + 
                ", Serviço=" + servico + 
                ", Data e Hora=" + dataHora + 
                ", Status=" + status + "]";
    }

    public void marcar() {
        this.status = "Confirmado";
    }

    public void mudar(Date novaDataHora) {
        this.dataHora = novaDataHora;
        this.status = "Alterado";
    }

    public void cancelar() {
        this.status = "Cancelado";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
