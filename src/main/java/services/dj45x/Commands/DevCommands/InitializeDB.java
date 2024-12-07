package services.dj45x.Commands.DevCommands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.dj45x.Models.MemberModel;
import services.dj45x.Repositories.MemberRepository;
import services.dj45x.Utils.Logger;

import java.time.LocalDateTime;

@Component
public class InitializeDB extends ListenerAdapter {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        super.onSlashCommandInteraction(event);

        if ("dev".equals(event.getName()) && "initialize-db".equals(event.getSubcommandName())) {
            event.deferReply(true).queue();
            Member interactionMember = event.getInteraction().getMember();
            Guild guild = event.getGuild();

            if (interactionMember != null && !interactionMember.getId().equals("264270923845074956")) {
                event.getHook().editOriginal("You don't have permission to use this command!").queue();
            } else {
                if (guild != null) {
                    guild.loadMembers().onSuccess(members -> {
                        for (Member member : members) {
                            MemberModel memberModel = new MemberModel();

                            memberModel.setDiscordID(member.getId());
                            memberModel.setMemberName(member.getEffectiveName());
                            memberModel.setLastActive(LocalDateTime.now());
                            memberModel.setInactive(false);

                            memberRepository.save(memberModel);
                        }

                        event.getHook().editOriginal("Successfully added all members to database!").queue();
                    }).onError(error -> event.getHook().editOriginal("Failed to add all members to database!\n\n" + error.getMessage()).queue());
                } else {
                    event.getHook().editOriginal("Failed to retrieve the guild!").queue();
                    Logger.error("Failed to retrieve the guild when running `/dev initialize-db`!");
                }
            }
        }
    }
}
