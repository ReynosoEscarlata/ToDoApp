package com.reynoso.ToDoApp.mapper;

public interface IMapper<I,O> {
    public O map(I in);
}
