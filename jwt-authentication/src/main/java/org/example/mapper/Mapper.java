package org.example.mapper;

public interface Mapper<T, R> {
    R map(T object);
}
