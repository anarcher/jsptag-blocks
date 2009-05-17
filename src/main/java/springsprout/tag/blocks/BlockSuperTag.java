package springsprout.tag.blocks;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * It is not yet worked .T_T
 */
public class BlockSuperTag extends TagSupport {

    private BlockTag bt;

    @Override
    public int doStartTag() throws JspException {
        bt = (BlockTag)findAncestorWithClass(this, BlockTag.class);
        if(bt == null) {
            throw new JspException("block:super tag must be contained block tag.");
        }
        return SKIP_BODY;
    }
    
    @Override
    public int doEndTag() throws JspException {
        if(bt == null) return EVAL_PAGE;
        String blockTagName = bt.getName();
        Blocks blocks = (Blocks) pageContext.getRequest().getAttribute(Blocks.BLOCK_TAG_NAME);
        String content = blocks.getBlockLastContent(blockTagName);
        if(content == null) return EVAL_PAGE;

        try {
            pageContext.getOut().write(content);
        } catch (IOException ex) {
            Logger.getLogger(BlockSuperTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EVAL_PAGE;
    }
}
