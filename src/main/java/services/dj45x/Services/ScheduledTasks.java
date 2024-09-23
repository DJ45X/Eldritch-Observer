package services.dj45x.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks {

    @Autowired
    private MemberService memberService;

    @Scheduled(cron = "0 0 0 * * ?") // runs daily at midnight
    public void checkInactiveMembers() {
        memberService.markInactiveMembers();
    }
}
