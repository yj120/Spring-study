package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import java.util.Optional;
import java.util.List;
public interface MemberRepository {
    Member save(Member member); // 회원이 저장소에 저장
    Optional<Member> findbyId(Long id); // 저장소에 저장된거 찾아오기
    Optional<Member> findbyName(String name); // 저장소에 저장된거 찾아오기
    List<Member> findAll(); // 지금까지 저장된 모든 고객리스트 가져오기
}



