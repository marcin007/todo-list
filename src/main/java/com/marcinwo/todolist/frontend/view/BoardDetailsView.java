package com.marcinwo.todolist.frontend.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("board")
public class BoardDetailsView extends VerticalLayout implements HasUrlParameter<Long> {

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long taskBoardId) {

    }
}
