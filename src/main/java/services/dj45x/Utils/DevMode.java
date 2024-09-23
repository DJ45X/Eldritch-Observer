package services.dj45x.Utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DevMode {
    private static boolean devMode;

    public DevMode(@Value("$(dev-mode)") boolean devMode) {
        DevMode.devMode = devMode;
    }

    public static boolean getDevMode() {
        return devMode;
    }

    public static String getDiscordToken() {
        Dotenv dotenv = Dotenv.load();

        if (!devMode) {
            Logger.warn("Running in Production Mode!");

            return dotenv.get("PROD_TOKEN");
        } else {
            Logger.warn("Running in Development Mode!");
            return dotenv.get("TEST_TOKEN");
        }
    }

    public static String getGuildID() {
        Dotenv dotenv = Dotenv.load();

        return devMode ? dotenv.get("DEV_GUILD_ID") : dotenv.get("PROD_GUILD_ID");
    }
}
