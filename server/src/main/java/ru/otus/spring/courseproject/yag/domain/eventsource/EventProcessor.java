package ru.otus.spring.courseproject.yag.domain.eventsource;

public interface EventProcessor<TEvent>  {
    <TEventResult> TEventResult process(TEvent event);
    boolean matches(TEvent event);
}
