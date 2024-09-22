package services.dj45x.Commands.DevCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import services.dj45x.Utils.DevMode;

@Component
public class Version extends ListenerAdapter {
    @Value("${app.version}")
    private String appVersion;

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if ("dev".equals(event.getName()) && "version".equals(event.getSubcommandName())) {
            boolean devMode = DevMode.getDevMode();
            if (devMode) {
                event.reply("The current bot version is " + appVersion + "-dev").setEphemeral(true).queue();
            } else {
                event.reply("The current bot version is " + appVersion).setEphemeral(true).queue();
            }
        }
    }
}
