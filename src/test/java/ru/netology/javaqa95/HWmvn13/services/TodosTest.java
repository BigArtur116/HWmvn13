package ru.netology.javaqa95.HWmvn13.services;

import com.sun.source.tree.NewArrayTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeEmpty() {

        Todos todos = new Todos();

        Task[] expected = { };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        String[] subtasks2 = {"Молоток", "Гвозди", "Клей"};
        Epic epic2 = new Epic(56, subtasks2);

        String[] subtasks3 = {"ОСБ", "Планка крепежная", "Клей"};
        Epic epic3 = new Epic(57, subtasks3);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(epic2);
        todos.add(epic3);
        todos.add(meeting);

        Assertions.assertArrayEquals(new Task[]{epic}, todos.search("Молоко"));
        Assertions.assertArrayEquals(new Task[]{epic2, epic3}, todos.search("Клей"));
        Assertions.assertArrayEquals(new Task[]{meeting}, todos.search("Выкатка"));
        Assertions.assertArrayEquals(new Task[]{meeting}, todos.search("НетоБанка"));
        Assertions.assertArrayEquals(new Task[]{meeting}, todos.search("вторник"));
        Assertions.assertArrayEquals(new Task[]{simpleTask}, todos.search("Позвонить"));
        Assertions.assertArrayEquals(new Task[]{ }, todos.search("Среда")); // Должен быть пустым

    }
}