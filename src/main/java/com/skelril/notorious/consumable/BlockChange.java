package com.skelril.notorious.consumable;

import java.util.Collection;

public class BlockChange implements ConsumableData<BlockChange> {

    @Override
    public BlockChange causedBy(Collection<Object> causes) {
        return new BlockChange();
    }

    @Override
    public void commit() {

    }
}
