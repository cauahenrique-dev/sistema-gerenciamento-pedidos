package pedidos;

import java.time.LocalDate;
import dados.Cliente;
import java.sql.Date;

public class Pedido {

	private int codPedido;
	private Date data;
	private String status;
	private Cliente cliente;

	public Pedido() {
}

	
	public int getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Pedido(int codPedido, Date data, String status, Cliente cliente) {
		super();
		this.codPedido = codPedido;
		this.data = data;
		this.status = status;
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Pedido [codPedido=" + codPedido + ", data=" + data + ", status=" + status + ", cliente=" + cliente
				+ "]";
	}

	
}
