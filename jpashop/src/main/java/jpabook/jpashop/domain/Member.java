package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded // 내장타입을 포함되어 있다라는 어노테이션
    private Address address;

    @OneToMany(mappedBy = "member")  //member로인해 맵핑이 되어 있구나, member가 연관관계 주인이 됨
    private List<Order> orders= new ArrayList<>();   //컬렉션은 생성 할때 초기화 시켜주는게 좋다. 변경 하지않기.


}
