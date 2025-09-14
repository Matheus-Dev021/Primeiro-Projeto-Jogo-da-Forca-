import java.util.*;
import java.util.stream.Collectors;

public class JogoForca {
    private String palavraSecreta;
    private char[] palavraExibida;
    private List<Character> letrasErradas;
    private int tentativasRestantes;
    private Scanner scanner;

    public JogoForca() {
        this.palavraSecreta = selecionarPalavraAleatoria();
        this.palavraExibida = new char[palavraSecreta.length()];
        Arrays.fill(palavraExibida, '_');
        this.letrasErradas = new ArrayList<>();
        this.tentativasRestantes = 6;
        this.scanner = new Scanner(System.in);
    }

    private String selecionarPalavraAleatoria() {
        String[] palavras = {"JAVA", "PROGRAMA", "COMPUTADOR", "ALGORITMO"};
        Random random = new Random();
        int indice = random.nextInt(palavras.length);
        return palavras[indice];
    }

    public void jogar() {
        System.out.println("Bem vindo ao jogo da Forca");
        System.out.println("A palavra tem " + palavraSecreta.length() + " letras.");
        while (tentativasRestantes > 0 && !jogoGanho()) {
            exibirEstadoJogo();
            char letra = lerLetra();
            if (adivinharLetra(letra)) {
                System.out.println("Letra Correta!");
            } else {
                tentativasRestantes--;
                letrasErradas.add(letra);
                System.out.println("Letra Errada! Tentativas restantes: " + tentativasRestantes);
            }
        }
        if (jogoGanho()) {
            exibirEstadoJogo();
            System.out.println("Parabéns! Você ganhou! A palavra era: " + palavraSecreta);
        } else {
            exibirForca();
            System.out.println("Fim de Jogo! A palavra era: " + palavraSecreta);
        }

        System.out.println("Deseja jogar novamente? (S/N)");
        String resposta = scanner.nextLine().trim().toUpperCase();
        if (resposta.equals("S")) {
            this.palavraSecreta = selecionarPalavraAleatoria();
            this.palavraExibida = new char[palavraSecreta.length()];
            Arrays.fill(palavraExibida, '_');
            this.letrasErradas.clear();
            this.tentativasRestantes = 6;
            jogar();
        } else {
            System.out.println("Obrigado por jogar!");
            scanner.close();
        }
    }

    private void exibirEstadoJogo() {
        exibirForca();
        System.out.print("Palavra: ");
        for (char c : palavraExibida) {
            System.out.print(c + " ");
        }
        System.out.println();
        System.out.println("Letras Erradas: " + letrasErradas.stream()
            .map(String::valueOf)
            .sorted()
            .collect(Collectors.joining(", ")));
    }

    private void exibirForca() {
        switch (tentativasRestantes) {
            case 6:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 5:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 4:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 3:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 2:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 1:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" /    |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 0:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" / \\  |");
                System.out.println("      |");
                System.out.println("=========");
                break;
        }
    }

    private char lerLetra() {
        char letra;
        do {
            System.out.println("Digite uma letra: ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Entrada inválida! Digite apenas uma letra.");
                continue;
            }
            letra = input.charAt(0);
            if (letrasErradas.contains(letra) || letraJaTentada(letra)) {
                System.out.println("Você já tentou essa letra. Tente outra.");
                continue;
            }
            break;
        } while (true);
        return letra;
    }

    private boolean letraJaTentada(char letra) {
        for (char c : palavraExibida) {
            if (c == letra) {
                return true;
            }
        }
        return false;
    }

    private boolean adivinharLetra(char letra) {
        boolean acertou = false;
        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letra) {
                palavraExibida[i] = letra;
                acertou = true;
            }
        }
        return acertou;
    }

    private boolean jogoGanho() {
        for (char c : palavraExibida) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        JogoForca jogo = new JogoForca();
        jogo.jogar();
    }
}
