package teoria.grafos;

public class Main {
	
	public static void main(String[] args) {
		
		Grafo h = new Grafo("H");
		
		Vertice u = new Vertice();
		u.setValorVertice("u");
		
		Vertice v = new Vertice();
		v.setValorVertice("v");
		
		Vertice w = new Vertice();
		w.setValorVertice("w");
		
		Vertice x = new Vertice();
		x.setValorVertice("x");
		
		Vertice y = new Vertice();
		y.setValorVertice("y");
		
		h.addVertice(u);
		h.addVertice(v);
		h.addVertice(w);
		h.addVertice(x);
		h.addVertice(y);
		
		Aresta a = new Aresta();
		a.setNomeAresta("a");
		a.setVertices(u, v);
		
		Aresta b = new Aresta();
		b.setNomeAresta("b");
		b.setVertices(u, u);
		
		Aresta c = new Aresta();
		c.setNomeAresta("c");
		c.setVertices(v, w);
		
		Aresta d = new Aresta();
		d.setNomeAresta("d");
		d.setVertices(w, x);
		
		Aresta e = new Aresta();
		e.setNomeAresta("e");
		e.setVertices(v, x);
		
		Aresta f = new Aresta();
		f.setNomeAresta("f");
		f.setVertices(w, x);
		
		Aresta g = new Aresta();
		g.setNomeAresta("g");
		g.setVertices(u, x);
		
		Aresta h1 = new Aresta();
		h1.setNomeAresta("h");
		h1.setVertices(x, y);
		
		h.addAresta(a);
		h.addAresta(b);
		h.addAresta(c);
		h.addAresta(d);
		h.addAresta(e);
		h.addAresta(f);
		h.addAresta(g);
		h.addAresta(h1);
		
		h.imprimirGrafo();
	}
}