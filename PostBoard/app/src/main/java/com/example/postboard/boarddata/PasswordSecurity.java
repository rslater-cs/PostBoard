package com.example.postboard.boarddata;

public class PasswordSecurity {
    private static final String SUBTRACT_STRING = "fDgMspjwQJ4rO2zWUzTR";

    public static String hash(String password){
        StringBuilder hash = new StringBuilder();

        for(int x = 0; x < password.length(); x++){
            if(password.charAt(x) > SUBTRACT_STRING.charAt(x%SUBTRACT_STRING.length()))
                hash.append(subChar(password.charAt(x), SUBTRACT_STRING.charAt(x%SUBTRACT_STRING.length())));
            else hash.append(subChar(SUBTRACT_STRING.charAt(x%SUBTRACT_STRING.length()), password.charAt(x)));
        }

        return hash.toString();
    }

    private static char subChar(char one, char two){
        return (char)(one - two);
    }
}
