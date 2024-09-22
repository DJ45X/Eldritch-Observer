package services.dj45x;

import lombok.SneakyThrows;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.dj45x.Utils.DevMode;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @SneakyThrows
    public Main(
            ObjectProvider<ListenerAdapter> listenerAdapters,
            DevMode devMode
    ){
        DefaultShardManagerBuilder shardBuilder = DefaultShardManagerBuilder
                .createDefault(DevMode.getDiscordToken())
                .setShardsTotal(1)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setEnabledIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.DIRECT_MESSAGES
                );

        for(var adapter : listenerAdapters) {
            shardBuilder.addEventListeners(adapter);
        }

        ShardManager shardManager = shardBuilder.build();

        shardManager.setActivity(Activity.customStatus("Surrender your wares!"));
    }
}