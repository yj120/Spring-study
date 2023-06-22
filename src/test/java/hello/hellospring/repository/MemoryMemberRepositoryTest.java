package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    // 테스트 시 순서 보장이 안된다.
    // 메서드별 따로 동작하도록 설계해야 한다.
    // 🚨테스트는 순서에 의존적으로 설계하면 안된다🚨
    // 하나의 테스트가 끝날 때 마다 저장소나 공용 데이터를 지워줘야함!
    //
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메서드가 끝날 때 마다 어떤 동작을 하는 콜백메서드
    public void afterEach(){
        repository.clearStore();
        // 테스트가 끝날때마다 저장소를 다 지움

    }

    @Test // 실행 테스트
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findbyId(member.getId()).get();
        assertThat(member).isEqualTo(result); // option+enter -> static import
        //Assertions.assertEquals(member,result);
        //System.out.println("result = "+(result==member));

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        Member result = repository.findbyName("spring1").get();


        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }


}
