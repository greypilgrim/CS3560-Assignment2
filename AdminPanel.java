
/**
 *
 * @author jmcgr
 */
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class AdminPanel implements ActionListener, TreeModelListener, TreeSelectionListener{
    
    public static List<Composite> listOfUsersAndGroups = new ArrayList<>();
    public static Map<String, User> mapOfUsers = new HashMap<>();
    public static Map<String, Group> mapOfGroups = new HashMap<>();
    static ComponentVisitor visitor = new ComponentVisitor();
    
    int panelHeight = 50, 
        panelWidth = 300, 
        buttonHeight = 50, 
        buttonWidth = 300,
        longButtonWidth = 600,
        topLabelHeight = 20,
        topLabelWidth = 600,
        compositePanelHeight = 100,
        compositePanelWidth = 600,
        treeHeight = 400,
        treeWidth = 300;
    
    JFrame adminPanel;
    
    JTree groupTree;    
    
    //top right panel group
    JPanel topRightGroupPanel;
    
    //user ID panel
    JPanel userIdPanel;
    JLabel userIdLabel;
    JTextArea userIdTextArea;
    JLabel userDirectionLabel;
    
    //add user Button
    JButton addUserButton;
    
    //group ID panel
    JPanel groupIdPanel;
    JLabel groupIdLabel;
    JTextArea groupIdTextArea;
    JLabel groupDirectionLabel;
    
    //add group button
    JButton addGroupButton;
    
    //open user view button
    JButton oUserViewButton;
    
    //show user total button
    JButton sUserTotalButton;
    
    //show group total button
    JButton sGroupTotalButton;
    
    //show messages total button
    JButton sMessagesTotalButton;
    
    //show positive percentage button
    JButton sPositivePercentageButton;
    
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
    DefaultTreeModel treeModel = new DefaultTreeModel(root);
    
    protected AdminPanel(){
        
        //group tree                        
        treeModel.addTreeModelListener(this);
        groupTree = new JTree(treeModel);
        groupTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        groupTree.setEditable(true);
        groupTree.setShowsRootHandles(true);
        
        groupTree.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        groupTree.setBounds(0, 0, treeWidth, treeHeight);
        groupTree.setBackground(new Color(80,128,128));
        JScrollPane treeView = new JScrollPane(groupTree);
        treeView.setBounds(0, 0, treeWidth, treeHeight);
        
        
        //user ID panel
        userIdPanel = new JPanel();
        userIdLabel = new JLabel("User ID");
        userDirectionLabel = new JLabel("Enter new ID and click 'Add User'");
        userIdTextArea = new JTextArea(1,20);
        userIdPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        userIdPanel.setBackground(new Color(0,128,128));
        userIdPanel.add(userIdLabel);
        userIdPanel.add(userIdTextArea);
        userIdPanel.add(userDirectionLabel);
        userIdPanel.setBounds(300,20,panelWidth,panelHeight);

        //add user Button
        addUserButton = new JButton("Add User");
        addUserButton.addActionListener(this);
        addUserButton.setBounds(600,20,buttonWidth,buttonHeight);
        addUserButton.setBackground(new Color(0,128,128));
        addUserButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addUserButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        
        //group ID panel
        groupIdPanel = new JPanel();
        groupIdLabel = new JLabel("Group ID");
        groupDirectionLabel = new JLabel("Enter new ID and click 'Add Group'");
        groupIdTextArea = new JTextArea(1,20);
        groupIdPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        groupIdPanel.add(groupIdLabel);
        groupIdPanel.add(groupIdTextArea);
        groupIdPanel.add(groupDirectionLabel);
        groupIdPanel.setBackground(new Color(0,128,128));
        
        //add group button
        addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener(this);
        addGroupButton.setBounds(600,20,buttonWidth,buttonHeight);
        addGroupButton.setBackground(new Color(0,128,128));
        addGroupButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addGroupButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        
        //open user view button
        oUserViewButton = new JButton("Open User View");
        oUserViewButton.addActionListener(this);
        oUserViewButton.setBounds(300,120,longButtonWidth,buttonHeight);
        oUserViewButton.setBackground(new Color(0,128,128));
        oUserViewButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        oUserViewButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        
        //show user total button
        sUserTotalButton = new JButton("Show User Total");
        sUserTotalButton.addActionListener(this);
        sUserTotalButton.setBounds(300,280,buttonWidth,buttonHeight);
        sUserTotalButton.setBackground(new Color(0,128,128));
        sUserTotalButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sUserTotalButton.setFont(new Font("Comic Sans", Font.BOLD, 20));    
        
        //show group total button
        sGroupTotalButton = new JButton("Show Group Total");
        sGroupTotalButton.addActionListener(this);
        sGroupTotalButton.setBounds(600,280,buttonWidth,buttonHeight);
        sGroupTotalButton.setBackground(new Color(0,128,128));
        sGroupTotalButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sGroupTotalButton.setFont(new Font("Comic Sans", Font.BOLD, 20));  
        
        //show messages total button
        sMessagesTotalButton = new JButton("Show Messages Total");
        sMessagesTotalButton.addActionListener(this);
        sMessagesTotalButton.setBounds(300,330,buttonWidth,buttonHeight);
        sMessagesTotalButton.setBackground(new Color(0,128,128));
        sMessagesTotalButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sMessagesTotalButton.setFont(new Font("Comic Sans", Font.BOLD, 20)); 
        
        //show positive percentage button
        sPositivePercentageButton = new JButton("Show Positive Percentage");
        sPositivePercentageButton.addActionListener(this);
        sPositivePercentageButton.setBounds(600,330,buttonWidth,buttonHeight);
        sPositivePercentageButton.setBackground(new Color(0,128,128));
        sPositivePercentageButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sPositivePercentageButton.setFont(new Font("Comic Sans", Font.BOLD, 20));;        
        
        
        //set top right corner panel
        topRightGroupPanel = new JPanel();
        topRightGroupPanel.setLayout(new GridLayout(2,2));
        topRightGroupPanel.setBounds(300,20,compositePanelWidth,compositePanelHeight);
        topRightGroupPanel.add(userIdPanel);
        topRightGroupPanel.add(addUserButton);
        topRightGroupPanel.add(groupIdPanel);
        topRightGroupPanel.add(addGroupButton);
        
        //set bottom right corner panel
        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new GridLayout(2,2));
        bottomRightPanel.setBounds(300,263,compositePanelWidth,compositePanelHeight);
        bottomRightPanel.add(sUserTotalButton);
        bottomRightPanel.add(sGroupTotalButton);
        bottomRightPanel.add(sMessagesTotalButton);
        bottomRightPanel.add(sPositivePercentageButton);
        
        JLabel topLabel = new JLabel("Admin Operations");
        topLabel.setForeground(Color.BLACK);
        topLabel.setBackground(Color.white);
        topLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topLabel.setBounds(300,0,topLabelWidth,topLabelHeight);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setOpaque(true);
        
        //create admin basic frame
        adminPanel = new JFrame();
        adminPanel.setLayout(null);
        adminPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminPanel.setResizable(false);
        adminPanel.setTitle("Admin Control Panel");
        adminPanel.setSize(900,400);
        adminPanel.getContentPane().setBackground(new Color(0x123456));
        adminPanel.setVisible(true);
        
        adminPanel.add(treeView);
        adminPanel.add(topLabel);
        adminPanel.add(topRightGroupPanel);
        adminPanel.add(oUserViewButton);
        adminPanel.add(bottomRightPanel);
    }
    
    public List getUGList(){
        return this.listOfUsersAndGroups;
    }
    
    
    //SINGLETON
    protected static AdminPanel instance = null;
    
    public static AdminPanel getInstance(){
        if(instance == null){
            synchronized(AdminPanel.class){
                if(instance == null){
                    instance = new AdminPanel();
                }
            }
        }
        return instance;
    }
    
    public double posMessages(){
        double percentage = 0;
        double pos = 0;
        for(int i = 0; i < UserPanel.messageFeed.size(); i++){
            if(UserPanel.messageFeed.get(i).contains("good")){
                pos++;
            }
        }
        percentage = (pos / UserPanel.messageFeed.size()) * 100;
        
        return percentage;
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        //add user button
        if(e.getSource() == addUserButton){
           
            User newUser = new User(userIdTextArea.getText());
            
            if(this.mapOfUsers.containsKey(newUser.getID())){
                JOptionPane.showMessageDialog(null, "That user already exists");                
            }
            else{
                TreePath path = groupTree.getSelectionPath();
                if(path != null){
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                    if(selectedNode.getAllowsChildren() == false){
                        JOptionPane.showMessageDialog(null, "Users cannot contain groups or other users. "
                                + "Your selection has been added to \"Root\".");
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(this.userIdTextArea.getText(), false);
                        this.root.add(newNode);
                        ((DefaultTreeModel) groupTree.getModel()).reload(root); 
                        this.mapOfUsers.put(userIdTextArea.getText(), newUser);
                        newUser.accept(visitor);
                    }            
                    else{
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(this.userIdTextArea.getText(), false);
                        selectedNode.add(newNode);
                        ((DefaultTreeModel) groupTree.getModel()).reload(selectedNode);
                        this.mapOfUsers.put(userIdTextArea.getText(), newUser);
                        newUser.accept(visitor);
                    }                
                }
                else if(path == null){
                    DefaultMutableTreeNode selectedNode = root;
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(this.userIdTextArea.getText(), false);
                    selectedNode.add(newNode);
                    ((DefaultTreeModel) groupTree.getModel()).reload(selectedNode);
                    this.mapOfUsers.put(userIdTextArea.getText(), newUser);
                    newUser.accept(visitor);
                    JOptionPane.showMessageDialog(null, "No Group has been selected. " 
                            + newUser.getID() + " has been added to \"Root\".");
                }
            }
            this.userIdTextArea.setText("");
        }
        
        //add group button
        if(e.getSource() == addGroupButton){          
            Group newGroup = new Group(groupIdTextArea.getText());
            if(this.mapOfGroups.containsKey(newGroup.getID())){
                JOptionPane.showMessageDialog(null, "That group already exists");
            }
            else{
                TreePath path = groupTree.getSelectionPath();
                if(path != null){
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                    if(selectedNode.getAllowsChildren() == false){
                        JOptionPane.showMessageDialog(null, "Users cannot contain groups or other users. Your selection has been added to Root");
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(this.groupIdTextArea.getText(), true);
                        this.root.add(newNode);
                        ((DefaultTreeModel) groupTree.getModel()).reload(root);
                        this.mapOfGroups.put(newGroup.getID(), newGroup);
                        visitor.visitGroups(newGroup);
                    }
                    else if(selectedNode.getAllowsChildren() == true){
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(this.groupIdTextArea.getText(), true);
                        selectedNode.add(newNode);
                        ((DefaultTreeModel) groupTree.getModel()).reload(selectedNode); 
                        this.mapOfGroups.put(newGroup.getID(), newGroup);
                        visitor.visitGroups(newGroup);
                    }       
                }
                else if(path == null){
                    DefaultMutableTreeNode selectedNode = root;
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(this.groupIdTextArea.getText(), true);
                    selectedNode.add(newNode);
                    ((DefaultTreeModel) groupTree.getModel()).reload(selectedNode); 
                    this.mapOfGroups.put(newGroup.getID(), newGroup);
                    visitor.visitGroups(newGroup);
                    JOptionPane.showMessageDialog(null, "No existing group has been selected. Your new group has been added to \"Root\"");
                }                
            }
            this.groupIdTextArea.setText("");
        }
        
        //open user view button
        if(e.getSource() == oUserViewButton && groupTree.getSelectionPath() != null){
            TreePath path = groupTree.getSelectionPath();
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
            if(selectedNode.getAllowsChildren() == true){
                JOptionPane.showMessageDialog(null, "This is not a user.");
            }
            else{
                new UserPanel(selectedNode.toString(), this.mapOfUsers.get(selectedNode.toString()));
            }            
        }
        
        //show user total button
        if(e.getSource() == sUserTotalButton){
            JOptionPane.showMessageDialog(null, "Total Users: " + this.visitor.getUserTotal());
        }
        
        //show group total button
        if(e.getSource() == sGroupTotalButton){
            JOptionPane.showMessageDialog(null, "Total Groups: " + this.visitor.getGroupTotal());
        }
        
        //show messages total button
        if(e.getSource() == sMessagesTotalButton){
            JOptionPane.showMessageDialog(null, "Total Messages: " + this.visitor.getMessageTotal());
        }
        
        //show positive percentage button
        if(e.getSource() == sPositivePercentageButton){
            JOptionPane.showMessageDialog(null, "Percentage of Positive Messages: " + this.posMessages() + "%");
        }
    }
    
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)groupTree.getLastSelectedPathComponent();
        
        if(node == null){
            return;
        }
        
        Object nodeInfo = node.getUserObject();
        if (node.isRoot()) {
            System.out.println("Hello");
        } 
        else {
            System.out.println(nodeInfo.toString());
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
}
