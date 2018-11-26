package ru.otus.spring.courseproject.yag.domain.project;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.spring.courseproject.yag.domain.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskTreeTest {

    @Test
    public void getLeaves() {

        List<Task> list = new ArrayList<>();

        Task t = new Task();
        t.setDescription("first");
        t.setId(1);
        list.add(t);

        Task t1 = new Task();
        t1.setDescription("second");
        t1.setId(2);
        t1.setParent(t);
        list.add(t1);


        Task t3 = new Task();
        t3.setDescription("third");
        t3.setId(3);
        t3.setParent(t);
        list.add(t3);


        TaskTree tree = new TaskTree(list);
        Assert.assertEquals(2, tree.getLeaves().size());
        Assert.assertEquals("second", tree.getLeaves().get(0).getDescription());
        Assert.assertEquals("third", tree.getLeaves().get(1).getDescription());





    }

}