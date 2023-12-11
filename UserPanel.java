
/**
 *
 * @author jmcgr
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class UserPanel implements ActionListener, TreeModelListener, TreeSelectionListener, ListSelectionListener{
    
    int buttonHeight = 80,
        buttonWidth = 250,
        panelHeight = 80,
        panelWidth = 250,
        treeHeight = 150,
        treeWidth = 500,
        topLabelHeight = 20,
        topLabelWidth = 500,
        frameDimensions = 500,
        largePanelWidth = 500;
        
    
    //the frame
    JFrame userPanel;
    
    //user ID panel
    JPanel userIdPanel;
    JLabel userIdLabel1;
    JLabel userIdLabel2;
    JTextArea userIdTextArea;
    String userID;
    
    //follow user button
    JButton followUserButton;    
    
    //currently following tree
    JTree currentlyFollowingTree;
    
    //tweet message panel
    JPanel tweetMessagePanel;
    JLabel tweetMessageLabel;
    JTextArea tweetMessageTextArea;
    String tweet;
    
    //post tweet button
    JButton postTweetButton;
    
    //new feed tree
    JTree newsFeedTree;
    
    //news feed list
    JList tweetList;
    DefaultListModel listModel;
    
    JPanel topPanel;
    JPanel midPanel;    

    DefaultMutableTreeNode following = new DefaultMutableTreeNode("Following");
    DefaultTreeModel treeModel = new DefaultTreeModel(following);
    DefaultMutableTreeNode tweets = new DefaultMutableTreeNode("Tweets");
    static List<String> messageFeed = new ArrayList<>();
    
    public User userCopy;
    
    protected UserPanel(String name, User user){
        
        userID = name;
        userCopy = user;
        
        //user ID panel
        userIdPanel = new JPanel();
        userIdLabel1 = new JLabel("User ID, Created at: " + this.userCopy.creationTime());
        userIdLabel2 = new JLabel("To follow a user, enter their name above");
        userIdTextArea = new JTextArea(userID, 1, 20);
        userIdPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        userIdPanel.setBackground(new Color(0,128,128));
        userIdPanel.add(userIdLabel1);
        userIdPanel.add(userIdTextArea);
        userIdPanel.add(userIdLabel2);
        userIdPanel.setBounds(0,0,panelWidth,panelHeight);  
        
        //follow user Button
        followUserButton = new JButton("Follow User");
        followUserButton.addActionListener(this);
        followUserButton.setBounds(200,0,buttonWidth,buttonHeight);
        followUserButton.setBackground(new Color(0,128,128));
        followUserButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        followUserButton.setFont(new Font("Comic Sans", Font.BOLD, 20));   
        
        
        //currently following tree                        
        treeModel.addTreeModelListener(this);
        currentlyFollowingTree = new JTree(treeModel);
        currentlyFollowingTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        currentlyFollowingTree.setEditable(true);
        currentlyFollowingTree.setShowsRootHandles(true);
        
        currentlyFollowingTree = new JTree(following);
        currentlyFollowingTree.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        currentlyFollowingTree.setBounds(0, 100, treeWidth, treeHeight);
        currentlyFollowingTree.setBackground(new Color(80,128,128));  
        JScrollPane treeView = new JScrollPane(currentlyFollowingTree);
        treeView.setBounds(0, 0, treeWidth, treeHeight);
        populateFollowerTree(following);
        
        //group ID panel
        tweetMessagePanel = new JPanel();
        tweetMessageLabel = new JLabel("Tweet Message");
        tweetMessageTextArea = new JTextArea(tweet, 1, 20);
        tweetMessagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tweetMessagePanel.add(tweetMessageLabel);
        tweetMessagePanel.add(tweetMessageTextArea);
        tweetMessagePanel.setBackground(new Color(0,128,128));  
        tweetMessagePanel.setBounds(0, 0, panelWidth, panelHeight);
        
        //post tweet button
        postTweetButton = new JButton("Post tweet");
        postTweetButton.addActionListener(this);
        postTweetButton.setBounds(200,0,buttonWidth,buttonHeight);
        postTweetButton.setBackground(new Color(0,128,128));
        postTweetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        postTweetButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        
        //news feed list
        listModel = new DefaultListModel();
        tweetList = new JList(listModel);
        tweetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tweetList.setLayoutOrientation(JList.VERTICAL);
        tweetList.setSelectedIndex(0);
        tweetList.addListSelectionListener(this);
        tweetList.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(tweetList);
        tweetList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tweetList.setBounds(0, 330, treeWidth, treeHeight);
        tweetList.setBackground(new Color(80,128,128));
        populateTweetTree();
        
        //set top panel
        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 20, largePanelWidth, panelHeight);
        topPanel.setLayout(new GridLayout(1,2));
        topPanel.add(userIdPanel);
        topPanel.add(followUserButton);
        
        //set mid panel
        JPanel midPanel = new JPanel();
        midPanel.setLayout(new GridLayout(1,2));  
        midPanel.setBounds(0,250,largePanelWidth,panelHeight);
        midPanel.add(tweetMessagePanel);
        midPanel.add(postTweetButton);
        
        JLabel topLabel = new JLabel("User View");
        topLabel.setForeground(Color.BLACK);
        topLabel.setBackground(Color.white);
        topLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topLabel.setBounds(0,0,topLabelWidth,topLabelHeight);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setOpaque(true);        
        
        //create user basic frame
        userPanel = new JFrame();
        userPanel.setLayout(null);
        userPanel.setResizable(false);
        userPanel.setTitle("User View Panel");
        userPanel.setSize(frameDimensions,frameDimensions);
        userPanel.setVisible(true);
        
        //populate frame
        userPanel.add(topLabel, BorderLayout.NORTH);
        userPanel.add(topPanel);
        userPanel.add(currentlyFollowingTree);
        userPanel.add(midPanel);
        //userPanel.add(newsFeedTree);
        userPanel.add(tweetList);
        
    }
    
    public void populateFollowerTree(DefaultMutableTreeNode seletedTreeNode){
        for(int i = 0; i < userCopy.getFollowers().size(); i++){
            seletedTreeNode.add(new DefaultMutableTreeNode(userCopy.getFollowers().get(i).toString()));
            //System.out.println(userCopy.getFollowers().get(i).toString());
        }
        ((DefaultTreeModel) currentlyFollowingTree.getModel()).reload(following);
    }
    public void populateTweetTree(){
        this.listModel.addAll(messageFeed);
    }
    
    public User getUser(String name){
        User newUser = new User(name);
        if(AdminPanel.mapOfUsers.containsValue(newUser)){
            //System.out.println("Got user object");
            int userInd = AdminPanel.listOfUsersAndGroups.indexOf(newUser);
            return newUser;
        }
        else{
            //System.out.println("Didn't get it");
            return null;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == followUserButton){
            
            if((!AdminPanel.mapOfUsers.containsKey(this.userIdTextArea.getText()))){
                JOptionPane.showMessageDialog(null, "That user does not exist");
            }
            else{
                TreePath path = currentlyFollowingTree.getSelectionPath();
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(this.userIdTextArea.getText(), false);
                this.following.add(newNode);
                ((DefaultTreeModel) currentlyFollowingTree.getModel()).reload(following);

                    userCopy.addObserver(new Follower(AdminPanel.mapOfUsers.get(userIdTextArea.getText())));
                
                JOptionPane.showMessageDialog(null, "You are now following " + this.userIdTextArea.getText());
            }

        }
        
        if(e.getSource() == postTweetButton){
            this.userCopy.makePost(this.tweetMessageTextArea.getText());
            this.userCopy.setUpdateTime(System.currentTimeMillis());
            AdminPanel.visitor.visitMessages(userCopy);
            listModel.addElement(this.userID + ", " + this.userCopy.updateTime() 
                    + ": " + this.tweetMessageTextArea.getText());
            this.messageFeed.add((this.userID + ", " + this.userCopy.updateTime()) 
                    + ": " + this.tweetMessageTextArea.getText());
        }
    }

    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        System.out.println();
    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        System.out.println();
    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        System.out.println();
    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {
        System.out.println();
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        System.out.println();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println();
    }
    
}
