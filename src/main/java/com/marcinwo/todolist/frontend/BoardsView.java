package com.marcinwo.todolist.frontend;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("boards")
public class BoardsView extends VerticalLayout {

    private Button createNewBoardButton = new Button("Create New Board", this::onCreateNewBoardButtonClick);

    private Dialog dialog = new Dialog();

    public BoardsView() {
        createLayout();
    }

    private void createLayout() {
        add(createNewBoardButton);
    }

    private void onCreateNewBoardButtonClick(ClickEvent<Button> e) {
        dialog.open();
    }
}
