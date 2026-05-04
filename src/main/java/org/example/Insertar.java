package org.example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Insertar {
    public static void insertar(Scanner sc){
        boolean salarioValido;
        double salario = 0;

        System.out.println("Ingrese el nombre del nuevo empleado");
        String nombre = sc.nextLine();
        do {
            salarioValido = false;
            try {
                System.out.println("Ingrese el salario del nuevo empleado");
                salario = sc.nextDouble();
                sc.nextLine();
                /**
                 * Si el salario es negativo vuelve a pedirlo
                 */
                if (salario < 0){
                    System.out.println("El salario no puede ser negativo");
                }else {
                    //si es positivo el salario es válido
                    salarioValido = true;
                }
            /**
             * Lanza esta excepción si el salario introducido no es numérico
             */
            }catch (InputMismatchException e){
                System.out.println("Salario no válido");
                sc.nextLine();
            }
        /**
         * El bucle se repite mientras salarioValido sea falso
         */
        }while (!salarioValido);

        /**
         * Consulta el último id llamando a la clase y método de consulta
         * y le suma 1 para el nuevo id
         */
        int id = Consulta.consultaUltimoID() + 1;

        /**
         * Realiza la conexión mediante Maven
         */
        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword())){
            //System.out.println("Conexión establecida con Oracle.");
            String sql = "INSERT INTO empleado (" +
                    "ID, nombre, salario) " +
                    "VALUES (?,?,?)";
            /**
             * Uso las variables para dar los valores a las ???
             */
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setDouble(3, salario);
            ps.executeUpdate();
            System.out.println("Empleado añadido con éxito con id " + id);

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}
