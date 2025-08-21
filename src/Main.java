import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Sudoku sudoku = new Sudoku();

    System.out.println("=== SUDOKU ===");
    System.out.println("Instruções:");
    System.out.println("- Digite 'jogar linha coluna numero' para fazer uma jogada");
    System.out.println("- Digite 'sair' para encerrar o jogo");
    System.out.println("- Digite 'solucao' para ver a solução");
    System.out.println("- Digite 'novo' para começar um novo jogo");

    boolean jogando = true;

    while (jogando) {
      sudoku.exibirTabuleiro();

      System.out.print("\nSua jogada: ");
      String input = scanner.nextLine().trim().toLowerCase();

      if (input.equals("sair")) {
        jogando = false;
        System.out.println("Obrigado por jogar!");
      } else if (input.equals("solucao")) {
        sudoku.mostrarSolucao();
      } else if (input.equals("novo")) {
        sudoku = new Sudoku();
        System.out.println("Novo jogo iniciado!");
      } else if (input.startsWith("jogar")) {
        String[] partes = input.split(" ");
        if (partes.length == 4) {
          try {
            int linha = Integer.parseInt(partes[1]);
            int coluna = Integer.parseInt(partes[2]);
            int numero = Integer.parseInt(partes[3]);

            if (sudoku.fazerJogada(linha, coluna, numero)) {
              System.out.println("Jogada realizada com sucesso!");

              if (sudoku.verificarVitoria()) {
                sudoku.exibirTabuleiro();
                System.out.println("🎉 Parabéns! Você completou o Sudoku!");
                System.out.print("Deseja jogar novamente? (s/n): ");
                String resposta = scanner.nextLine().trim().toLowerCase();
                if (resposta.equals("s")) {
                  sudoku = new Sudoku();
                } else {
                  jogando = false;
                }
              }
            }
          } catch (NumberFormatException e) {
            System.out.println("Formato inválido! Use: jogar linha coluna numero");
          }
        } else {
          System.out.println("Formato inválido! Use: jogar linha coluna numero");
        }
      } else {
        System.out.println("Comando não reconhecido!");
      }
    }

    scanner.close();
  }
}
