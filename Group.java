
import java.util.*;
/**
 *
 * @author jmcgr
 */
public class Group implements Composite, ComponentElement{
    private List<Composite> listOfUserGroups = new ArrayList<>();
    
    private String groupName;
    
    public Group(String newID){
        setID(newID);
    }
    
    public Composite getUserGroup(int index){
        return (Composite)listOfUserGroups.get(index);
    }
    
    public List getList(){
        return listOfUserGroups;
    }

    public void add(Composite newUserGroup) {
        listOfUserGroups.add(newUserGroup); 
    }
    
    public void setID(String newID) {
        groupName = newID; 
    }

    @Override
    public String getID() {
        return groupName; 
    }
    
    public String toString(){
      String newString = "Group ID: " + this.getID();
      return newString;
    }    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Group)) {
            return false;
        }

        Group group = (Group) obj;

        return group.groupName.equals(groupName);
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        visitor.visitGroups(this);
    }
}
