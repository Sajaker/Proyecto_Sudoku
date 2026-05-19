# Sudoku 9x9

El propósito del programa es resolver un tablero de Sudoku 9x9 incompleto o vacío usando backtracking.

## Características

- Representación de tablero 9x9 con matrices.
- Validación de reglas del Sudoku:
  - Filas
  - Columnas
  - Subcuadrículas 3x3
- Resolución automática del tablero mediante backtracking.
- Interfaz gráfica (Swing) para interactuar con el tablero.
- Soporte para tablero vacío y tablero con valores iniciales.
- Diferenciación visual entre valores iniciales y valores agregados por el algoritmo.
- Generación automática de tableros aleatorios.
- Validación en tiempo real de números ingresados.
- Carga de tablero de ejemplo.
- Mensajes visuales de estado y alertas emergentes.
- Detección automática de conflictos antes de resolver.
- Documentación formal del algoritmo mediante pseudocódigo y formulación lógica.

---

## Estructura del proyecto (Clases)

### Main

El archivo Main se encarga de iniciar la interfaz gráfica del programa.

### Sudoku9x9

La clase Sudoku9x9 contiene la lógica del algoritmo:

- Búsqueda de celdas vacías.
- Validación de números en filas, columnas y subcuadrículas.
- Backtracking con recursividad.
- Validación del tablero inicial.
- Generación automática de tableros completos válidos.
- Eliminación aleatoria de casillas para crear Sudokus jugables.

### Interfaz

La clase Interfaz crea la GUI con Java Swing. Permite al usuario:

- Ingresar números manualmente.
- Cargar un tablero de ejemplo.
- Generar un Sudoku aleatorio.
- Resolver el tablero con un botón.
- Limpiar el tablero.
- Crear tablero vacío.
- Ver validaciones visuales en tiempo real.
- Ver mensajes de estado durante la ejecución.
- Visualizar diferencias entre valores iniciales y valores calculados.

### Pseudocodigo.txt

Describe paso a paso el algoritmo recursivo de resolución:

- Búsqueda de celdas vacías.
- Caso base.
- Validaciones.
- Recursividad.
- Backtracking.

### Formulaciondelasolucion.txt

Explica formalmente:

- Estado inicial.
- Decisiones posibles.
- Restricciones del problema.
- Caso base.
- Lógica de retroceso.

---

## Funcionamiento del algoritmo

1. Busca una celda vacía (0).
2. Prueba números del 1 al 9.
3. Verifica restricciones:
   - Fila
   - Columna
   - Subcuadro 3x3
4. Si es válido, continúa recursivamente.
5. Si falla, retrocede y prueba otro número.
6. Si no quedan celdas vacías, el Sudoku está resuelto.

Para generación aleatoria:

1. Se construye un tablero completo válido.
2. Se eliminan casillas aleatoriamente.
3. Se entrega un Sudoku listo para resolver.

---

## Modos de inicio

### Caso 1: Tablero vacío

El usuario inicia desde cero y el algoritmo construye una solución completa.

### Caso 2: Tablero con valores iniciales

El usuario ingresa pistas iniciales y el algoritmo completa el tablero respetándolas.

### Caso 3: Tablero aleatorio

Se genera automáticamente un Sudoku jugable.

### Caso 4: Tablero de ejemplo

Carga un tablero clásico predefinido.

---

## Restricciones

- Cada fila debe contener números del 1 al 9 sin repetirse.
- Cada columna debe contener números del 1 al 9 sin repetirse.
- Cada subcuadrícula 3x3 debe contener números del 1 al 9 sin repetirse.
- Los valores iniciales no pueden modificarse una vez resuelto.
- El sistema detecta conflictos antes de resolver.

---

## Interfaz gráfica

La interfaz permite visualizar:

- Cuadrícula 9x9.
- Valores iniciales (negro, fondo gris).
- Valores calculados (azul, fondo blanco).
- Celdas válidas (verde).
- Celdas inválidas (rojo).
- Mensajes de estado.
- Alertas emergentes.
- Solución completa encontrada.

---

## ¿Por qué usamos Backtracking?

El Sudoku presenta múltiples combinaciones posibles.

El algoritmo de backtracking prueba soluciones recursivamente.

Si una decisión produce un conflicto:

- Deshace la asignación.
- Retrocede.
- Prueba otra alternativa.

Esto permite encontrar soluciones válidas de forma eficiente y también generar nuevos tableros automáticamente.
