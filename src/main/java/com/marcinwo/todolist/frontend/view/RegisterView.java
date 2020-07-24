package com.marcinwo.todolist.frontend.view;

import com.marcinwo.todolist.app.entity.User;
import com.marcinwo.todolist.app.service.UserService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
@PageTitle("Register now!")
public class RegisterView extends VerticalLayout {

    private TextField name = new TextField("Name");
    private TextField lastName = new TextField("Last name");
    private TextField userName = new TextField("Username");
    private EmailField email = new EmailField("Email");
    private PasswordField password = new PasswordField("Password");
    private PasswordField confirmPassword = new PasswordField("Confirm password");

    private Button registerButton = new Button("Register", this::onRegisterButtonClick);
    private Button resetButton = new Button("Reset", this::onResetButtonClick);

    private UserService userService;

    @Autowired
    public RegisterView(UserService userService) {
        this.userService = userService;
        createLayout();
    }

    private void createLayout() {
        FormLayout formLayout = new FormLayout();

        formLayout.setResponsiveSteps(
                new ResponsiveStep("25em", 1),
                new ResponsiveStep("32em", 2),
                new ResponsiveStep("40em", 3));

        formLayout.add(name, 1);
        formLayout.add(lastName, 1);
        formLayout.add(userName, 1);
        formLayout.add(email, 3);
        formLayout.add(password, 1);
        formLayout.add(confirmPassword, 1);

        HorizontalLayout actions = new HorizontalLayout();
        actions.add(registerButton, resetButton);
        registerButton.getStyle().set("marginRight", "10px");

        add(formLayout, actions);
    }

    private void onRegisterButtonClick(ClickEvent<Button> clickEvent) {
        User user = new User(name.getValue(), lastName.getValue(),
                userName.getValue(), password.getValue(), email.getValue());
        userService.save(user);
        clearForm();
    }

    private void onResetButtonClick(ClickEvent<Button> buttonClickEvent) {
        clearForm();
    }

    private void clearForm() {
        name.clear();
        lastName.clear();
        userName.clear();
        email.clear();
        password.clear();
        confirmPassword.clear();
    }

}
