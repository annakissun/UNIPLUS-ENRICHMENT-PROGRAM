package services;

import java.security.SecureRandom;

public class CodeGenerator {
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    /**
     * Generates a random code of given length.
     * @param length length of the code
     * @return randomly generated code
     */
    public static String generateCode(int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHAR_POOL.length());
            code.append(CHAR_POOL.charAt(index));
        }
        return code.toString();
    }

    // Optional: generate numeric-only codes
    public static String generateNumericCode(int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10)); // 0-9
        }
        return code.toString();
    }

    public static void main(String[] args) {
        
        String sessionCode = CodeGenerator.generateCode(6); // e.g., ABC123
        System.out.println("Session Code: " + sessionCode);

    }
}
