package net.sue445.gaerminal.controller

import javax.servlet.http.HttpServletRequest

class ScriptController implements Controller{
    private static final String ENCODE = "UTF-8"

    @Override
    String execute(Object request){
        execute((HttpServletRequest)request)
    }

    String execute(HttpServletRequest request){
        String script = request.getParameter("script")
        runScript(script)
    }

    String runScript(String script){
        PrintStream origOut = System.out
        try{
            ByteArrayOutputStream stream = new ByteArrayOutputStream()
            System.setOut(new PrintStream(stream))

            def shell = new GroovyShell()
            shell.evaluate(script)
            stream.toString(ENCODE)

        } catch (Throwable e){
            ByteArrayOutputStream stream = new ByteArrayOutputStream()
            e.printStackTrace(new PrintStream(stream))
            stream.toString(ENCODE)

        } finally {
            System.setOut(origOut)
        }
    }
}
