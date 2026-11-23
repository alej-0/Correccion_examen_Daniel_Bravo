public class EnvioNacional extends Envio {
    private boolean entregaDomicilio;

    public EnvioNacional(String guia, String destinatario, String ciudadDestino, double pesoKg, boolean entregaDomicilio) {
        super(guia, destinatario, ciudadDestino, pesoKg);
        this.entregaDomicilio = entregaDomicilio;
    }

    public boolean isEntregaDomicilio() {
        return entregaDomicilio;
    }

    public void setEntregaDomicilio(boolean entregaDomicilio) {
        this.entregaDomicilio = entregaDomicilio;
    }

    @Override
    public double calcularCosto() {
        if (entregaDomicilio) {
            return super.calcularCosto() + 1.50;
        }
        return super.calcularCosto();
    }

    @Override
    public String describir() {
        return super.describir() + " | Entrega a domicilio: " + entregaDomicilio;
    }
}