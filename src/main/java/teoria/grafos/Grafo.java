package teoria.grafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Grafo {
	
	private final List<Aresta> arestas;
	private final List<Vertice> vertices;
	
	private String nome;
	private boolean eDirigido;
	private Integer ordem;
	private Integer tamanho;
	
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
		
		ordem = 0;
		tamanho = 0;
	}
	
	public Grafo(boolean eDirigido, Integer ordem, Integer tamanho) {
		this.eDirigido = eDirigido;
		this.ordem = ordem;
		this.tamanho = tamanho;
		arestas = new ArrayList<>();
		vertices = new ArrayList<>();
	}
	
	public void adicionaVertices(String... nomes) {
		
		for (String nome : nomes) {
			Vertice v = new Vertice(nome);
			vertices.add(v);
		}
		
		ordem = vertices.size();
	}
	
	public void addAresta(String nomeVertice1, String nomeVertice2) {
		addAresta("", nomeVertice1, nomeVertice2);
	}
	
	public void addAresta(String nomeAresta, String nomeVertice1, String nomeVertice2) {
		// Mantem a consistencia: nao cria aresta com vertices inexistentes.
		Vertice origem = getVerticeObrigatorio(nomeVertice1);
		Vertice destino = getVerticeObrigatorio(nomeVertice2);
		
		Aresta aresta = nomeAresta == null || nomeAresta.isBlank()
				? new Aresta(origem, destino)
				: new Aresta(nomeAresta, origem, destino);
		
		arestas.add(aresta);
		atualizarEstruturas(aresta);
	}
	
	private void atualizarEstruturas(Aresta a) {
		
		Vertice origem = a.getVerticeOrigem();
		Vertice destino = a.getVerticeDestino();
		
		if (eDirigido) {
			// No dirigido: OUT sai da origem, IN chega no destino.
			origem.incrementarOutDegree();
			destino.incrementarInDegree();
			
			// Grau total no dirigido = IN + OUT.
			origem.incrementarGrau();
			destino.incrementarGrau();
			
			origem.adicionarAdjacencia(destino);
			destino.adicionarAdjacente(origem);
		} else {
			// No nao dirigido, a mesma aresta conta para os dois vertices.
			origem.incrementarGrau();
			destino.incrementarGrau();
			
			origem.adicionarAdjacencia(destino);
			destino.adicionarAdjacencia(origem);
			
			origem.adicionarAdjacente(destino);
			destino.adicionarAdjacente(origem);
		}
		
		tamanho = arestas.size();
	}
	
	private Vertice getVerticeObrigatorio(String nomeVertice) {
		return encontraVertice(nomeVertice)
				.orElseThrow(() -> new IllegalArgumentException("Vertice " + nomeVertice + " não encontrado."));
	}
	
	private static int valorOuZero(Integer valor) {
		// Evita espalhar checagens de null em todos os getters numericos.
		return valor == null ? 0 : valor;
	}
	
	public String resumoOrdemETamanho() {
		return "Ordem: "
				+ "\nQuantidade de vertices = " + getOrdem()
				+ "\n\nTamanho: "
				+ "\nQuantidade de arestas = " + getTamanho();
	}
	
	public Optional<Vertice> encontraVertice(String nome) {
		
		for (Vertice vertice : vertices) {
			
			if (vertice.getValorVertice().equalsIgnoreCase(nome)) {
				return Optional.of(vertice);
			}
		}
		
		return Optional.empty();
	}
	
	public Integer getOrdem() {
		return ordem != null ? ordem : vertices.size();
	}
	
	public Integer getTamanho() {
		return tamanho != null ? tamanho : arestas.size();
	}
	
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	
	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}
	
	public Integer getGrau(String nomeVertice) {
		Vertice v = getVerticeObrigatorio(nomeVertice);
		
		if (eDirigido) {
			// Regra do grau em grafo dirigido.
			int in = valorOuZero(v.getInDegree());
			int out = valorOuZero(v.getOutDegree());
			return in + out;
		}
		
		return valorOuZero(v.getGrau());
	}
	
	public Integer getInDegree(String nomeVertice) {
		return valorOuZero(getVerticeObrigatorio(nomeVertice).getInDegree());
	}
	
	public Integer getOutDegree(String nomeVertice) {
		return valorOuZero(getVerticeObrigatorio(nomeVertice).getOutDegree());
	}
	
	public List<Vertice> getAdjacencias(String nomeVertice) {
		Vertice v = getVerticeObrigatorio(nomeVertice);
		return Collections.unmodifiableList(v.getAdjacenciasSeguro());
	}
	
	public List<Vertice> getAdjacentes(String nomeVertice) {
		Vertice v = getVerticeObrigatorio(nomeVertice);
		return Collections.unmodifiableList(v.getAdjacentesSeguro());
	}
	
	public String gerarRelatorioVertice(String nomeVertice) {
		Vertice v = getVerticeObrigatorio(nomeVertice);
		
		List<Vertice> listaAdjacencias = getAdjacencias(nomeVertice);
		List<Vertice> listaAdjacentes = getAdjacentes(nomeVertice);
		
		if (eDirigido) {
			return "Vertice " + v + " -> grau=" + getGrau(nomeVertice)
					+ ", inDegree=" + getInDegree(nomeVertice)
					+ ", outDegree=" + getOutDegree(nomeVertice)
					+ ", adjacencias(out)=" + listaAdjacencias
					+ ", adjacentes(in)=" + listaAdjacentes;
		}
		
		return "Vertice " + v + " -> grau=" + getGrau(nomeVertice)
				+ ", adjacencias=" + listaAdjacencias
				+ ", adjacentes=" + listaAdjacentes;
	}
	
	public String resumoFeaturesDoVertice(String nomeVertice) {
		Vertice v = getVerticeObrigatorio(nomeVertice);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Vertice ").append(v).append("\n");
		
		if (eDirigido) {
			sb.append("Grau de Vertice (Direcionado - outDegree + inDegree): ")
					.append(getOutDegree(nomeVertice)).append(" + ")
					.append(getInDegree(nomeVertice)).append(" = ")
					.append(getGrau(nomeVertice)).append("\n");
			sb.append("Adjacente (In - recebe arestas): ")
					.append(getAdjacentes(nomeVertice)).append("\n");
			sb.append("Adjacencias (Out - envia arestas): ")
					.append(getAdjacencias(nomeVertice));
		} else {
			sb.append("Grau de Vertice (Nao Direcionado - numero de arestas que se conectam nele): ")
					.append(getGrau(nomeVertice)).append("\n");
			sb.append("Adjacente (In): ")
					.append(getAdjacentes(nomeVertice)).append("\n");
			sb.append("Adjacencias (Out): ")
					.append(getAdjacencias(nomeVertice));
		}
		
		return sb.toString();
	}
	
	public void imprimirRelatorioVertices() {
		System.out.println("Relatorio de vertices:");
		for (Vertice v : vertices) {
			System.out.println(gerarRelatorioVertice(v.getValorVertice()));
		}
	}

	public void exibeMatrizAdjacencia() {
		int n = vertices.size();
		int[][] matriz = new int[n][n];

		for (Aresta a : arestas) {
			int origemIndex = vertices.indexOf(a.getVerticeOrigem());
			int destinoIndex = vertices.indexOf(a.getVerticeDestino());
			if (origemIndex < 0 || destinoIndex < 0) {
				continue;
			}

			if (eDirigido) {
				matriz[origemIndex][destinoIndex]++;
			} else {
				if (origemIndex == destinoIndex) {
					matriz[origemIndex][destinoIndex] += 2;
				} else {
					matriz[origemIndex][destinoIndex]++;
					matriz[destinoIndex][origemIndex]++;
				}
			}
		}

		System.out.println("Matriz de adjacencia:");
		imprimirMatrizQuadrada(matriz, vertices);
	}

	public void exibeMatrizIncidencia() {
		int n = vertices.size();
		int m = arestas.size();
		int[][] matriz = new int[n][m];

		for (int j = 0; j < arestas.size(); j++) {
			Aresta a = arestas.get(j);
			int origemIndex = vertices.indexOf(a.getVerticeOrigem());
			int destinoIndex = vertices.indexOf(a.getVerticeDestino());
			if (origemIndex < 0 || destinoIndex < 0) {
				continue;
			}

			if (eDirigido) {
				if (origemIndex == destinoIndex) {
					matriz[origemIndex][j] = 2;
				} else {
					matriz[origemIndex][j] = -1;
					matriz[destinoIndex][j] = 1;
				}
			} else {
				if (origemIndex == destinoIndex) {
					matriz[origemIndex][j] = 2;
				} else {
					matriz[origemIndex][j] = 1;
					matriz[destinoIndex][j] = 1;
				}
			}
		}

		System.out.println("Matriz de incidencia:");
		imprimirMatrizIncidencia(matriz, vertices, arestas);
	}

	private void imprimirMatrizQuadrada(int[][] matriz, List<Vertice> listaVertices) {
		int largura = larguraColunaVertices(listaVertices);
		StringBuilder header = new StringBuilder();
		header.append(String.format("%" + largura + "s", ""));
		for (Vertice v : listaVertices) {
			header.append(" ").append(String.format("%" + largura + "s", v.getValorVertice()));
		}
		System.out.println(header);

		for (int i = 0; i < matriz.length; i++) {
			StringBuilder linha = new StringBuilder();
			linha.append(String.format("%" + largura + "s", listaVertices.get(i).getValorVertice()));
			for (int j = 0; j < matriz[i].length; j++) {
				linha.append(" ").append(String.format("%" + largura + "d", matriz[i][j]));
			}
			System.out.println(linha);
		}
	}

	private void imprimirMatrizIncidencia(int[][] matriz, List<Vertice> listaVertices, List<Aresta> listaArestas) {
		int largura = Math.max(larguraColunaVertices(listaVertices), larguraColunaArestas(listaArestas));
		StringBuilder header = new StringBuilder();
		header.append(String.format("%" + largura + "s", ""));
		for (int j = 0; j < listaArestas.size(); j++) {
			String nome = nomeArestaExibicao(listaArestas.get(j), j);
			header.append(" ").append(String.format("%" + largura + "s", nome));
		}
		System.out.println(header);

		for (int i = 0; i < matriz.length; i++) {
			StringBuilder linha = new StringBuilder();
			linha.append(String.format("%" + largura + "s", listaVertices.get(i).getValorVertice()));
			for (int j = 0; j < matriz[i].length; j++) {
				linha.append(" ").append(String.format("%" + largura + "d", matriz[i][j]));
			}
			System.out.println(linha);
		}
	}

	private int larguraColunaVertices(List<Vertice> listaVertices) {
		int largura = 3;
		for (Vertice v : listaVertices) {
			if (v.getValorVertice() != null) {
				largura = Math.max(largura, v.getValorVertice().length());
			}
		}
		return largura;
	}

	private int larguraColunaArestas(List<Aresta> listaArestas) {
		int largura = 3;
		for (int i = 0; i < listaArestas.size(); i++) {
			String nome = nomeArestaExibicao(listaArestas.get(i), i);
			largura = Math.max(largura, nome.length());
		}
		return largura;
	}

	private String nomeArestaExibicao(Aresta aresta, int index) {
		String nomeAresta = aresta.getNomeA();
		if (nomeAresta == null || nomeAresta.isBlank()) {
			return "e" + (index + 1);
		}
		return nomeAresta;
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
		
		System.out.print("V(" + nomeGrafo + ") ={");
		
		for (int i = 0; i < vertices.size(); i++) {
			
			System.out.print(vertices.get(i));
			
			if (i < vertices.size() - 1) {
				System.out.print(", ");
			}
		}
		
		System.out.println("}\n");
		
		System.out.print("A(" + nomeGrafo + ")={");
		
		for (int i = 0; i < arestas.size(); i++) {
			
			System.out.print(arestas.get(i).getNomeA());
			
			if (i < arestas.size() - 1) {
				System.out.print(", ");
			}
		}
		
		System.out.println("}\n");
		
		System.out.println("ψ" + nomeGrafo + " :");
		
		for (Aresta a : arestas) {
			
			System.out.println(
					"ψ" + nomeGrafo + "(" + a.getNomeA() + ")= ("
							+ a.getVerticeOrigem() + ", "
							+ a.getVerticeDestino() + ")"
			);
		}
	}
}