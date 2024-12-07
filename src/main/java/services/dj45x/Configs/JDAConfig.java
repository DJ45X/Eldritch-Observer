package services.dj45x.Configs;

import lombok.SneakyThrows;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import services.dj45x.Utils.DevMode;

@Configuration
public class JDAConfig {

    private final DevMode devMode;

    public JDAConfig(DevMode devMode) {
        this.devMode = devMode;
    }

    @Bean
    @Lazy
    @SneakyThrows
    public ShardManager shardManager(ObjectProvider<ListenerAdapter> listenerAdapters) {
        DefaultShardManagerBuilder shardBuilder = DefaultShardManagerBuilder
                .createDefault(DevMode.getDiscordToken())
                .setShardsTotal(2)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setEnabledIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                        GatewayIntent.SCHEDULED_EVENTS
                );

        for (ListenerAdapter adapter : listenerAdapters) {
            shardBuilder.addEventListeners(adapter);
        }

        ShardManager shardManager = shardBuilder.build();
        shardManager.setActivity(Activity.customStatus("Surrender your wares!"));

        return shardManager;
    }
}
