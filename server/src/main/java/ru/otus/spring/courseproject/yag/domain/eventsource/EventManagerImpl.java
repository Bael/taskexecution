package ru.otus.spring.courseproject.yag.domain.eventsource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventManagerImpl implements EventManager {

    private final List<EventHandler> handlers;
    private final List<EventProcessor> processors;

    @Autowired
    public EventManagerImpl(List<EventHandler> handlers, List<EventProcessor> processors) {
        this.handlers = handlers;
        this.processors = processors;
    }


    @Override
    public <TEvent> void handle(TEvent event) {
        handlers.stream()
                .filter(eventHandler ->
                    eventHandler.matches(event)
                ).findFirst().get().handle(event);
    }

    @Override
    public <TEvent, TEventResult> TEventResult process(TEvent event) {
        return (TEventResult) processors.stream()
                .filter(eventProcessor ->
                        eventProcessor.matches(event)
                ).findFirst().get().process(event);
    }
}
