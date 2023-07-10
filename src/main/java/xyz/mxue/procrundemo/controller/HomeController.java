package xyz.mxue.procrundemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 进入首页
 * @author: mxuexxmy
 * @date: 2023/5/7 16:18
 * @version: 1.0
 */
@RestController
@RequestMapping
public class HomeController {

    @GetMapping(value = {"/", "/index"})
    public String index () { return "procrun demo"; }

}
