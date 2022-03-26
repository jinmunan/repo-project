package com.cj.fmsc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jinmunan
 * 2022/3/26
 * 16:40
 */
@Controller
@RequestMapping("page")
public class PageController {
    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }

    @RequestMapping("/test.html")
    public String indexThy() {
        return "test";
    }
}
