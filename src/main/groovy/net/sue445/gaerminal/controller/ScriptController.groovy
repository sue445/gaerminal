package net.sue445.gaerminal.controller

import javax.servlet.http.HttpServletRequest

class ScriptController implements Controller{
    private static final String ENCODE = "UTF-8"
    private static final String LINE_SEPARATOR = System.getProperty("line.separator")

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

            GroovyShell shell = new GroovyShell()
            Object scriptResult = shell.evaluate(script)
            String consoleResult = stream.toString(ENCODE)

            String result = ""
            if(!consoleResult.isEmpty()){
                result += consoleResult + LINE_SEPARATOR
            }
            result += "Result: ${scriptResult.inspect()}" + LINE_SEPARATOR
            result

        } catch (Throwable e){
            ByteArrayOutputStream stream = new ByteArrayOutputStream()
            e.printStackTrace(new PrintStream(stream))
            stream.toString(ENCODE)

        } finally {
            System.setOut(origOut)
        }
    }
}
