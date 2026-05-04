package org.example;

import java.sql.*;

public class Consulta {
    public static int consultaUltimoID(){
        int id = 0;
        /**
         * Realiza la conexión mediante Maven
         */
        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()); Statement statement = conn.createStatement()){
            //System.out.println("Conexión establecida con Oracle.");
            /**
             * Consulta simple, maximo id de la tabla empleado
             * para añadir nuevos empleados
             */
            String sql = "SELECT MAX(ID) FROM empleado" ;
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            id = resultSet.getInt(1);
            System.out.println("El último id era " + id);

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return id;
    }

    public static boolean consultaExisteID(int id){
        boolean result = false;

        /**
         * Realiza la conexión mediante Maven
         */
        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()); Statement statement = conn.createStatement()){
            //System.out.println("Conexión establecida con Oracle.");
            /**
             * Consulta simple, recorre los id de la tabla empleado
             * y los compara con el id pasado por parámetro
             */
            String sql = "SELECT ID FROM empleado" ;
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int idConsulta = resultSet.getInt(1);
                if(idConsulta == id){
                    result = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return result;
    }

    public static void mostrarEmpleados(){
        /**
         * Realiza la conexión mediante Maven
         */
        try (Connection conn = DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUser(),
                DBConfig.getPassword()); Statement statement = conn.createStatement()){
            //System.out.println("Conexión establecida con Oracle.");
            /**
             * Consulta para mostrar todos los empleados
             */
            String sql = "SELECT * FROM empleado" ;
            ResultSet resultSet = statement.executeQuery(sql);
            /**
             * Se ejecuta mientra haya resultados
             */
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                double salario = resultSet.getDouble("salario");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Salario: " + salario);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}