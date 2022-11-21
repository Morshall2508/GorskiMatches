package pl.piekoszek.gorskimatches.validation;

public class StringEditor {

    public static String removeSpaces(String text) {
        return text.trim().replaceAll(" ", "");
    }
}