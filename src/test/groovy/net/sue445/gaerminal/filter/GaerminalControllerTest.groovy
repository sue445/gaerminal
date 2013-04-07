package net.sue445.gaerminal.filter

import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

import javax.servlet.FilterConfig

@RunWith(Enclosed)
class GaerminalControllerTest {

    static class init {
        GaerminalController filter = new GaerminalController()

        @Test
        void notExistsParam(){
            // setup
            def config = [ getInitParameter : { Object[] args -> null } ] as FilterConfig

            // exercise
            filter.init(config)

            // assertion
            assert filter.pathPrefix == GaerminalController.DEFAULT_PATH_PREFIX
        }

        @Test
        void pathPrefix(){
            // setup
            String pathPrefix = "aaaaaa"
            def config = [ getInitParameter : { Object[] args -> args[0] == "pathPrefix" ? pathPrefix : null } ] as FilterConfig

            // exercise
            filter.init(config)

            // assertion
            assert filter.pathPrefix == pathPrefix
        }
    }
}