package util;

import java.io.*;
import java.util.*;
import model.*;
import services.AuthService;

public class FileService {

    private static final String SESSION_FILE = "sessions.txt";

    public static void saveSessions(List<Session> sessions) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(SESSION_FILE))) {
            for (Session s : sessions) {
                String joined = String.join(",", s.getStudentNames());
                String wait = String.join(",", s.getWaitlistNames());

                pw.println(
                    s.getCapacity() + "|" +
                    s.getHost() + "|" +
                    s.getSubject() + "|" +
                    s.isPrivate() + "|" +
                    s.getLocation() + "|" +
                    s.getDescription() + "|" +
                    s.getTime() + "|" +
                    joined + "|" +
                    wait
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Session> loadSessions(AuthService auth) {
        List<Session> sessions = new ArrayList<>();
        File file = new File(SESSION_FILE);
        if (!file.exists()) return sessions;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|", -1);
                Session s = new Session(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        Boolean.parseBoolean(parts[3]),
                        parts[4],
                        parts[5],
                        parts[6]
                );

                if (parts.length > 7 && !parts[7].isEmpty()) {
                    for (String username : parts[7].split(",")) {
                        Student st = findStudent(auth, username.trim());
                        if (st != null) s.addJoined(st);
                    }
                }

                if (parts.length > 8 && !parts[8].isEmpty()) {
                    for (String username : parts[8].split(",")) {
                        Student st = findStudent(auth, username.trim());
                        if (st != null) s.addWaitlist(st);
                    }
                }

                sessions.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    private static Student findStudent(AuthService auth, String username) {
        for (var u : auth.getAllUsers()) {
            if (u instanceof Student && u.getUsername().equals(username)) return (Student) u;
        }
        return null;
    }
}
