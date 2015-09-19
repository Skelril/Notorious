package com.skelril.notorious.consumer;

import com.skelril.notorious.consumable.ConsumableData;

public class Consumer implements Runnable {

    private final DataPool dataPool;

    public Consumer(DataPool dataPool) {
        this.dataPool = dataPool;
    }

    @Override
    public void run() {
        for (ConsumableData data : dataPool) {
            data.commit();
        }
    }
}
