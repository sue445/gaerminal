package net.sue445.gaerminal.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class GaerminalController implements Filter {
    static DEFAULT_PATH_PREFIX = "/gaerminal"

    protected String pathPrefix = DEFAULT_PATH_PREFIX

    @Override
    void init(FilterConfig filterConfig)  {
        String pathPrefix = filterConfig.getInitParameter("pathPrefix")

        if(!isEmpty(pathPrefix)){
            this.pathPrefix = pathPrefix
        }
    }

    @Override
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void destroy() {
    }

    boolean isEmpty(String str){
        return str == null || str.isEmpty()
    }
}
