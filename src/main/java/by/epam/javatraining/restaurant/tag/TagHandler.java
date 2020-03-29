package by.epam.javatraining.restaurant.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;

public class TagHandler extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        String message = "<b>Developed by Aleh Kastsiukovich 2020</b>";

        try {
            JspWriter out = pageContext.getOut();
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
