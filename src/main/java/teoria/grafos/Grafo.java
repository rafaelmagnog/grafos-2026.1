package teoria.grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Grafo {
	private final List<Aresta> arestas;
	private final List<Vertice> vertices;
	private String nome;
	private boolean eDirigido;
	
	public Grafo() {
		this(false);
	}
	
	public Grafo(String nome) {
		this();
		this.nome = nome;
	}
	
	public Grafo(boolean eDirigido) {
		this.eDirigido = eDirigido;
		arestas = new ArrayList<>();
		vertices = new ArrayList<>();
	}
	
	public void adicionaVertices(String... nomes) {
		for (String nome : nomes) { //for-each
			vertices.add(new Vertice(nome));
		}
	}
	
	public void addAresta(String nomeVertice1, String nomeVertice2) {
		arestas.add(criaAresta("", nomeVertice1, nomeVertice2));
	}
	
	public void addAresta(String nomeAresta, String nomeVertice1, String nomeVertice2) {
		arestas.add(criaAresta(nomeAresta, nomeVertice1, nomeVertice2));
	}
	
	private Aresta criaAresta(String nomeAresta, String nomeVertice1, String nomeVertice2) {
		Vertice v1 = encontraVertice(nomeVertice1)
				.orElseThrow(() -> new IllegalArgumentException("Vertice " + nomeVertice1 + " não encontrado."));
		Vertice v2 = encontraVertice(nomeVertice2)
				.orElseThrow(() -> new IllegalArgumentException("Vertice " + nomeVertice2 + " não encontrado."));
		infereSeGrafoEDirecionado(v1, v2);
		return nomeAresta.isEmpty() ? new Aresta(v1, v2) : new Aresta(nomeAresta, v1, v2);
	}
	
	private void infereSeGrafoEDirecionado(Vertice v1, Vertice v2) {
		if (!eDirigido) { //eDirigido == false
			if (eSelfLoop(v1, v2)) {
				eDirigido = true;
			} else {
				for (Aresta aresta : arestas) { //for-each
					if (eViaMaoDupla(v1, v2, aresta)) {
						eDirigido = true;
						break;
					}
					if (eArestaDuplicada(v1, v2, aresta)) {
						eDirigido = true;
						break;
					}
				}
			}
		}
	}
	
	private static boolean eArestaDuplicada(Vertice v1, Vertice v2, Aresta aresta) {
		return aresta.getVerticeOrigem().equals(v1) && aresta.getVerticeDestino().equals(v2);
	}
	
	private static boolean eViaMaoDupla(Vertice v1, Vertice v2, Aresta aresta) {
		return aresta.getVerticeOrigem().equals(v2) && aresta.getVerticeDestino().equals(v1);
	}
	
	private static boolean eSelfLoop(Vertice v1, Vertice v2) {
		return v1.getValorVertice().equals(v2.getValorVertice());
	}
	
	public Optional<Vertice> encontraVertice(String nome) {
		for (Vertice vertice : vertices) {
			if (vertice.getValorVertice().equalsIgnoreCase(nome)) {
				return Optional.of(vertice);
			}
		}
		return Optional.empty();
	}
	
	@Override
	public String toString() {
		return """
                Grafo{
                   direcionado = %s,
                   vertices = %s,
                   arestas = %s
                }""".formatted(eDirigido ? "sim" : "não", vertices, arestas);
	}
	
	public void imprimirGrafo() {
		
		String nomeGrafo = (nome == null || nome.isBlank()) ? "G" : nome;
		
		System.out.println(nomeGrafo + "=(V(" + nomeGrafo + "), A(" + nomeGrafo + "), ψ" + nomeGrafo + ")\n");
		
		// VERTICES
		System.out.print("V(" + nomeGrafo + ") ={");
		for (int i = 0; i < vertices.size(); i++) {
			System.out.print(vertices.get(i));
			if (i < vertices.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("}\n");
		
		// ARESTAS
		System.out.print("A(" + nomeGrafo + ")={");
		for (int i = 0; i < arestas.size(); i++) {
			System.out.print(arestas.get(i).getNomeA());
			if (i < arestas.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("}\n");
		
		// FUNÇÃO PSI
		System.out.println("ψ" + nomeGrafo + " :");
		for (Aresta a : arestas) {
			System.out.println(
					"ψ" + nomeGrafo + "(" + a.getNomeA() + ")= (" +
							a.getVerticeOrigem() + ", " +
							a.getVerticeDestino() + ")"
			);
		}
	}
}