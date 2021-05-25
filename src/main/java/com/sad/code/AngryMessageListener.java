package com.sad.code;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

@Slf4j
@AllArgsConstructor
public class AngryMessageListener extends ListenerAdapter {

    private final String channels;
    private final String reactions;
    private final String ratio;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        val msg = event.getMessage();

        if (!msg.getAuthor().isBot() &&
                DiscordUtils.getChannelsToWorkWith(channels, event.getJDA()).contains(msg.getChannel().getIdLong())) {

            val coinFlip = Math.random() * (100 - 1) + 1;
            log.info("{} % to coinflip {} ", coinFlip, msg);

            if (coinFlip <= DiscordUtils.getBullingRatio(ratio, event.getJDA())) {
                val chatReactions = DiscordUtils.getGifsToWorkWith(reactions, event.getJDA());

                event.getChannel()
                        .sendMessage(
                                "<@" + event.getAuthor().getIdLong() + "> " +
                                        chatReactions.get(new Random().nextInt(chatReactions.size()))
                        ).queue();
            }
        }
    }
}