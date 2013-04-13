package net.sue445.gaerminal.controller

import net.sue445.gaerminal.util.FileUtil

class PageController implements Controller{
    @Override
    String execute(Object request) {
        FileUtil.readPage(request.toString());
    }
}
