package sudoku;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Interfaz extends JFrame {
    private JTextField[][] celdas;
    private int[][] tableroInicial;
    private Sudoku9x9 sudoku;
    private boolean resuelto;

    private static final Color COLOR_FONDO = new Color(245, 245, 245);
    private static final Color COLOR_INICIAL = new Color(30, 30, 30);
    private static final Color COLOR_RESUELTO = new Color(0, 100, 200);
    private static final Color COLOR_CELDA_INICIAL = new Color(220, 220, 235);
    private static final Color COLOR_CELDA_NORMAL = Color.WHITE;
    private static final Color COLOR_BORDE_GRUESO = new Color(30, 30, 30);
    private static final Color COLOR_BORDE_FINO = new Color(180, 180, 180);
    private static final Font FUENTE_NUMEROS = new Font("SansSerif", Font.BOLD, 22);
    private static final Font FUENTE_BOTONES = new Font("SansSerif", Font.BOLD, 14);

    public Interfaz() {
        super("Sudoku 9x9 - Backtracking");
        this.celdas = new JTextField[9][9];
        this.tableroInicial = new int[9][9];
        this.resuelto = false;
        inicializarUI();
    }

    private void inicializarUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Sudoku 9x9", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelTablero = crearPanelTablero();
        add(panelTablero, BorderLayout.CENTER);

        JPanel panelBotones = crearPanelBotones();
        add(panelBotones, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel crearPanelTablero() {
        JPanel panel = new JPanel(new GridLayout(9, 9));
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setBackground(COLOR_BORDE_GRUESO);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                JTextField celda = new JTextField();
                celda.setHorizontalAlignment(JTextField.CENTER);
                celda.setFont(FUENTE_NUMEROS);
                celda.setForeground(COLOR_INICIAL);
                celda.setBackground(COLOR_CELDA_NORMAL);
                celda.setCaretColor(COLOR_INICIAL);
                celda.setBorder(crearBordeCelda(fila, col));

                celda.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (c < '1' || c > '9') {
                            e.consume();
                            return;
                        }
                        if (!celda.getText().isEmpty()) {
                            celda.setText("");
                        }
                    }
                });

                celdas[fila][col] = celda;
                panel.add(celda);
            }
        }
        return panel;
    }

    private Border crearBordeCelda(int fila, int col) {
        int top = (fila % 3 == 0) ? 3 : 1;
        int left = (col % 3 == 0) ? 3 : 1;
        int bottom = (fila == 8) ? 3 : 0;
        int right = (col == 8) ? 3 : 0;

        Color colorTop = (fila % 3 == 0) ? COLOR_BORDE_GRUESO : COLOR_BORDE_FINO;
        Color colorLeft = (col % 3 == 0) ? COLOR_BORDE_GRUESO : COLOR_BORDE_FINO;
        Color colorBottom = (fila == 8) ? COLOR_BORDE_GRUESO : COLOR_BORDE_FINO;
        Color colorRight = (col == 8) ? COLOR_BORDE_GRUESO : COLOR_BORDE_FINO;

        return new MatteBorder(top, left, bottom, right,
                (fila % 3 == 0 || col % 3 == 0 || fila == 8 || col == 8)
                        ? COLOR_BORDE_GRUESO : COLOR_BORDE_FINO);
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JButton btnResolver = crearBoton("Resolver", new Color(46, 125, 50));
        btnResolver.addActionListener(e -> resolver());

        JButton btnLimpiar = crearBoton("Limpiar", new Color(211, 47, 47));
        btnLimpiar.addActionListener(e -> limpiar());

        JButton btnCargarEjemplo = crearBoton("Cargar Ejemplo", new Color(25, 118, 210));
        btnCargarEjemplo.addActionListener(e -> cargarEjemplo());

        JButton btnTableroVacio = crearBoton("Tablero Vacío", new Color(120, 80, 180));
        btnTableroVacio.addActionListener(e -> limpiar());

        panel.add(btnResolver);
        panel.add(btnLimpiar);
        panel.add(btnCargarEjemplo);
        panel.add(btnTableroVacio);

        return panel;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_BOTONES);
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(150, 40));
        return boton;
    }

    private void resolver() {
        int[][] tablero = new int[9][9];
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                String texto = celdas[fila][col].getText().trim();
                if (!texto.isEmpty()) {
                    try {
                        int valor = Integer.parseInt(texto);
                        if (valor < 1 || valor > 9) {
                            JOptionPane.showMessageDialog(this,
                                    "Valor inválido en fila " + (fila + 1) + ", columna " + (col + 1)
                                            + ". Solo se permiten números del 1 al 9.",
                                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        tablero[fila][col] = valor;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this,
                                "Entrada inválida en fila " + (fila + 1) + ", columna " + (col + 1) + ".",
                                "Error de entrada", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    tablero[fila][col] = 0;
                }
            }
        }

        guardarTableroInicial(tablero);
        sudoku = new Sudoku9x9(tablero);

        if (!sudoku.validarTableroInicial()) {
            JOptionPane.showMessageDialog(this,
                    "El tablero inicial tiene conflictos. Revisa que no haya\n"
                            + "números repetidos en filas, columnas o subcuadrículas 3x3.",
                    "Tablero inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (sudoku.resolverSudoku()) {
            resuelto = true;
            mostrarSolucion();
            JOptionPane.showMessageDialog(this,
                    "¡Sudoku resuelto exitosamente!",
                    "Solución encontrada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se encontró solución para este tablero.",
                    "Sin solución", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void guardarTableroInicial(int[][] tablero) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tableroInicial[i][j] = tablero[i][j];
            }
        }
    }

    private void mostrarSolucion() {
        int[][] solucion = sudoku.getTablero();
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                celdas[fila][col].setText(String.valueOf(solucion[fila][col]));
                celdas[fila][col].setEditable(false);

                if (sudoku.esInicial(fila, col)) {
                    celdas[fila][col].setForeground(COLOR_INICIAL);
                    celdas[fila][col].setBackground(COLOR_CELDA_INICIAL);
                } else {
                    celdas[fila][col].setForeground(COLOR_RESUELTO);
                    celdas[fila][col].setBackground(COLOR_CELDA_NORMAL);
                }
            }
        }
    }

    private void limpiar() {
        resuelto = false;
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                celdas[fila][col].setText("");
                celdas[fila][col].setEditable(true);
                celdas[fila][col].setForeground(COLOR_INICIAL);
                celdas[fila][col].setBackground(COLOR_CELDA_NORMAL);
                tableroInicial[fila][col] = 0;
            }
        }
    }

    private void cargarEjemplo() {
        limpiar();
        int[][] ejemplo = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (ejemplo[fila][col] != 0) {
                    celdas[fila][col].setText(String.valueOf(ejemplo[fila][col]));
                    celdas[fila][col].setForeground(COLOR_INICIAL);
                    celdas[fila][col].setBackground(COLOR_CELDA_INICIAL);
                }
            }
        }
    }
}
