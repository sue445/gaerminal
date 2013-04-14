package net.sue445.gaerminal.controller

import net.sue445.gaerminal.util.FileUtil

class PageController implements Controller{
    @Override
    String execute(Object request) {
        String pageName =request.toString()
        FileUtil.readPage(pageName);
    }
}
