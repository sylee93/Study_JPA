package Study_JPA.Study_JPA.service;

import Study_JPA.Study_JPA.domain.Member;
import Study_JPA.Study_JPA.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기전용이라 리소스를 많이 잡아먹지않음, 데이터 변경이 안됨
@RequiredArgsConstructor // final이 있는 필드만 가지고 생성자를 만들어 주고 만들어진 생성자에 해당필드를 파라미터로 하는 생성자 인젝션
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     *회원가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
