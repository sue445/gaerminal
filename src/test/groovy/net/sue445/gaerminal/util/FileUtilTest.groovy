package net.sue445.gaerminal.util

import org.junit.Test

import static org.hamcrest.Matchers.*
import static org.junit.Assert.*

class FileUtilTest {
    static class readPage{
        static class whenExistsFile{
            @Test
            void "should return file content"(){
                String actual = FileUtil.readPage("helloworld")
                assertThat actual, containsString("helloworld")
            }
        }

        static class whenNotExistsFile{
            @Test
            void "should return null"(){
                String actual = FileUtil.readPage("invalid_file")
                assert actual == null
            }
        }
    }
}
