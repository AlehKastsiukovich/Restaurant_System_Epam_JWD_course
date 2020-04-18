package by.epam.javatraining.restaurant.filter;

import by.epam.javatraining.restaurant.command.JSPParameter;
import by.epam.javatraining.restaurant.entity.Position;
import by.epam.javatraining.restaurant.util.PositionCash;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class CashFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(CashFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        List<Position> positionList = PositionCash.getInstance().getPositionList();
        req.setAttribute(JSPParameter.POSITIONS.getValue(), positionList);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Filter initialize");
    }

    @Override
    public void destroy() {
        LOGGER.info("Filter destroyed");
    }
}
