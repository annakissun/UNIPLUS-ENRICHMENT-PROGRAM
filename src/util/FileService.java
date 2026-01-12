package util;

import model.*;
import services.AuthService;
import structure.SessionList;

import java.io.*;
import java.util.*;

public class FileService {

    private static final String SESSION_FILE = "sessions.txt";

    // ===== LOAD =====
    public static SessionList loadSessions(AuthService authService) {
        SessionList sessionList = new SessionList();
        File file = new File(SESSION_FILE);

        if (!file.exists()) return sessionList;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Session currentSession = null;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                if (line.startsWith("SESSION|")) {
                    
                    String[] p = line.split("\\|");
                    currentSession = new Session(Integer.parseInt(p[1]),p[2],p[3],Boolean.parseBoolean(p[4]),p[5], p[6], p[7]);
                    sessionList.addSession(currentSession);

                } else if (line.startsWith("STUDENT|") && currentSession != null) {
                    String username = line.split("\\|")[1];
                    Student s = authService.findStudentByUsername(username);
                    if (s != null) currentSession.addFromFile(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sessionList;
    }

    // ===== SAVE =====
    public static void saveSessions(List<Session> sessions) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(SESSION_FILE))) {
            for (Session s : sessions) {
                pw.println("SESSION|" +
                        s.getCapacity() + "|" +
                        s.getHost() + "|" +
                        s.getSubject() + "|" +
                        s.isPrivate() + "|" +
                        s.getLocation() + "|" +
                        s.getDescription() + "|" +
                        s.getTime()
                );
                for (Student stu : s.getJoinedListStudents()) {
                    pw.println("STUDENT|" + stu.getUsername());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
