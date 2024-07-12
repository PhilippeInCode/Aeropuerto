package InicializadorApp;
import java.awt.EventQueue;

import GUI.Acceso;

public class AppAeropuerto {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Abre la interfaz gráfica
                    Acceso acceso = new Acceso();
                    acceso.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
