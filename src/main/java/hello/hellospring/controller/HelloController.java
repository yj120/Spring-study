package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody // http body 에 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //name 을 어떻게 넣어주냐에 따라 값이 다르게 출력
        // 이전의 템플릿이랑 다른점은 view가 없음 그냥 문자그대로 넘어감->html tag 없이!
    }


    // 찐 API ----> json 형태로 출력
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private String name;

        public String getName(){
            return name;
        };

        public void setName(String name){
            this.name= name;

        }
    }
}
