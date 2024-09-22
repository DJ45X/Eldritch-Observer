package services.dj45x.Utils;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.List;

public class JDAUtils {
    public static TextChannel getTextChannelByName(Guild guild, String channelName) {
        List<TextChannel> channels = guild.getTextChannelsByName(channelName, true);
        if(!channels.isEmpty()){
            return channels.get(0);
        }
        return null;
    }

    public static Category getCategoryByName(Guild guild, String categoryName) {
        List<Category> categories = guild.getCategoriesByName(categoryName, true);
        if(!categories.isEmpty()) {
            return categories.get(0);
        }
        return null;
    }

    public static Role getRoleByName(Guild guild, String roleName) {
        List<Role> roles = guild.getRolesByName(roleName, true);
        if(!roles.isEmpty()) {
            return roles.get(0);
        }
        return null;
    }

    public static Boolean memberHasRole(Member member, Role roleName) {
        return member.getRoles().contains(roleName);
    }

    public static void sendErrorToLogsChannel(Guild guild, String message) {
        TextChannel errorLogsChannel = getTextChannelByName(guild, "error-logs");
        if (errorLogsChannel == null) {
            Logger.error("Error logs channel not found");
            return;
        }
        errorLogsChannel.sendMessage(message).queue();
    }
}
