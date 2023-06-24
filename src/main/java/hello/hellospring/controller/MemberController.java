package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService; // final -> 무언가를 제한한다.

    //cmd+n
    // 위에 선언한 memberService 생성
    // @Autowired : spring 안에 있는 memberService 를 연결해줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 조회할 때 주로 사용 get 방식
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // 데이터를 form 형태로 전달할 post 방식
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 회원가입이 끝난후 처음 화면으로 보내버리기

    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
    // 서버 끄고 다시 실행하면 다 사라져 있을거 -> 메모리를 사용하고 있기 때문에;;

}
