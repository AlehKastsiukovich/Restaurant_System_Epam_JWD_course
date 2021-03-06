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
        commands.put(CommandName.PROCESS_USER_ORDER, new ProcessUserOrderByAdminCommand());
        commands.put(CommandName.VIEW_ALL_USERS_ORDERS, new ViewAllOrdersByAdminCommand());
        commands.put(CommandName.VIEW_ALL_USERS, new ViewAllUsersByAdminCommand());
        commands.put(CommandName.ENTER_FIND_USER_PAGE, new FindUserByAdminCommand());
        commands.put(CommandName.ENTER_FIND_ORDER_PAGE, new FindOrderByAdminCommand());
        commands.put(CommandName.VIEW_ORDER_INFO, new ViewOrderInfoByAdminCommand());
        commands.put(CommandName.DELETE_ORDER, new DeleteOrderByAdminCommand());
        commands.put(CommandName.VIEW_USER_ORDERS_BY_ADMIN, new ViewUserOrdersByAdminCommand());
        commands.put(CommandName.DELETE_USER_ORDER_BY_ADMIN_INFO_PAGE, new DeleteOrderByAdminOnOrderSearchPageCommand());
        commands.put(CommandName.DELETE_USER, new DeleteUserByAdminCommand());
        commands.put(CommandName.FIND_USER_BY_ID, new FindUserByIdCommand());
        commands.put(CommandName.DELETE_USER_BY_ID_SEARCH_PAGE, new DeleteUserByAdminOnUserSearchPageCommand());
        commands.put(CommandName.FIND_USER_BY_LOGIN, new FindUserByLoginCommand());
        commands.put(CommandName.FIND_USER_BY_EMAIL, new FindUserByEmailCommand());
        commands.put(CommandName.PROCESS_USER_ORDER_ON_SEARCH_PAGE, new ConfirmUserOrderByAdminOnOrderSearchPageCommand());
        commands.put(CommandName.FIND_ORDER_BY_ID, new FindOrderByIdCommand());
        commands.put(CommandName.FIND_ORDER_BY_USER_ID, new FindOrdersByUserIdCommand());
        commands.put(CommandName.DELETE_ORDER_ON_SEARCH_PAGE, new DeleteOrderByAdminOnOrderSearchPageCommand());
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
            checkMethodType(commandName, request);
            command = commands.get(CommandName.valueOf(request.getParameter(JSPParameter.COMMAND.getValue())));
        } else {
            command = new RedirectToStartPageCommand();
        }

        return command;
    }

    private void checkMethodType(String commandName, HttpServletRequest request) {
        if (commandName.equals(JSPParameter.CONFIRM_ORDER.getValue())
                || commandName.equals(JSPParameter.REGISTRATION.getValue())) {
            request.setAttribute(JSPParameter.REQUEST_TYPE.getValue(), JSPParameter.POST.getValue());
        }
    }
}
