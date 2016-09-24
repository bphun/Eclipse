package com.weatherapplication.brandonphan.sci_fi_name;

import java.util.Random;

public class SciFiName  {

    public String generateSci_FiFirstName(String firstName, String lastName) {
        lastName = lastName.toLowerCase();
        return firstName.substring(0,3) + lastName.substring(0,2);
    }

    public String generateSci_FiLastName(String cityOfBirth, String elementarySchool) {
        elementarySchool = elementarySchool.toLowerCase();
        return cityOfBirth.substring(0,2) + elementarySchool.substring(0,3);
    }

    public String generateOrigin(String relative) {
        int startIndex = generateRandomStartIndex(relative.length());
        return relative.substring(startIndex, relative.length());
    }

    private int generateRandomStartIndex(int maxVal) {
        Random randomNumGenerator = new Random();

        return randomNumGenerator.nextInt(maxVal);
    }
}
