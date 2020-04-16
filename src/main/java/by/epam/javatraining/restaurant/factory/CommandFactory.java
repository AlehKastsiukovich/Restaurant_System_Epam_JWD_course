package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.CommandName;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.impl.RedirectToStartPageCommand;
import by.epam.javatraining.restaurant.command.impl.UserSignInCommand;
import by.epam.javatraining.restaurant.command.impl.UserRegistrationCommand;
import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.Map;

public class CommandFactory {
    private final Map<CommandName, Command> commands;

    private CommandFactory() {
        commands = new EnumMap<>(CommandName.class);
        commands.put(CommandName.REGISTRATION, new UserRegistrationCommand());
        commands.put(CommandName.SIGN_IN, new UserSignInCommand());
    }

    private static class CommandFactoryHolder {
        private static final CommandFactory INSTANCE = new CommandFactory();
    }

    public static CommandFactory getInstance() {
        return CommandFactoryHolder.INSTANCE;
    }

    public Command spotCommand(HttpServletRequest request) {
        Command command;

        String commandName = request.getParameter(JSPParameter.COMMAND.getValue());
        if (commandName != null) {
            command = commands.get(CommandName.valueOf(request.getParameter(JSPParameter.COMMAND.getValue())));
        } else {
            command = new RedirectToStartPageCommand();
        }
        System.out.println("Command is " + command.toString());
        return command;
    }
}
