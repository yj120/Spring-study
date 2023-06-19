package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!!!!!!");
        return "hello"; // resources -> templates -> hello.html 으로 가서 랜더링해랏!!
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name",required = true)String name,Model model){
        model.addAttribute("name",name);
        return "hello-templates"; // hello-templates 에서 랜더링
    }
}
