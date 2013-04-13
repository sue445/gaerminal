package net.sue445.gaerminal.filter;

import javax.servlet.*;

public class GaerminalController implements Filter {
    private static final String DEFAULT_PATH_PREFIX = "/gaerminal";

    protected String pathPrefix = DEFAULT_PATH_PREFIX;

    @Override
    public void init(FilterConfig filterConfig)  {
        String pathPrefix = filterConfig.getInitParameter("pathPrefix");

        if(!isEmpty(pathPrefix)){
            this.pathPrefix = pathPrefix;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void destroy() {
    }

    boolean isEmpty(String str){
        return str == null || str.isEmpty();
    }
}
