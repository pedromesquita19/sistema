public class Pagamento {
    private int id;
    private Agendamento agendamento;
    private double valor;
    private String metodoPagamento;
    private String status;

    public Pagamento(int id, Agendamento agendamento, double valor, String metodoPagamento) {
        this.id = id;
        this.agendamento = agendamento;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.status = "Pendente";
    }

    public void processarPagamento() {
        this.status = "Pago";
        System.out.println("Pagamento de R$" + valor + " processado com sucesso para o agendamento: " + agendamento);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
