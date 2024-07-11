package service;


public class ConvierteDatos {
    String monedaBase;
    String monedaFinal;
    String tipoDeCambio;
    String resultado;
    public ConvierteDatos(String monedaBase, String monedaFinal, String tipoDeCambio, String resultado) {
        this.monedaBase = monedaBase;
        this.monedaFinal = monedaFinal;
        this.tipoDeCambio = tipoDeCambio;
        this.resultado = resultado;
    }
    public ConvierteDatos(ExchangeRate apiConversor) {
        this.monedaBase = apiConversor.base_code();
        this.monedaFinal = apiConversor.target_code();
        this.tipoDeCambio = apiConversor.conversion_rate();
        this.resultado = apiConversor.conversion_result();
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    public String getMonedaFinal() {
        return monedaFinal;
    }

    public void setMonedaFinal(String monedaFinal) {
        this.monedaFinal = monedaFinal;
    }

    public String getTipoDeCambio() {
        return tipoDeCambio;
    }

    public void setTipoDeCambio(String tipoDeCambio) {
        this.tipoDeCambio = tipoDeCambio;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;

    }

    @Override
    public String toString() {
        return "Conversión {" +
                "monedaBase='" + monedaBase + '\'' +
                ", monedaFinal='" + monedaFinal + '\'' +
                ", tipoDeCambio='" + tipoDeCambio + '\'' +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
