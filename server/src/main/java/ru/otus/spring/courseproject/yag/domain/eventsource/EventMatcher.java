package ru.otus.spring.courseproject.yag.domain.eventsource;

public interface EventMatcher<TEvent>  {
    boolean matches(TEvent event);
}
