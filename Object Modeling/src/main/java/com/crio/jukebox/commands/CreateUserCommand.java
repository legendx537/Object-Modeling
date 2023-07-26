package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;

public class CreateUserCommand implements ICommand{
    private final IUserService userService;
    
    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String name = tokens.get(1);
        
        try {
          User user = userService.create(name);
          System.out.println( user.getId() + " " + user.getName());
        } catch (RuntimeException e) {
            System.out.println("Error Occured :- " + e.getMessage());
        }
    }
    // expected output :- "1 Kiran"
    
}
