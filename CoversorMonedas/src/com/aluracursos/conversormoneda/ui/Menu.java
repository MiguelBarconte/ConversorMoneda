package com.aluracursos.conversormoneda.ui;

import com.aluracursos.conversormoneda.models.TipoCambio;
import com.aluracursos.conversormoneda.models.TipoCambioOmdb;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    private Scanner scan = new Scanner(System.in);
    private String[] codigos = {"USD", "AUD", "CAD", "HKD", "EUR", "CHF"
    , "GBP", "ARS", "MXN", "BRL", "RUB", "JPY", "CNY"};
    private HttpClient client = HttpClient.newHttpClient();


    public void menu() throws IOException, InterruptedException {
        while(true) {
            System.out.println("*****************");
            System.out.println("> Seleccione su moneda:");
            opciones();
            String moneda1 = scan.nextLine().toUpperCase();
            System.out.println("> Pasar a:");
            String moneda2 = scan.nextLine().toUpperCase();
            System.out.println("> Monto:");
            double monto = scan.nextDouble();
            scan.nextLine();
            if(moneda1.equals("SALIR") || moneda2.equals("SALIR")){
                System.out.println("> Hasta la proxima");
                break;
            }
            if (control(moneda1) && control(moneda2)){
                convertir(moneda1, moneda2, monto);
            }else{
                System.out.println(">Alguna opcion no fue valida");
            }
            System.out.println("> Presione Enter para continuar");
            scan.nextLine();
            System.out.println();
        }

    }
    public void opciones(){
        System.out.println("""
        ******************* Opciones *******************
        USD - Dolar Americano  | AUD - Dolar Australiano
        CAD - Dolar Canadiense | HKD - Dolar Hongkones
        EUR - Euro             | CHF - Franco Suizo
        GBP - Libra Esterlina  | ARS - Pesos Argentinos
        MXN - Pesos Mexicanos  | BRL - Real Brasileño
        RUB - Rublo ruso       | JPY - Yen japones
        CNY - Yuan Chino       | SALIR
        [Ingrese el codigo de la izquierda o salir]
        """);
    }
    public boolean control(String opc1){
        int x = codigos.length;
        for (int i = 0 ; i<x; i++){
           if(opc1.equals(codigos[i])){
               return true;
           }
        }
        return false;
    }
    public void convertir(String moneda, String cambio, double monto) throws IOException, InterruptedException {
        String uri = "https://v6.exchangerate-api.com/v6/dd21e1454c88e053eeab269b/pair/"
                + moneda+"/"+cambio+"/"+monto;
        /*https://v6.exchangerate-api.com/v6/dd21e1454c88e053eeab269b/pair/ARS/USD/10000*/
        HttpRequest request = request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();;
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());;
        Gson gson = new GsonBuilder().create();
    /*private Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();*/
        String jsonResponse = response.body();
        TipoCambioOmdb tcambioOmdb = gson.fromJson(jsonResponse, TipoCambioOmdb.class);
        TipoCambio tcambio = new TipoCambio(tcambioOmdb);
        System.out.println(tcambio.toString(monto));
    }
}
