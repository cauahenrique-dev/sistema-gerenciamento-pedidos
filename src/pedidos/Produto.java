package pedidos;

public class Produto {
	
	private int codProduto, estoque;
	private String nome;
	private double preco;
	
	public Produto(){
	}
	
	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	
	public Produto(int codProduto, int estoque, String nome, double preco) {
		super();
		this.codProduto = codProduto;
		this.estoque = estoque;
		this.nome = nome;
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Produto [codProduto=" + codProduto + ", estoque=" + estoque + ", nome=" + nome + ", preco=" + preco
				+ "]";
	}

	
	
}
