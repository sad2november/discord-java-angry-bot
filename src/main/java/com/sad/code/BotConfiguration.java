package com.sad.code;

import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfiguration {

    @Value("${discord.token}")
    private String token;

    @Value("${discord.channels}")
    private String channels;

    @Value("${discord.reactions}")
    private String reactions;

    @Value("${discord.ratio}")
    private String ratio;

    @Bean
    @SneakyThrows
    public JDA gatewayDiscordClient() {
        return JDABuilder.createDefault(token)
                .addEventListeners(new AngryMessageListener(channels, reactions, ratio))
                .build()
                .awaitReady();
    }
}