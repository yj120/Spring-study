package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*; // static import 로 들어가고
import static org.junit.jupiter.api.Assertions.assertThrows;
class MemberServiceTest {

    // 테스트 함수명은 한글로 적어도 된다! -> 직관적이라 좋음

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    // <Dependency Injection> DI
    // 이렇게 하면 테스트 실행할 때마다 각각 생성을 해준다.
    // @Test 들을 실행하기전에 MemoryMemberRepository를 만들고, memberRepository에 넣어놓고
    // MemberService(memberRepository)를 넣어줌
    // => 그러면 같은 MemoryMemberRepository가 사용된다
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach // 메서드가 끝날 때 마다 어떤 동작을 하는 콜백메서드
    public void afterEach(){
        memberRepository.clearStore();
        // 테스트가 끝날때마다 저장소를 다 지움(DB값 초기화)

    }
    @Test
    void 회원가입() {
        // given
        Member member= new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // option + enter ,static import 로
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");


        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// cmd+option+v

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*try{
            memberService.join(member2);// 예외 터지게 하기 위해
            fail("예외가 발생해야 합니다");
        }catch (IllegalStateException e){ // exception 발생
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}