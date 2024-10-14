package com.aluracursos.conversormoneda.exceptions;

public class ConversionErrorException  extends RuntimeException{
    private String mensaje;
    public ConversionErrorException(String mensaje){
        this.mensaje = mensaje;
    }
    @Override
    public String getMessage(){
        return this.mensaje;
    }
}
