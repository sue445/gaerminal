package net.sue445.gaerminal.controller

import javax.servlet.http.HttpServletRequest

class RunController {
    String execute(HttpServletRequest request){
        String script = request.getParameter("script")
        runScript(script)
    }

    String runScript(String script){
        // TODO 例外のハンドリング
        PrintStream origOut = System.out
        try{
            ByteArrayOutputStream stream = new ByteArrayOutputStream()
            PrintStream scriptOut = new PrintStream(stream)
            System.setOut(scriptOut)

            def shell = new GroovyShell()
            shell.evaluate(script)
            return stream.toString("UTF-8")

        } finally {
            System.setOut(origOut)
        }
    }
}
