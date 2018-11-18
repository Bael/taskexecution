package ru.otus.spring.courseproject.yag.domain.eventsource;

public interface EventHandler<TEvent>  {
    void handle(TEvent event);
    boolean matches(TEvent event);
}
