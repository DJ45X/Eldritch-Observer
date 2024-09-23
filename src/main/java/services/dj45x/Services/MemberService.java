package services.dj45x.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.dj45x.Models.Member;
import services.dj45x.Repositories.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void updateMemberActivity(String discordId, String memberName) {
        Member member = memberRepository.findByDiscordID(discordId)
                .orElse(new Member());

        member.setDiscordID(discordId);
        member.setMemberName(memberName);
        member.setLastActive(LocalDateTime.now());
        member.setInactive(false); // Member is active

        memberRepository.save(member);
    }

    public List<Member> findInactiveMembers() {
        return memberRepository.findInactiveMembers();
    }

    @Transactional
    public void markInactiveMembers() {
        LocalDateTime threshold = LocalDateTime.now().minusDays(30);
        memberRepository.markInactiveMembers(threshold);
    }
}
