package sudoku;

    public class Sudoku9x9 {
        int[][] tablero;

        public Sudoku9x9(int[][] tablero) {
            this.tablero = tablero;
        }

        public int[] buscarVacia(){
            for(int fila = 0; fila<9; fila++){
                for(int columna = 0; columna<9; columna++){
                    if(tablero[fila][columna]==0){
                        return new int[]{fila,columna};
                    }
                }
            }
            return null;
        }
        public boolean esValido(int fila, int columna, int numero){
            for(int i = 0; i < 9; i++){
                if(tablero[fila][i]==numero){
                    return false;
                }
            }

            for (int i = 0;i < 9;i++){
                if(tablero[i][columna]==numero){
                    return false;
                }
            }

            int inicioFila = (fila/3)*3;
            int inicioColumna = (columna/3)*3;

            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if(tablero[inicioFila+i][inicioColumna+j]==numero){
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean resolverSudoku(){
            int[] celdaVacia = buscarVacia();
            if(celdaVacia==null){
                return true;
            }

            int fila = celdaVacia[0];
            int columna = celdaVacia[1];

            for (int numero=1; numero<=9; numero++){
                if(esValido(fila,columna,numero)){
                    tablero[fila][columna]=numero;

                    if(resolverSudoku()){
                        return true;
                    }
                    tablero[fila][columna]=0;
                }
            }
            return false;
        }
        public void imprimirSudoku(){
            for(int fila = 0; fila<9; fila++){
                if(fila%3==0 && fila !=0){
                    System.out.println("---------------------");
                }
                for(int columna = 0; columna<9; columna++){
                    if(columna%3==0 && columna !=0){
                        System.out.print("| ");
                    }
                    System.out.print(tablero[fila][columna] + " ");
                }
                System.out.println();
            }
        }
    }