package net.sue445.gaerminal.controller

import static org.hamcrest.Matchers.*
import static org.junit.Assert.*
import org.junit.Test

class RunControllerTest {
    static class execute {

    }

    static class runScript {
        RunController controller = new RunController()

        @Test
        void "run groovy script, return console output"(){
            String script = """
println("HelloWorld")
"""
            assert controller.runScript(script) == "HelloWorld\n"
        }

    }
}
