package springsprout.tag;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class BodyTag extends BodyTagSupport {

    public int doAfterBody() throws JspException {
        BodyContent bc = getBodyContent();
        String body = bc.getString();

        try{
            JspWriter out = bc.getEnclosingWriter();
            out.print(body.toLowerCase());
        }
        catch(IOException ioe) {
            System.out.println(ioe);
        }

        return SKIP_BODY;
    }

}

