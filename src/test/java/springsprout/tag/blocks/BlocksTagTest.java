package springsprout.tag.blocks;

import java.util.List;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BlocksTagTest {

    private BlocksTag bst;
    private BlockTag bt;
    private Blocks blocks;
    private PageContext pc;

    @Before
    public void setUp() {
        blocks = new Blocks();
        pc = mock(PageContext.class);
        when(pc.getAttribute(Blocks.BLOCK_TAG_NAME,PageContext.REQUEST_SCOPE))
                .thenReturn(blocks);

        bst = new BlocksTag();
        bt = new BlockTag();
        bst.setPageContext(pc);
        bt.setPageContext(pc);
        bt.setParent(bst);
    }

    /**
     * Blocks에 Blocks을 읽어서, BodyContent을 등록한다. 
     */
    @Test
    public void blocksTagContainedBlockTags() throws JspException {
        bt.setName("test");

        BodyContent bc = mock(BodyContent.class);
        when(bc.getString()).thenReturn("test");
        bt.setBodyContent(bc);

        bst.doStartTag();
        bt.doStartTag();
        bt.doEndTag();

        Map<String,List<String>> blockContents = blocks.getBlockContents();

        assertTrue(blockContents.containsKey("test"));
    }

    /**
     * Blocks을 랜더링 한다.
     */
    @Test
    public void renderBlock() throws JspException {
        bt.setName("name");
        bt.setBodyContent(getBodyContent("test"));

        BodyContent bc = mock(BodyContent.class);
        when(pc.getOut()).thenReturn(bc);

        bst.doStartTag();
        bt.doStartTag();
        bt.doEndTag();
        bst.doEndTag();

    }

    public BodyContent getBodyContent(String content) {
        BodyContent bc = mock(BodyContent.class);
        when(bc.getString()).thenReturn(content);
        return bc;
    }
}
