//en esta clase voy a crear funciones a las que voy a llamar desde el Main para quitarme código del Main

package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class CRUDCliente {

    ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public void aniadirUsuario(Scanner sc) { //le paso el escaner del Main --> cuando llame a este método desde el main, le paso el Scanner de poder escribir
        System.out.println("Introduce un correo:");
        String identificador_correo = sc.next();
        System.out.println("Introduce una contraseña:");
        String contrasena = sc.next();
        System.out.println("Introduce un descuento:");
        double descuento = sc.nextDouble();
        System.out.println("Eres premium?(si/no): ");
        String premium = sc.next().toLowerCase(); //lo pongo en minúsculas;
        boolean premium2;
        while (!premium.equals("si") && !premium.equals("no")) { //mientras que la respuesta sea distinta de 'si' o 'no', pedir reescribirlo
            System.out.println("Introduce 'si' o 'no'.");
            premium = sc.next().toLowerCase();
        }

        if (premium.equals("si")) premium2 = true;
        else premium2 = false;

        Usuario usuario = new Usuario(identificador_correo, contrasena, descuento, premium2); //creo objeto con los datos del nuevo usuarios
        listaUsuarios.add(usuario); //añado el usuario en la lista
    }//aniadirUsuario


    public void buscarUsuario(Scanner sc) {
        boolean semaforo = false; //semáforo para ver si está o no registrado un usuario
        System.out.println("Introduce un correo:");
        String identificador_correo = sc.next();
        for (Usuario usuario2 : listaUsuarios) { //busco en la listaUsuarios los datos que tengo guardados en Usuario y lo guarda en usuario2
            if (usuario2.getIdentificador().equals(identificador_correo)) semaforo = true;
        }
        if (semaforo) System.out.println("El usuario ha sido encontrado correctamente.");
        else System.out.println("NO se ha encontrado un usuario con ese correo.");
    }//buscarUsuario


    public void totalIngresos() {
        double ingresos = 0;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.isPremium()) ingresos += 35.5 - usuario.getImporte_descuento();
            else ingresos += 20.5 - usuario.getImporte_descuento();
        }
        System.out.println("El total de ingresos es de " + ingresos + "€.");
    }//TotalIngresos
}//class