package hello.hellospring.service;

import com.fasterxml.jackson.databind.util.RootNameLookup;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    // 서비스를 만들려면 회원 레포지토리가 있어야 함
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository; // 그대로 두고

    // 외부에서 넣어주게? 바꿔준다.
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * 회원 가입을 하면 id를 반환해주겠다.
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원X
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // / ifPresent(value) : null 이 아니라 어떤 value 가 있으면 로직에 동작을 함
        // findbyName 의 return 값은 optionalMember 니까
        // 바로 ifPresent 작성
        // ctrl + t (리팩토링과 관련된 여러가지 메소드) -> 9. Extract Method...(cmd+option+m)
        memberRepository.findbyName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findbyId(memberId);
    }
}
