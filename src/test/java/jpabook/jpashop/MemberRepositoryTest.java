package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
//    @Rollback(false)
    public void testMember(){
        Member member = new Member();
        member.setUsername("member");

        Long savedId = memberRepository.save(member);

        Member findMember = memberRepository.find(savedId);

        assertThat(member.getId()).isEqualTo(findMember.getId());
        assertThat(findMember).isEqualTo(member);

    }

    
}
