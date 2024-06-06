/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import org.json.JSONArray;

/**
 *
 * @author yizun
 */
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\+\\d{1,15}$"
    );

    private static final Pattern SPECIFIC_PASSWORD_PATTERN = Pattern.compile(
             "^(TP|LC)\\d{1,4}@\\d{4}$"
    );

    private static final Pattern DEFAULT_PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!?]).{8,}$"
    );

    private static final Pattern IC_PATTERN = Pattern.compile(
            "^[0-9]{6}[0-9]{2}[0-9]{4}$"
    );

    public static void validateString(String value, String fieldName, List<String> errors) {
        if (value == null || value.isEmpty() || value.equalsIgnoreCase("-")) {
            errors.add(fieldName + " is required.");
        }
    }

    public static void validateEmail(String email, List<String> errors) {
        validateString(email, "Email", errors);
        if (errors.isEmpty()) {
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                errors.add("Invalid email format.");
            }
        }
    }

    public static void validatePhone(String phone, List<String> errors) {
        validateString(phone, "Phone number", errors);
        if (errors.isEmpty()) {
            if (!PHONE_PATTERN.matcher(phone).matches()) {
                errors.add("Phone number is invalid.");
            }
        }
    }

    public static void validatePassword(String password, List<String> errors) {
        validateString(password, "Password", errors);
        if (errors.isEmpty()) {
            if (!SPECIFIC_PASSWORD_PATTERN.matcher(password).matches()) {
                if (!DEFAULT_PASSWORD_PATTERN.matcher(password).matches()) {
                    errors.add("Password must be at least 8 characters long and contain at least one digit, "
                            + "one lower case letter, one upper case letter, and one special character.");
                }
            }
        }
    }

    public static void validateDate(Date date, List<String> errors) {
        if (date == null) {
            errors.add("Date is invalid.");
        }
    }

    public static void validateIC(String ic, List<String> errors) {
        validateString(ic, "IC", errors);
        if (errors.isEmpty()) {
            if (!IC_PATTERN.matcher(ic).matches()) {
                errors.add("IC is invalid. It should be in the format XXXXXX-XX-XXXX.");
            }
        }
    }

    public static void validateJSONArray(JSONArray jsonArray, String entity, List<String> errors) {
        if (jsonArray.isEmpty()) {
            errors.add(entity + " not found.");
        }
    }

    public static boolean validateLecturer(String name) {
        FileController.FileService fs = new FileController.FileService();
        JSONArray jsonArray = (JSONArray) fs.readData("lecturer.txt", "array");

        for (int i = 0; i < jsonArray.length(); i++) {
            if (name.equals(jsonArray.getString(i))) {
                return true;
            }
        }
        return false;
    }

}
