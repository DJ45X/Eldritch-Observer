package services.dj45x.Listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.poll.MessagePollVoteAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.dj45x.Services.MemberService;

@Component
public class EventListeners extends ListenerAdapter {

    @Autowired
    private MemberService memberService;


    @Override
    public void onUserActivityStart(@NotNull UserActivityStartEvent event) {
        super.onUserActivityStart(event);

        String discordID = event.getMember().getId();
        String memberName = event.getMember().getEffectiveName();

        memberService.updateMemberActivity(discordID, memberName);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if (!event.getAuthor().isBot()) {
            String discordID = event.getAuthor().getId();
            String memberName = event.getAuthor().getEffectiveName();

            memberService.updateMemberActivity(discordID, memberName);
        }
    }

    @Override
    public void onMessageUpdate(@NotNull MessageUpdateEvent event) {
        super.onMessageUpdate(event);

        String discordID = event.getAuthor().getId();
        String memberName = event.getAuthor().getEffectiveName();

        memberService.updateMemberActivity(discordID, memberName);
    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        super.onMessageReactionAdd(event);

        String discordID = event.getMember().getId();
        String memberName = event.getMember().getEffectiveName();

        memberService.updateMemberActivity(discordID, memberName);
    }

    @Override
    public void onMessagePollVoteAdd(@NotNull MessagePollVoteAddEvent event) {
        super.onMessagePollVoteAdd(event);

        String discordID = event.getUserId();
        String pollMemberID = event.getUserId();
        Member member = event.getGuild().getMemberById(pollMemberID);
        String memberName = member.getEffectiveName();

        memberService.updateMemberActivity(discordID, memberName);
    }

    @Override
    public void onGuildVoiceUpdate(@NotNull GuildVoiceUpdateEvent event) {
        super.onGuildVoiceUpdate(event);

        String discordID = event.getMember().getId();
        String memberName = event.getMember().getEffectiveName();

        memberService.updateMemberActivity(discordID, memberName);
    }
}
