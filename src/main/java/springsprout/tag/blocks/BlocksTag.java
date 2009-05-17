package springsprout.tag.blocks;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class BlocksTag extends BodyTagSupport {

    private String with;
    private Blocks blocks;
    private boolean isRendered = false;

    public void setWith(String with){
        this.with = with;
    }

    public boolean isRendered() {
        return isRendered;
    }
    public PageContext getPageContext() {
        return pageContext;
    }

    public void addBlockContent(String name,String content){
        blocks.addBlockContent(name, content);
    }

    public boolean containsBlock(String name){
        if(blocks.getBlockContents().containsKey(name)) {
            return true;
        }
        return false;
    }

    public String getBlockContent(String name){
        if(containsBlock(name)) {
           return blocks.getBlockContents().get(name).get(0);
        }
        return "";
    }

    @Override
    public int doStartTag() throws JspException {
        blocks = (Blocks) getPageContext().getRequest()
                            .getAttribute(Blocks.BLOCK_TAG_NAME);
        //System.out.println("with="+this.with+" blocks="+blocks);
        if(blocks == null) {
            blocks = new Blocks();
            getPageContext().getRequest()
                .setAttribute(Blocks.BLOCK_TAG_NAME,blocks);
        }

        if(this.with == null) {
            this.isRendered = true;
            return EVAL_BODY_INCLUDE;
        }
        return EVAL_BODY_BUFFERED;
    }

    public int doEndTag() throws JspException {
        if(with != null){
            try {
                getPageContext().include(this.with, false);
            } catch (ServletException ex) {
                Logger.getLogger(BlocksTag.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BlocksTag.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return EVAL_PAGE;
    }

}
