import java.util.Random;
import java.util.Scanner;

    public class Sudoku {
        private int[][] tabuleiro;
        private int[][] solucao;
        private boolean[][] fixo;

        public Sudoku() {
            tabuleiro = new int[9][9];
            solucao = new int[9][9];
            fixo = new boolean[9][9];
            gerarSudoku();
        }

        private void gerarSudoku() {
            // Gerar um sudoku resolvido
            resolverSudoku(0, 0);

            // Copiar a solução
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    solucao[i][j] = tabuleiro[i][j];
                }
            }

            // Remover alguns números para criar o jogo
            Random random = new Random();
            int numerosParaRemover = 40; // Ajuste a dificuldade aqui

            for (int i = 0; i < numerosParaRemover; i++) {
                int linha = random.nextInt(9);
                int coluna = random.nextInt(9);

                if (tabuleiro[linha][coluna] != 0) {
                    tabuleiro[linha][coluna] = 0;
                    fixo[linha][coluna] = false;
                } else {
                    i--; // Tenta novamente se já estiver vazio
                }
            }

            // Marcar números fixos (os que não foram removidos)
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (tabuleiro[i][j] != 0) {
                        fixo[i][j] = true;
                    }
                }
            }
        }

        private boolean resolverSudoku(int linha, int coluna) {
            if (linha == 9) {
                return true; // Sudoku resolvido
            }

            if (coluna == 9) {
                return resolverSudoku(linha + 1, 0);
            }

            if (tabuleiro[linha][coluna] != 0) {
                return resolverSudoku(linha, coluna + 1);
            }

            Random random = new Random();
            int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9};

            // Embaralhar números para variedade
            for (int i = 0; i < 9; i++) {
                int j = random.nextInt(9);
                int temp = numeros[i];
                numeros[i] = numeros[j];
                numeros[j] = temp;
            }

            for (int num : numeros) {
                if (ehMovimentoValido(linha, coluna, num)) {
                    tabuleiro[linha][coluna] = num;

                    if (resolverSudoku(linha, coluna + 1)) {
                        return true;
                    }

                    tabuleiro[linha][coluna] = 0;
                }
            }

            return false;
        }

        private boolean ehMovimentoValido(int linha, int coluna, int numero) {
            // Verificar linha
            for (int i = 0; i < 9; i++) {
                if (tabuleiro[linha][i] == numero) {
                    return false;
                }
            }

            // Verificar coluna
            for (int i = 0; i < 9; i++) {
                if (tabuleiro[i][coluna] == numero) {
                    return false;
                }
            }

            // Verificar bloco 3x3
            int blocoLinha = (linha / 3) * 3;
            int blocoColuna = (coluna / 3) * 3;

            for (int i = blocoLinha; i < blocoLinha + 3; i++) {
                for (int j = blocoColuna; j < blocoColuna + 3; j++) {
                    if (tabuleiro[i][j] == numero) {
                        return false;
                    }
                }
            }

            return true;
        }

        public void exibirTabuleiro() {
            System.out.println("\n  1 2 3   4 5 6   7 8 9");
            System.out.println(" +-------+-------+-------+");

            for (int i = 0; i < 9; i++) {
                System.out.print((i + 1) + "| ");
                for (int j = 0; j < 9; j++) {
                    if (tabuleiro[i][j] == 0) {
                        System.out.print(". ");
                    } else {
                        System.out.print(tabuleiro[i][j] + " ");
                    }

                    if (j == 2 || j == 5) {
                        System.out.print("| ");
                    }
                }
                System.out.println("|");

                if (i == 2 || i == 5) {
                    System.out.println(" +-------+-------+-------+");
                }
            }
            System.out.println(" +-------+-------+-------+");
        }

        public boolean fazerJogada(int linha, int coluna, int numero) {
            if (linha < 1 || linha > 9 || coluna < 1 || coluna > 9) {
                System.out.println("Posição inválida! Use valores entre 1-9.");
                return false;
            }

            if (numero < 1 || numero > 9) {
                System.out.println("Número inválido! Use valores entre 1-9.");
                return false;
            }

            if (fixo[linha - 1][coluna - 1]) {
                System.out.println("Esta posição é fixa e não pode ser alterada!");
                return false;
            }

            if (!ehMovimentoValido(linha - 1, coluna - 1, numero)) {
                System.out.println("Movimento inválido! O número " + numero + " não pode ser colocado aqui.");
                return false;
            }

            tabuleiro[linha - 1][coluna - 1] = numero;
            return true;
        }

        public boolean verificarVitoria() {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (tabuleiro[i][j] != solucao[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void mostrarSolucao() {
            System.out.println("\nSolução:");
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(solucao[i][j] + " ");
                    if (j == 2 || j == 5) {
                        System.out.print("| ");
                    }
                }
                System.out.println();
                if (i == 2 || i == 5) {
                    System.out.println("------+-------+------");
                }
            }
        }
    }

