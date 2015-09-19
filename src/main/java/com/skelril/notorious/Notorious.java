package com.skelril.notorious;

import com.skelril.notorious.consumer.Consumer;
import com.skelril.notorious.consumer.DataPool;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;

public class Notorious {
    private final DataPool dataPool = new DataPool();

    @Listener
    public void onInit(GameStartingServerEvent event) {
        Game game = event.getGame();
        game.getScheduler().createTaskBuilder().async().interval(20 * 60).execute(new Consumer(dataPool)).submit(this);
    }
}
