public class EnvioInternacional extends Envio {
    private double valorFobUsd;

    public EnvioInternacional(String guia, String destinatario, String ciudadDestino, double pesoKg, double valorFobUsd) {
        super(guia, destinatario, ciudadDestino, pesoKg);
        this.valorFobUsd = valorFobUsd;
    }

    public double getValorFobUsd() {
        return valorFobUsd;
    }

    public void setValorFobUsd(double valorFobUsd) {
        this.valorFobUsd = valorFobUsd;
    }

    public boolean cumple4x4() {
        return super.getPesoKg() <= 4 && valorFobUsd <= 400;
    }

    @Override
    public double calcularCosto() {
        return (super.kilosFacturables() * 6.00) + 20.00;
    }

    @Override
    public String describir() {
        return super.describir() + " | Valor compra USD: " + valorFobUsd;
    }
}