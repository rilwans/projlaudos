package br.com.app;

public class Oferta {

	private String OFERTA; // parent node
	private String SITE ;
	private String TITULO;
	private String HREF;
	private String IMAGEM;
	private String PRECOTOTAL;
	private String PRECODESCONTO;
	private String DESCONTO ;
	private String QTDCOMPRADOS ;
	private String ATIVA ;
	/**
	 * @return the oFERTA
	 */
	public String getOFERTA() {
		return OFERTA;
	}
	/**
	 * @param oFERTA the oFERTA to set
	 */
	public void setOFERTA(String oFERTA) {
		OFERTA = oFERTA;
	}
	/**
	 * @return the sITE
	 */
	public String getSITE() {
		return SITE;
	}
	/**
	 * @param sITE the sITE to set
	 */
	public void setSITE(String sITE) {
		SITE = sITE;
	}
	/**
	 * @return the tITULO
	 */
	public String getTITULO() {
		return TITULO;
	}
	/**
	 * @param tITULO the tITULO to set
	 */
	public void setTITULO(String tITULO) {
		TITULO = tITULO;
	}
	/**
	 * @return the hREF
	 */
	public String getHREF() {
		return HREF;
	}
	/**
	 * @param hREF the hREF to set
	 */
	public void setHREF(String hREF) {
		HREF = hREF;
	}
	/**
	 * @return the iMAGEM
	 */
	public String getIMAGEM() {
		return IMAGEM;
	}
	/**
	 * @param iMAGEM the iMAGEM to set
	 */
	public void setIMAGEM(String iMAGEM) {
		IMAGEM = iMAGEM;
	}
	/**
	 * @return the pRECOTOTAL
	 */
	public String getPRECOTOTAL() {
		return PRECOTOTAL;
	}
	/**
	 * @param pRECOTOTAL the pRECOTOTAL to set
	 */
	public void setPRECOTOTAL(String pRECOTOTAL) {
		PRECOTOTAL = pRECOTOTAL;
	}
	/**
	 * @return the pRECODESCONTO
	 */
	public String getPRECODESCONTO() {
		return PRECODESCONTO;
	}
	/**
	 * @param pRECODESCONTO the pRECODESCONTO to set
	 */
	public void setPRECODESCONTO(String pRECODESCONTO) {
		PRECODESCONTO = pRECODESCONTO;
	}
	/**
	 * @return the dESCONTO
	 */
	public String getDESCONTO() {
		return DESCONTO;
	}
	/**
	 * @param dESCONTO the dESCONTO to set
	 */
	public void setDESCONTO(String dESCONTO) {
		DESCONTO = dESCONTO;
	}
	/**
	 * @return the qTDCOMPRADOS
	 */
	public String getQTDCOMPRADOS() {
		return QTDCOMPRADOS;
	}
	/**
	 * @param qTDCOMPRADOS the qTDCOMPRADOS to set
	 */
	public void setQTDCOMPRADOS(String qTDCOMPRADOS) {
		QTDCOMPRADOS = qTDCOMPRADOS;
	}
	/**
	 * @return the aTIVA
	 */
	public String getATIVA() {
		return ATIVA;
	}
	/**
	 * @param aTIVA the aTIVA to set
	 */
	public void setATIVA(String aTIVA) {
		ATIVA = aTIVA;
	}

	public float getVlDesconto(){
		try{
			return Float.valueOf(PRECODESCONTO.replace("R$", "").replace(",", ".").trim());
		}catch (Exception e) {
			return 0;
		}

	}

	public float getVlPrecoTotal(){
		try{
			return Float.valueOf(PRECOTOTAL.replace("R$", "").replace(",", ".").trim());
		}catch (Exception e) {
			return 0;
		}
	}

	public boolean getOfertaAtiva(){
		if(ATIVA.trim().equals("ativo")){
			return true;
		}else
			return false;
	}

	public float getVlPercDesconto(){
		try{
			return Float.valueOf(DESCONTO.replace("%", "").replace(",", ".").trim());
		}catch (Exception e) {
			return 0;
		}
	}

}
