package services.dj45x.Commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import services.dj45x.Utils.DevMode;
import services.dj45x.Utils.Logger;

import java.util.concurrent.TimeUnit;

@Component
public class CommandRegistration extends ListenerAdapter {
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        super.onGuildReady(event);
        Guild guild = event.getGuild();
        String guildID = DevMode.getGuildID();

        if (guild.getId().equals(guildID)) {
            guild.updateCommands().addCommands(
                    Commands.slash("dev", "Developer commands")
                            .addSubcommands(
                                    new SubcommandData("initialize", "Initialize sticky messages"),
                                    new SubcommandData("version", "Get the bot's current version")
                            ).setGuildOnly(true)
            ).queueAfter(3, TimeUnit.SECONDS,
                    success -> Logger.info("All commands registered!"));
        }
    }
}
