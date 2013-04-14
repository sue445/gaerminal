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
            String expected = """HelloWorld

Result: null
"""

            assert controller.runScript(script) == expected
        }

        @Test
        void "when exception, return stacktrace"(){
            String script = """
throw new RuntimeException("test")
"""
            assert controller.runScript(script).contains("RuntimeException")
        }

        @Test
        void "when has result, return result"(){
            String script = """
"a" * 10
"""
            String expected = """Result: 'aaaaaaaaaa'
"""

            assert controller.runScript(script) == expected
        }
    }
}
