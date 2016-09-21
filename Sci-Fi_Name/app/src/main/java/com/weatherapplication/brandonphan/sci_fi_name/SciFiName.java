package com.weatherapplication.brandonphan.sci_fi_name;

public class SciFiName  {

    public String generateSci_FiFirstName(String firstName, String lastName) {
        lastName = lastName.toLowerCase();
        return firstName.substring(0,3) + lastName.substring(0,2);
    }

    public String generateSci_FiLastName(String cityOfBirth, String elementarySchool) {
        elementarySchool = elementarySchool.toLowerCase();
        return cityOfBirth.substring(0,2) + elementarySchool.substring(0,3);
    }
}
