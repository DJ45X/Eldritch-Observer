package services.dj45x.Commands.Moderation;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.dj45x.Services.WarningMessageService;
import services.dj45x.Utils.JDAUtils;
import services.dj45x.Utils.Logger;

import java.util.Objects;

@Component
public class UpdateWarningMessage extends ListenerAdapter {

    @Autowired
    private WarningMessageService warningMessageService;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if ("mod".equals(event.getName()) && "update-warning-message".equals(event.getSubcommandName())) {
            event.deferReply(true).queue();

            String message = Objects.requireNonNull(event.getOption("message")).getAsString();

            try {
                warningMessageService.updateWarningMessage(message);
                event.getHook().editOriginal("Successfully updated the warning message!").queue();
            } catch (Exception e) {
                event.getHook().editOriginal("Failed to update the warning message! Please try again or contact ``DJ45X``!").queue();
                Logger.error("Failed to update the warning message!");
                JDAUtils.sendErrorToLogsChannel(event.getGuild(), "``/mod update-warning-message`` failed with error: " + e.getMessage());
            }
        }
    }
}
