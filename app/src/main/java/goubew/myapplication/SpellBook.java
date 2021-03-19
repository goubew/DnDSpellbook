package goubew.myapplication;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpellBook {
    public List<Spell> fullSpellList;
    public List<Spell> spellList;

    public Spell readSpell(JsonReader reader) throws IOException {
        String name = "";
        String desc = "";
        String higherLevel = "";
        String page = "";
        String range = "";
        String components = "";
        String material = "";
        String ritual = "";
        String duration = "";
        String concentration = "";
        String castingTime = "";
        String level = "";
        String school = "";
        String charClass = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String json_name = reader.nextName();
            switch (json_name) {
                case "name":
                    name = reader.nextString();
                    break;
                case "desc":
                    desc = reader.nextString();
                    break;
                case "higher_level":
                    higherLevel = reader.nextString();
                    break;
                case "page":
                    page = reader.nextString();
                    break;
                case "range":
                    range = reader.nextString();
                    break;
                case "components":
                    components = reader.nextString();
                    break;
                case "material":
                    material = reader.nextString();
                    break;
                case "ritual":
                    ritual = reader.nextString();
                    break;
                case "duration":
                    duration = reader.nextString();
                    break;
                case "concentration":
                    concentration = reader.nextString();
                    break;
                case "casting_time":
                    castingTime = reader.nextString();
                    break;
                case "level":
                    level = reader.nextString();
                    break;
                case "school":
                    school = reader.nextString();
                    break;
                case "class":
                    charClass = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Spell(name, desc, higherLevel, page, range, components, material, ritual, duration,
                concentration, castingTime, level, school, charClass);
    }

    public List<Spell> readSpellArray(JsonReader reader) throws IOException {
        List<Spell> spells = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            spells.add(readSpell(reader));
        }
        reader.endArray();
        return spells;
    }

    public List<Spell> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return readSpellArray(reader);
        }
    }

    public void filterSpells(String filter) {
        if (filter.equals("")) {
            spellList = fullSpellList;
        }
        else {
            spellList = fullSpellList.stream()
                    .filter(spell -> spell.name.toLowerCase().contains(filter.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }

    public Spell getSpell(String spellName) {
        for (Spell spell : spellList) {
            if (spell.name.equals(spellName)) {
                return spell;
            }
        }
        return null;
    }

    public void filterSpellList(String filter) {
        if (filter.equals("")) {
            spellList = fullSpellList;
        }
        else {
            spellList = fullSpellList.stream()
                    .filter(spell -> spell.name.toLowerCase().contains(filter.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }

    public void initialize(InputStream spell_json_stream) {
        try {
            fullSpellList = readJsonStream(spell_json_stream);
        } catch (IOException e) {
            e.printStackTrace();
            fullSpellList = new ArrayList<>();
        }
        spellList = fullSpellList;
    }
}
