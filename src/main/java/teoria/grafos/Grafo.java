package teoria.grafos;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
	
	List<Vertice> vertices;
	List<Aresta> arestas;
	String nome;
	
	public Grafo(String nome) {
		this.nome = nome;
		vertices = new ArrayList<>();
		arestas = new ArrayList<>();
	}
	
	public void addVertice(Vertice vertice) {
		vertices.add(vertice);
	}
	
	public void addAresta(Aresta aresta) {
		arestas.add(aresta);
	}
	
	public void imprimirGrafo() {
		
		System.out.println(nome + "=(V(" + nome + "), A(" + nome + "), ψ" + nome + ")\n");
		
		// VERTICES
		System.out.print("V(" + nome + ") ={");
		for (int i = 0; i < vertices.size(); i++) {
			System.out.print(vertices.get(i));
			if (i < vertices.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("}\n");
		
		// ARESTAS
		System.out.print("A(" + nome + ")={");
		for (int i = 0; i < arestas.size(); i++) {
			System.out.print(arestas.get(i).getNomeAresta());
			if (i < arestas.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("}\n");
		
		
		// FUNÇÃO PSI
		System.out.println("ψ" + nome + " :");
		for (Aresta a : arestas) {
			System.out.println(
					"ψ" + nome + "(" + a.getNomeAresta() + ")= (" +
							a.getVertice1() + ", " +
							a.getVertice2() + ")"
			);
		}
	}
}