package Study_JPA.Study_JPA.repository;

import Study_JPA.Study_JPA.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository{

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        //find(Type,PK)
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        // jsql : 테이블이 아닌 entity 개체를 대상으로 쿼리를 실행함
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m,name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
