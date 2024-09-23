package services.dj45x;

import net.dv8tion.jda.api.sharding.ShardManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Main {

    private final ShardManager shardManager;

    @Autowired
    public Main(ShardManager shardManager) {
        this.shardManager = shardManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}