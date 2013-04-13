package net.sue445.gaerminal.filter

import org.junit.Ignore
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.slim3.tester.ControllerTestCase

import javax.servlet.FilterConfig

class GaerminalControllerTest {

    static class init extends ControllerTestCase {
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

    static class doFilter extends ControllerTestCase  {
        GaerminalController filter = new GaerminalController()

        @Test
        void "when exists file, return page file"(){
            // setup
            tester.request.setServletPath(GaerminalController.DEFAULT_PATH_PREFIX + "helloworld")

            // exercise
            filter.doFilter(tester.request, tester.response, tester.filterChain)

            // assertion
            assert tester.response.getOutputAsString().contains("Hello World") == true
            assert tester.response.getStatus() == 200
        }

        @Test
        void "when not exists file, return status 404"(){
            // setup
            tester.request.setServletPath(GaerminalController.DEFAULT_PATH_PREFIX + "invalid")

            // exercise
            filter.doFilter(tester.request, tester.response, tester.filterChain)

            // assertion
            assert tester.response.getOutputAsString() == "Not Found"
            assert tester.response.getStatus() == 404
        }

        @Test
        void "when called /gaerminal/, return index.html"(){
            // setup
            tester.request.setServletPath(GaerminalController.DEFAULT_PATH_PREFIX)

            // exercise
            filter.doFilter(tester.request, tester.response, tester.filterChain)

            // assertion
            assert tester.response.getOutputAsString().contains("pages/index.html") == true
            assert tester.response.getStatus() == 200
        }
    }
}
