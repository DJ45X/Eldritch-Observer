package services.dj45x.Commands.Moderation;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.dj45x.Services.WarningMessageService;

import java.util.Optional;

@Component
public class CheckWarningMessage extends ListenerAdapter {

    @Autowired
    private WarningMessageService warningMessageService;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if ("mod".equals(event.getName()) && "check-warning-message".equals(event.getSubcommandName())) {
            Optional<String> warningMessage = warningMessageService.getWarningMessage();

            if (warningMessage.isEmpty()) {
                event.reply("No warning message found in the database!").setEphemeral(true).queue();
            } else {
                event.reply(String.format("The current warning message is:\n\n``%s``", warningMessage.get())).setEphemeral(true).queue();
            }
        }
    }
}
