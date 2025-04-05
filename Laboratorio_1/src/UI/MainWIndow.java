package UI;

import javax.swing.*;

public class MainWIndow {

    public class MainWindow extends JFrame {

        public MainWindow() {
            setTitle("Zaun Hospital System");
            setSize(500, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // Centrar la ventana

            // Botón de ejemplo
            JButton btnDoctor = new JButton("Agregar Doctor");
            btnDoctor.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Ventana para agregar doctor próximamente");
            });

            add(btnDoctor); // Agregar botón a la ventana
        }

        public void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new MainWindow().setVisible(true);
            });
        }
    }

}
