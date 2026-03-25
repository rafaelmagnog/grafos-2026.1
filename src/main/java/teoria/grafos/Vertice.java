package teoria.grafos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Vertice {
	
	private String valorVertice;
	private Integer grau;
	private Integer inDegree;
	private Integer outDegree;
	
	private List<Vertice> adjacencias;
	private List<Vertice> adjacentes;
	
	public Vertice(String valorVertice) {
		this.valorVertice = valorVertice;
		
		grau = 0;
		inDegree = 0;
		outDegree = 0;
		
		adjacencias = new ArrayList<>();
		adjacentes = new ArrayList<>();
	}
	
	public Vertice(String valorVertice,
	               Integer grau,
	               Integer inDegree,
	               Integer outDegree,
	               List<Vertice> adjacencias,
	               List<Vertice> adjacentes) {
		this.valorVertice = valorVertice;
		this.grau = grau;
		this.inDegree = inDegree;
		this.outDegree = outDegree;
		this.adjacencias = adjacencias;
		this.adjacentes = adjacentes;
	}
	
	public void adicionarAdjacencia(Vertice destino) {
		if (adjacencias == null) {
			adjacencias = new ArrayList<>();
		}
		adjacencias.add(destino);
	}
	
	public void adicionarAdjacente(Vertice origem) {
		if (adjacentes == null) {
			adjacentes = new ArrayList<>();
		}
		adjacentes.add(origem);
	}
	
	public void incrementarGrau() {
		if (grau == null) {
			grau = 0;
		}
		grau++;
	}
	
	public void incrementarInDegree() {
		if (inDegree == null) {
			inDegree = 0;
		}
		inDegree++;
	}
	
	public void incrementarOutDegree() {
		if (outDegree == null) {
			outDegree = 0;
		}
		outDegree++;
	}
	
	public List<Vertice> getAdjacenciasSeguro() {
		return adjacencias == null ? Collections.emptyList() : new ArrayList<>(adjacencias);
	}
	
	public List<Vertice> getAdjacentesSeguro() {
		return adjacentes == null ? Collections.emptyList() : new ArrayList<>(adjacentes);
	}
	
	@Override
	public String toString() {
		return valorVertice;
	}
}
