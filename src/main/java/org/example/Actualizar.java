package org.example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Actualizar {
    public static void actualizar(Scanner sc){
        boolean opValida;
        boolean existe = false;

        int opcion = -1;
        int id = -1;

        double salario = 0;
        String nombre;

        int filasActualizadas = 0;

        do {
            try {
                /**
                 * Pido el id del empleado y consulto si existe
                 * Llamando a la clase y al metodo de consulta
                 * y pasando por parámetro el id introducido por teclado
                 */
                System.out.println("Indique el id del empleado a actualizar");
                id = sc.nextInt();
                sc.nextLine();
                existe = Consulta.consultaExisteID(id);
                if(!existe){
                    //si no existe muestro el mensaje
                    System.out.println("No existe el empleado a actualizar");
                }
            /**
             * Lanza esta excepción si el id introducido no es numérico
             */
            }catch (InputMismatchException e){
                System.out.println("Indique un id válido");
                sc.next();
            }
        /**
         * El bucle se repite mientras existe sea falso
         */
        }while (!existe);

        do {
            opValida = false;
            try {
                /**
                 * Menú para elegir que modificar
                 */
                System.out.println("¿Qué campo desea actualizar?");
                System.out.println("1. Nombre");
                System.out.println("2. Salario");
                opcion = sc.nextInt();
                sc.nextLine();
                /**
                 * Si la opción no es ni 1 ni 2 opValida se mantiene a false
                 */
                if (opcion != 1 && opcion != 2){
                    System.out.println("Opción no válida");
                }else {
                    //sino, pasa a true
                    opValida = true;
                }
            /**
             * Lanza esta excepción si la opción introducida no es numérica
             */
            }catch (InputMismatchException e){
                System.out.println("Opción no válida");
                sc.nextLine();
            }
        /**
         * El bucle se repite mientras opValida sea falso
         */
        }while (!opValida);


        /**
         * Realiza la conexión mediante Maven
         */
        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()); Statement statement = conn.createStatement()){
            //System.out.println("Conexión establecida con Oracle.");

            if (opcion == 1){
                /**
                 * solicita el nuevo nombre y lo actualiza mediante
                 * PreparedStatement
                 */
                System.out.println("Ingrese el nuevo nombre del empleado");
                nombre = sc.nextLine();
                String sql = "UPDATE empleado SET nombre = ? WHERE ID = ? ";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setInt(2, id);
                filasActualizadas = ps.executeUpdate();

            } else if (opcion == 2) {
                /**
                 * Solicita el nuevo salario
                 */
                boolean salarioValido;
                do {
                    salarioValido = false;
                    try {
                        System.out.println("Ingrese el nuevo salario del empleado");
                        salario = sc.nextDouble();
                        sc.nextLine();
                        /**
                         * si es negativo vuelve a pedirlo
                         */
                        if (salario < 0){
                            System.out.println("El salario no puede ser negativo");
                        }else {
                            salarioValido = true;
                        }
                    /**
                     * Si el salario introducido no es numérico lanza esta excepción
                     */
                    }catch (InputMismatchException e){
                        System.out.println("Salario no válido.");
                        sc.nextLine();
                    }
                /**
                 * El bucle se repite mientras salarioValido sea false
                 */
                }while (!salarioValido);
                /**
                 * Se usan las variables para actualizar los campos
                 */
                String sql = "UPDATE empleado SET salario = ? WHERE ID = ? ";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setDouble(1, salario);
                ps.setInt(2, id);
                filasActualizadas = ps.executeUpdate();
            }
            System.out.println("Empleado modificado con éxito.");
            System.out.println("Se ha actualizado " + filasActualizadas + " empleado.");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}