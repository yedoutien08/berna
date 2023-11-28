package securite.appSecurity.utils;

import java.util.Random;

public class CodeGenerator {
    private static StringBuilder codeBuilder;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String frontendServerUrl = "http://localhost:4200";
    public static String numIdentifiant(String nom, String prenom, int dateNaiss){
        codeBuilder = new StringBuilder();
        codeBuilder.append(nom.substring(0,2));
        codeBuilder.append(prenom.substring(0,2));
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(ALPHABET.length());
            codeBuilder.append(ALPHABET.charAt(index));
        }
        codeBuilder.append(dateNaiss);

        return codeBuilder.toString();
    }

    public static String password(String nom, String prenom, int dateNaiss){
        codeBuilder = new StringBuilder();
        codeBuilder.append(nom.substring(0,2));
        codeBuilder.append(dateNaiss);
        codeBuilder.append(prenom.substring(0,2));
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(ALPHABET.length());
            codeBuilder.append(ALPHABET.charAt(index));
        }


        return codeBuilder.toString();
    }


}
