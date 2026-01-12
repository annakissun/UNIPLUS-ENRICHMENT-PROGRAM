package util;

import model.*;
import services.AuthService;
import structure.SessionList;

import java.io.*;
import java.util.*;

public class FileService {

    private static final String SESSION_FILE = "sessions.txt"; // adjust path

    /**
     * Loads all sessions from file.
     * Auto-saving in Session is suppressed during loading to prevent infinite recursion.
     */
    public static SessionList loadSessions(AuthService authService) {
        SessionList sessionList = new SessionList();

        File file = new File(SESSION_FILE);
        if (!file.exists()) return sessionList; // no file → empty SessionList

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Session currentSession = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("SESSION|")) {
                    String[] parts = line.split("\\|");
                    int capacity = Integer.parseInt(parts[1]);
                    String host = parts[2];
                    String subject = parts[3];
                    boolean isPrivate = Boolean.parseBoolean(parts[4]);
                    String location = parts[5];
                    String desc = parts[6];
                    String time = parts[7];

                    currentSession = new Session(capacity, host, subject, isPrivate, location, desc, time);
                    currentSession.setSuppressSave(true);
                    sessionList.addSession(currentSession); // ✅ add to wrapper

                } else if (line.startsWith("STUDENT|") && currentSession != null) {
                    String username = line.split("\\|")[1];
                    Student s = authService.findStudentByUsername(username);
                    if (s != null) {
                        currentSession.addFromFile(s);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Re-enable saving after load
        for (Session s : sessionList.getAllSessions()) {
            s.setSuppressSave(false);
        }

        return sessionList;
    }


    /**
     * Saves sessions to file.
     */
    public static void saveSessions(List<Session> sessions) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(SESSION_FILE))) {
            for (Session s : sessions) {
                pw.println("SESSION|" + s.getCapacity() + "|" + s.getHost() + "|" + s.getSubject() + "|" +
                           s.isPrivate() + "|" + s.getLocation() + "|" + s.getDescription() + "|" + s.getTime());

                for (String username : s.getStudentNames()) {
                    pw.println("STUDENT|" + username);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
