package net.sue445.gaerminal.filter

import net.sue445.gaerminal.controller.RunController
import net.sue445.gaerminal.util.FileUtil

import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

public class GaerminalController implements Filter {
    private static final String DEFAULT_PATH_PREFIX = "/gaerminal/"

    protected String pathPrefix = DEFAULT_PATH_PREFIX

    @Override
    public void init(FilterConfig config)  {
        String pathPrefix = config.getInitParameter("pathPrefix")

        if(!isEmpty(pathPrefix)){
            if(!pathPrefix.startsWith("/")){
                pathPrefix = "/" + pathPrefix
            }
            if(!pathPrefix.endsWith("/")){
                pathPrefix += "/"
            }
            this.pathPrefix = pathPrefix
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain)
    }

    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        if(!request.getServletPath().startsWith(pathPrefix)){
            chain.doFilter(request, response)
            return
        }

        String pageName = request.getServletPath().replaceFirst(pathPrefix, "")
        if(pageName.isEmpty()){
            pageName = "index"
        }

        if(pageName.startsWith("script")){
            RunController controller = new RunController()
            String content = controller.execute(request)
            response.setStatus(200)
            response.setContentType("text/plain; charset=UTF-8")
            response.setContentLength(content.length())
            response.getOutputStream().print(content)
            return
        }

        String content = FileUtil.readPage(pageName)
        if(content == null) {
            content = "Not Found"
            response.setStatus(404)
            response.setContentType("text/plain; charset=UTF-8")

        } else {
            response.setStatus(200)
            response.setContentType("text/html; charset=UTF-8")
        }

        response.setContentLength(content.length())
        response.getOutputStream().print(content)
    }

    @Override
    public void destroy() {
    }

    boolean isEmpty(String str){
        return str == null || str.isEmpty()
    }
}
