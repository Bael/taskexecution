package ru.otus.spring.courseproject.yag.domain.project;

import ru.otus.spring.courseproject.yag.domain.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskTree {
    private final List<Task> tasks;
    /**
     * Карта показывающая родителя для указанной задачи
     */
    private final Map<Long, Task> parentMap;
    /**
     * Карта ключ - задача. (нужна?)
     */
    private final Map<Long, Task> flatMap;
    private final Map<Task, List<Task>> directChildren;

    public TaskTree(List<Task> tasks) {
        this.tasks = tasks;

        this.directChildren = new HashMap<>();
        this.parentMap = new HashMap<>();
        this.flatMap = new HashMap<>();
        build();
    }

    private void build() {

        for (Task t : tasks) {
            flatMap.put(t.getId(), t);
            if (t.getParent() != null) {
                parentMap.put(t.getId(), t.getParent());

                List<Task> children = directChildren.getOrDefault(t.getParent(), new ArrayList<>());
                children.add(t);
                directChildren.put(t.getParent(), children);
            }
        }
    }

    public List<Task> getLeaves() {
        return tasks.stream().filter(task -> !directChildren.containsKey(task)).collect(Collectors.toList());
    }
}
