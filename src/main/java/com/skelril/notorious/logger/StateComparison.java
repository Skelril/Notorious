package com.skelril.notorious.logger;

public interface StateComparison<T, D> {
    D compare(T a, T b);
}
