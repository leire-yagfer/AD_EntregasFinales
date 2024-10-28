package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CRUDCliente crudCliente = new CRUDCliente();

        Scanner sc = new Scanner(System.in);
        int opcion;
        try {
            do {
                System.out.println("-------------- \n " +
                        "Elige opción:\n " +
                        "1. Añadir usuario\n " +
                        "2. Buscar usuario \n " +
                        "3. Total ingresos \n " +
                        "0. Salir ");
                opcion = sc.nextInt();

                sc.nextLine();

                switch (opcion) {
                    case 1:
                        crudCliente.aniadirUsuario(sc);
                        break;

                    case 2:
                        crudCliente.buscarUsuario(sc);
                        break;

                    case 3:
                        crudCliente.totalIngresos();
                        break;
                }
            } while (opcion != 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }//try-catch
    }//main
}//class