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
                /**
                 * Pido el id del empleado y consulto si existe
                 */
                System.out.println("Indique el id del empleado a eliminar");
                id = sc.nextInt();
                sc.nextLine();
                existe = Consulta.consultaExisteID(id);
                if(!existe){
                    //si no existe muestro el mensaje
                    System.out.println("No existe el empleado a eliminar");
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

        /**
         * Realiza la conexión mediante Maven
         */
        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword())){
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
