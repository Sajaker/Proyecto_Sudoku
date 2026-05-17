package sudoku;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class Interfaz extends JFrame {
    private JTextField[][] celdas;
    private int[][] tableroInicial;
    private Sudoku9x9 sudoku;
    private boolean resuelto;
    private JLabel estadoLabel;
    private JTextField celdaSeleccionada;

    private static final Color PRIMARY = new Color(55, 71, 133);
    private static final Color PRIMARY_DARK = new Color(38, 50, 96);
    private static final Color ACCENT = new Color(72, 149, 239);
    private static final Color SUCCESS = new Color(46, 174, 96);
    private static final Color DANGER = new Color(214, 64, 69);
    private static final Color WARNING = new Color(243, 156, 18);

    private static final Color BG_MAIN = new Color(234, 237, 244);
    private static final Color BG_HEADER = PRIMARY;
    private static final Color BG_CELDA = Color.WHITE;
    private static final Color BG_CELDA_ALT = new Color(240, 243, 255);
    private static final Color BG_CELDA_INICIAL = new Color(220, 228, 248);
    private static final Color BG_CELDA_FOCO = new Color(232, 240, 255);

    private static final Color TEXT_DARK = new Color(33, 37, 41);
    private static final Color TEXT_INICIAL = new Color(30, 30, 50);
    private static final Color TEXT_RESUELTO = ACCENT;
    private static final Color TEXT_LIGHT = new Color(255, 255, 255);
    private static final Color TEXT_MUTED = new Color(130, 140, 160);

    private static final Color BORDER_GRUESO = new Color(55, 71, 133);
    private static final Color BORDER_FINO = new Color(195, 205, 220);

    private static final Font FONT_TITULO = new Font("Segoe UI", Font.BOLD, 26);
    private static final Font FONT_SUBTITULO = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_NUMEROS = new Font("Segoe UI", Font.BOLD, 22);
    private static final Font FONT_BOTONES = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FONT_ESTADO = new Font("Segoe UI", Font.PLAIN, 12);

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
        getContentPane().setBackground(BG_MAIN);
        setLayout(new BorderLayout(0, 0));

        add(crearHeader(), BorderLayout.NORTH);
        add(crearContenido(), BorderLayout.CENTER);
        add(crearFooter(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, PRIMARY, getWidth(), 0, PRIMARY_DARK);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(new EmptyBorder(18, 20, 14, 20));

        JLabel titulo = new JLabel("SUDOKU");
        titulo.setFont(FONT_TITULO);
        titulo.setForeground(TEXT_LIGHT);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Resolución automática con Backtracking");
        subtitulo.setFont(FONT_SUBTITULO);
        subtitulo.setForeground(new Color(180, 195, 230));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(titulo);
        header.add(Box.createVerticalStrut(3));
        header.add(subtitulo);

        return header;
    }

    private JPanel crearContenido() {
        JPanel contenido = new JPanel(new BorderLayout(0, 12));
        contenido.setBackground(BG_MAIN);
        contenido.setBorder(new EmptyBorder(16, 20, 8, 20));

        contenido.add(crearPanelTablero(), BorderLayout.CENTER);
        contenido.add(crearPanelBotones(), BorderLayout.SOUTH);

        return contenido;
    }

    private JPanel crearPanelTablero() {
        JPanel wrapper = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 20));
                g2.fillRoundRect(3, 3, getWidth() - 3, getHeight() - 3, 16, 16);
                g2.setColor(BORDER_GRUESO);
                g2.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 16, 16);
                g2.dispose();
            }
        };
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(4, 4, 4, 4));

        JPanel grid = new JPanel(new GridLayout(9, 9, 0, 0));
        grid.setOpaque(false);
        grid.setPreferredSize(new Dimension(468, 468));

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                celdas[fila][col] = crearCelda(fila, col);
                grid.add(celdas[fila][col]);
            }
        }

        wrapper.add(grid, BorderLayout.CENTER);
        return wrapper;
    }

    private JTextField crearCelda(int fila, int col) {
        JTextField celda = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                g2.setColor(getBackground());
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };

        celda.setHorizontalAlignment(JTextField.CENTER);
        celda.setFont(FONT_NUMEROS);
        celda.setForeground(TEXT_DARK);
        celda.setCaretColor(ACCENT);
        celda.setOpaque(true);

        boolean bloqueOscuro = ((fila / 3) + (col / 3)) % 2 == 1;
        celda.setBackground(bloqueOscuro ? BG_CELDA_ALT : BG_CELDA);
        celda.setBorder(crearBordeCelda(fila, col));

        final Color bgOriginal = celda.getBackground();

        celda.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (celda.isEditable()) {
                    celda.setBackground(BG_CELDA_FOCO);
                    celdaSeleccionada = celda;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (celda.isEditable() && !resuelto) {
                    celda.setBackground(bgOriginal);
                }
                celdaSeleccionada = null;
            }
        });

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

        return celda;
    }

    private Border crearBordeCelda(int fila, int col) {
        boolean topGrueso = (fila % 3 == 0);
        boolean leftGrueso = (col % 3 == 0);
        boolean bottomGrueso = (fila == 8 || fila % 3 == 2);
        boolean rightGrueso = (col == 8 || col % 3 == 2);

        int top = topGrueso ? 3 : 1;
        int left = leftGrueso ? 3 : 1;
        int bottom = bottomGrueso ? 3 : 1;
        int right = rightGrueso ? 3 : 1;

        return new MatteBorder(top, left, bottom, right, BORDER_FINO) {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setColor(BORDER_FINO);
                g2.fillRect(x, y, w, top);
                g2.fillRect(x, y, left, h);
                g2.fillRect(x, y + h - bottom, w, bottom);
                g2.fillRect(x + w - right, y, right, h);

                g2.setColor(BORDER_GRUESO);
                if (topGrueso) g2.fillRect(x, y, w, top);
                if (leftGrueso) g2.fillRect(x, y, left, h);
                if (fila == 8) g2.fillRect(x, y + h - bottom, w, bottom);
                if (col == 8) g2.fillRect(x + w - right, y, right, h);

                g2.dispose();
            }
        };
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 0));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(4, 0, 0, 0));

        panel.add(crearBoton("Resolver", SUCCESS, "▶"));
        panel.add(crearBoton("Cargar Ejemplo", ACCENT, "◈"));
        panel.add(crearBoton("Limpiar", WARNING, "↺"));
        panel.add(crearBoton("Tablero Vacío", DANGER, "✕"));

        return panel;
    }

    private JButton crearBoton(String texto, Color color, String icono) {
        JButton boton = new JButton(icono + "  " + texto) {
            private boolean hover = false;
            private boolean pressed = false;

            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hover = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        hover = false;
                        repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        pressed = true;
                        repaint();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        pressed = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color bg = color;
                if (pressed) {
                    bg = bg.darker();
                } else if (hover) {
                    bg = new Color(
                            Math.min(255, bg.getRed() + 20),
                            Math.min(255, bg.getGreen() + 20),
                            Math.min(255, bg.getBlue() + 20));
                }

                if (!pressed) {
                    g2.setColor(new Color(0, 0, 0, 25));
                    g2.fill(new RoundRectangle2D.Float(1, 2, getWidth() - 2, getHeight() - 2, 10, 10));
                }

                g2.setColor(bg);
                g2.fill(new RoundRectangle2D.Float(0, pressed ? 1 : 0, getWidth() - 1, getHeight() - 2, 10, 10));

                g2.setFont(getFont());
                g2.setColor(TEXT_LIGHT);
                FontMetrics fm = g2.getFontMetrics();
                String text = getText();
                int x = (getWidth() - fm.stringWidth(text)) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2 + (pressed ? 1 : 0);
                g2.drawString(text, x, y);

                g2.dispose();
            }
        };

        boton.setFont(FONT_BOTONES);
        boton.setForeground(TEXT_LIGHT);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(140, 42));

        switch (texto) {
            case "Resolver":
                boton.addActionListener(e -> resolver());
                break;
            case "Limpiar":
                boton.addActionListener(e -> limpiar());
                break;
            case "Cargar Ejemplo":
                boton.addActionListener(e -> cargarEjemplo());
                break;
            case "Tablero Vacío":
                boton.addActionListener(e -> limpiar());
                break;
        }

        return boton;
    }

    private JPanel crearFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(224, 228, 237));
        footer.setBorder(new EmptyBorder(8, 20, 8, 20));

        estadoLabel = new JLabel("Listo — Ingresa los números o carga un ejemplo");
        estadoLabel.setFont(FONT_ESTADO);
        estadoLabel.setForeground(TEXT_MUTED);

        JLabel credito = new JLabel("Proyecto Sudoku  •  Backtracking");
        credito.setFont(FONT_ESTADO);
        credito.setForeground(new Color(160, 170, 185));

        footer.add(estadoLabel, BorderLayout.WEST);
        footer.add(credito, BorderLayout.EAST);

        return footer;
    }

    private void actualizarEstado(String mensaje, Color color) {
        estadoLabel.setText(mensaje);
        estadoLabel.setForeground(color);
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
                            actualizarEstado("✖  Error en fila " + (fila + 1) + ", col " + (col + 1), DANGER);
                            JOptionPane.showMessageDialog(this,
                                    "Valor inválido en fila " + (fila + 1) + ", columna " + (col + 1)
                                            + ".\nSolo se permiten números del 1 al 9.",
                                    "Error de entrada", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        tablero[fila][col] = valor;
                    } catch (NumberFormatException ex) {
                        actualizarEstado("✖  Entrada inválida en fila " + (fila + 1) + ", col " + (col + 1), DANGER);
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
            actualizarEstado("⚠  El tablero tiene conflictos", WARNING);
            JOptionPane.showMessageDialog(this,
                    "El tablero inicial tiene conflictos. Revisa que no haya\n"
                            + "números repetidos en filas, columnas o subcuadrículas 3x3.",
                    "Tablero inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        actualizarEstado("⏳  Resolviendo...", ACCENT);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        SwingUtilities.invokeLater(() -> {
            if (sudoku.resolverSudoku()) {
                resuelto = true;
                mostrarSolucion();
                actualizarEstado("✔  ¡Sudoku resuelto exitosamente!", SUCCESS);
                setCursor(Cursor.getDefaultCursor());
                JOptionPane.showMessageDialog(this,
                        "¡Sudoku resuelto exitosamente!",
                        "Solución encontrada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                actualizarEstado("✖  No se encontró solución", DANGER);
                setCursor(Cursor.getDefaultCursor());
                JOptionPane.showMessageDialog(this,
                        "No se encontró solución para este tablero.",
                        "Sin solución", JOptionPane.WARNING_MESSAGE);
            }
        });
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
                    celdas[fila][col].setForeground(TEXT_INICIAL);
                    celdas[fila][col].setBackground(BG_CELDA_INICIAL);
                } else {
                    celdas[fila][col].setForeground(TEXT_RESUELTO);
                    boolean bloqueOscuro = ((fila / 3) + (col / 3)) % 2 == 1;
                    celdas[fila][col].setBackground(bloqueOscuro ? BG_CELDA_ALT : BG_CELDA);
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
                celdas[fila][col].setForeground(TEXT_DARK);
                boolean bloqueOscuro = ((fila / 3) + (col / 3)) % 2 == 1;
                celdas[fila][col].setBackground(bloqueOscuro ? BG_CELDA_ALT : BG_CELDA);
                tableroInicial[fila][col] = 0;
            }
        }
        actualizarEstado("Listo — Ingresa los números o carga un ejemplo", TEXT_MUTED);
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
                    celdas[fila][col].setForeground(TEXT_INICIAL);
                    celdas[fila][col].setBackground(BG_CELDA_INICIAL);
                }
            }
        }
        actualizarEstado("◈  Ejemplo cargado — Presiona Resolver", ACCENT);
    }
}
