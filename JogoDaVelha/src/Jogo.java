import java.util.Scanner;

public class Jogo {

    private char[][] tabuleiro =  new char[3][3];
    private int[] posicao =  new int[2];

    public void Executa(Scanner leitor) {
        int resposta = 0;
        
        while(resposta != 2) {
            
            resposta = ExibirMenu(leitor);

            switch (resposta) {
                case 1:
                    popularJogo();
                    mostrarJogo();
                    fazerJogadas(leitor);
                    break;
                case 2:
                    System.out.println("Encerrando jogo.");
                    break;
                default:
                    break;
            }
        }

    }

    public int ExibirMenu(Scanner leitor) {

        boolean respostaValida = false;
        int resposta = 0;

        do {

            System.out.println("=-=-=-=-=-=-=-=-=-=");
            System.out.println("   JOGO DA VELHA");
            System.out.println("=-=-=-=-=-=-=-=-=-=");
            System.out.println("[ 1 ] Jogar");
            System.out.println("[ 2 ] Sair");
            System.out.println("=-=-=-=-=-=-=-=-=-=");
            System.out.print("Escolha: ");

            resposta = leitor.nextInt();

            if (resposta > 0 && resposta < 3) {
                respostaValida = true;
            } else {
                System.out.println("Resposta inválida, tente novamente!");
            }

        } while (!respostaValida);
     
        return resposta;

    }

    public void popularJogo() {
        for (int linha = 0; linha < tabuleiro[0].length; linha++) {
            for (int coluna = 0; coluna < tabuleiro[1].length; coluna++) {
                tabuleiro[linha][coluna] = ' ';
            }
        }
    }


    public void mostrarJogo() {
        System.out.println("  0   1   2");
        for (int linha = 0; linha < tabuleiro[0].length; linha++) {
            System.out.print(linha + " ");
            for (int coluna = 0; coluna < tabuleiro[1].length; coluna++) {
                System.out.print(tabuleiro[linha][coluna]);
                if (coluna < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

    public void fazerJogadas(Scanner leitor) {
        boolean ganhador = false;
        int jogadas = 0;
        char jogador = 'X';

        while (!ganhador && jogadas < 9) {
            boolean jogadaFeita = false;
            do {
                if (jogadas % 2 == 0) {
                        jogador = 'X';

                    } else {
                        jogador = 'O';
                }
                System.out.println("=-=-=-=-=-=-=-=-=-=");
                System.out.println("Jogador: " + jogador);
                System.out.println("=-=-=-=-=-=-=-=-=-=");
                
                System.out.print("Linha: ");
                posicao[0] = leitor.nextInt();

                System.out.print("Coluna: ");
                posicao[1] = leitor.nextInt(); 

                if (verificarPosicaoValida()) {
                    
                    jogadas++;

                    if (jogadas % 2 == 0) {
                        jogador = 'O';

                    } else {
                        jogador = 'X';
                    }

                    atualizarJogo(jogador);

                    jogadaFeita = true;
                    
                } else {
                    System.out.println("Posição inválida! Tente novamente.");
                }

            } while(!jogadaFeita);

            mostrarJogo();

            if(verificarSeGanhou(jogador)) {
                ganhador = true;
            }

        }

        if (jogadas == 9 && !ganhador) {
            System.out.println("=-=-=-=-=-=-=-=-=-=");
            System.out.println("Deu velha!");
        }
    }

    public boolean verificarPosicaoValida() {
        
        if (posicao[0] >= 0 && posicao[0] <= 2) {
            if (posicao[1] >= 0 && posicao[1] <= 2) {
                if (tabuleiro[posicao[0]][posicao[1]] == ' ') {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }  else {
            return false;
        }
    }

    public void atualizarJogo(char jogador) {
        tabuleiro[posicao[0]][posicao[1]] = jogador;
    }

    public boolean verificarSeGanhou(char jogador) {
        boolean ganhou = false;
        for (int i = 0; i < tabuleiro[0].length; i++) {
            if (tabuleiro[i][0] != ' ' && 
            tabuleiro[i][0] == tabuleiro[i][1] && 
            tabuleiro[i][1] == tabuleiro[i][2]) {
                ganhou = true;
            }
        }

        for (int i = 0; i < tabuleiro[0].length; i++) {
            if (tabuleiro[0][i] != ' ' && 
            tabuleiro[0][i] == tabuleiro[1][i] && 
            tabuleiro[1][i] == tabuleiro[2][i]) {
                ganhou = true;
            }
        }

        if (tabuleiro[0][0] != ' ' &&
        tabuleiro[0][0] == tabuleiro[1][1] &&
        tabuleiro[1][1] == tabuleiro[2][2]) {
            ganhou = true;
        } else if (tabuleiro[0][2] != ' ' &&
        tabuleiro[0][2] == tabuleiro[1][1] &&
        tabuleiro[1][1] == tabuleiro[2][0]) {
            ganhou = true;
        }

        if (!ganhou) {
            return false;
        } else {
            System.out.println("=-=-=-=-=-=-=-=-=-=");
            System.out.println("Ganhador " + jogador);
            return true;
        }
    }
}
