package com.aluracursos.conversormoneda.models;

import com.aluracursos.conversormoneda.exceptions.ConversionErrorException;

public class TipoCambio {
    private String moneda;
    private String cambio;
    private double resultadoCambio;
    public TipoCambio(TipoCambioOmdb moneda){
        this.moneda = moneda.base_code();
        this.cambio = moneda.target_code();
        this.resultadoCambio = Double.valueOf(moneda.conversion_result());
    }

    public String toString(double monto){
        return "Tipo de cambio: " + moneda + " a " + cambio
                + "\n $" + monto + " = $" + resultadoCambio;
    }
}
