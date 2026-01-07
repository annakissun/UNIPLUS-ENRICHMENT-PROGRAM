package gui.Controller;

public class Session {
    private String subject;
    private String location;
    private String description;
    private String time;
    private String capacity;
    
    // Constructor
    public Session(String subject, String location, String description, String time, String capacity) {
        this.subject = subject;
        this.location = location;
        this.description = description;
        this.time = time;
        this.capacity = capacity;
    }
    
    // Getters
    public String getSubject() { return subject; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getTime() { return time; }
    public String getCapacity() { return capacity; }
    
    // Setters (optional, if you need to modify sessions later)
    public void setSubject(String subject) { this.subject = subject; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setTime(String time) { this.time = time; }
    public void setCapacity(String capacity) { this.capacity = capacity; }
    
    // toString() method for easy debugging
    @Override
    public String toString() {
        return "Session{" +
                "subject='" + subject + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", time='" + time + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}