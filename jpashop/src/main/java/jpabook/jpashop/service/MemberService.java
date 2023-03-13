package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //자동으로 스프링빈 등록
@Transactional(readOnly = true) // 읽기에는 readOnly를 추가 해주면 데이터아낄수잇음
@RequiredArgsConstructor //final 이 있는 필드만 생성자를 만들어줌.
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;



    //회원 가입
    @Transactional//데이터 변경은 트랜잭셔널이 필요함 readonly 디폴트값이 false
    public Long join(Member member){
        validateDuplicateMember(member);  //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {  //중복회원 검증
        //exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


}
