package main.domain.classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;


public class Usuario {
    private String nombre;
    private String contrasenya;

    public Usuario(String nombre, String contrasenya) {
        this.nombre = nombre;
        this.contrasenya = contrasenya;
    }
}

