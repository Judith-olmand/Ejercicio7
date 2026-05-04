package org.example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        boolean opValida;

        do {
            do {
                opValida = false;
                try {
                    System.out.println("Elija una opción.");
                    System.out.println("1.Insertar un nuevo empleado");
                    System.out.println("2.Mostrar todos los empleado");
                    System.out.println("3.Actualizar un empleado");
                    System.out.println("4.Eliminar un nuevo empleado");
                    System.out.println("0.Salir");
                    opcion = sc.nextInt();
                    sc.nextLine();
                    opValida = true;
                /**
                 * Lanza el siguiente error si la opción introducida no es un número
                 */
                }catch (InputMismatchException e){
                    System.out.println("Ingrese un número valido");
                    sc.nextLine();
                }
            /**
             * Repite el bucle mientras opValida sea falsa
             */
            }while (!opValida);

            /**
             * Cada opción llama a la clase correspondiente y a su método
             * pasando por parámetro cuando corresponda el Scanner
             */
            switch (opcion){
                case 1:
                    Insertar.insertar(sc);
                    System.out.println();
                    break;
                case 2:
                    Consulta.mostrarEmpleados();
                    System.out.println();
                    break;
                case 3:
                    Actualizar.actualizar(sc);
                    System.out.println();
                    break;
                case 4:
                    Eliminar.eliminarPorID(sc);
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
                }
        /**
         * El bucle se repite mientras opcion sea diferente a 0
         */
        }while (opcion!=0);
    }
}