package com.imooc.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend")
public class FrontendController {
    @GetMapping("/index")
    public String index() {
        return "frontend/index";
    }

    @GetMapping("shoplist")
    public String shopList() {
        return "frontend/shoplist";
    }

    @GetMapping("shopdetail")
    public String productDetail() {
        return "frontend/shopdetail";
    }
}
