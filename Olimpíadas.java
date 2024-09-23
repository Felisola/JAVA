import java.util.*;


class Jogador {
    String nome;
    String pais;
    String modalidade;
    int medalhas;

    public Jogador(String nome, String pais, String modalidade) {
        this.nome = nome;
        this.pais = pais;
        this.modalidade = modalidade;
        this.medalhas = 0; // Inicializa o número de medalhas como 0
    }

    public void adicionarMedalha() {
        this.medalhas++;
    }
}

class Pais {
    String nome;
    int totalMedalhas;

    public Pais(String nome) {
        this.nome = nome;
        this.totalMedalhas = 0; // Inicializa o total de medalhas como 0
    }

    public void adicionarMedalha() {
        this.totalMedalhas++;
    }
}

public class Olimpíadas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Jogador> jogadores = new HashMap<>();
        Map<String, Pais> paises = new HashMap<>();

        // Entrada de dados
        System.out.print("Número de jogadores: ");
        int numJogadores = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        for (int i = 0; i < numJogadores; i++) {
            System.out.println("Jogador " + (i + 1) + ":");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("País: ");
            String paisNome = scanner.nextLine();
            System.out.print("Modalidade: ");
            String modalidade = scanner.nextLine();

            // Adiciona o jogador
            Jogador jogador = new Jogador(nome, paisNome, modalidade);
            jogadores.put(nome, jogador);

            // Adiciona o país
            paises.putIfAbsent(paisNome, new Pais(paisNome));
        }

        // Simulação da contagem de medalhas
        System.out.print("Número de medalhas a serem contabilizadas: ");
        int numMedalhas = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        for (int i = 0; i < numMedalhas; i++) {
            System.out.println("Medalha " + (i + 1) + ":");
            System.out.print("Nome do jogador: ");
            String nomeJogador = scanner.nextLine();
            System.out.print("Tipo de medalha (Ouro/Prata/Bronze): ");
            String tipoMedalha = scanner.nextLine();

            // Adiciona medalha ao jogador
            Jogador jogador = jogadores.get(nomeJogador);
            if (jogador != null) {
                jogador.adicionarMedalha();
                paises.get(jogador.pais).adicionarMedalha();
            } else {
                System.out.println("Jogador não encontrado.");
            }
        }

        // Saída de dados
        System.out.println("\nClassificação dos países e jogadores:");
        List<Map.Entry<String, Pais>> paisList = new ArrayList<>(paises.entrySet());
        paisList.sort((a, b) -> b.getValue().totalMedalhas - a.getValue().totalMedalhas);

        for (Map.Entry<String, Pais> entry : paisList) {
            System.out.println("País: " + entry.getKey() + ", Total de Medalhas: " + entry.getValue().totalMedalhas);
        }

        System.out.println("\nDetalhes dos jogadores:");
        for (Jogador jogador : jogadores.values()) {
            System.out.println("Jogador: " + jogador.nome + ", País: " + jogador.pais + ", Modalidade: " + jogador.modalidade + ", Medalhas: " + jogador.medalhas);
        }

        scanner.close();
    }
}