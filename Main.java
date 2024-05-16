
package network_project.gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import network_project.Database;
import network_project.Project;
import network_project.User;
import pkg_client.Client;
import pkg_server.SClient;

import pkg_server.Server;

/**
 * tarik.alrayan
 * 2121221366
 */

public class Main extends javax.swing.JFrame {

    User u;
    Client client;
    private String key;
    
    // the ip and port values accordıng to the server
    String ip = "127.0.0.1";
    int port = Integer.parseInt("8000");
    
    

    public static DefaultListModel <String>lst_online_clients_model = new DefaultListModel<>();// list model to handel the online clients 
    public static DefaultListModel lst_messagesFromServer_model = new DefaultListModel();// list model to handel the messages
    public static DefaultListModel<String> listModel = new DefaultListModel<>(); //list messages to handel the project members

    /**
     * Creates new form Main
     */
    public Main(User u) throws SQLException {
        initComponents();
        this.u = u;
        updateProjects();

        list_msg.setModel(lst_messagesFromServer_model);
        list_online.setModel(lst_online_clients_model);
        
        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_projects = new javax.swing.JList<>();
        btn_refresh = new javax.swing.JButton();
        btn_connect = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_members = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        btn_create_project = new javax.swing.JButton();
        btn_join_project = new javax.swing.JButton();
        btn_key_list = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_project_name = new javax.swing.JTextField();
        txt_msg = new javax.swing.JTextField();
        btn_send = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_state = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        list_msg = new javax.swing.JList<>();
        btn_Leave = new javax.swing.JButton();
        btn_send_file = new javax.swing.JButton();
        emo_smile = new javax.swing.JButton();
        emo_smile1 = new javax.swing.JButton();
        emo_smile2 = new javax.swing.JButton();
        emo_smile3 = new javax.swing.JButton();
        emo_smile4 = new javax.swing.JButton();
        emo_smile5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        list_online = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 700));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 700));

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setMaximumSize(new java.awt.Dimension(257, 645));
        jPanel3.setMinimumSize(new java.awt.Dimension(257, 645));

        jScrollPane1.setViewportView(list_projects);

        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        btn_connect.setText("Connect");
        btn_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btn_refresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btn_connect)
                .addGap(38, 38, 38))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_connect)
                    .addComponent(btn_refresh))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Projects", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        list_members.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(list_members);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Members", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));

        btn_create_project.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btn_create_project.setText("Create Project");
        btn_create_project.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_create_projectActionPerformed(evt);
            }
        });

        btn_join_project.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btn_join_project.setText("Join Project");
        btn_join_project.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_join_projectActionPerformed(evt);
            }
        });

        btn_key_list.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        btn_key_list.setText("Key List");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_create_project, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_join_project, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(btn_key_list, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(btn_create_project, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addComponent(btn_join_project, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(btn_key_list, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Menu", jPanel5);

        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setText("State:");

        txt_project_name.setEditable(false);

        btn_send.setText("send");
        btn_send.setEnabled(false);
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setText("Project :");

        txt_state.setEditable(false);

        list_msg.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(list_msg);

        btn_Leave.setText("Leave");
        btn_Leave.setEnabled(false);
        btn_Leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LeaveActionPerformed(evt);
            }
        });

        btn_send_file.setText("send file");
        btn_send_file.setEnabled(false);
        btn_send_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_send_fileActionPerformed(evt);
            }
        });

        emo_smile.setText("😊");
        emo_smile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emo_smileActionPerformed(evt);
            }
        });

        emo_smile1.setText("😀");
        emo_smile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emo_smile1ActionPerformed(evt);
            }
        });

        emo_smile2.setText(" 🔥 ");
        emo_smile2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emo_smile2ActionPerformed(evt);
            }
        });

        emo_smile3.setText("💗");
        emo_smile3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emo_smile3ActionPerformed(evt);
            }
        });

        emo_smile4.setText("👍");
        emo_smile4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emo_smile4ActionPerformed(evt);
            }
        });

        emo_smile5.setText("👎");
        emo_smile5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emo_smile5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(txt_project_name, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(68, 68, 68)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_state, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_Leave, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(emo_smile)
                                        .addGap(18, 18, 18)
                                        .addComponent(emo_smile1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(emo_smile2)
                                        .addGap(27, 27, 27)
                                        .addComponent(emo_smile3)))
                                .addGap(26, 26, 26)
                                .addComponent(emo_smile4)
                                .addGap(26, 26, 26)
                                .addComponent(emo_smile5)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_send_file, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_send, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_project_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_state, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_send, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(txt_msg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_send_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emo_smile)
                            .addComponent(emo_smile1)
                            .addComponent(emo_smile2)
                            .addComponent(emo_smile3)
                            .addComponent(emo_smile4)
                            .addComponent(emo_smile5))
                        .addGap(18, 18, 18)
                        .addComponent(btn_Leave)))
                .addGap(12, 12, 12))
        );

        jScrollPane3.setViewportView(list_online);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel4.setText("online");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(202, 202, 202))))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_create_projectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_create_projectActionPerformed
        //open the create project frame to create new project 
        
        Create_Project cp = new Create_Project(u);
        cp.setVisible(true);

    }//GEN-LAST:event_btn_create_projectActionPerformed

    private void btn_join_projectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_join_projectActionPerformed
        // open the join project frame to join new project

        Join_project jp = new Join_project(u);
        jp.setVisible(true);


    }//GEN-LAST:event_btn_join_projectActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        try {
            updateProjects();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

        try {
            updateProjects();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btn_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connectActionPerformed
        // handel the connection to server
        
        String project[] = list_projects.getSelectedValue().split(",");
        key = project[0];
        String projectName = project[1];

        
        client = new Client(ip, port,u.getName());
        client.ConnectToServer(key);

        txt_project_name.setText(projectName);
        txt_state.setText("connected");

        btn_send.setEnabled(true);
        btn_send_file.setEnabled(true);
        btn_Leave.setEnabled(true);
        btn_connect.setEnabled(false);

        
        // gets the project members list 
        try {
            
        ArrayList<String> members = Database.getMemebersopjects(key);
        
        for (String name: members) {
            listModel.addElement(name);
        }

        list_members.setModel(listModel);
        
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_btn_connectActionPerformed

    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed
        // handel sending messages to server
        
        String message = txt_msg.getText();
        client.SendMessage(message);
        txt_msg.setText("");


    }//GEN-LAST:event_btn_sendActionPerformed

    private void btn_LeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LeaveActionPerformed
        // handel the discconnect from server
        client.disconnect();
        
        btn_send.setEnabled(false);
        btn_send_file.setEnabled(false);
        btn_Leave.setEnabled(false);
        btn_connect.setEnabled(true);
        
        lst_messagesFromServer_model.clear();
        
        txt_project_name.setText("");
        txt_msg.setText("");
        txt_state.setText("");
        
        listModel.clear();
        lst_online_clients_model.clear();
    }//GEN-LAST:event_btn_LeaveActionPerformed

    private void btn_send_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_send_fileActionPerformed
       
        JFileChooser fileChooser = new JFileChooser();

        // Set file chooser to select only files
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Optionally, set a file filter to specify the allowed file types
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.setFileFilter(filter);

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(null);

        // Check if a file was selected
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            java.io.File selectedFile = fileChooser.getSelectedFile();

            // Get the file path
            String filePath = selectedFile.getAbsolutePath();

            client.sendFile(filePath);
        }
    }//GEN-LAST:event_btn_send_fileActionPerformed

    private void emo_smileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emo_smileActionPerformed
        // handel emoji message
        String old =txt_msg.getText();
        txt_msg.setText(old +" "+ emo_smile.getText());
    }//GEN-LAST:event_emo_smileActionPerformed

    private void emo_smile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emo_smile1ActionPerformed
        // handel emoji message
         String old =txt_msg.getText();
        txt_msg.setText(old +" "+ emo_smile1.getText());
    }//GEN-LAST:event_emo_smile1ActionPerformed

    private void emo_smile2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emo_smile2ActionPerformed
        // handel emoji message
         String old =txt_msg.getText();
        txt_msg.setText(old +" "+ emo_smile2.getText());
    }//GEN-LAST:event_emo_smile2ActionPerformed

    private void emo_smile3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emo_smile3ActionPerformed
        // handel emoji message
         String old =txt_msg.getText();
        txt_msg.setText(old +" "+ emo_smile3.getText());
    }//GEN-LAST:event_emo_smile3ActionPerformed

    private void emo_smile4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emo_smile4ActionPerformed
        // handel emoji message
         String old =txt_msg.getText();
        txt_msg.setText(old +" "+ emo_smile4.getText());
    }//GEN-LAST:event_emo_smile4ActionPerformed

    private void emo_smile5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emo_smile5ActionPerformed
        // handel emoji message
         String old =txt_msg.getText();
        txt_msg.setText(old +" "+ emo_smile5.getText());
    }//GEN-LAST:event_emo_smile5ActionPerformed

    
    
    // handel updating the project list whenever joined new project
    private void updateProjects() throws SQLException {
        ArrayList<Project> arr = Database.getProjectsopjects(u.getName());
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Project item : arr) {
            String namekey = item.getKey() + " , " + item.getName();
            listModel.addElement(namekey);
        }

        list_projects.setModel(listModel);

    }
    
    

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Leave;
    private javax.swing.JButton btn_connect;
    private javax.swing.JButton btn_create_project;
    private javax.swing.JButton btn_join_project;
    private javax.swing.JButton btn_key_list;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_send;
    private javax.swing.JButton btn_send_file;
    private javax.swing.JButton emo_smile;
    private javax.swing.JButton emo_smile1;
    private javax.swing.JButton emo_smile2;
    private javax.swing.JButton emo_smile3;
    private javax.swing.JButton emo_smile4;
    private javax.swing.JButton emo_smile5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JList<String> list_members;
    private javax.swing.JList<String> list_msg;
    private javax.swing.JList<String> list_online;
    private javax.swing.JList<String> list_projects;
    private javax.swing.JTextField txt_msg;
    private javax.swing.JTextField txt_project_name;
    private javax.swing.JTextField txt_state;
    // End of variables declaration//GEN-END:variables
}
