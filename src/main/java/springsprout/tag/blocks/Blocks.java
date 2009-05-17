package springsprout.tag.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blocks {
    public static String BLOCK_TAG_NAME = "springsprout.blocks";

    public Map<String,List<String>> blockContents = new HashMap<String,List<String>>();

    public void addBlockContent(String name,String content) {
        if(blockContents.containsKey(name)) {
            blockContents.get(name).add(content);
        }
        else {
            List<String> contents = new ArrayList<String>();
            contents.add(content);
            blockContents.put(name, contents);
        }
    }

    public String getBlockLastContent(String name) {
        if(blockContents.containsKey(name)) {
            List<String> contents = blockContents.get(name);
            return contents != null ? contents.get(0) : null;
        }
        return null;
    }
    public Map<String,List<String>> getBlockContents() {
        return blockContents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.blockContents);
        return sb.toString();
    }
}
