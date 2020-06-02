package com.example.formationcours;

import java.io.Serializable;
import java.util.HashMap;

class Villager implements Serializable {
    public int id;
    public HashMap<String, String> name;
    public String personality;
    public String birthday;
    public String species;
    public String gender;
    public String icon_uri;
    public String image_uri;

    public int getId() {
        return id;
    }

    public HashMap<String, String> getName() {
        return name;
    }

    public String getNameFR() {
        return getName().get("name-EUfr");
    }

    public String getBirthday() {
        return birthday;
    }

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public String getPersonality() {
        return personality;
    }

    public String getIcon_uri() {
        return icon_uri;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public String toString() {
        if(name == null) {
            name = new HashMap<String, String>();
        }
        return "\n=== Villageois ===\n" +
                " - id: " + id + "\n" +
                " - names keys: " + name.keySet() + "\n" +
                " - names values: " + name.values() + "\n" +
                " - personality: " + personality + "\n" +
                " - Birthday: " + birthday + "\n" +
                " - Species: " + species + "\n" +
                " - Gender: " + gender;
    }
}
