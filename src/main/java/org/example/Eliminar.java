package org.example;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Eliminar {
    public static void eliminarPorID(Scanner sc){
        boolean existe = false;
        int id = 0;

        do {
            try {
                System.out.println("Indique el id del empleado a eliminar");
                id = sc.nextInt();
                sc.nextLine();
                existe = Consulta.consultaExisteID(id);
                if(!existe){
                    System.out.println("No existe el empleado a eliminar");
                }
            }catch (InputMismatchException e){
                System.out.println("Indique un id válido");
                sc.next();
            }

        }while (!existe);

        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()); Statement statement = conn.createStatement()){
            //System.out.println("Conexión establecida con Oracle.");
            String sql = "DELETE FROM empleado WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int filasEliminadas = ps.executeUpdate();
            System.out.println("Empleado eliminado con éxito.");
            System.out.println("Se ha eliminado " + filasEliminadas + " empleado");

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}
