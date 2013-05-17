import java.util.Date;
import java.util.List;


public class Venda {

	private int idVenda;

	private Date dtVenda;

	private String nmVendendor;

	private float VlTotal;

	private List<ItmVenda> items;

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public Date getDtVenda() {
		return dtVenda;
	}

	public void setDtVenda(Date dtVenda) {
		this.dtVenda = dtVenda;
	}

	public String getNmVendendor() {
		return nmVendendor;
	}

	public void setNmVendendor(String nmVendendor) {
		this.nmVendendor = nmVendendor;
	}

	public float getVlTotal() {
		return VlTotal;
	}

	public void setVlTotal(float vlTotal) {
		VlTotal = vlTotal;
	}

	public List<ItmVenda> getItems() {
		return items;
	}

	public void setItems(List<ItmVenda> items) {
		this.items = items;
	}

}
