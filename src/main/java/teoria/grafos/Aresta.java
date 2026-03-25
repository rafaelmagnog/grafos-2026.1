package teoria.grafos;

public class Aresta {
	
	private String nomeAresta;
	private Vertice vertice1;
	private Vertice vertice2;
	
	public void setNomeAresta(String nome) {
		this.nomeAresta = nome;
	}
	
	public String getNomeAresta() {
		return nomeAresta;
	}
	
	public Vertice getVertice1() {
		return vertice1;
	}
	
	public Vertice getVertice2() {
		return vertice2;
	}
	
	public void setVertices(Vertice v1, Vertice v2) {
		this.vertice1 = v1;
		this.vertice2 = v2;
	}
}