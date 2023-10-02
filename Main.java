import Controlador.Controlador;
import Modelo.Modelo;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Main {
    private static final String TITULO = "Práctica 1 - Java y MySQL"; //TÍTULO
    static String osName = System.getProperty("os.name").toLowerCase(); //DETECTAR EL NOMBRE DEL SISTEMA OPERATIVO
    static boolean esMac = osName.startsWith("mac os x"); //BOOLEANO PARA DETECTAR SI ES MAC OS
    static Image icono; //ICONO DE LA VENTANA Y DOCK EN MAC
    public static void MacOS() {
        //ICONO Y NOMBRE EN MAC
        if (esMac) {
            System.setProperty( "apple.awt.application.appearance", "system" ); //CAMBIA COLOR DE VENTANA DEPENDIENDO LA CONFIGURACIÓN DEL SISTEMA
            System.setProperty("apple.awt.application.name", TITULO); //TÍTULO DE LA VENTANA EN LA BARRA DE MENÚS
            URL imageUrl = Main.class.getClassLoader().getResource("icon.png");

            if (imageUrl != null) {
                icono = new ImageIcon(imageUrl).getImage(); //ÍCONO EN EL DOCK
                Taskbar.getTaskbar().setIconImage(icono); //SETEAMOS EL ÍCONO DEL DOCK
            }
        }
    }

    public static void main(String[] args) {
        MacOS();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Modelo modelo = new Modelo();
                Vista vista = new Vista();
                Controlador controlador = new Controlador(modelo, vista);
            }
        });
    }
}