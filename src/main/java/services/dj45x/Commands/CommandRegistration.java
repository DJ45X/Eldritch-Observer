package services.dj45x.Commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.dj45x.Utils.DevMode;
import services.dj45x.Utils.Logger;

import java.util.concurrent.TimeUnit;

@Component
public class CommandRegistration extends ListenerAdapter {

    @Autowired
    private DevMode devMode;

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
                                    new SubcommandData("initialize-db", "Initialize the database"),
                                    new SubcommandData("version", "Get the bot's current version")
                            ).setGuildOnly(true),
                    Commands.slash("mod", "Moderator commands")
                            .addSubcommands(
                                    new SubcommandData("check-inactive", "Get a list of inactive members"),
                                    new SubcommandData("check-warning-message", "Get the current warning message"),
                                    new SubcommandData("update-warning-message", "Update the warning message")
                                            .addOption(OptionType.STRING, "message", "The new warning message", true)
                            ).setGuildOnly(true)
            ).queueAfter(3, TimeUnit.SECONDS,
                    success -> Logger.info("All commands registered!"));
        }
    }
}
