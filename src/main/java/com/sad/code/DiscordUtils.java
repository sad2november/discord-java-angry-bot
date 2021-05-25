package com.sad.code;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class DiscordUtils {

    public List<Long> getChannelsToWorkWith(@NonNull final String channelIdToGrabInfo, @NonNull final JDA jda) {
        return Objects.requireNonNull(jda.getTextChannelById(channelIdToGrabInfo))
                .getHistoryFromBeginning(100)
                .complete()
                .getRetrievedHistory()
                .stream()
                .map(x -> Long.parseLong(x.getContentRaw()))
                .collect(Collectors.toList());
    }

    public List<String> getGifsToWorkWith(@NonNull final String channelIdToGrabInfo, @NonNull final JDA jda) {
        return Objects.requireNonNull(jda.getTextChannelById(channelIdToGrabInfo))
                .getHistoryFromBeginning(100)
                .complete()
                .getRetrievedHistory()
                .stream()
                .map(Message::getContentRaw)
                .collect(Collectors.toList());
    }

    public int getBullingRatio(@NonNull final String channelIdToGrabInfo, @NonNull final JDA jda) {
        return Integer.parseInt(
                Objects.requireNonNull(jda.getTextChannelById(channelIdToGrabInfo))
                        .getHistoryFromBeginning(1)
                        .complete()
                        .getRetrievedHistory()
                        .stream()
                        .map(Message::getContentRaw)
                        .collect(Collectors.toList())
                        .get(0)
        );
    }
}