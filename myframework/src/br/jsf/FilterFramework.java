package br.jsf;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import br.hibernate.utils.HibernateUtils;

public abstract class FilterFramework implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		try {
			if (!HibernateUtils.transacaoAtiva())
				HibernateUtils.beginTransaction();
			chain.doFilter(request, response);
			HibernateUtils.commitTransaction();
		} catch (FileNotFoundException e) {
			HibernateUtils.rollbackTransaction();
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendError(404, e.getMessage());
		} catch (Exception e) {
			HibernateUtils.rollbackTransaction();
		} finally {
			HibernateUtils.closeSession();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		init();
		HibernateUtils.getSessionFactory();
	}

	public abstract void init();

}
