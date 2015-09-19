package com.skelril.notorious.logger;

import com.skelril.notorious.consumable.BlockChange;
import com.skelril.notorious.consumer.DataPool;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTransaction;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;

public class BlockChangeLogger implements StateComparison<BlockSnapshot, BlockChange> {

    private final DataPool dataPool;

    public BlockChangeLogger(DataPool dataPool) {
        this.dataPool = dataPool;
    }

    @Override
    public BlockChange compare(BlockSnapshot a, BlockSnapshot b) {
        return null;
    }

    @Listener
    public void onBockBreak(ChangeBlockEvent event) {
        for (BlockTransaction transaction : event.getTransactions()) {
            BlockChange change = compare(transaction.getOriginal(), transaction.getFinalReplacement());
            if (change != null) {
                change.causedBy(event.getCause().all());
                dataPool.enqueue(change);
            }
        }
    }
}
