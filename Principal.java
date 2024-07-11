package principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import service.ConsumoAPI;
import service.ConvierteDatos;
import service.ExchangeRate;

import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
   private List<String> conversiones = new ArrayList<>();
    int contador1 = 0;
    int contador2 = 0;
    int contador3 = 0;
    int contador4 = 0;
    int contador5 = 0;
    int contador6 = 0;





    public void muestraElMenu() {

        int contadorDefault = 0;
            int opcion = -1;
            while (opcion != 0) {
                var menu = """
                        ////////////////////////////////////////////
                        Elige la opción deseada:
                                            
                        Conversiones disponibles:
                        1 - USD a MXN 
                        2 - MXN a USD
                        3 - USD a EUR
                        4 - MXN a EUR
                        5 - EUR a USD
                        6 - EUR a MXN
                        Otras opciones
                        7 - Historial de conversiones
                        8 - Total de conversiones realizadas 
                        9- Conversión más utilizada
                        0 - Salir
                        /////////////////////////////////////////////////////////
                        """;
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1:
                        dolarAPesos();
                        contador1++;

                        break;
                    case 2:
                        pesosADolar();
                        contador2++;
                        break;
                    case 3:
                        dolarAEuro();
                        contador3++;
                        break;
                    case 4:
                        pesosAEuro();
                        contador4++;
                        break;
                    case 5:
                        euroADolar();
                        contador5++;
                        break;
                    case 6:
                        euroAPesos();
                        contador6++;
                        break;
                    case 7:
                        historialDeConversiones();
                        break;
                    case 8:
                        conversionesRealizadas();
                        break;
                    case 9:
                        conversionMasUtilizada();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                        contadorDefault++;
                }
            }

    }


    private void Principal(String url) {
        System.out.println("Ingresa la cantidad que deseas convertir");
        var monto = teclado.nextLine();
        String json = consumoApi.obtenerDatos("https://v6.exchangerate-api.com/v6/2c60e480e67175e493ace74d/pair/" + url + monto);
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();
        ExchangeRate apiconversor = gson.fromJson(json, ExchangeRate.class);
        ConvierteDatos conversor = new ConvierteDatos(apiconversor);
       conversiones.add(conversor.toString());
        System.out.println("Conversión realizada\n" +
                "Moneda base: " + conversor.getMonedaBase() + " //// Moneda final: " + conversor.getMonedaFinal());
        System.out.println("La tasa de cambio es: " + conversor.getTipoDeCambio());
        System.out.println("El resultado de la conversión es: " + conversor.getResultado());

    }
    private void conversionMasUtilizada() {
        List<Integer> contadores = new ArrayList<>();
        contadores.add(contador1);
        contadores.add(contador2);
        contadores.add(contador3);
        contadores.add(contador4);
        contadores.add(contador5);
        contadores.add(contador6);
        contadores.sort(Collections.reverseOrder());
        var popular = contadores.get(0);
        if(popular == contador1) {
            System.out.println("USD/MXN es la conversión más utilizada con " + contador1 + (popular == 1 ? " vez realizada \n" : " veces realizada \n"));
        }
        else if (popular == contador2){
            System.out.println("MXN/USD es la conversión más utilizada con  " + contador2 + (popular == 1 ? " vez realizada \n" : " veces realizada \n"));
        }
        else if (popular == contador3){
            System.out.println("USD/EUR es la conversión más utilizada con  " + contador3 + (popular == 1 ? " vez realizada \n" : " veces realizada \n"));
        }
        else if (popular == contador4){
            System.out.println("MXN/EUR es la conversión más utilizada con  " + contador4 + (popular == 1 ? " vez realizada \n" : " veces realizada \n"));
        }
        else if (popular == contador5){
            System.out.println("EUR/USD es la conversión más utilizada con  " + contador5 + (popular == 1 ? " vez realizada \n" : " veces realizada \n"));
        }
        else if (popular == contador6){
            System.out.println("EUR/MXN es la conversión más utilizada con  " + contador6 + (popular == 1 ? " vez realizada \n" : " veces realizada \n"));
        } else {
            System.out.println("la evaluación falló");
        }
    }


    private void conversionesRealizadas() {
        System.out.printf("USD/MXN = " + contador1 + (contador1 == 1 ? " vez ejecutado \n" : " veces ejecutado \n") +
                "MXN/USD = " + contador2 +  (contador2 == 1 ? " vez ejecutado \n" : " veces ejecutado \n") +
                "USD/EUR = " + contador3 +  (contador3 == 1 ? " vez ejecutado \n" : " veces ejecutado \n") +
                "MXN/EUR = " + contador4 +  (contador4 == 1 ? " vez ejecutado \n" : " veces ejecutado \n") +
                "EUR/USD = " + contador5 +  (contador5 == 1 ? " vez ejecutado \n" : " veces ejecutado \n") +
                "EUR/MXN = " + contador6 +  (contador6 == 1 ? " vez ejecutado \n" : " veces ejecutado \n"));


    }

    private void historialDeConversiones() {
        conversiones.forEach(System.out::println);


    }

    private void euroAPesos() {
        Principal("EUR/MXN/");
    }

    private void euroADolar() {
        Principal("EUR/USD/");
    }

    private void pesosAEuro() {
        Principal("MXN/EUR/");
    }

    private void dolarAEuro() {
        Principal("USD/EUR/");
    }

    private void pesosADolar() {
        Principal ("MXN/USD/");
    }

    private void dolarAPesos() {
        Principal("USD/MXN/");
    }
}
