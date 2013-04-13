package net.sue445.gaerminal.controller

import org.junit.Test

class ScriptControllerTest {
    static class execute {

    }

    static class runScript {
        ScriptController controller = new ScriptController()

        @Test
        void "run groovy script, return console output"(){
            String script = """
println("HelloWorld")
"""
            assert controller.runScript(script) == "HelloWorld\n"
        }

        @Test
        void "when exception, return stacktrace"(){
            String script = """
throw new RuntimeException("test")
"""
            assert controller.runScript(script).contains("RuntimeException")
        }
    }
}
