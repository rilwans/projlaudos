package br.com.benz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.rtf.RTFEditorKit;

import org.apache.log4j.Logger;

/**
 * Classe da conversão em rtf.
 *
 * @author vinicius
 *
 */
public class ConvertTexto {

	private static Logger logger = Logger.getLogger(ConvertTexto.class);

	/**
	 * Converte um arquivo RTF em HTML
	 *
	 * @param pathFileSource
	 *            File em RTF
	 * @param pathFileTarget
	 *            File em HTML
	 */
	public static void convertFileRTF2HTML(String pathFileSource,
			String pathFileTarget) {
		if (pathFileSource == null || pathFileTarget == null) {
			return;
		}
		try {
			File file = new File(pathFileSource);
			FileInputStream fi = new FileInputStream(file);
			FileOutputStream fo = new FileOutputStream(pathFileTarget);
			RTFEditorKit rtfEditorKit = new RTFEditorKit();
			HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
			Document doc = rtfEditorKit.createDefaultDocument();
			rtfEditorKit.read(fi, doc, 0);
			htmlEditorKit.write(fo, doc, 0, doc.getLength());

			fi.close();
		} catch (Exception ex) {
			logger.error("Erro na conversão de RTF2HTML.", ex);
		}
	}

	/**
	 * Transforma RTF em String
	 *
	 * @param text
	 *            File rtf a ser convertido
	 * @return Retorna uma <code>string</code> em html do rtf recebido.
	 */
	public static String convertTextRTF2HTML(String text) {
		if (text == null) {
			return "";
		}
		StringReader reader = new StringReader(text);
		StringWriter writer = new StringWriter();
		RTFEditorKit rtfEditorKit = new RTFEditorKit();
		HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
		Document doc = rtfEditorKit.createDefaultDocument();
		try {
			rtfEditorKit.read(reader, doc, 0);
			htmlEditorKit.write(writer, doc, 0, doc.getLength());
		} catch (IOException ex) {
			logger.error("Erro na conversão de RTF2HTML.", ex);
		} catch (BadLocationException ex) {
			logger.error("Erro na conversão de RTF2HTML.", ex);
		}
		return writer.toString();
	}

	/**
	 * Transforma RTF em txt
	 *
	 * @param text
	 *            File rtf a ser convertido
	 * @return Retorna uma <code>string</code> em txt do rtf recebido.
	 */
	public static String convertTextRTF2TXT(String text) {
		if (text == null) {
			return "";
		}
		StringReader reader = new StringReader(text);
		RTFEditorKit rtfEditorKit = new RTFEditorKit();
		Document doc = rtfEditorKit.createDefaultDocument();
		try {
			rtfEditorKit.read(reader, doc, 0);
			return doc.getText(0, doc.getLength());
		} catch (IOException ex) {
			logger.error("Erro na conversão de RTF2TXT.", ex);
		} catch (BadLocationException ex) {
			logger.error("Erro na conversão de RTF2TXT.", ex);
		}
		return null;
	}

	public static String convertTextHTML2TXT(String text) {
		if (text == null) {
			return "";
		}
		StringReader reader = new StringReader(text);
		HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
		Document doc = htmlEditorKit.createDefaultDocument();
		try {
			htmlEditorKit.read(reader, doc, 0);
			return doc.getText(0, doc.getLength());
		} catch (IOException ex) {
			logger.error("Erro na conversão de HTML2TXT.", ex);
		} catch (BadLocationException ex) {
			logger.error("Erro na conversão de HTML2TXT.", ex);
		}
		return null;
	}
}