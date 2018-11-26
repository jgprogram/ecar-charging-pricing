package com.jgprogram.common.utils;

public interface Mapper<T, K> {

    K map(T source);
}
