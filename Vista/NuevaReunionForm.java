package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.db;
import Modelo.reunion;

public class NuevaReunionForm extends JFrame {
    private JTextField fechaField;
    private JTextField horaField;
    private JTextField lugarField;
    private JTextField temaField;
    private JButton guardarButton;

    public NuevaReunionForm() {
        setTitle("Nueva Reunión");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        fechaField = new JTextField(10);
        horaField = new JTextField(10);
        lugarField = new JTextField(20);
        temaField = new JTextField(20);
        guardarButton = new JButton("Guardar");

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Fecha: "));
        panel.add(fechaField);
        panel.add(new JLabel("Hora: "));
        panel.add(horaField);
        panel.add(new JLabel("Lugar: "));
        panel.add(lugarField);
        panel.add(new JLabel("Tema: "));
        panel.add(temaField);
        panel.add(new JLabel(""));
        panel.add(guardarButton);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarReunion();
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void guardarReunion() {
        String fecha = fechaField.getText();
        String hora = horaField.getText();
        String lugar = lugarField.getText();
        String tema = temaField.getText();

        if (!fecha.isEmpty() && !hora.isEmpty() && !lugar.isEmpty() && !tema.isEmpty()) {
            reunion Obj = new reunion();
            db conn = new db();
            Obj.insertarReunion(conn.getdb(), fecha, hora, lugar, tema);

            JOptionPane.showMessageDialog(this, "Reunión guardada exitosamente.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
        }
    }
}