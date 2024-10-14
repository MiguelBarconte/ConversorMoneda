package com.aluracursos.conversormoneda.main;

import com.aluracursos.conversormoneda.ui.Menu;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] arg) throws IOException, InterruptedException {
       Menu menu = new Menu();
       menu.menu();
    }

}
