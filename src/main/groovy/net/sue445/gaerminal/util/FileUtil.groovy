package net.sue445.gaerminal.util


class FileUtil {
    static String readPage(String pageName, String charset="UTF-8"){
        URL url = FileUtil.class.getResource("/pages/${pageName}.html")
        url == null ? null : url.getText(charset)
    }
}
