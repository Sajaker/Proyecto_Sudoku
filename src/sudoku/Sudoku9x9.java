package sudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sudoku9x9 {
    private int[][] tablero;
    private boolean[][] esInicial;

    public Sudoku9x9(int[][] tablero) {
        this.tablero = new int[9][9];
        this.esInicial = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.tablero[i][j] = tablero[i][j];
                this.esInicial[i][j] = tablero[i][j] != 0;
            }
        }
    }

    public Sudoku9x9() {
        this.tablero = new int[9][9];
        this.esInicial = new boolean[9][9];
    }

    public int[][] getTablero() {
        return tablero;
    }

    public boolean esInicial(int fila, int columna) {
        return esInicial[fila][columna];
    }

    public int[] buscarVacia() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                if (tablero[fila][columna] == 0) {
                    return new int[]{fila, columna};
                }
            }
        }
        return null;
    }

    public boolean esValido(int fila, int columna, int numero) {
        for (int i = 0; i < 9; i++) {
            if (i != columna && tablero[fila][i] == numero) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (i != fila && tablero[i][columna] == numero) {
                return false;
            }
        }

        int inicioFila = (fila / 3) * 3;
        int inicioColumna = (columna / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if((inicioFila + i != fila || inicioColumna + j != columna) &&
                tablero [inicioFila + i][inicioColumna + j] == numero) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean resolverSudoku() {
        int[] celdaVacia = buscarVacia();
        if (celdaVacia == null) {
            return true;
        }

        int fila = celdaVacia[0];
        int columna = celdaVacia[1];

        for (int numero = 1; numero <= 9; numero++) {
            if (esValido(fila, columna, numero)) {
                tablero[fila][columna] = numero;

                if (resolverSudoku()) {
                    return true;
                }
                tablero[fila][columna] = 0;
            }
        }
        return false;
    }

    public boolean validarTableroInicial() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                int num = tablero[fila][columna];
                if (num != 0) {
                    tablero[fila][columna] = 0;
                    if (!esValido(fila, columna, num)) {
                        tablero[fila][columna] = num;
                        return false;
                    }
                    tablero[fila][columna] = num;
                }
            }
        }
        return true;
    }

    public void imprimirSudoku() {
        for (int fila = 0; fila < 9; fila++) {
            if (fila % 3 == 0 && fila != 0) {
                System.out.println("---------------------");
            }
            for (int columna = 0; columna < 9; columna++) {
                if (columna % 3 == 0 && columna != 0) {
                    System.out.print("| ");
                }
                System.out.print(tablero[fila][columna] + " ");
            }
            System.out.println();
        }
    }

    public boolean generarSudoku() {
        for(int fila = 0; fila < 9; fila++) {
            for(int columna = 0; columna < 9; columna++) {
                if(this.tablero[fila][columna] == 0) {
                    List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

                    Collections.shuffle(numeros);

                    for(int numero : numeros) {
                        if(esValido(fila, columna, numero)){
                            this.tablero[fila][columna] = numero;
                            if(generarSudoku()) {
                                return true;
                            }
                        }
                        this.tablero[fila][columna] = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public void eliminarCasillas(int cantidad) {
        Random random = new Random();
        int eliminadas = 0;

        while(eliminadas < cantidad) {
            int fila = random.nextInt(9);
            int columna = random.nextInt(9);

            if(tablero[fila][columna] != 0) {
                tablero[fila][columna] = 0;
                eliminadas++;
            }
        }
    }
    public int[][] crearTableroAleatorio(){
        this.tablero = new int[9][9];
        generarSudoku();
        eliminarCasillas(40);

        return this.tablero;
    }
}
