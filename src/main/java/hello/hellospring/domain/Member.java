package hello.hellospring.domain;

public class Member {

    private Long id; // 데이터를 구분하기 위해서 시스템이 사용하는 아이디
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name){ this.name=name;}
    public String getName(){
        return name;
    }
}
