package Controlador;

import Modelo.db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class controladorAlumno extends db {
    Connection conn = getdb();
    public void borrarRegistro(int identificador) {
        UIManager.put("OptionPane.yesButtonText", "Sí"); // Cambia "Yes" a "Sí"
        UIManager.put("OptionPane.noButtonText", "No");   // Cambia "No" a "No"

        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas borrar este registro?", "Confirmar Borrado", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                String personaQuery = "DELETE FROM persona WHERE persona.id_persona = ?";
                PreparedStatement personaStatement = conn.prepareStatement(personaQuery);
                personaStatement.setInt(1, identificador);
                int personaFilas = personaStatement.executeUpdate();

                if (personaFilas > 0) {
                    String alumnoQuery = "DELETE FROM alumno WHERE alumno.id_persona = ?";
                    PreparedStatement alumnoStatement = conn.prepareStatement(alumnoQuery);
                    alumnoStatement.setInt(1, identificador);
                    int alumnoFilas = alumnoStatement.executeUpdate();

                    if (alumnoFilas > 0) {
                        JOptionPane.showMessageDialog(null, "Registro eliminado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el registro en la tabla de alumnos correspondiente al identificador especificado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el registro con el identificador especificado en la tabla de personas.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al intentar borrar el registro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void actualizarRegistro(int identificador, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String matricula, String especialidad, String semestre) {
        try {
            String personaQuery = "UPDATE persona SET nombre_uno = ?, nombre_dos = ?, apellido_uno = ?, apellido_dos = ?, D_nacimiento = ? WHERE id_persona = ?";
            PreparedStatement personaStatement = conn.prepareStatement(personaQuery);
            personaStatement.setString(1, primerNombre);
            personaStatement.setString(2, segundoNombre);
            personaStatement.setString(3, apellidoPaterno);
            personaStatement.setString(4, apellidoMaterno);
            personaStatement.setString(5, fechaNacimiento);
            personaStatement.setInt(6, identificador);
            int personaFilas = personaStatement.executeUpdate();

            if (personaFilas > 0) {
                String alumnoQuery = "UPDATE alumno SET matricula = ?, especialidad = ?, semestre = ? WHERE id_persona = ?";
                PreparedStatement alumnoStatement = conn.prepareStatement(alumnoQuery);
                alumnoStatement.setString(1, matricula);
                alumnoStatement.setString(2, especialidad);
                alumnoStatement.setString(3, semestre);
                alumnoStatement.setInt(4, identificador);
                int alumnoFilas = alumnoStatement.executeUpdate();

                if (alumnoFilas > 0) {
                    JOptionPane.showMessageDialog(null, "Registro actualizado con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el registro en la tabla de alumnos correspondiente al identificador especificado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro con el identificador especificado en la tabla de personas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar actualizar el registro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
