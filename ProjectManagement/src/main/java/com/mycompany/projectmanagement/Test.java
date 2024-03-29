/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectmanagement;

import java.util.Locale;

/**
 *
 * @author yizun
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test(); // Create an instance of the Test class
        String[] countries = test.getAllCountries(); // Call the getAllCountries method
        // Now you can use the countries array as needed
        for (String country : countries) {
            System.out.println(country);
        }

    }

    public String[] getAllCountries() {
        String[] countries = new String[Locale.getISOCountries().length];
        String[] countryCodes = Locale.getISOCountries();
        for (int i = 0; i < countryCodes.length; i++) {
            Locale obj = new Locale("", countryCodes[i]);
            countries[i] = obj.getDisplayCountry();
        }
        return countries;
    }
}
