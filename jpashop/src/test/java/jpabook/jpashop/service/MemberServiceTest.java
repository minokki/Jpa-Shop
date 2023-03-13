package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)  //junit 실행할때 스프링이랑 같이 실행 하기위함
@SpringBootTest //스프링부트로 테스트 하기위함
@Transactional //테스트가 끝나면 롤백 됨.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("ko");

        //when
        Long savedId = memberService.join(member);

        //then
        em.flush(); //트랜잭셔널이 있어 롤백되어 인설트쿼리가 안보일때 flush 사용하여 볼수있음
        assertEquals(member,memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예약() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("ko");

        Member member2 = new Member();
        member2.setName("ko");

        //when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야함 , 이름이 같으므로

        //then
        fail("예외가 발생한다.");
    }

}