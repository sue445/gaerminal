package net.sue445.gaerminal.filter

import org.junit.Ignore
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.slim3.tester.MockFilterChain
import org.slim3.tester.MockHttpServletRequest
import org.slim3.tester.MockHttpServletResponse
import org.slim3.tester.MockServletContext

import javax.servlet.FilterConfig

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
            assert filter.pathPrefix == "/" + pathPrefix + "/"
        }
    }

    static class doFilter {
        GaerminalController filter = new GaerminalController()

        MockHttpServletRequest request = new MockHttpServletRequest(new MockServletContext())
        MockHttpServletResponse response = new MockHttpServletResponse()
        MockFilterChain filterChain = new MockFilterChain()

        @Test
        void "when exists file, return page file"(){
            // setup
            request.setServletPath(GaerminalController.DEFAULT_PATH_PREFIX + "helloworld")

            // exercise
            filter.doFilter(request, response, filterChain)

            // assertion
            assert response.getOutputAsString().contains("Hello World") == true
            assert response.getStatus() == 200
        }

        @Test
        void "when not exists file, return status 404"(){
            // setup
            request.setServletPath(GaerminalController.DEFAULT_PATH_PREFIX + "invalid")

            // exercise
            filter.doFilter(request, response, filterChain)

            // assertion
            assert response.getOutputAsString() == "Not Found"
            assert response.getStatus() == 404
        }

        @Test
        void "when called /gaerminal/, return index.html"(){
            // setup
            request.setServletPath(GaerminalController.DEFAULT_PATH_PREFIX)

            // exercise
            filter.doFilter(request, response, filterChain)

            // assertion
            assert response.getOutputAsString().contains("pages/index.html") == true
            assert response.getStatus() == 200
        }
    }
}
