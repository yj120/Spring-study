package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

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
        return new ArrayList<>(store.values());
    }


    public void clearStore(){
        store.clear(); // stroe 싹 비움
    }
}
