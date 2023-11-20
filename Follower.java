
import java.util.*;
/**
 *
 * @author jmcgr
 */
public class Follower implements Observer {
    
    private List<String> followingPosts = new ArrayList<>();
    
    private String name;
    
    public Follower(User thisUser){
        this.name = thisUser.getID();
        getOwnPosts(thisUser);
    }
    
    public String getFollowerName(){
        return this.name;
    }

    public String toString(){
        String newString = this.getFollowerName();
        return newString;
    }
    
    public List getFollowingPosts(){
        return this.followingPosts;
    }
    
    public void getOwnPosts(User user){
        for(int i = 0; i < user.getPosts().size(); i++){
            this.getFollowingPosts().add(user.getPosts().get(i));
        }
    }
    
    @Override
    public void update(User user) {
        System.out.println(this.name + " received a new post: " + user.getPost());
    }
    
}
