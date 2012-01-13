package br.com.veridistec.izbio.sample.service;

import java.io.Serializable;

import javax.persistence.Query;

public abstract class FilterCRUD implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param objIns
	 * @param whereClause
	 * @return
	 */
	public abstract String addWhereClause(String objIns, String whereClause);

	public abstract void addParameters(Query q);

	public abstract boolean isFieldsNull();

	public static String addANDClause(String whereClause, String newClause) {
		if (whereClause == null || whereClause.trim().length() == 0) {
			return newClause;
		}
		return whereClause + " AND " + newClause;

	}

	public static String addORClause(String whereClause, String newClause) {
		if (whereClause == null || whereClause.trim().length() == 0) {
			return newClause;
		}
		return whereClause + " OR " + newClause;
	}

}