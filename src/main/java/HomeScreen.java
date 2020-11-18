package airmanagement;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class HomeScreen extends javax.swing.JFrame {
    private CentralManager centralManager;
    private Map<String, Map<String, Integer>> data = new HashMap<String, Map<String, Integer>>();
    private Map<String, String> levels = new HashMap<String, String>();
    private List<Place> places = new ArrayList<Place>();
    private List<String> recipients = new ArrayList<String>();
    
    private void runWithInterval(long millis) {
        Runnable task = () -> {
            try {
                while (true) {
                    Thread.sleep(millis);
                    add();
                }
            } catch(InterruptedException e) {
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
    
    public HomeScreen() {
        initComponents();
        CentralManager cc3 = new CentralManager();
        this.centralManager = cc3;
        
        recipients.add("technology.shreyas@gmail.com");
        recipients.add("iit2019117@iiita.ac.in");
        
        places = this.centralManager.getPlaces();
        place0.setText(places.get(0).name);
        place1.setText(places.get(1).name);
        place2.setText(places.get(2).name);
        
        for(int i=0; i< recipients.size() ; i++){
            recipientsList.append(recipients.get(i) + "\n");
        }
        add();
        runWithInterval(4000);
        homescreen.setVisible(true);
        displaylogs.setVisible(false);
    }
    
//    public void retrieveData(){
//        CentralManager CC3 = new CentralManager();
//        places = new String[4];
//        for (Map.Entry<String, Map<String, Integer>> place : CC3.getData().entrySet()) {
//            String key = place.getKey();
//            for (Map.Entry<String, Integer> sensor : place.getValue().entrySet()) { 
//                String sensorName = sensor.getKey();
//                double sensorValue = sensor.getValue();
//            }
//        }
//    }
    
    
    public void add(){
        boolean alert = false;
        String alertPlace = "";
        data = this.centralManager.getData(); 
        levels = this.centralManager.checkLevels(); 
        for(Map.Entry i : data.entrySet()){
            String place = (String)i.getKey();
            Map <String, Integer> sensor = (HashMap)i.getValue();
            for(Map.Entry j : sensor.entrySet()){
                String gas = (String)j.getKey();
                int value = (Integer)j.getValue();
                
                switch(gas){
                    case "no2": 
                        if(place.equals(places.get(0).name)) {
                            NO2_1.setText(value + "");
                        }
                                
                        if(place.equals(places.get(1).name)) {
                            NO2_2.setText(value + "");
                        }
                        
                        if(place.equals(places.get(2).name)) {
                            NO2_3.setText(value + "");
                        }      
                        break;
                    case "o3":
                        if(place.equals(places.get(0).name)) {
                            O3_1.setText(value + "");
                        }
                                
                        if(place.equals(places.get(1).name)) {
                            O3_2.setText(value + "");
                        }
                        
                        if(place.equals(places.get(2).name)) {
                            O3_3.setText(value + "");
                        }      
                        break;
                    case "pm10":
                        if(place.equals(places.get(0).name)) {
                            PM10_1.setText(value + "");
                        }
                                
                        if(place.equals(places.get(1).name)) {
                            PM10_2.setText(value + "");
                        }
                        
                        if(place.equals(places.get(2).name)) {
                            PM10_3.setText(value + "");
                        }      
                        break;
                    case "pm2.5":
                        if(place.equals(places.get(0).name)) {
                            PM25_1.setText(value + "");
                        }
                                
                        if(place.equals(places.get(1).name)) {
                            PM25_2.setText(value + "");
                        }
                        
                        if(place.equals(places.get(2).name)) {
                            PM25_3.setText(value + "");
                        }      
                        break;
                    case "temp": 
                        if(place.equals(places.get(0).name)) {
                            temp_1.setText(value + "");
                        }
                                
                        if(place.equals(places.get(1).name)) {
                            temp_2.setText(value + "");
                        }
                        
                        if(place.equals(places.get(2).name)) {
                            temp_3.setText(value + "");
                        }      
                        break;
                    case "hum": 
                        if(place.equals(places.get(0).name)) {
                            hum_1.setText(value + "");
                        }
                                
                        if(place.equals(places.get(1).name)) {
                            hum_2.setText(value + "");
                        }
                        
                        if(place.equals(places.get(2).name)) {
                            hum_3.setText(value + "");
                        }      
                        break;        
                }
                
                for(Map.Entry k : levels.entrySet()){
                    String levelsPlace = (String)k.getKey();
                    String aqi = (String)k.getValue();
                    if(levelsPlace.equals(places.get(0).name)){
                        place0aqi.setText(aqi);
                            switch(aqi){
                                case "Very Low":
                                    place0aqi.setForeground(Color.green);
                                    break;
                                case "Low":
                                    place0aqi.setForeground(new Color(89,170,25));
                                    break;
                                case "Medium":
                                    place0aqi.setForeground(new Color(171,126,40));
                                    break;
                                case "High":
                                    if(send.isSelected() == true){
                                        alert = true;
                                        alertPlace = levelsPlace;
                                    }
                                    place0aqi.setForeground(Color.red);
                                    break;
                                case "Very High":
                                    if(send.isSelected() == true){
                                        alert = true;
                                        alertPlace = levelsPlace;
                                    }
                                    place0aqi.setForeground(new Color(147,75,23));
                                    break;
                            }
                    }
                    
                    if(levelsPlace.equals(places.get(1).name)){
                        place1aqi.setText(aqi);
                            switch(aqi){
                                case "Very Low":
                                    place1aqi.setForeground(Color.green);
                                    break;
                                case "Low":
                                    place1aqi.setForeground(new Color(89,170,25));
                                    break;
                                case "Medium":
                                    place1aqi.setForeground(new Color(171,126,40));
                                    break;
                                case "High":
                                    if(send.isSelected() == true){
                                        alert = true;
                                        alertPlace = levelsPlace;
                                    }
                                    place1aqi.setForeground(Color.red);
                                    break;
                                case "Very High":
                                    if(send.isSelected() == true){
                                        alert = true;
                                        alertPlace = levelsPlace;
                                    }
                                    place1aqi.setForeground(new Color(147,75,23));
                                    break;
                            }
                    }
                    
                    if(levelsPlace.equals(places.get(2).name)){
                        place2aqi.setText(aqi);
                            switch(aqi){
                                case "Very Low":
                                    place2aqi.setForeground(Color.green);
                                    break;
                                case "Low":
                                    place2aqi.setForeground(new Color(89,170,25));
                                    break;
                                case "Medium":
                                    place2aqi.setForeground(new Color(171,126,40));
                                    break;
                                case "High":
                                    if(send.isSelected() == true){
                                        alert = true;
                                        alertPlace = levelsPlace;
                                    }
                                    place2aqi.setForeground(Color.red);
                                    break;
                                case "Very High":
                                    if(send.isSelected() == true){
                                        alert = true;
                                        alertPlace = levelsPlace;
                                    }
                                    place2aqi.setForeground(new Color(147,75,23));
                                    break;
                            }
                    }               
                }
            }
        }
        if(alert) {
            this.centralManager.sendMail(recipients, alertPlace);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        homescreen = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        place1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        NO2_2 = new javax.swing.JLabel();
        O3_2 = new javax.swing.JLabel();
        PM10_2 = new javax.swing.JLabel();
        PM25_2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        temp_2 = new javax.swing.JLabel();
        hum_2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        place0 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        NO2_1 = new javax.swing.JLabel();
        O3_1 = new javax.swing.JLabel();
        PM10_1 = new javax.swing.JLabel();
        PM25_1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        temp_1 = new javax.swing.JLabel();
        hum_1 = new javax.swing.JLabel();
        place0aqi = new javax.swing.JLabel();
        place1aqi = new javax.swing.JLabel();
        place2aqi = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        place2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        NO2_3 = new javax.swing.JLabel();
        O3_3 = new javax.swing.JLabel();
        PM10_3 = new javax.swing.JLabel();
        PM25_3 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        temp_3 = new javax.swing.JLabel();
        hum_3 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        getLogs = new javax.swing.JButton();
        displaylogs = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        getLogs1 = new javax.swing.JButton();
        startDate = new com.toedter.calendar.JCalendar();
        endDate = new com.toedter.calendar.JCalendar();
        back = new javax.swing.JButton();
        settings = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        getLogs2 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        addEmail = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        removeEmail = new javax.swing.JTextArea();
        jLabel34 = new javax.swing.JLabel();
        back1 = new javax.swing.JButton();
        send = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        recipientsList = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        displayDateSpecificLogs = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayLogsTextArea = new javax.swing.JTextArea();
        back2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(229, 229, 229));
        getContentPane().setLayout(new java.awt.CardLayout());

        homescreen.setBackground(new java.awt.Color(229, 229, 229));

        jPanel8.setBackground(new java.awt.Color(229, 229, 229));

        jPanel2.setBackground(new java.awt.Color(248, 124, 114));
        jPanel2.setPreferredSize(new java.awt.Dimension(210, 232));

        place1.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        place1.setForeground(new java.awt.Color(255, 203, 199));
        place1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        place1.setText("Classroom");

        jPanel5.setBackground(new java.awt.Color(238, 107, 97));

        NO2_2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NO2_2.setForeground(new java.awt.Color(239, 242, 221));
        NO2_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        O3_2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        O3_2.setForeground(new java.awt.Color(239, 242, 221));
        O3_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        PM10_2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PM10_2.setForeground(new java.awt.Color(239, 242, 221));
        PM10_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        PM25_2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PM25_2.setForeground(new java.awt.Color(239, 242, 221));
        PM25_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("PM2.5");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("PM10");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("O3");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("NO2");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("TEMP.");

        temp_2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        temp_2.setForeground(new java.awt.Color(239, 242, 221));
        temp_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        hum_2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hum_2.setForeground(new java.awt.Color(239, 242, 221));
        hum_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("HUM.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(O3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NO2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PM10_2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PM25_2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(temp_2, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(hum_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(temp_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hum_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NO2_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(O3_2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PM10_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PM25_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(place1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(place1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel1.setBackground(new java.awt.Color(248, 124, 114));
        jPanel1.setPreferredSize(new java.awt.Dimension(210, 232));

        place0.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        place0.setForeground(new java.awt.Color(255, 203, 199));
        place0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        place0.setText("Physics Lab");

        jPanel4.setBackground(new java.awt.Color(238, 107, 97));
        jPanel4.setPreferredSize(new java.awt.Dimension(202, 164));

        NO2_1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NO2_1.setForeground(new java.awt.Color(239, 242, 221));
        NO2_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        O3_1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        O3_1.setForeground(new java.awt.Color(239, 242, 221));
        O3_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        PM10_1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PM10_1.setForeground(new java.awt.Color(239, 242, 221));
        PM10_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        PM25_1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PM25_1.setForeground(new java.awt.Color(239, 242, 221));
        PM25_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("PM2.5");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("PM10");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("O3");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("NO2");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("HUM.");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TEMP.");

        temp_1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        temp_1.setForeground(new java.awt.Color(239, 242, 221));
        temp_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        hum_1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hum_1.setForeground(new java.awt.Color(239, 242, 221));
        hum_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(O3_1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(NO2_1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(PM10_1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(PM25_1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(temp_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hum_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(temp_1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(hum_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NO2_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(O3_1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PM10_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PM25_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(place0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(place0, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        place0aqi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        place0aqi.setForeground(new java.awt.Color(248, 124, 114));
        place0aqi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        place1aqi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        place1aqi.setForeground(new java.awt.Color(248, 124, 114));
        place1aqi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        place2aqi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        place2aqi.setForeground(new java.awt.Color(248, 124, 114));
        place2aqi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel10.setBackground(new java.awt.Color(248, 124, 114));
        jPanel10.setPreferredSize(new java.awt.Dimension(210, 232));

        place2.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        place2.setForeground(new java.awt.Color(255, 203, 199));
        place2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        place2.setText("Faculty Room");

        jPanel11.setBackground(new java.awt.Color(238, 107, 97));

        NO2_3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NO2_3.setForeground(new java.awt.Color(239, 242, 221));
        NO2_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        O3_3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        O3_3.setForeground(new java.awt.Color(239, 242, 221));
        O3_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        PM10_3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PM10_3.setForeground(new java.awt.Color(239, 242, 221));
        PM10_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        PM25_3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PM25_3.setForeground(new java.awt.Color(239, 242, 221));
        PM25_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("PM2.5");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("PM10");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("O3");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("NO2");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("TEMP.");

        temp_3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        temp_3.setForeground(new java.awt.Color(239, 242, 221));
        temp_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        hum_3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hum_3.setForeground(new java.awt.Color(239, 242, 221));
        hum_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("HUM.");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(O3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NO2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PM10_3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PM25_3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(temp_3, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(hum_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(temp_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hum_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NO2_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(O3_3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PM10_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PM25_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(place2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(place2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(place0aqi, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116)
                .addComponent(place1aqi, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)
                .addComponent(place2aqi, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(238, 238, 238)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(248, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(place2aqi, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(place1aqi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(place0aqi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(81, Short.MAX_VALUE)))
        );

        jButton2.setBackground(new java.awt.Color(94, 107, 107));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Settings");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Atmospheric Management System");

        getLogs.setBackground(new java.awt.Color(94, 107, 107));
        getLogs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getLogs.setForeground(new java.awt.Color(255, 255, 255));
        getLogs.setText("Get Logs");
        getLogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getLogsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout homescreenLayout = new javax.swing.GroupLayout(homescreen);
        homescreen.setLayout(homescreenLayout);
        homescreenLayout.setHorizontalGroup(
            homescreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homescreenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homescreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homescreenLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGroup(homescreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homescreenLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(getLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        homescreenLayout.setVerticalGroup(
            homescreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homescreenLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(homescreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(47, 47, 47)
                .addGroup(homescreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(185, Short.MAX_VALUE))
        );

        getContentPane().add(homescreen, "card2");

        displaylogs.setBackground(new java.awt.Color(229, 229, 229));
        displaylogs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Select the duration");

        jPanel7.setBackground(new java.awt.Color(248, 124, 114));
        jPanel7.setPreferredSize(new java.awt.Dimension(210, 232));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Start Date");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("End Date");

        getLogs1.setBackground(new java.awt.Color(94, 107, 107));
        getLogs1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getLogs1.setForeground(new java.awt.Color(255, 255, 255));
        getLogs1.setText("Display Data");
        getLogs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getLogs1ActionPerformed(evt);
            }
        });

        startDate.setWeekOfYearVisible(false);

        endDate.setDecorationBackgroundColor(new java.awt.Color(153, 255, 255));
        endDate.setRequestFocusEnabled(false);
        endDate.setWeekOfYearVisible(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(getLogs1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addComponent(getLogs1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        back.setBackground(new java.awt.Color(94, 107, 107));
        back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("Go Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout displaylogsLayout = new javax.swing.GroupLayout(displaylogs);
        displaylogs.setLayout(displaylogsLayout);
        displaylogsLayout.setHorizontalGroup(
            displaylogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displaylogsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addGroup(displaylogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(309, Short.MAX_VALUE))
        );
        displaylogsLayout.setVerticalGroup(
            displaylogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displaylogsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displaylogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addGap(132, 132, 132))
        );

        getContentPane().add(displaylogs, "card3");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Modify mailing list");

        jPanel9.setBackground(new java.awt.Color(248, 124, 114));
        jPanel9.setPreferredSize(new java.awt.Dimension(210, 232));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Add Emails*");

        getLogs2.setBackground(new java.awt.Color(94, 107, 107));
        getLogs2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getLogs2.setForeground(new java.awt.Color(255, 255, 255));
        getLogs2.setText("Modify");
        getLogs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getLogs2ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Remove Emails*");

        addEmail.setColumns(20);
        addEmail.setRows(5);
        addEmail.setToolTipText("Seperate the email addresses using commas for multiple emails");
        jScrollPane4.setViewportView(addEmail);

        removeEmail.setColumns(20);
        removeEmail.setRows(5);
        removeEmail.setToolTipText("Seperate the email addresses using commas for multiple emails");
        jScrollPane5.setViewportView(removeEmail);

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("*Seperate email addresses by commas to add/remove multiple emails");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getLogs2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jScrollPane5))))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(getLogs2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        back1.setBackground(new java.awt.Color(94, 107, 107));
        back1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        back1.setForeground(new java.awt.Color(255, 255, 255));
        back1.setText("Go Back");
        back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back1ActionPerformed(evt);
            }
        });

        send.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        send.setText("Activate Notifications");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        recipientsList.setEditable(false);
        recipientsList.setColumns(20);
        recipientsList.setRows(5);
        jScrollPane2.setViewportView(recipientsList);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Recipient List");

        javax.swing.GroupLayout settingsLayout = new javax.swing.GroupLayout(settings);
        settings.setLayout(settingsLayout);
        settingsLayout.setHorizontalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(settingsLayout.createSequentialGroup()
                        .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        settingsLayout.setVerticalGroup(
            settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        getContentPane().add(settings, "card4");

        displayLogsTextArea.setColumns(20);
        displayLogsTextArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        displayLogsTextArea.setRows(5);
        jScrollPane1.setViewportView(displayLogsTextArea);

        back2.setBackground(new java.awt.Color(94, 107, 107));
        back2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        back2.setForeground(new java.awt.Color(255, 255, 255));
        back2.setText("Go Back");
        back2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout displayDateSpecificLogsLayout = new javax.swing.GroupLayout(displayDateSpecificLogs);
        displayDateSpecificLogs.setLayout(displayDateSpecificLogsLayout);
        displayDateSpecificLogsLayout.setHorizontalGroup(
            displayDateSpecificLogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayDateSpecificLogsLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        displayDateSpecificLogsLayout.setVerticalGroup(
            displayDateSpecificLogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayDateSpecificLogsLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(displayDateSpecificLogsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(128, 128, 128))
        );

        getContentPane().add(displayDateSpecificLogs, "card5");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getLogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getLogsActionPerformed
        // TODO add your handling code here:
        homescreen.setVisible(false);
        displaylogs.setVisible(true);
        
    }//GEN-LAST:event_getLogsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        homescreen.setVisible(false);
        settings.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void getLogs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getLogs1ActionPerformed
        // TODO add your handling code here:
        Date startDateFilter = startDate.getDate();
        LocalDateTime startLocal = LocalDateTime.ofInstant(startDateFilter.toInstant(), ZoneId.systemDefault());

        Date endDateFilter = endDate.getDate();
        LocalDateTime endLocal = LocalDateTime.ofInstant(endDateFilter.toInstant(), ZoneId.systemDefault());
        
        endLocal = endLocal.withHour(0).withMinute(0).withSecond(0);
        startLocal = startLocal.withHour(0).withMinute(0).withSecond(0);
       
        displaylogs.setVisible(false);
        displayDateSpecificLogs.setVisible(true);
        displayLogsTextArea.setText("");
        Map<LocalDateTime, Map<String, Map<String, Integer>>> logs = new HashMap<LocalDateTime, Map<String, Map<String, Integer>>>();
        logs = this.centralManager.getLogs(startLocal, endLocal);
        
        for(Map.Entry<LocalDateTime, Map<String, Map<String, Integer>>> log: logs.entrySet()) {
            LocalDateTime time = log.getKey();
            for(Map.Entry<String, Map<String, Integer>> placeData: log.getValue().entrySet()){
                   String place = (String)placeData.getKey();
                    Map <String, Integer> sensor = (HashMap)placeData.getValue();
                    for(Map.Entry j : sensor.entrySet()){
                        String gas = (String)j.getKey();
                        int value = (Integer)j.getValue();
                        displayLogsTextArea.append(time + " : " + place + " : " + gas + " : " + value + "\n");   
                    }
            }
        }
    }//GEN-LAST:event_getLogs1ActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        homescreen.setVisible(true);
        displaylogs.setVisible(false);
    }//GEN-LAST:event_backActionPerformed

    private void back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back1ActionPerformed
        // TODO add your handling code here:
        homescreen.setVisible(true);
        settings.setVisible(false);
    }//GEN-LAST:event_back1ActionPerformed

    private void getLogs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getLogs2ActionPerformed
        // TODO add your handling code here:
        String add = addEmail.getText();
        String remove = removeEmail.getText();
        
        if(remove.equals("") && add.equals("")){
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter a valid input");
            return;
        }
        
        if(!"".equals(add)){
            List <String> addList = Arrays.asList(add.split(",", 100));            
            for(String addItem : addList){
                addItem = addItem.trim();
                if(recipients.contains(addItem)) {
                    continue;
                } else
                    recipients.add(addItem);
            }
        }
        
        if(!"".equals(remove)){
            List <String> removeList = Arrays.asList(remove.split(",", 100));
            for(String removeItem : removeList){
                removeItem = removeItem.trim();
                if(recipients.contains(removeItem))
                    recipients.remove(removeItem);
                else
                    javax.swing.JOptionPane.showMessageDialog(this, removeItem + " does not exist");
            }
        }
        
        for(int i=0; i< recipients.size() ; i++){
            if(i==0)
            recipientsList.setText("");
            System.out.println(recipients.get(i));
            recipientsList.append(recipients.get(i) + "\n");
        }
        
        addEmail.setText("");
        removeEmail.setText("");
    }//GEN-LAST:event_getLogs2ActionPerformed

    private void back2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back2ActionPerformed
        // TODO add your handling code here:
        displaylogs.setVisible(true);
        displayDateSpecificLogs.setVisible(false);
    }//GEN-LAST:event_back2ActionPerformed

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new HomeScreen().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NO2_1;
    private javax.swing.JLabel NO2_2;
    private javax.swing.JLabel NO2_3;
    private javax.swing.JLabel O3_1;
    private javax.swing.JLabel O3_2;
    private javax.swing.JLabel O3_3;
    private javax.swing.JLabel PM10_1;
    private javax.swing.JLabel PM10_2;
    private javax.swing.JLabel PM10_3;
    private javax.swing.JLabel PM25_1;
    private javax.swing.JLabel PM25_2;
    private javax.swing.JLabel PM25_3;
    private javax.swing.JTextArea addEmail;
    private javax.swing.JButton back;
    private javax.swing.JButton back1;
    private javax.swing.JButton back2;
    private javax.swing.JPanel displayDateSpecificLogs;
    private javax.swing.JTextArea displayLogsTextArea;
    private javax.swing.JPanel displaylogs;
    private com.toedter.calendar.JCalendar endDate;
    private javax.swing.JButton getLogs;
    private javax.swing.JButton getLogs1;
    private javax.swing.JButton getLogs2;
    private javax.swing.JPanel homescreen;
    private javax.swing.JLabel hum_1;
    private javax.swing.JLabel hum_2;
    private javax.swing.JLabel hum_3;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel place0;
    private javax.swing.JLabel place0aqi;
    private javax.swing.JLabel place1;
    private javax.swing.JLabel place1aqi;
    private javax.swing.JLabel place2;
    private javax.swing.JLabel place2aqi;
    private javax.swing.JTextArea recipientsList;
    private javax.swing.JTextArea removeEmail;
    private javax.swing.JCheckBox send;
    private javax.swing.JPanel settings;
    private com.toedter.calendar.JCalendar startDate;
    private javax.swing.JLabel temp_1;
    private javax.swing.JLabel temp_2;
    private javax.swing.JLabel temp_3;
    // End of variables declaration//GEN-END:variables
}
