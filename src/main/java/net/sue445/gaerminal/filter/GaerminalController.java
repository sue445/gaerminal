package net.sue445.gaerminal.filter;

import net.sue445.gaerminal.controller.Controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GaerminalController implements Filter {
    protected static final String DEFAULT_PATH_PREFIX = "/gaerminal/";

    protected String pathPrefix = DEFAULT_PATH_PREFIX;

    @Override
    public void init(FilterConfig config)  {
        String pathPrefix = config.getInitParameter("pathPrefix");

        if(!isEmpty(pathPrefix)){
            if(!pathPrefix.startsWith("/")){
                pathPrefix = "/" + pathPrefix;
            }
            if(!pathPrefix.endsWith("/")){
                pathPrefix += "/";
            }
            this.pathPrefix = pathPrefix;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain);
    }

    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(!request.getServletPath().startsWith(pathPrefix)){
            chain.doFilter(request, response);
            return;
        }

        String pageName = request.getServletPath().replaceFirst(pathPrefix, "");
        if(pageName.isEmpty()){
            pageName = "index";
        }

        if(pageName.equals("script")){
            Controller controller = newInstance("net.sue445.gaerminal.controller.ScriptController");
            String content = controller.execute(request);
            response.setStatus(200);
            response.setContentType("text/plain; charset=UTF-8");
            printContent(response, content);
            return;
        }

        // read src/main/resources/pages/*.html
        Controller controller = newInstance("net.sue445.gaerminal.controller.PageController");
        String content = controller.execute(pageName);
        if(content == null) {
            response.setStatus(404);
            response.setContentType("text/plain; charset=UTF-8");
            printContent(response, "Not Found");

        } else {
            response.setStatus(200);
            response.setContentType("text/html; charset=UTF-8");
            printContent(response, content);
        }
    }

    @Override
    public void destroy() {
    }

    protected boolean isEmpty(String str){
        return str == null || str.isEmpty();
    }

    protected <T> T newInstance(String className){
        try {
            return (T)Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void printContent(HttpServletResponse response, String content) throws IOException {
        response.setContentLength(content.length());
        response.getOutputStream().print(content);
    }
}
