package pedidos;

public class ItemPedido {
	
	private int CodItem, quantidade;
	private Produto produto;
	private Pedido pedido;
	private double precoUnit;
	
	public ItemPedido() {
	}

	public int getCodItem() {
		return CodItem;
	}
	public void setCodItem(int codItem) {
		CodItem = codItem;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public double getPrecoUnit() {
		return precoUnit;
	}
	public void setPrecoUnit(double precoUnit) {
		this.precoUnit = precoUnit;
	}
	
	
	public ItemPedido(int codItem, int quantidade, Produto produto, Pedido pedido, double precoUnit) {
		super();
		CodItem = codItem;
		this.quantidade = quantidade;
		this.produto = produto;
		this.pedido = pedido;
		this.precoUnit = precoUnit;
	}
	@Override
	public String toString() {
		return "ItemPedido [CodItem=" + CodItem + ", quantidade=" + quantidade + ", pedido=" + pedido + ", precoUnit="
				+ precoUnit + "]";
	}
	
	

}
