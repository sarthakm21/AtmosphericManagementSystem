package airmanagement;

import java.util.*;
import java.time.LocalDateTime;
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
import javax.mail.Session; 
import javax.mail.Transport; 

public class CentralManager {
    private List<Place> places = new ArrayList<Place>();
    private Map<LocalDateTime, Map<String, Map<String, Integer>>> logData = new HashMap<LocalDateTime, Map<String, Map<String, Integer>>>();

    public CentralManager() {
        Place p1 = new Place("Physics Lab", 25.431993, 81.770237);
        Place p2 = new Place("Lecture Room 1", 25.432424, 81.770116);
        Place p3 = new Place("HOD Room", 25.432150, 81.770746);
        this.places.add(p1);
        this.places.add(p2);
        this.places.add(p3);
    }

    //Central Manager's getData calls place manager's get data which then calls sensors get data.
    //A Hash <String, <String, Integer>> with the Place name(first string), sensors name and the sensors data that is randomly generated
    public Map<String, Map<String, Integer>> getData() {
        Map<String, Map<String, Integer>> data = new HashMap<String, Map<String, Integer>>();

        for(Place place: this.places) {
            data.put(place.name, place.getData());
        }
        this.logData.put(LocalDateTime.now(), data);
        return data;
    }
    
    public List<Place> getPlaces(){
        return places;
    }

    public Map<LocalDateTime, Map<String, Map<String, Integer>>> getLogs(LocalDateTime start, LocalDateTime end) {

        Map<LocalDateTime, Map<String, Map<String, Integer>>> logs = new HashMap<LocalDateTime, Map<String, Map<String, Integer>>>();

        //Getting log data according to the date entered.
        for(Map.Entry<LocalDateTime, Map<String, Map<String, Integer>>> log: this.logData.entrySet()) {
            if(log.getKey().isBefore(start)) continue;
            if(log.getKey().isAfter(end)) break;
            logs.put(log.getKey(), log.getValue());
        }
        return logs;
    }

    public Map<String, String> checkLevels() {
        Map<String, String> data = new HashMap<String, String>();
        for(Place place: this.places) {
            data.put(place.name, place.checkLevels());
        }
        return data;
    }
    
    public void sendMail(List<String> emails, String placename) {
      
       Runnable task = () -> {
            String sender = "atmosphericmanagementsystem@gmail.com";
            String password = "atmospheric12#";
            String host = "smtp.gmail.com"; 

            Properties properties = new Properties(); 

            properties.put("mail.smtp.host",host);  
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            // creating session object to get properties 
            Session session = Session.getDefaultInstance(properties,  
              new javax.mail.Authenticator() {  
              protected PasswordAuthentication getPasswordAuthentication() {  
                  return new PasswordAuthentication(sender, password);  
              }  
              }); 

            try 
            { 
               // MimeMessage object. 
               MimeMessage message = new MimeMessage(session); 

               // Set From Field: adding senders email to from field. 
               message.setFrom(new InternetAddress(sender)); 
               for(String email: emails) {
               // Set To Field: adding recipient's email to from field. 
                  message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 
               }
               // Set Subject: subject of the email 
               message.setSubject("High Pollutant Level Warning"); 

               // set body of the email. 
               message.setText("WARNING: High Level of Pollutants has been detected at " + placename + "."); 

               // Send email. 
               Transport.send(message); 
               System.out.println("Mail successfully sent"); 
            } 
            catch (MessagingException mex)  
            { 
               mex.printStackTrace(); 
            }
        };

        Thread thread = new Thread(task);
        thread.start();
      
      
    }
}
