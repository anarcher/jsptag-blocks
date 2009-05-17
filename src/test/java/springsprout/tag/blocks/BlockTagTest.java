package springsprout.tag.blocks;

import java.util.List;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BlockTagTest {

    /**
     * BlockTag의 BodyContent을 Blocks에 추가한다.
     */
    @Test
    public void blockTagAddBodyContentToBlocks() throws JspException {
        PageContext pc = mock(PageContext.class);
        BodyContent bc = mock(BodyContent.class);
        Blocks blocks = new Blocks();

        when(pc.getAttribute(Blocks.BLOCK_TAG_NAME,PageContext.REQUEST_SCOPE))
                .thenReturn(blocks);
        when(bc.getString()).thenReturn("hello");

        BlocksTag bst = new BlocksTag();
        bst.setPageContext(pc);
        bst.doStartTag();

        BlockTag blockTag = new BlockTag();
        blockTag.setName("test");
        blockTag.setParent(bst);
        blockTag.setPageContext(pc);
        blockTag.setBodyContent(bc);

        blockTag.doStartTag();
        int retValue = blockTag.doEndTag();
        assertTrue(blocks.getBlockContents() != null);
        for (Map.Entry<String,List<String>> content : blocks.getBlockContents().entrySet()) {
            assertTrue(content.getValue().size() > 0);
        }
    }
}