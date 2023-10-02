package Controlador;

import Modelo.Modelo;
import Vista.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {
    private Modelo model;
    private Vista view;

    public Controlador(Modelo model, Vista view) {
        this.model = model;
        this.view = view;
    }
}
