package teoria.grafos;

public class Main {

    public static void main(String[] args) {

        // GRAFO NÃO DIRECIONADO
        Grafo grafo = new Grafo("R");

        grafo.adicionaVertices("u", "v", "w", "x", "y");

        grafo.addAresta("a", "u", "v");
        grafo.addAresta("b", "u", "u");
        grafo.addAresta("c", "v", "w");
        grafo.addAresta("d", "w", "x");
        grafo.addAresta("e", "v", "x");
        grafo.addAresta("f", "w", "x");
        grafo.addAresta("g", "u", "x");
        grafo.addAresta("h", "x", "y");

        System.out.println("===== GRAFO R (Não direcionado) =====");
        grafo.imprimirGrafo();
        grafo.exibeMatrizAdjacencia();
        grafo.exibeMatrizIncidencia();

        // GRAFO DIRECIONADO
        Grafo grafoDirigido = new Grafo(true);

        grafoDirigido.adicionaVertices("A", "B", "C", "D");

        grafoDirigido.addAresta("a1", "A", "B");
        grafoDirigido.addAresta("a2", "B", "C");
        grafoDirigido.addAresta("a3", "C", "A");
        grafoDirigido.addAresta("a4", "C", "D");

        System.out.println("\n===== GRAFO DIRECIONADO =====");
        grafoDirigido.imprimirGrafo();
        grafoDirigido.exibeMatrizAdjacencia();
        grafoDirigido.exibeMatrizIncidencia();

        // GRAFO PARA TESTAR FEATURES
        Grafo grafoTeste = new Grafo("T");

        grafoTeste.adicionaVertices("1", "2", "3", "4", "5");

        grafoTeste.addAresta("e1", "1", "2");
        grafoTeste.addAresta("e2", "1", "3");
        grafoTeste.addAresta("e3", "2", "4");
        grafoTeste.addAresta("e4", "3", "4");
        grafoTeste.addAresta("e5", "4", "5");

        System.out.println("\n===== GRAFO T (Teste de funcionalidades) =====");
        grafoTeste.imprimirGrafo();
        grafoTeste.exibeMatrizAdjacencia();
        grafoTeste.exibeMatrizIncidencia();

        // TESTES DAS NOVAS FEATURES
        System.out.println("\n===== TESTES =====");

        System.out.println("\n[Grafo T] " + grafoTeste.resumoOrdemETamanho());

        System.out.println("\n[Grafo T - Vertice 1]");
        System.out.println(grafoTeste.resumoFeaturesDoVertice("1"));

        System.out.println("\n[Grafo T - Vertice 4]");
        System.out.println(grafoTeste.resumoFeaturesDoVertice("4"));

        System.out.println("\n[Grafo Dirigido - Vertice A]");
        System.out.println(grafoDirigido.resumoFeaturesDoVertice("A"));

        System.out.println("\n[Grafo Dirigido - Vertice C]");
        System.out.println(grafoDirigido.resumoFeaturesDoVertice("C"));

        // RELATORIO COMPLETO POR VERTICE
        System.out.println("\n===== RELATORIO COMPLETO POR VERTICE (GRAFO T) =====");
        grafoTeste.imprimirRelatorioVertices();
    }
}
