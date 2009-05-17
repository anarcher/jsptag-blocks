package springsprout.tag.blocks;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class BlockTag extends BodyTagSupport {

    private String name;
    private BlocksTag blocksTag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() throws JspException {
        blocksTag = (BlocksTag) findAncestorWithClass(this, BlocksTag.class);
        if (blocksTag == null) {
            throw new JspException("block tag is contained blocks tag..");
        }

        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        if(!blocksTag.isRendered()) {
            String bc = null;
            if(bodyContent != null) {
                bc = bodyContent.getString();
            }
            else {
                bc = "";
            }

            if(bc != null) {
                blocksTag.addBlockContent(name,bc);
            }
            return EVAL_PAGE;
        }

        try {
            //override.
            if(blocksTag.containsBlock(this.name)) {
                pageContext.getOut().write(blocksTag.getBlockContent(this.name));
            }
            else{
                pageContext.getOut().write(bodyContent.getString());
            }
        } catch (IOException ex) {
            Logger.getLogger(BlockTag.class.getName()).log(Level.SEVERE, null, ex);
        }

        return EVAL_PAGE;
    }
}
