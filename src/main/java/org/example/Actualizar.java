package org.example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Actualizar {
    public static void actualizar(Scanner sc){
        boolean opValida;
        int opcion = -1;
        double salario = 0;
        String nombre;
        int filasActualizadas = 0;
        boolean existe = false;
        int id = -1;

        do {
            try {
                System.out.println("Indique el id del empleado a actualizar");
                id = sc.nextInt();
                sc.nextLine();
                existe = Consulta.consultaExisteID(id);
                if(!existe){
                    System.out.println("No existe el empleado a actualizar");
                }
            }catch (InputMismatchException e){
                System.out.println("Indique un id válido");
                sc.next();
            }
        }while (!existe);

        do {
            opValida = false;
            try {
                System.out.println("¿Qué campo desea actualizar?");
                System.out.println("1. Nombre");
                System.out.println("2. Salario");
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion != 1 && opcion != 2){
                    System.out.println("Opción no válida");
                }else {
                    opValida = true;
                }
            }catch (InputMismatchException e){
                System.out.println("Opción no válida");
                sc.nextLine();
            }
        }while (!opValida);


        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()); Statement statement = conn.createStatement()){
            //System.out.println("Conexión establecida con Oracle.");

            if (opcion == 1){
                System.out.println("Ingrese el nuevo nombre del empleado");
                nombre = sc.nextLine();
                String sql = "UPDATE empleado SET nombre = ? WHERE ID = ? ";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setInt(2, id);
                filasActualizadas = ps.executeUpdate();

            } else if (opcion == 2) {
                boolean salarioValido;
                do {
                    salarioValido = false;
                    try {
                        System.out.println("Ingrese el nuevo salario del empleado");
                        salario = sc.nextDouble();
                        sc.nextLine();
                        if (salario < 0){
                            System.out.println("El salario no puede ser negativo");
                        }else {
                            salarioValido = true;
                        }
                    }catch (InputMismatchException e){
                        System.out.println("Salario no válido.");
                        sc.nextLine();
                    }
                }while (!salarioValido);
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