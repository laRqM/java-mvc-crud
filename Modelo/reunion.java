package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.db;

public class reunion extends db {
    Connection conn = getdb();

    public List<String[]> mostrarTablaReunion(String nombreTabla) {
        List<String[]> ListaDatos = new ArrayList<>();

        String query = "SELECT * FROM " + nombreTabla;

        try (Statement statement = conn.createStatement();
             ResultSet resultados = statement.executeQuery(query)) {

            int contadorColumnas = resultados.getMetaData().getColumnCount();
            while (resultados.next()) {
                String[] Datos = new String[contadorColumnas];
                for (int i = 0; i < contadorColumnas; i++) {
                    Datos[i] = resultados.getString(i + 1);
                }
                ListaDatos.add(Datos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListaDatos;
    }

    public void insertarReunion(Connection conn, String fecha, String hora, String lugar, String tema) {
        String query = "INSERT INTO reunion (fecha, hora, lugar, tema) VALUES (?, ?, ?, ?)";

        try (PreparedStatement sentenciaPreparada = conn.prepareStatement(query)) {
            sentenciaPreparada.setString(1, fecha);
            sentenciaPreparada.setString(2, hora);
            sentenciaPreparada.setString(3, lugar);
            sentenciaPreparada.setString(4, tema);

            sentenciaPreparada.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
