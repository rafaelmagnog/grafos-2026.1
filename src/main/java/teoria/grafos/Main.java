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
		
		
		// GRAFO DIRECIONADO
		Grafo grafoDirigido = new Grafo(true);
		
		grafoDirigido.adicionaVertices("A", "B", "C", "D");
		
		grafoDirigido.addAresta("a1", "A", "B");
		grafoDirigido.addAresta("a2", "B", "C");
		grafoDirigido.addAresta("a3", "C", "A");
		grafoDirigido.addAresta("a4", "C", "D");
		
		System.out.println("\n===== GRAFO DIRECIONADO =====");
		grafoDirigido.imprimirGrafo();
	}
}