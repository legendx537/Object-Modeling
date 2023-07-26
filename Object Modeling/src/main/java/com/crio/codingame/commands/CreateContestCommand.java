package com.crio.codingame.commands;

import java.util.List;
import com.crio.codingame.entities.Level;
import com.crio.codingame.exceptions.QuestionNotFoundException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.services.IContestService;

public class CreateContestCommand implements ICommand {

    private final IContestService contestService;

    public CreateContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void execute(List<String> tokens) {
        String contestName = tokens.get(1);
        String level = tokens.get(2);
        String creatorName = tokens.get(3);
        Integer score = tokens.size() == 5 ? Integer.parseInt(tokens.get(4)) : null;

        // System.out.println( contestName);
        // System.out.println( level );
        // System.out.println( creatorName );
        // System.out.println( score);

        try {
            System.out.println(
                    contestService.create(contestName, Level.valueOf(level), creatorName, score));
        } catch (UserNotFoundException | QuestionNotFoundException e) {
            System.out.println(e.getMessage());
            // e.printStackTrace();
        }

    }

}
