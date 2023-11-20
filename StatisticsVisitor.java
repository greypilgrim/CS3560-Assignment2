

import java.util.*;

/**
 *
 * @author jmcgr
 */
public interface StatisticsVisitor {
    public void visitUsers(User user);
    public void visitGroups(Group group);
    public void visitMessages(User user);
    public void selectMessages(ArrayList<String> tweetList);
}
