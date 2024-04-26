import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FormularioCine {
    private JPanel principal;
    private JComboBox cboPelicula;
    private JComboBox cboCantidad;
    private JButton cboComprar;
    private JTextArea txtEntradas;
    private JButton btnPelicula1;
    private JButton btnPelicula2;
    private JLabel txtAutor;
    private Cine cine = new Cine();
    public FormularioCine() {
        try {
            String a = "", b = "";
            do {
                b = JOptionPane.showInputDialog("Ingrese su número de cédula real");
                a = JOptionPane.showInputDialog("Ingrese su nombre real");
            } while (b.length() < 10);
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Taller1ProgramacionIII.dat"));
            o.writeObject(a + b);
            } catch (Exception ex) {
        }
        cboComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad = Integer.parseInt(cboCantidad.getSelectedItem().toString());
                if (cantidad>=4){
                    JOptionPane.showMessageDialog(null,"Cantidad de entradas invalida. Maximo 3");
                    return;
                }else {
                    String pelicula = cboPelicula.getSelectedItem().toString();
                    if (cantidad>EntradasDisponibles(pelicula)){
                        JOptionPane.showMessageDialog(null,"No hay boletos disponibles");
                    }else {
                        String otherpelicula = cboPelicula.getSelectedItem().toString();
                        cine.encolar(pelicula, cantidad);
                        txtEntradas.setText(cine.listarAsistentes());

                        JOptionPane.showMessageDialog(null, "Entradas disponibles para "+pelicula+": " + EntradasDisponibles(otherpelicula));
            }
                }
            }
        });
        btnPelicula1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mensajeEntradasDisponibles = cine.listarCantidadEntradas("X-MEN");
                JOptionPane.showMessageDialog(null, "Entradas disponibles para X-MEN: \n"+mensajeEntradasDisponibles);
            }
        });

        btnPelicula2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mensajeEntradasDisponibles = cine.listarCantidadEntradas("MARIO BROSS");
                JOptionPane.showMessageDialog(null, "Entradas disponibles para MARIO BROSS: \n"+mensajeEntradasDisponibles);
            }
        });
    }

    public int EntradasDisponibles(String pelicula){
        int nodisponibles = cine.listarCantidadEntradas(pelicula);
        int disponibles = 23-nodisponibles;
        if(disponibles<=0){
            return 0;
        }else {
            return disponibles;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FormularioCine");
        frame.setContentPane(new FormularioCine().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}