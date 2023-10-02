package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controlador.controladorInstructor;

public class EdicionInstructor extends JFrame {
    private int identificador;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;

    public EdicionInstructor(int identificador, String[] rowData) {
        this.identificador = identificador;

        setTitle("Editar Registro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        textField1 = new JTextField(rowData[1]);
        add(new JLabel("Primer Nombre:"));
        add(textField1);

        textField2 = new JTextField(rowData[2]);
        add(new JLabel("Segundo Nombre:"));
        add(textField2);

        textField3 = new JTextField(rowData[3]);
        add(new JLabel("Apellido Paterno:"));
        add(textField3);

        textField4 = new JTextField(rowData[4]);
        add(new JLabel("Apellido Materno:"));
        add(textField4);

        textField5 = new JTextField(rowData[5]);
        add(new JLabel("Fecha de Nacimiento:"));
        add(textField5);

        textField6 = new JTextField(rowData[6]);
        add(new JLabel("Folio:"));
        add(textField6);

        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String primerNombre = textField1.getText();
                String segundoNombre = textField2.getText();
                String apellidoPaterno = textField3.getText();
                String apellidoMaterno = textField4.getText();
                String fechaNacimiento = textField5.getText();
                String folio = textField6.getText();

                controladorInstructor controlador = new controladorInstructor();
                controlador.actualizarRegistro(identificador, primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, folio);

                dispose();
            }
        });
        add(btnGuardar);

        pack();
        setLocationRelativeTo(null);
    }
}
