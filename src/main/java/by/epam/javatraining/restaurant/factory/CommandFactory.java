package by.epam.javatraining.restaurant.factory;

import by.epam.javatraining.restaurant.command.Command;
import by.epam.javatraining.restaurant.command.CommandName;
import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.command.impl.*;
import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.Map;

public class CommandFactory {
    private final Map<CommandName, Command> commands;

    private CommandFactory() {
        commands = new EnumMap<>(CommandName.class);
        commands.put(CommandName.REGISTRATION, new UserRegistrationCommand());
        commands.put(CommandName.SIGN_IN, new UserSignInCommand());
        commands.put(CommandName.ADD_TO_CART, new AddPositionToCartCommand());
        commands.put(CommandName.LOGOUT, new UserLogoutCommand());
        commands.put(CommandName.PROFILE, new UserProfileViewCommand());
        commands.put(CommandName.CREATE_ORDER, new CreateOrderCommand());
        commands.put(CommandName.CONFIRM_ORDER, new ConfirmOrderCommand());
        commands.put(CommandName.VIEW_USER_ORDERS, new ViewUserOrdersCommands());
        commands.put(CommandName.VIEW_ADMIN_PROFILE, new ViewAdminProfileCommand());
        commands.put(CommandName.VIEW_UNCONFIRMED_ORDERS, new ViewUnconfirmedOrdersCommand());
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
