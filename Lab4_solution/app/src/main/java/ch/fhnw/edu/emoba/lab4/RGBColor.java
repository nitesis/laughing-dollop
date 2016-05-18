package ch.fhnw.edu.emoba.lab4;

import java.io.Serializable;

public class RGBColor implements Serializable {
    int r;
    int g;
    int b;

    @Override
    public String toString() {
        return "r="+r+", g="+g+", b="+b;
    }
}
