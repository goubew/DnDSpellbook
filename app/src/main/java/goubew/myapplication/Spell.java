package goubew.myapplication;

import java.io.Serializable;

public class Spell implements Serializable {
    public String name;
    public String desc;
    public String higherLevel;
    public String page;
    public String range;
    public String components;
    public String material;
    public String ritual;
    public String duration;
    public String concentration;
    public String castingTime;
    public String level;
    public String school;
    public String charClass;

    public Spell(String name, String desc, String higherLevel, String page, String range,
                 String components, String material, String ritual, String duration,
                 String concentration, String castingTime, String level, String school,
                 String charClass) {
        this.name = name;
        this.desc = desc;
        this.higherLevel = higherLevel;
        this.page = page;
        this.range = range;
        this.components = components;
        this.material = material;
        this.ritual = ritual;
        this.duration = duration;
        this.concentration = concentration;
        this.castingTime = castingTime;
        this.level = level;
        this.school = school;
        this.charClass = charClass;
    }

    public String getInfo() {
        StringBuilder builder = new StringBuilder()
                .append(level).append(" ")
                .append(school);
        return builder.toString();
    }

    public String getCastingTime() {
        StringBuilder builder = new StringBuilder().append(castingTime);
        if (ritual != null && ritual.equals("yes")) {
            builder.append(" or ritual");
        }
        return builder.toString();
    }

    public String getComponents() {
        StringBuilder builder = new StringBuilder()
                .append(components);
        if (material != null && ! material.equals("")) {
            builder.append(" (")
                    .append(material)
                    .append(")");
        }

        return builder.toString();
    }

    public String getDuration() {
        StringBuilder builder = new StringBuilder();
        if (concentration != null && concentration.equals("yes")) {
            builder.append("Concentration, ");
        }
        builder.append(duration);
        return builder.toString();
    }
}
