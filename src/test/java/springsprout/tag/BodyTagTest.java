package springsprout.tag;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.*;
import org.springframework.web.context.WebApplicationContext;
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author anarcher
 */
public class BodyTagTest {

    private MockServletContext mockServletContext;
    private MockPageContext mockPageContext;
    private WebApplicationContext mockWebApplicationContext;

    private BodyTag bodyTag;

    @Before
    public void setUp() {
        mockServletContext = new MockServletContext();
        mockServletContext.setAttribute(
                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
                mockWebApplicationContext);
        mockPageContext = new MockPageContext(mockServletContext);

        bodyTag = new BodyTag(); 
        bodyTag.setPageContext(mockPageContext);
    }

    @After
    public void tearDown() {}

    @Test
    public void bodyTag() throws UnsupportedEncodingException {

        JspWriter jw = mock(JspWriter.class); 
        BodyContent bc = mock(BodyContent.class);
        when(bc.getEnclosingWriter()).thenReturn(jw);
        when(bc.getString()).thenReturn("this is body");
        bodyTag.setBodyContent(bc);
        try {
            bodyTag.doAfterBody();
        }
        catch(JspException jspe) {
            System.out.println(jspe);    
        }

        try{
            verify(jw).print("this is body");
        } catch(IOException ioe) {
            System.out.println(ioe);
        }

    }
}
