# Teoria dos Grafos — 2026.1

Implementação em Java de estruturas e algoritmos fundamentais de **Teoria dos Grafos**, desenvolvida como material de estudo para a disciplina de 2026.1.

---

## 📋 Conteúdo

- [Sobre o projeto](#sobre-o-projeto)
- [Tecnologias](#tecnologias)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Exemplos de uso](#exemplos-de-uso)

---

## Sobre o projeto

Este repositório contém a modelagem de grafos dirigidos e não dirigidos, com suporte a:

- Adição de vértices e arestas
- Cálculo de grau, in-degree e out-degree
- Consulta de adjacências e adjacentes
- Impressão formal do grafo no formato matemático `G=(V(G), A(G), ψG)`
- Geração de relatórios detalhados por vértice

---

## Tecnologias

| Ferramenta | Versão |
|---|---|
| Java | 25+ |
| Gradle| 8.x |
| Lombok | 1.18.44 |

---

## Estrutura do projeto

```
src/
└── main/
    └── java/
        └── teoria/grafos/
            ├── Aresta.java    # Modelo de aresta (edge)
            ├── Grafo.java     # Estrutura principal do grafo
            ├── Main.java      # Demonstração e testes manuais
            └── Vertice.java   # Modelo de vértice (node)
lib/
└── lombok-1.18.42.jar         # Lombok local (fallback)
build.gradle.kts               # Configuração do build
```

---


## Exemplos de uso

```java
// Grafo não dirigido
Grafo g = new Grafo("G");
g.adicionaVertices("u", "v", "w");
g.addAresta("a1", "u", "v");
g.addAresta("a2", "v", "w");

g.imprimirGrafo();
System.out.println(g.resumoOrdemETamanho());
System.out.println(g.resumoFeaturesDoVertice("v"));

// Grafo dirigido
Grafo dirigido = new Grafo(true);
dirigido.adicionaVertices("A", "B", "C");
dirigido.addAresta("e1", "A", "B");
dirigido.addAresta("e2", "B", "C");

System.out.println(dirigido.getInDegree("B"));   // 1
System.out.println(dirigido.getOutDegree("B"));  // 1
```

---

## Licença

Uso acadêmico — 2026.1.
