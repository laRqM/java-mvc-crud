package Vista;

import Controlador.controladorInstructor;
import Modelo.alumno;
import Modelo.instructor;
import Modelo.reunion;
import Modelo.personaAlumno;

import Controlador.controladorAlumno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;

public class Vista {
    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel tablePanel;
    private JTable tablaAlumno;
    private JTable tablaInstructor;

    public Vista() {
        frame = new JFrame("Práctica 1 - Java y MySQL");
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        barraMenu();

        buttonPanel = new JPanel(new GridLayout(0, 1));
        addButton("alumno");
        addButton("instructor");
        addButton("reunion");
        addButton("agenda");

        tablePanel = new JPanel(new BorderLayout());

        frame.add(buttonPanel, BorderLayout.WEST);
        frame.add(tablePanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private void mostrarTablaAlumno() {
        tablePanel.removeAll();

        alumno Obj = new alumno();

        List<String[]> datosAlumno = Obj.mostrarTablaAlumno("alumno");

        String[] nombresColumnas = {"Identificador", "Primer Nombre", "Segundo Nombre", "Apellido Paterno", "Apellido Materno", "Fecha de Nacimiento", "Matrícula", "Especialidad", "Semestre"};
        DefaultTableModel tableModelAlumno = new DefaultTableModel(nombresColumnas, 0);

        for (String[] rowData : datosAlumno) {
            tableModelAlumno.addRow(rowData);
        }

        tablaAlumno = new JTable(tableModelAlumno); // Inicialización
        //JTable tablaAlumno = new JTable(tableModelAlumno);

        Font font = new Font("Arial", Font.PLAIN, 18);
        tablaAlumno.setFont(font);

        JTableHeader header = tablaAlumno.getTableHeader();
        header.setFont(font);
        //header.setForeground(Color.WHITE);

        // Agregar listener de eventos para el clic derecho en la tabla
        tablaAlumno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = tablaAlumno.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < tablaAlumno.getRowCount()) {
                        tablaAlumno.setRowSelectionInterval(row, row);
                        showPopupMenu(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });

        tablePanel.add(new JScrollPane(tablaAlumno), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    private void mostrarTablaInstructor() {
        tablePanel.removeAll();

        instructor Obj = new instructor();

        List<String[]> datosInstructor = Obj.mostrarTablaInstructor("instructor");

        String[] nombresColumnas = {"Identificador", "Primer Nombre", "Segundo Nombre", "Apellido Paterno", "Apellido Materno", "Fecha de Nacimiento", "Folio"};
        DefaultTableModel tableModelInstructor = new DefaultTableModel(nombresColumnas, 0);

        for (String[] rowData : datosInstructor) {
            tableModelInstructor.addRow(rowData);
        }

        tablaInstructor = new JTable(tableModelInstructor); // Inicialización
        //JTable tablaInstructor = new JTable(tableModelInstructor);

        Font font = new Font("Arial", Font.PLAIN, 18);
        tablaInstructor.setFont(font);

        JTableHeader header = tablaInstructor.getTableHeader();
        header.setFont(font);
        //header.setForeground(Color.WHITE);

        // Agregar listener de eventos para el clic derecho en la tabla
        tablaInstructor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = tablaInstructor.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < tablaInstructor.getRowCount()) {
                        tablaInstructor.setRowSelectionInterval(row, row);
                        showPopupMenuInstructor(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });

        tablePanel.add(new JScrollPane(tablaInstructor), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    private void mostrarTablaReunion() {
        tablePanel.removeAll();

        reunion Obj = new reunion();

        List<String[]> datosReunion = Obj.mostrarTablaReunion("reunion");

        String[] nombresColumnas = {"Identificador", "Fecha", "Hora", "Lugar", "Tema"};
        DefaultTableModel tableModel = new DefaultTableModel(nombresColumnas, 0);

        for (String[] rowData : datosReunion) {
            tableModel.addRow(rowData);
        }

        JTable tablaReunion = new JTable(tableModel);

        Font font = new Font("Arial", Font.PLAIN, 18);
        tablaReunion.setFont(font);

        JTableHeader header = tablaReunion.getTableHeader();
        header.setFont(font);
        //header.setForeground(Color.WHITE);

        tablePanel.add(new JScrollPane(tablaReunion), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    private void mostrarTablaPersonaAlumno() {
        tablePanel.removeAll();

        personaAlumno Obj = new personaAlumno();

        List<String[]> datosPersonaAlumno = Obj.mostrarTablaPersonaAlumno("persona");

        String[] nombresColumnas = {"Identificador", "Primer Nombre", "Segundo Nombre", "Apellido Paterno", "Apellido Materno", "Matrícula", "Semestre", "Especialidad", "Identificador de Reunión", "Tema", "Fecha", "Hora", "Lugar"};
        DefaultTableModel tableModel = new DefaultTableModel(nombresColumnas, 0);

        for (String[] rowData : datosPersonaAlumno) {
            tableModel.addRow(rowData);
        }

        JTable tablaPersonaAlumno = new JTable(tableModel);

        Font font = new Font("Arial", Font.PLAIN, 18);
        tablaPersonaAlumno.setFont(font);

        JTableHeader header = tablaPersonaAlumno.getTableHeader();
        header.setFont(font);
        //header.setForeground(Color.WHITE);

        tablePanel.add(new JScrollPane(tablaPersonaAlumno), BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    public void addButton(String nombreTabla) {
        JButton button = new JButton(nombreTabla);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("alumno".equals(nombreTabla)) {
                    mostrarTablaAlumno();
                } else if ("instructor".equals(nombreTabla)) {
                    mostrarTablaInstructor();
                } else if ("reunion".equals(nombreTabla)) {
                    mostrarTablaReunion();
                } else if ("agenda".equals(nombreTabla)) {
                    mostrarTablaPersonaAlumno();
                }
            }
        });
        buttonPanel.add(button);
    }

    private void showPopupMenu(Component component, int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem itemBorrar = new JMenuItem("BORRAR");
        JMenuItem itemActualizar = new JMenuItem("ACTUALIZAR");

        itemBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaAlumno.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int identificador = Integer.parseInt((String) tablaAlumno.getValueAt(filaSeleccionada, 0));
                    controladorAlumno controlador = new controladorAlumno();
                    controlador.borrarRegistro(identificador);

                    // Actualiza la tabla después de borrar el registro si es necesario
                    mostrarTablaAlumno();
                }
            }
        });

        itemActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaAlumno.getSelectedRow();
                if (filaSeleccionada != -1) {
                    String[] rowData = new String[9]; // Ajusta el tamaño según el número de atributos
                    for (int i = 0; i < rowData.length; i++) {
                        rowData[i] = (String) tablaAlumno.getValueAt(filaSeleccionada, i);
                    }

                    int identificador = Integer.parseInt((String) tablaAlumno.getValueAt(filaSeleccionada, 0));
                    EdicionAlumno editForm = new EdicionAlumno(identificador, rowData);
                    editForm.setVisible(true);
                }
            }
        });

        popupMenu.add(itemBorrar);
        popupMenu.add(itemActualizar);

        popupMenu.show(component, x, y);
    }

    private void showPopupMenuInstructor(Component component, int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem itemBorrar = new JMenuItem("BORRAR");
        JMenuItem itemActualizar = new JMenuItem("ACTUALIZAR");

        itemBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaInstructor.getSelectedRow();
                if (filaSeleccionada != -1) {
                    int identificador = Integer.parseInt((String) tablaInstructor.getValueAt(filaSeleccionada, 0));
                    controladorInstructor controlador = new controladorInstructor();
                    controlador.borrarRegistro(identificador);

                    // Actualiza la tabla después de borrar el registro si es necesario
                    mostrarTablaInstructor();
                }
            }
        });

        itemActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaInstructor.getSelectedRow();
                if (filaSeleccionada != -1) {
                    String[] rowData = new String[7]; // Ajusta el tamaño según el número de atributos
                    for (int i = 0; i < rowData.length; i++) {
                        rowData[i] = (String) tablaInstructor.getValueAt(filaSeleccionada, i);
                    }

                    int identificador = Integer.parseInt((String) tablaInstructor.getValueAt(filaSeleccionada, 0));
                    EdicionInstructor editForm = new EdicionInstructor(identificador, rowData);
                    editForm.setVisible(true);
                }
            }
        });

        popupMenu.add(itemBorrar);
        popupMenu.add(itemActualizar);

        popupMenu.show(component, x, y);
    }

    private void barraMenu() {
        JMenuBar menuBar = new JMenuBar();

        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Práctica 1 - Java y MySQL");
        }

        JMenu MenuArchivo = new JMenu("Archivo");

        JMenuItem itemArchivoOP1 = new JMenuItem("Buscar datos");
        itemArchivoOP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaBusqueda();
            }
        });

        JMenuItem itemArchivoOP2 = new JMenuItem("Opción 2");

        JMenu MenuReunion = new JMenu("Reuniones");

        JMenuItem itemNuevaReunion = new JMenuItem("Nueva reunión");
        itemNuevaReunion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NuevaReunionForm nuevaReunionForm = new NuevaReunionForm();
                nuevaReunionForm.setVisible(true);
            }
        });

        JMenu MenuAyuda = new JMenu("Ayuda");

        JMenuItem acercaDe = new JMenuItem("Acerca de");
        acercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                URL imageUrl = getClass().getClassLoader().getResource("icon.png");

                if (imageUrl != null) {
                    ImageIcon icon = new ImageIcon(imageUrl);
                    JOptionPane.showMessageDialog(frame, "Developed with love by Luis Ronquillo <3", "Acerca de", JOptionPane.INFORMATION_MESSAGE, icon);
                } else {
                    // La imagen no se pudo cargar
                    JOptionPane.showMessageDialog(frame, "Developed with love by Luis Ronquillo <3", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        MenuArchivo.add(itemArchivoOP1);
        MenuArchivo.add(itemArchivoOP2);
        MenuReunion.add(itemNuevaReunion);
        MenuAyuda.add(acercaDe);

        menuBar.add(MenuArchivo);
        menuBar.add(MenuReunion);
        menuBar.add(MenuAyuda);
        frame.setJMenuBar(menuBar);
    }

    private void abrirVentanaBusqueda() {
        BusquedaForm busquedaForm = new BusquedaForm();
        busquedaForm.setVisible(true);
    }
}