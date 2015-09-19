package com.skelril.notorious.consumer;

import com.skelril.notorious.consumable.ConsumableData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class DataPool implements Iterable<ConsumableData> {

    // TODO Make configurable
    private static long FLUSH_TIMEOUT = TimeUnit.MINUTES.toMillis(5);
    private static long BATCH_SIZE = 1000;

    private final ArrayList<ConsumableData> dataSource = new ArrayList<>();
    private final CopyOnWriteArrayList<ConsumableData> dataQueue = new CopyOnWriteArrayList<>();

    private long lastFlush = System.currentTimeMillis();

    /**
     * This method should be called only from the owning thread of this DataPool.
     *
     * @param data the incoming data
     */
    public void enqueue(ConsumableData data) {
        dataSource.add(data);
        if (passesFlushTimeout() || passesFlushQuantity()) {
            dataQueue.addAll(dataSource);
            dataSource.clear();
        }
    }

    /**
     * This method may be called from any thread.
     *
     * @return the active data queue
     */
    @Override
    public Iterator<ConsumableData> iterator() {
        return dataQueue.iterator();
    }

    private boolean passesFlushTimeout() {
        return System.currentTimeMillis() - lastFlush >= FLUSH_TIMEOUT;
    }

    private boolean passesFlushQuantity() {
        return dataSource.size() > BATCH_SIZE;
    }
}
