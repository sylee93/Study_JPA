package Study_JPA.Study_JPA.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private  String name;

    @Embedded // 내장 개체 선언
    private Address address;

    @OneToMany(mappedBy = "member") // 연관관계 명시  order테이블에 있는 member필드에 의해서 매핑됨을 의미, 읽기전용 member 테이블에서 변경해도 안바뀜
    private List<Order> orders = new ArrayList<>();
}
