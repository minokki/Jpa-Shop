package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //스프링 빈 등록
@RequiredArgsConstructor
public class MemberRepository{

 //   @PersistenceContext // 스프링이 엔티티매니저를 만들어셔 주입해줌,인젝션

    @Autowired
    private final EntityManager em;


    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id); //맴버 클래스는 타입, id는 pk
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){   //특정 회원이름 맞 찾는 메서드
        return em.createQuery("select m from Member m where m.name = :name" , Member.class)
                .setParameter("name", name)
                .getResultList();

    }
}
