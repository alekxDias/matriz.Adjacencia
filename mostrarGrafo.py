import networkx as nx
import matplotlib.pyplot as plt

# Lendo o arquivo de entrada
with open("matriz.txt") as f:
    type_of_graph = f.readline().strip()  # Tipo de grafo: "D" para dirigido, "ND" para não dirigido
    matrix = [list(map(int, line.split())) for line in f]  # Matriz de adjacência

# Criando o grafo
if type_of_graph == "D":
    G = nx.DiGraph()  # Grafo dirigido
else:
    G = nx.Graph()  # Grafo não dirigido

# Adicionando nós ao grafo
num_nodes = len(matrix)
G.add_nodes_from(range(1, num_nodes+1))

# Adicionando arestas ao grafo
for i in range(num_nodes):
    for j in range(num_nodes):
        if matrix[i][j] == 1:
            G.add_edge(i+1, j+1)

# Ajustando o estilo da visualização
plt.style.use("seaborn-darkgrid")
pos = nx.spring_layout(G)

# Desenhando o grafo
nx.draw(G, pos, with_labels=True, node_color="lightblue", node_size=300, font_size=12, font_weight="bold")
nx.draw_networkx_edge_labels(G, pos, edge_labels={(u, v): str(u)+"-"+str(v) for u, v in G.edges()}, font_color="red", font_size=10)
plt.title("Grafo " + type_of_graph, fontsize=16, fontweight="bold")
plt.show()  