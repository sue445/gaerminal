package net.sue445.gaerminal.filter

import net.sue445.gaerminal.controller.ScriptController
import net.sue445.gaerminal.util.FileUtil

import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class GaerminalController implements Filter {
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

        if(pageName == "script"){
            ScriptController controller = new ScriptController()
            String content = controller.execute(request)
            response.setStatus(200)
            response.setContentType("text/plain; charset=UTF-8")
            printContent(response, content)
            return
        }

        String content = FileUtil.readPage(pageName)
        if(content == null) {
            response.setStatus(404)
            response.setContentType("text/plain; charset=UTF-8")
            printContent(response, "Not Found")

        } else {
            response.setStatus(200)
            response.setContentType("text/html; charset=UTF-8")
            printContent(response, content)
        }
    }

    @Override
    public void destroy() {
    }

    boolean isEmpty(String str){
        return str == null || str.isEmpty()
    }

    private void printContent(HttpServletResponse response, String content){
        response.setContentLength(content.length())
        response.getOutputStream().print(content)
    }
}
