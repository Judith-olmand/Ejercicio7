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
                if (salario < 0){
                    System.out.println("El salario no puede ser negativo");
                }else {
                    salarioValido = true;
                }
            }catch (InputMismatchException e){
                System.out.println("Salario no válido");
                sc.nextLine();
            }
        }while (!salarioValido);

        int id = Consulta.consultaUltimoID() + 1;

        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()); Statement statement = conn.createStatement()){
            //System.out.println("Conexión establecida con Oracle.");
            String sql = "INSERT INTO empleado (" +
                    "ID, nombre, salario) " +
                    "VALUES (?,?,?)";
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
