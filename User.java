
import java.util.*;
/**
 *
 * @author jmcgr
 */
public class User implements Observable, Composite, ComponentElement {
    
    private ArrayList<Observer> followers = new ArrayList<Observer>();
    private ArrayList<String> posts = new ArrayList<String>();
    private String post;
    private String name;

    
    public User(String name){
        this.name = name;
    }
    
    public void makePost(String newPost){
        this.post = newPost;
        this.posts.add(this.getPost());
        notifyObservers();
    }
    
    public String getPost(){
        return this.post;
    }
    
    @Override
    public String getID(){
        return this.name;
    }
    
    public ArrayList getFollowers(){
        return this.followers;
    }
    
    public ArrayList getPosts(){
        return this.posts;
    }
    
    public void printFollowers(){
        System.out.println(this.getID() + "'s Followers:");
        for(int i = 0; i < this.getFollowers().size(); i++){
            System.out.println(this.getFollowers().get(i).toString());            
        }
    }
    
    public void printPosts(){
        System.out.println(this.getID() + "'s Posts:");
        for(int i = 0; i < this.getPosts().size(); i++){
            System.out.println(this.getPosts().get(i));
        }
    }
    
    @Override
    public void addObserver(Observer observer) {
        if(!this.followers.contains(observer)){
            this.followers.add(observer);
        }
        else{
            System.out.println(observer.toString() + " already follows " + this.getID());
        }
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : followers){
            observer.update(this);
        }
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visitUsers(this);
    }
    
}
