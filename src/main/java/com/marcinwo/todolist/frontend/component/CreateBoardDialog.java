package com.marcinwo.todolist.frontend.component;

import com.github.juchar.colorpicker.ColorPickerField;
import com.marcinwo.todolist.app.entity.TasksBoard;
import com.marcinwo.todolist.app.entity.User;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import org.vaadin.gatanaso.MultiselectComboBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateBoardDialog extends Dialog {

    private TextField name = new TextField("Name");
    private ColorPickerField colourPicker = new ColorPickerField();
    private MultiselectComboBox<User> users = new MultiselectComboBox<>("Select users");
    private Binder<TasksBoard> binder = new BeanValidationBinder<>(TasksBoard.class);
    private TasksBoard tasksBoard = new TasksBoard();

    private User currentUser;

    private OnSaveButtonClickListener onSaveButtonClickListener;
    private OnCancelButtonClickListener onCancelButtonClickListener;

    public CreateBoardDialog(OnSaveButtonClickListener onSaveButtonClickListener, OnCancelButtonClickListener onCancelButtonClickListener) {
        this.onSaveButtonClickListener = onSaveButtonClickListener;
        this.onCancelButtonClickListener = onCancelButtonClickListener;

        this.users.setItems(new ArrayList<>());
        this.binder.bindInstanceFields(this);
        this.binder.setBean(tasksBoard);
        createLayout();
    }

    private void createLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();

        FormLayout formLayout = new FormLayout();

//        List<String> colors = Arrays.stream(Color.values()).map(Enum::name).collect(Collectors.toList());
//        colour2.setItems(colors);

        createColorPicker(); // #1d1d1d -> Color
        binder.forField(colourPicker).bind(tb -> {
            String hex = tb.getColour();
            if (hex != null) {
                return new Color(
                        Integer.valueOf(hex.substring(1, 3), 16),
                        Integer.valueOf(hex.substring(3, 5), 16),
                        Integer.valueOf(hex.substring(5, 7), 16));
            }

            return Color.BLUE;

        }, (tb, c) -> {
            if (c != null) {
                String hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
                tb.setColour(hex);
            }else {
                tb.setColour("#1d1d1d");
            }

        });

        users.setWidth("100%");
        users.setLabel("Select users");
        users.setPlaceholder("Choose...");
        users.setItemLabelGenerator(User::getUserName);

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("25em", 2));

        formLayout.add(name, 1);
        formLayout.add(colourPicker, 1);
        formLayout.add(users, 2);

        verticalLayout.add(formLayout, createActionButtons());
        this.add(verticalLayout);
    }

    private void createColorPicker() {
        colourPicker.setPinnedPalettes(true);
        colourPicker.setHexEnabled(true);
        colourPicker.setPalette(Color.RED, Color.GREEN, Color.BLUE);
        colourPicker.setChangeFormatButtonVisible(true);
        colourPicker.setWidth("400px");
        colourPicker.setHeight("55px");
    }

    public HorizontalLayout createActionButtons() {
        HorizontalLayout actions = new HorizontalLayout();

        Button save = new Button("Save", e -> this.onSaveButtonClickListener.onSaveButtonClick());
        Button cancel = new Button("Cancel", e -> this.onCancelButtonClickListener.onCancelButtonClick());
        actions.add(save, cancel);

        cancel.getStyle().set("marginRight", "10px");
        save.getStyle().set("marginRight", "10px");


        return actions;
    }

    public TasksBoard getTaskBoard() {
//        String hexColor = Color.valueOf(colour2.getValue()).getHexValue();
//        tasksBoard.setColour(hexColor);
        tasksBoard.setOwner(currentUser);
        return tasksBoard;
    }

    public void setDbUsers(List<User> dbUsers) {
        users.setItems(dbUsers);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void clear() {
        binder.getFields().forEach(HasValue::clear);
        tasksBoard = new TasksBoard();
        binder.setBean(tasksBoard);
    }

    public Binder<TasksBoard> getBinder() {
        return binder;
    }
}
