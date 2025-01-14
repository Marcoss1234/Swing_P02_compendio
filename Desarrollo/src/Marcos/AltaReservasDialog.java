package Marcos;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class AltaReservasDialog.
 */
public class AltaReservasDialog extends JDialog {

    /** The importe field. */
    // Variables de instancia
    private JTextField nombreField, apellidosField, dniField, telefonoField, diasEstanciaField, extrasField, importeField;
    
    /** The edad ninos spinner. */
    private JSpinner fechaEntradaSpinner, fechaSalidaSpinner, numHabitacionesSpinner, edadNinosSpinner;
    
    /** The tipo habitacion combo. */
    private JComboBox<String> tipoHabitacionCombo;
    
    /** The ninos check box. */
    private JCheckBox ninosCheckBox;

    /**
     * Instantiates a new alta reservas dialog.
     *
     * @param parent the parent
     */
    public AltaReservasDialog(JFrame parent) {
        super(parent, "Alta Reservas", true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(parent);
        // Panel principal con GridLayout
        JPanel mainPanel = new JPanel(new GridLayout(6, 1, 10, 10));

     // Título centrado
        JLabel tituloLabel = new JLabel("El Mirador", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Mantener el tamaño de fuente original
        tituloLabel.setForeground(Color.WHITE); // Color blanco para el texto

        // Fondo para el título
        tituloLabel.setOpaque(true); // Para que el fondo se vea
        tituloLabel.setBackground(new Color(34, 45, 50)); // Fondo oscuro para el título

        // Agregar una sombra al texto (sin cambiar la estructura del panel)
        tituloLabel.setText("<html><span style='text-shadow: 2px 2px 8px #000000;'>El Mirador</span></html>");

        // Borde decorativo alrededor del título
        tituloLabel.setBorder(BorderFactory.createLineBorder(new Color(255, 150, 0), 2)); // Borde naranja claro

        // Agregar el título al panel
        mainPanel.add(tituloLabel, BorderLayout.NORTH);


        // Panel de Datos Personales
        JPanel personalDataPanel = new JPanel();
        personalDataPanel.setBorder(new TitledBorder("Datos Personales"));
        personalDataPanel.setBackground(Color.CYAN);
        personalDataPanel.setLayout(new GridLayout(4, 2, 5, 5));

        nombreField = new JTextField();
        apellidosField = new JTextField();
        dniField = new JTextField();
        telefonoField = new JTextField();
        importeField = new JTextField();
        importeField.setEditable(false);
        importeField.setBackground(Color.MAGENTA);
        importeField.setForeground(Color.BLACK);

        setTextFieldBackground(nombreField, Color.CYAN);
        setTextFieldBackground(apellidosField, Color.CYAN);
        setTextFieldBackground(dniField, Color.CYAN);
        setTextFieldBackground(telefonoField, Color.CYAN);

        dniField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (dniField.getText().length() >= 9 || !Character.isLetterOrDigit(e.getKeyChar())) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "El DNI debe tener 8 números y 1 letra.");
                }
            }
        });

        telefonoField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (telefonoField.getText().length() >= 9 || !Character.isDigit(e.getKeyChar())) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "El Teléfono debe tener 9 dígitos.");
                }
            }
        });

        personalDataPanel.add(new JLabel("Nombre:"));
        personalDataPanel.add(nombreField);
        personalDataPanel.add(new JLabel("Apellidos:"));
        personalDataPanel.add(apellidosField);
        personalDataPanel.add(new JLabel("DNI:"));
        personalDataPanel.add(dniField);
        personalDataPanel.add(new JLabel("Teléfono:"));
        personalDataPanel.add(telefonoField);

        // Panel de Fechas
        JPanel datePanel = new JPanel();
        datePanel.setBorder(new TitledBorder("Fechas"));
        datePanel.setBackground(Color.GREEN);
        datePanel.setLayout(new GridLayout(3, 2, 5, 5));

        fechaEntradaSpinner = new JSpinner(new SpinnerDateModel());
        fechaSalidaSpinner = new JSpinner(new SpinnerDateModel());
        diasEstanciaField = new JTextField();
        diasEstanciaField.setEditable(false);

        setSpinnerBackground(fechaEntradaSpinner, Color.GREEN);
        setSpinnerBackground(fechaSalidaSpinner, Color.GREEN);
        setTextFieldBackground(diasEstanciaField, Color.GREEN);

        fechaEntradaSpinner.addChangeListener(e -> calcularDiasEstancia());
        fechaSalidaSpinner.addChangeListener(e -> calcularDiasEstancia());

        datePanel.add(new JLabel("Fecha Entrada:"));
        datePanel.add(fechaEntradaSpinner);
        datePanel.add(new JLabel("Fecha Salida:"));
        datePanel.add(fechaSalidaSpinner);
        datePanel.add(new JLabel("Nº de días estancia:"));
        datePanel.add(diasEstanciaField);

        // Panel de Habitación
        JPanel roomPanel = new JPanel();
        roomPanel.setBorder(new TitledBorder("Habitación"));
        roomPanel.setBackground(Color.MAGENTA);
        roomPanel.setLayout(new GridLayout(6, 2, 5, 5));

        tipoHabitacionCombo = new JComboBox<>(new String[]{"","Simple", "Doble", "Suite"});
        numHabitacionesSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        tipoHabitacionCombo.setBackground(Color.MAGENTA);
        ninosCheckBox = new JCheckBox("¿Niños?");
        ninosCheckBox.setBackground(Color.MAGENTA);

        edadNinosSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 14, 1));
        extrasField = new JTextField();

        setSpinnerBackground(numHabitacionesSpinner, Color.MAGENTA);
        setSpinnerBackground(edadNinosSpinner, Color.MAGENTA);
        setTextFieldBackground(extrasField, Color.MAGENTA);

        edadNinosSpinner.setEnabled(false);
        extrasField.setEnabled(false);

        ninosCheckBox.addActionListener(e -> {
            boolean habilitar = ninosCheckBox.isSelected();
            edadNinosSpinner.setEnabled(habilitar);
            extrasField.setEnabled(habilitar);
        });

        edadNinosSpinner.addChangeListener(e -> actualizarExtras());

        tipoHabitacionCombo.addActionListener(e -> calcularImporte());
        numHabitacionesSpinner.addChangeListener(e -> calcularImporte());

        roomPanel.add(new JLabel("Tipo de habitación:"));
        roomPanel.add(tipoHabitacionCombo);
        roomPanel.add(new JLabel("Nº de habitaciones:"));
        roomPanel.add(numHabitacionesSpinner);
        roomPanel.add(new JLabel("Edad de niños (si aplica):"));
        roomPanel.add(edadNinosSpinner);
        roomPanel.add(new JLabel("Extras:"));
        roomPanel.add(extrasField);
        roomPanel.add(new JLabel("Importe habitación (€):"));
        roomPanel.add(importeField);
        roomPanel.add(ninosCheckBox);

        mainPanel.add(personalDataPanel);
        mainPanel.add(datePanel);
        mainPanel.add(roomPanel);
        add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> dispose());
        buttonPanel.add(btnAceptar);
        add(buttonPanel, BorderLayout.SOUTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        
        JPanel clientePanel = new JPanel();
        clientePanel.setLayout(new GridLayout(4, 2, 5, 5));
        clientePanel.setBorder(new TitledBorder("Datos del Cliente"));
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblNombreValue = new JLabel();
        JLabel lblApellidos = new JLabel("Apellidos:");
        JLabel lblApellidosValue = new JLabel();
        JLabel lblDni = new JLabel("DNI:");
        JLabel lblDniValue = new JLabel();
        JLabel lblTelefono = new JLabel("Teléfono:");
        JLabel lblTelefonoValue = new JLabel();
        
        clientePanel.add(lblNombre);
        clientePanel.add(lblNombreValue);
        clientePanel.add(lblApellidos);
        clientePanel.add(lblApellidosValue);
        clientePanel.add(lblDni);
        clientePanel.add(lblDniValue);
        clientePanel.add(lblTelefono);
        clientePanel.add(lblTelefonoValue);
        
        JPanel habitacionPanel = new JPanel();
        habitacionPanel.setLayout(new GridLayout(4, 2, 5, 5));
        habitacionPanel.setBorder(new TitledBorder("Datos de la Habitación"));

        JLabel lblTipoHabitacion = new JLabel("Tipo de Habitación:");
        JLabel lblTipoHabitacionValue = new JLabel();
        JLabel lblNumHabitaciones = new JLabel("Nº de Habitaciones:");
        JLabel lblNumHabitacionesValue = new JLabel();
        JLabel lblExtras = new JLabel("Extras:");
        JLabel lblExtrasValue = new JLabel();
        JLabel lblImporte = new JLabel("Importe Total (€):");
        JLabel lblImporteValue = new JLabel();

        habitacionPanel.add(lblTipoHabitacion);
        habitacionPanel.add(lblTipoHabitacionValue);
        habitacionPanel.add(lblNumHabitaciones);
        habitacionPanel.add(lblNumHabitacionesValue);
        habitacionPanel.add(lblExtras);
        habitacionPanel.add(lblExtrasValue);
        habitacionPanel.add(lblImporte);
        habitacionPanel.add(lblImporteValue);

        // Añadir los paneles al JTabbedPane
        tabbedPane.addTab("Datos Cliente", clientePanel);
        tabbedPane.addTab("Datos Habitación", habitacionPanel);

        // Actualizar etiquetas al aceptar
        btnAceptar.addActionListener(e -> {
            lblNombreValue.setText(nombreField.getText());
            lblApellidosValue.setText(apellidosField.getText());
            lblDniValue.setText(dniField.getText());
            lblTelefonoValue.setText(telefonoField.getText());
            lblTipoHabitacionValue.setText((String) tipoHabitacionCombo.getSelectedItem());
            lblNumHabitacionesValue.setText(String.valueOf(numHabitacionesSpinner.getValue()));
            lblExtrasValue.setText(extrasField.getText());
            lblImporteValue.setText(importeField.getText());
            dispose();
        });
        
        mainPanel.add(tabbedPane);
        
        JPanel actionButtonPanel = new JPanel();
        
        
        
        JButton btnImprimir = new JButton("Imprimir");
     // Cargar el ícono desde la carpeta Media
     ImageIcon imprimirIcon = new ImageIcon(getClass().getResource("/Media/imprimir.png"));
     btnImprimir.setIcon(new ImageIcon(imprimirIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))); // Ajusta el tamaño del ícono
     btnImprimir.setPreferredSize(new Dimension(110, 30)); // Establece un tamaño preferido para el botón
     
     
        btnImprimir.addActionListener(e -> {
            if (validarCampos()) {
                // Actualizar las etiquetas del cuarto panel
                lblNombreValue.setText(nombreField.getText());
                lblApellidosValue.setText(apellidosField.getText());
                lblDniValue.setText(dniField.getText());
                lblTelefonoValue.setText(telefonoField.getText());
                lblTipoHabitacionValue.setText((String) tipoHabitacionCombo.getSelectedItem());
                lblNumHabitacionesValue.setText(String.valueOf(numHabitacionesSpinner.getValue()));
                lblExtrasValue.setText(extrasField.getText());
                lblImporteValue.setText(importeField.getText());

                JOptionPane.showMessageDialog(this, "Datos impresos correctamente en las pestañas.");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

     // Crear el botón btnNuevo con el ícono y el texto
        JButton btnNuevo = new JButton("Nuevo");
        // Cargar el ícono desde la carpeta Media
        ImageIcon nuevoIcon = new ImageIcon(getClass().getResource("/Media/nuevo.png"));
        btnNuevo.setIcon(new ImageIcon(nuevoIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))); // Ajusta el tamaño del ícono
        btnNuevo.setPreferredSize(new Dimension(110, 30)); // Establece un tamaño preferido para el botón

        btnNuevo.addActionListener(e -> {
            // Reiniciar todos los campos del formulario
            nombreField.setText("");
            apellidosField.setText("");
            dniField.setText("");
            telefonoField.setText("");
            diasEstanciaField.setText("");
            extrasField.setText("");
            importeField.setText("0");
            tipoHabitacionCombo.setSelectedIndex(0);
            numHabitacionesSpinner.setValue(0);
            edadNinosSpinner.setValue(0);
            ninosCheckBox.setSelected(false);
            fechaEntradaSpinner.setValue(new Date());
            fechaSalidaSpinner.setValue(new Date());
            edadNinosSpinner.setEnabled(false);
            extrasField.setEnabled(false);
            nombreField.requestFocus();
        });

        // Botón Guardar
        JButton btnGuardar = new JButton("Guardar");
     // Cargar el ícono desde la carpeta Media
     ImageIcon guardarIcon = new ImageIcon(getClass().getResource("/Media/guardar.png"));
     btnGuardar.setIcon(new ImageIcon(guardarIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))); // Ajusta el tamaño del ícono
     btnGuardar.setPreferredSize(new Dimension(110, 30)); // Establece un tamaño preferido para el botón
   

        btnGuardar.addActionListener(e -> {
            if (validarCampos()) {
                JOptionPane.showMessageDialog(this, "Registro Guardado.");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Añadir botones al panel
        actionButtonPanel.add(btnImprimir);
        actionButtonPanel.add(btnNuevo);
        actionButtonPanel.add(btnGuardar);
        add(actionButtonPanel, BorderLayout.SOUTH);
        
     // Panel de Descuento
        JPanel descuentoPanel = new JPanel();
        descuentoPanel.setBorder(new TitledBorder("Descuento Promocional"));
        descuentoPanel.setLayout(new GridLayout(2, 1, 5, 5));

        JSlider descuentoSlider = new JSlider(0, 50, 0); // Descuento del 0% al 50%
        descuentoSlider.setMajorTickSpacing(10);
        descuentoSlider.setMinorTickSpacing(5);
        descuentoSlider.setPaintTicks(true);
        descuentoSlider.setPaintLabels(true);
        descuentoSlider.setToolTipText("Ajusta el porcentaje de descuento aplicable a la reserva.");

        JLabel descuentoLabel = new JLabel("Descuento aplicado: 0%");
        descuentoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Evento para actualizar el importe en tiempo real
        descuentoSlider.addChangeListener(e -> {
            int descuento = descuentoSlider.getValue();
            descuentoLabel.setText("Descuento aplicado: " + descuento + "%");
            calcularImporteConDescuento(descuento);
        });

        // Añadir el slider y el label al panel
        descuentoPanel.add(descuentoLabel);
        descuentoPanel.add(descuentoSlider);

        // Añadir el panel de descuento al layout principal
        mainPanel.add(descuentoPanel);
        
        
        setVisible(true);
       
    }
    
    /**
     * Calcular importe con descuento.
     *
     * @param descuento the descuento
     */
    private void calcularImporteConDescuento(int descuento) {
        try {
            if (importeBaseOriginal <= 0) {
                importeField.setText("0");
                return;
            }

            // Aplicar el descuento al importe original
            int importeConDescuento = importeBaseOriginal - (importeBaseOriginal * descuento / 100);

            // Actualizar el campo del importe
            importeField.setText(String.valueOf(importeConDescuento));
        } catch (NumberFormatException e) {
            importeField.setText("0");
        }
    }
    
    
    
    /**
     * Validar campos.
     *
     * @return true, if successful
     */
    private boolean validarCampos() {
        // Verificar que los campos no estén vacíos
        return !nombreField.getText().isEmpty() &&
               !apellidosField.getText().isEmpty() &&
               !dniField.getText().isEmpty() &&
               !telefonoField.getText().isEmpty() &&
               tipoHabitacionCombo.getSelectedIndex() != 0 && 
               numHabitacionesSpinner.getValue() != null &&
               (ninosCheckBox.isSelected() ? edadNinosSpinner.getValue() != null : true) && 
               !diasEstanciaField.getText().isEmpty() && 
               !extrasField.getText().isEmpty(); 
    }


    /** The importe base original. */
    private int importeBaseOriginal = 0;

 /**
  * Calcular importe.
  */
 // Actualizar el cálculo del importe base original al seleccionar la habitación
 private void calcularImporte() {
     try {
         // Obtener el número de días de estancia
         int diasEstancia = Integer.parseInt(diasEstanciaField.getText());
         if (diasEstancia <= 0) {
             importeField.setText("0");
             importeBaseOriginal = 0; // Restablecer el importe original
             return;
         }

         // Obtener el número de habitaciones
         int numHabitaciones = (int) numHabitacionesSpinner.getValue();

         // Determinar el precio por día según el tipo de habitación
         int precioPorDia = switch ((String) tipoHabitacionCombo.getSelectedItem()) {
             case "Simple" -> 50;
             case "Doble" -> 75;
             case "Suite" -> 125;
             default -> 0;
         };

         // Calcular el coste base (habitaciones * días * precio por día)
         int costeBase = diasEstancia * numHabitaciones * precioPorDia;

         // Calcular el coste de extras (20€ por día por habitación si hay niños)
         int costeExtras = 0;
         if (ninosCheckBox.isSelected()) {
             costeExtras = costeExtras + 20;
         }

         // Sumar el coste base y los extras
         int importeTotal = costeBase + costeExtras;

         // Guardar el importe base original
         importeBaseOriginal = importeTotal;

         // Mostrar el importe total en el campo de texto
         importeField.setText(String.valueOf(importeTotal));
     } catch (NumberFormatException e) {
         importeField.setText("0");
         importeBaseOriginal = 0;
     }
 }
    
    /**
     * Calcular dias estancia.
     */
    private void calcularDiasEstancia() {
        try {
            Date entrada = ((SpinnerDateModel) fechaEntradaSpinner.getModel()).getDate();
            Date salida = ((SpinnerDateModel) fechaSalidaSpinner.getModel()).getDate();
            long diferencia = salida.getTime() - entrada.getTime();

            // Calcular los días totales
            int dias = (int) (diferencia / (1000 * 60 * 60 * 24));

            // Si la diferencia es positiva, al menos 1 día
            diasEstanciaField.setText(dias > 0 ? String.valueOf(dias + 1) : "1");
        } catch (Exception e) {
            diasEstanciaField.setText("0");
        }
    }


    /**
     * Actualizar extras.
     */
    private void actualizarExtras() {
        if (ninosCheckBox.isSelected()) {
            int edad = (int) edadNinosSpinner.getValue();
            if (edad <= 3) {
                extrasField.setText("Cuna");
            } else if (edad <= 10) {
                extrasField.setText("Cama supletoria pequeña");
            } else {
                extrasField.setText("Cama supletoria normal");
            }
        } else {
            extrasField.setText("");
        }
    }

    /**
     * Sets the spinner background.
     *
     * @param spinner the spinner
     * @param color the color
     */
    private void setSpinnerBackground(JSpinner spinner, Color color) {
        JComponent editor = spinner.getEditor();
        editor.setBackground(color);
        for (Component component : editor.getComponents()) {
            component.setBackground(color);
        }
    }

    /**
     * Sets the text field background.
     *
     * @param textField the text field
     * @param color the color
     */
    private void setTextFieldBackground(JTextField textField, Color color) {
        textField.setBackground(color);
        textField.setOpaque(true);
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AltaReservasDialog(null));
    }
}

