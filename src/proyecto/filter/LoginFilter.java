package proyecto.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import proyecto.bean.LoginBean;

/**
 * Servlet Filter implementation class SesionFilter
 */
@WebFilter("/SesionFilter")
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		LoginBean loginBean = (LoginBean) req.getSession().getAttribute("loginBean");

		String urlStr = req.getRequestURL().toString().toLowerCase();

		if (noProteger(urlStr)) {
			chain.doFilter(request, response);
			return;
		}

		if (loginBean == null || !loginBean.isLogeado()) {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
			return;
		}

		chain.doFilter(request, response);
	}

	private boolean noProteger(String urlStr) {
		if (urlStr.contains("resources"))
			return true;
		if (urlStr.endsWith("login.xhtml"))
			return true;
		if (urlStr.indexOf("/javax.faces.resource/") != -1)
			return true;
		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
