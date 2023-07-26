package com.crio.codingame.commands;

import java.util.List;
import com.crio.codingame.services.IUserService;

public class CreateUserCommand implements ICommand {

    private final IUserService userService;

    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userName = tokens.get(1);
        try {
            System.out.println(userService.create(userName));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

}
