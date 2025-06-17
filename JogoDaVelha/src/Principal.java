import java.util.Scanner;

public class Principal {

    public Principal() {
        Scanner scanner = new Scanner(System.in);

        Jogo jogo = new Jogo();

        jogo.Executa(scanner);

        scanner.close();
    }
    public static void main(String[] args) throws Exception {
        new Principal();
    }
}
