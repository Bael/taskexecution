package ru.otus.spring.courseproject.yag.domain.eventsource;

public interface EventManager {

    <TEvent> void handle(TEvent event);
    <TEvent, TEventResult> TEventResult process(TEvent event);
}
