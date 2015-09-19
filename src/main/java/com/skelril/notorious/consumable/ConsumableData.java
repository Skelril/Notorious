package com.skelril.notorious.consumable;

import java.util.Collection;

public interface ConsumableData<T extends ConsumableData> {
    T causedBy(Collection<Object> causes);
    void commit();
}
