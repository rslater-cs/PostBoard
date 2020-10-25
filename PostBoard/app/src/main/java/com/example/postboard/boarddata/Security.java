package com.example.postboard.boarddata;

public class Security {
    private static final String COMPARATOR = "a6XxcUe$&^li;WD}!(IfNn(*Fdg:IE;'#";

    public static String hash(String password){
        StringBuilder hash = new StringBuilder();

        for(int x = 0; x < password.length(); x++){
            char currChar = (char)Math.abs(password.charAt(x) - COMPARATOR.charAt(x%COMPARATOR.length()));
            hash.append(currChar);
        }

        return hash.toString();
    }
}
