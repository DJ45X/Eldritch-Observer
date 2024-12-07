package services.dj45x.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.dj45x.Models.MemberModel;
import services.dj45x.Repositories.MemberRepository;
import services.dj45x.Utils.Logger;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void updateMemberActivity(String discordId) {
        MemberModel memberModel = memberRepository.findByDiscordID(discordId)
                .orElse(null);

        if (memberModel != null) {
            try {
                memberModel.setLastActive(LocalDateTime.now());
                memberModel.setInactive(false); // Member is active

                memberRepository.save(memberModel);
            } catch (Exception e) {
                Logger.error("Failed to update member activity: " + e.getMessage());
            }
        }
    }

    public List<MemberModel> findInactiveMembers() {
        return memberRepository.findInactiveMembers();
    }

    @Transactional
    public void markInactiveMembers() {
        LocalDateTime threshold = LocalDateTime.now().minusDays(30);
        memberRepository.markInactiveMembers(threshold);
    }
}
