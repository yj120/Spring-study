package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 실무에서는 "동시성 문제"가 있을 수 있어서 공유되는 변수일때는 concurrent hashmap을 사용해줘야함 //
    private static Map<Long,Member> store = new HashMap<>(); // option + enter
    private static long sequence = 0L; // sequence : key 값 생성



    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 세팅
        store.put(member.getId(),member); // store 에 저장
        return member;
    }

    @Override
    public Optional<Member> findbyId(Long id) {
        // Null 이 반환될 가능성이 있으면 Optional.ofNullable()로 감싸서 반환
        // 클라이언트에서 작업을 할 때 도움
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findbyName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // member.getName() 과 파라미터로 들어온 name 같은 지 확인
        // 같은 경우에만 필터링 -> 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        // store 에 있는 멤버들 반환
        return new ArrayList<>(store.values());
    }


    public void clearStore(){

        store.clear(); // store 싹 비움
    }
}
