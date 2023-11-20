

import java.util.*;

/**
 *
 * @author jmcgr
 */
public class ComponentVisitor implements StatisticsVisitor{
    
    private int userCount;
    private int groupCount = 1;
    private int messageCount;
    private int posMessageCount;
    private double posMessagePercentage;

    @Override
    public void visitUsers(User user) {
        this.setUserTotal(this.getUserTotal() + 1);
    }

    @Override
    public void visitGroups(Group group) {
        this.setGroupTotal(this.getGroupTotal() + 1);
    }

    @Override
    public void visitMessages(User user) {
        this.setMessageTotal(this.getMessageTotal() + 1);
    }

    @Override
    public void selectMessages(ArrayList<String> tweetList) {
        for(String str : tweetList){
            if(str.contains("good")){
                this.setPosMessageTotal(this.getPosMessageTotal() + 1);
            }
        }
    }
    
    public int getUserTotal(){
        return this.userCount;
    }
    
    public void setUserTotal(int count){
        this.userCount = count;
    }
    
    public int getGroupTotal(){
        return this.groupCount;
    }
    
    public void setGroupTotal(int count){
        this.groupCount = count;
    }
    
    public int getMessageTotal(){
        return this.messageCount;
    }
    
    public void setMessageTotal(int count){
        this.messageCount = count;
    }
    
    public int getPosMessageTotal(){
        return this.posMessageCount;
    }
    public void setPosMessageTotal(int count){
        this.posMessageCount = count;
    }
    
    public void setPosMessPercentage(int totalM, int posM){
        double percentage = (double)posM / (double)totalM;
        this.posMessagePercentage = percentage;
    }
    
    public double getPosMessPercentage(){
        return this.posMessagePercentage;
    }
    
}
