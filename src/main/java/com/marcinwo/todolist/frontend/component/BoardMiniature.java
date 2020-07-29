package com.marcinwo.todolist.frontend.component;

import com.marcinwo.todolist.app.entity.TasksBoard;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class BoardMiniature extends VerticalLayout {

    private TasksBoard tasksBoard;
    private Label title = new Label();

    public BoardMiniature(TasksBoard tasksBoard) {
        this.tasksBoard = tasksBoard;
        createLayout();

        addClickListener(e -> {
            UI.getCurrent().getPage().executeJs("location.assign('board/" + tasksBoard.getId() +  "')");
        });
    }

    private void createLayout() {
        setHeight("80px");
        setWidth("120px");

        getStyle().set("border", "1px solid black");
        getStyle().set("background-color", tasksBoard.getColour());

        title.setText(tasksBoard.getName());
        add(title);
    }

}
