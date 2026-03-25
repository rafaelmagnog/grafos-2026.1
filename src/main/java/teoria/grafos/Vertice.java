package teoria.grafos;

public class Vertice {
	
	private String valorVertice;
	
	public void setValorVertice(String valorVert) {
		this.valorVertice = valorVert;
	}
	
	@Override
	public String toString() {
		return valorVertice;
	}
}