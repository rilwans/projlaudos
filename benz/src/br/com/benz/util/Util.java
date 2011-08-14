package br.com.benz.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

import br.com.benz.exception.InvalidConvertDateException;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * Classe de Utilizade
 *
 * @author viniciusa
 *
 */
public final class Util {

	/**
	 * @param txt
	 *            <code>String</code> que representa o texto a ser mascarado.
	 * @param mask
	 *            <code>String</code> que sera a máscara a ser inserida.
	 * @return Retorna uma <code>String</code> com a máscara inserida.
	 */
	public final static String marcara(String txt, String mask) {
		MaskFormatter mf;
		try {
			mf = new MaskFormatter(mask);
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(txt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Calcula carimbo MD5 sobre um string dado.
	 *
	 * @param toMd5
	 *            O string base.
	 * @return O carimbo MD5, na forma de um string.
	 */
	@SuppressWarnings("finally")
	public final static String md5(String toMd5) {
		MessageDigest md = null;
		String wdgs = null;

		try {
			md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(toMd5.getBytes("ISO-8859-1"));
			byte[] wdg = md.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < wdg.length; i++) {
				String w_dup = Integer.toHexString(0xFF & wdg[i]);
				// correção da geração indevida de zeros à esquerda -
				// contribuição de Elaine Yoshikune, da Unimed Paulistana,
				// e de Winsley, da Hotsoft
				// 30/11/2006 - liberada na versão 3.0
				if (w_dup.length() < 2)
					w_dup = "0" + w_dup;
				// correcao de "bug do zero": quando o byte continha um valor
				// menor que 0F,
				// o agente nao concatenava o zero a esquerda, encurtando a
				// representacao
				// do string - contribuicao de Neville, medicware, set/2006
				hexString.append(w_dup);
			}
			wdgs = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			return wdgs;
		}
	}

	/**
	 * @param txt
	 *            <code>String</code> que sera retirado os acentos.
	 * @return Retorna uma <code>String</code> sem acentuação.
	 */
	public final static String retiraAcentuacao(String txt) {
		txt = Normalizer.normalize(txt, Normalizer.Form.NFD);
		txt = txt.replaceAll("[^\\p{ASCII}]", "");

		return txt.trim();
	}

	/**
	 * @param value
	 *            <code>String</code> Valor a ser complementado a esquerda
	 * @param size
	 *            <code>int</code> Quantidade de caracteres
	 * @param complete
	 *            <code>String</code> caractere a ser complementado.
	 * @return Retorna uma <code>String</code> complementada a esquerda.
	 */
	public final static String leftComplete(String value, int size,
			String complete) {
		value = value.trim();
		if (value.length() < size) {
			while (value.length() < size)
				value = complete + value;

		}
		return value;
	}

	/**
	 * @param value
	 *            <code>String</code> Valor a ser complementado a direita
	 * @param size
	 *            <code>int</code> Quantidade de caracteres
	 * @param complete
	 *            <code>String</code> caractere a ser complementado.
	 * @return Retorna uma <code>String</code> complementada a direita.
	 */
	public static String rightComplete(String value, int size, String complete) {
		while (value.length() < size) {
			value = value + complete;
		}
		return value;
	}

	/**
	 * @param txt
	 *            <code>String</code> texto a ser limitado.
	 * @param size
	 *            <code>int</code> limite do texto
	 * @return retorna uma <code>String</code> limitada pelo tamanho recebido.
	 */
	public static String limiteTamanho(String txt, int size) {
		txt = txt.trim();
		return txt.substring(0, txt.length() > size ? size : txt.length());
	}

	/**
	 * @param dataIni
	 *            Data inicial.
	 * @param dataFim
	 *            Data Final.
	 * @return Retorna um <code>long</code> da diferença entre as datas;
	 */
	public static Long diferencaEntreDatas(Date dataIni, Date dataFim) {
		GregorianCalendar ini = new GregorianCalendar();
		ini.setTime(dataIni);

		GregorianCalendar fim = new GregorianCalendar();
		fim.setTime(dataFim);

		long dt1 = ini.getTimeInMillis();
		long dt2 = fim.getTimeInMillis();

		return (dt2 - dt1) / 86400000;
	}

	/**
	 * @param str
	 * @return Retora a string sem os espaços duplicados
	 */
	public static String removerEspacosDuplicados(String str) {
		String patternStr = "\\s+";
		String replaceStr = " ";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(str);
		return matcher.replaceAll(replaceStr);
	}

	/**
	 * @param str
	 * @return Retorna a string com a inicial maiúscula desde que cada palavra
	 *         tenha mais de 3 caracteres
	 */
	public static String inicialMaiuscula(String str) {
		String[] words = str.trim().split(" ");
		String out = "";
		for (String word : words)
			out += (word.trim().length() > 3 ? StringUtils.capitalize(word)
					.trim() : word.trim())
					+ " ";

		return out.trim();
	}

	public static Calendar converteDataInicioDia(String data)
			throws InvalidConvertDateException {

		try {
			data = data.substring(data.indexOf(",") + 1).trim();
			int ano = Integer.parseInt(data
					.substring(data.lastIndexOf("/") + 1).trim());
			int dia = Integer.parseInt(data.substring(0, data.indexOf("/"))
					.trim());
			String mes1 = data.substring(data.indexOf("/") + 1,
					data.lastIndexOf("/")).toLowerCase().trim();

			int mes;
			if (mes1.equals("jan"))
				mes = Calendar.JANUARY;
			else if (mes1.equals("fev"))
				mes = Calendar.FEBRUARY;
			else if (mes1.equals("mar"))
				mes = Calendar.MARCH;
			else if (mes1.equals("abr"))
				mes = Calendar.APRIL;
			else if (mes1.equals("mai"))
				mes = Calendar.MAY;
			else if (mes1.equals("jun"))
				mes = Calendar.JUNE;
			else if (mes1.equals("jul"))
				mes = Calendar.JULY;
			else if (mes1.equals("ago"))
				mes = Calendar.AUGUST;
			else if (mes1.equals("set"))
				mes = Calendar.SEPTEMBER;
			else if (mes1.equals("out"))
				mes = Calendar.OCTOBER;
			else if (mes1.equals("nov"))
				mes = Calendar.NOVEMBER;
			else
				mes = Calendar.DECEMBER;

			Calendar dataRetorno = Calendar.getInstance();
			dataRetorno.set(ano, mes, dia, 0, 0, 0);
			dataRetorno.set(Calendar.MILLISECOND, 0);

			return dataRetorno;
		} catch (Exception e) {
			new InvalidConvertDateException("Erro ao Converter data");
		}
		return null;
	}

	public static Calendar converteDataFimDia(String data)
			throws InvalidConvertDateException {

		try {
			data = data.substring(data.indexOf(",") + 1).trim();
			int ano = Integer.parseInt(data
					.substring(data.lastIndexOf("/") + 1).trim());
			int dia = Integer.parseInt(data.substring(0, data.indexOf("/"))
					.trim());
			String mes1 = data.substring(data.indexOf("/") + 1,
					data.lastIndexOf("/")).toLowerCase().trim();

			int mes;
			if (mes1.equals("jan"))
				mes = Calendar.JANUARY;
			else if (mes1.equals("fev"))
				mes = Calendar.FEBRUARY;
			else if (mes1.equals("mar"))
				mes = Calendar.MARCH;
			else if (mes1.equals("abr"))
				mes = Calendar.APRIL;
			else if (mes1.equals("mai"))
				mes = Calendar.MAY;
			else if (mes1.equals("jun"))
				mes = Calendar.JUNE;
			else if (mes1.equals("jul"))
				mes = Calendar.JULY;
			else if (mes1.equals("ago"))
				mes = Calendar.AUGUST;
			else if (mes1.equals("set"))
				mes = Calendar.SEPTEMBER;
			else if (mes1.equals("out"))
				mes = Calendar.OCTOBER;
			else if (mes1.equals("nov"))
				mes = Calendar.NOVEMBER;
			else
				mes = Calendar.DECEMBER;

			Calendar dataRetorno = Calendar.getInstance();
			dataRetorno.set(ano, mes, dia, 23, 59, 59);
			dataRetorno.set(Calendar.MILLISECOND, 0);

			return dataRetorno;
		} catch (Exception e) {
			new InvalidConvertDateException("Erro ao Converter data");
		}
		return null;
	}

}
