import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class MatrizAdjacente {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // Leitura do arquivo
        File file = new File("vertices.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
            return;
        }

        // Leitura do tipo de grafo (D ou ND)
        String tipo = scanner.nextLine();
        boolean direcionado = tipo.equals("D");
        System.out.println("Tipo do grafo: " + tipo);

        // Leitura dos vértices e conexões
        int numVertices = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] vertices = line.split(",");
            int origem = Integer.parseInt(vertices[0]);
            int destino = Integer.parseInt(vertices[1]);
            numVertices = Math.max(numVertices, Math.max(origem, destino));
        }
        int[][] matrizAdjacente = new int[numVertices][numVertices];
        scanner.close();
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
            return;
        }
        scanner.nextLine(); // Pula a primeira linha (tipo de grafo)
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] vertices = line.split(",");
            int origem = Integer.parseInt(vertices[0]) - 1;
            int destino = Integer.parseInt(vertices[1]) - 1;
            matrizAdjacente[origem][destino] = 1;
            if (!direcionado) {
                matrizAdjacente[destino][origem] = 1;
            }
        }

        // Impressão da matriz de adjacência
        System.out.println("Matriz de adjacência:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrizAdjacente[i][j] + " ");
            }
            System.out.println();
        }
        
        try {
            PrintWriter writer = new PrintWriter("matriz.txt", "UTF-8");
            if (direcionado) {
                writer.println("D");
            } else {
                writer.println("ND");
            }
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    writer.print(matrizAdjacente[i][j] + " ");
                }
                writer.println();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Codificação não suportada");
        }

        int choice = 0;
        try (Scanner input = new Scanner(System.in)) {
            while (choice != 5) {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Verificar se dois vertices sao adjacentes");
                System.out.println("2. Calcular o grau de um vertice qualquer");
                System.out.println("3. Buscar todos os vizinhos de um vertice qualquer");
                System.out.println("4. Visitar todas as arestas do grafo");
                System.out.println("5. Sair");

                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Digite o primeiro vértice: ");
                        int v = sc.nextInt();
                        System.out.print("Digite o segundo vértice: ");
                        int vx = sc.nextInt();
                        scanner.close();
                        boolean adjacente = false;
                        if (matrizAdjacente[v - 1][vx - 1] == 1 || matrizAdjacente[vx - 1][v - 1] == 1) {
                            adjacente = true;
                        }

                        if (adjacente) {
                            System.out.println("Os vértices " + v + " e " + vx + " são adjacentes.");
                        } else {
                            System.out.println("Os vértices " + v + " e " + vx + " não são adjacentes.");
                        }

                        break;
                    case 2:
                        Scanner sca = new Scanner(System.in);
                        System.out.print("Digite o vértice para calcular o grau: ");
                        int vertice = sca.nextInt();
                        int grau = 0;
                        for (int i = 0; i < matrizAdjacente.length; i++) {
                            if (matrizAdjacente[vertice - 1][i] == 1) {
                                grau++;
                            }
                        }
                        System.out.println("O grau do vértice " + vertice + " é " + grau);

                        break;

                    case 3:
                        Scanner sc3 = new Scanner(System.in);
                        System.out.print("Digite o vértice: ");
                        int vertice3 = sc3.nextInt();

                        System.out.print("Os vizinhos do vértice " + vertice3 + " são: ");
                        for (int i = 0; i < numVertices; i++) {
                            if (matrizAdjacente[vertice3 - 1][i] == 1) {
                                System.out.print(i + 1 + " ");
                            }
                        }
                        System.out.println();
                        break;
                    case 4:

                        System.out.println("Visitando todas as arestas do grafo:");
                        for (int i = 0; i < numVertices; i++) {
                            for (int j = 0; j < numVertices; j++) {
                                if (matrizAdjacente[i][j] == 1) {
                                    System.out.println("Aresta entre vértices " + (i + 1) + " e " + (j + 1));
                                }
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Escolha novamente.");
                }
            }
        }

    }
}
