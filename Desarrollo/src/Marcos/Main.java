package Marcos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends JFrame {

  
    /**
     * Instantiates a new main.
     */
    public Main() {
        // Configuración de la ventana principal
        setTitle("Gestión Hotel | El Mirador");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIconImage(new ImageIcon(getClass().getResource("/Media/hotel.png")).getImage());


        // Configuración de la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Menú Archivo
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem menuItemSalir = new JMenuItem("Salir");
        // Acción para salir de la aplicación
        menuItemSalir.addActionListener(e -> System.exit(0));
        menuArchivo.add(menuItemSalir);

        // Menú Registro
        JMenu menuRegistro = new JMenu("Registro");

        // Menú de "Alta Reservas" con atajo de teclado Ctrl+A
        JMenuItem menuItemAltaReservas = new JMenuItem("Alta Reservas");
        menuItemAltaReservas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        menuItemAltaReservas.addActionListener(e -> new AltaReservasDialog(this));

        // Menú de "Baja Reservas" con atajo de teclado Ctrl+B
        JMenuItem menuItemBajaReservas = new JMenuItem("Baja Reservas");
        menuItemBajaReservas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
        menuItemBajaReservas.addActionListener(e -> mostrarDialogo("Información", "La opción 'Baja Reservas' no está desarrollada aún."));

        menuRegistro.add(menuItemAltaReservas);
        menuRegistro.add(menuItemBajaReservas);

        // Menú Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");

        // Menú de "Acerca de"
        JMenuItem menuItemAcercaDe = new JMenuItem("Acerca de...");
        menuItemAcercaDe.addActionListener(e -> mostrarDialogo("Acerca de", "Acerca de Nosotros\r\n"
        		+ "\r\n"
        		+ "Bienvenidos a El Mirador, un destino único donde el confort y la excelencia se encuentran en armonía. Ubicado en el corazón de Almeria,"
        		+ "\r\n"
        		+ "nuestro hotel es el refugio perfecto para aquellos que buscan una experiencia inolvidable,"
        		+ "\r\n"
        		+ " ya sea para un viaje de negocios, una escapada romántica o unas vacaciones en familia.\r\n"
        		+ "\r\n"
        		+ "Con una decoración moderna y acogedora, [Nombre del Hotel] ofrece habitaciones espaciosas"
        		+ "\r\n"
        		+ " y equipadas con todas las comodidades necesarias para hacer de su estancia un verdadero placer."
        		+ "\r\n"
        		+ " Nuestros servicios incluyen un restaurante gourmet, un spa de relajación, gimnasio, y acceso a espacios diseñados para eventos especiales."
        		+ "\r\n"
        		+ " Además, nuestra ubicación privilegiada le permitirá disfrutar de las principales atracciones turísticas de la zona, así como de un ambiente tranquilo y exclusivo.\r\n"
        		+ "\r\n"
        		+ "En El Mirador, nos dedicamos a brindarle el mejor servicio, con un equipo de profesionales comprometidos a hacer de su visita una experiencia única."
        		+ "\r\n"
        		+ " Venga y descubra lo que significa sentirse como en casa, mientras disfruta de un servicio de clase mundial y de las mejores vistas.\r\n"
        		+ "\r\n"
        		+ "Esperamos darle la bienvenida pronto."));
        menuAyuda.add(menuItemAcercaDe);

        // Añadir menús a la barra de menús
        menuBar.add(menuArchivo);
        menuBar.add(menuRegistro);
        menuBar.add(menuAyuda);

        setJMenuBar(menuBar);

        // Panel central con los botones
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));

        // Botón para "Alta Reservas" con ícono
        JButton btnAltaReservas = new JButton("Alta Reservas", new ImageIcon("Media/alta.png"));
        // Botón para "Baja Reservas" con ícono
        JButton btnBajaReservas = new JButton("Baja Reservas", new ImageIcon("Media/baja.png"));

        // Acción para el botón "Alta Reservas"
        btnAltaReservas.addActionListener(e -> new AltaReservasDialog(this));

        // Acción para el botón "Baja Reservas"
        btnBajaReservas.addActionListener(e -> mostrarDialogo("Información", "La opción 'Baja Reservas' no está desarrollada aún."));

        // Añadir los botones al panel
        panelCentral.add(btnAltaReservas);
        panelCentral.add(btnBajaReservas);

        // Añadir el panel al layout principal
        add(panelCentral, BorderLayout.CENTER);

        // Hacer visible la ventana
        setVisible(true);
    }

   
    /**
     * Mostrar dialogo.
     *
     * @param titulo the titulo
     * @param mensaje the mensaje
     */
    private void mostrarDialogo(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

   
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
