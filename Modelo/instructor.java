package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class instructor extends db {
    Connection conn = getdb();

    public List<String[]> mostrarTablaInstructor(String nombreTabla) {
        List<String[]> ListaDatos = new ArrayList<>();

        String query = "SELECT persona.id_persona, nombre_uno, nombre_dos, apellido_uno, apellido_dos, D_nacimiento, folio FROM persona, " + nombreTabla + " WHERE persona.id_persona = instructor.id_persona";

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
}
