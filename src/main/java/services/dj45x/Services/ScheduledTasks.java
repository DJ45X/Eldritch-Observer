package services.dj45x.Services;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import services.dj45x.Models.MemberModel;
import services.dj45x.Utils.DevMode;
import services.dj45x.Utils.JDAUtils;
import services.dj45x.Utils.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScheduledTasks {

    @Autowired
    private MemberService memberService;

    @Autowired
    private WarningMessageService warningMessageService;

    @Autowired
    private ShardManager shardManager;

    /*@Scheduled(cron = "0 0 0 * * ?") // runs daily at midnight*/
    @Scheduled(cron = "0 * * * * ?") // runs every minute
    public void checkInactiveMembers() {
        memberService.markInactiveMembers();

        List<MemberModel> inactiveMembers = memberService.findInactiveMembers();

        Optional<String> warningMessage = warningMessageService.getWarningMessage();
        if (warningMessage.isPresent()) {
            Guild guild = shardManager.getGuildById(DevMode.getGuildID());
            TextChannel statusUpdatesChannel = JDAUtils.getTextChannelByName(Objects.requireNonNull(guild), "inactive-status-updates");

            if (statusUpdatesChannel != null && !inactiveMembers.isEmpty()) {

                try {
                    statusUpdatesChannel.sendMessage(String.format("%s\n\n%s", warningMessage.get(), inactiveMembers)).queue();
                } catch (Exception e) {
                    JDAUtils.sendErrorToLogsChannel(guild, "Failed to send automated message.\n\n" + e.getMessage());
                    Logger.error("Failed to send automated message: " + e.getMessage());
                }
            }
        }
    }
}
