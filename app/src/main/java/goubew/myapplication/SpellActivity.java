package goubew.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SpellActivity extends AppCompatActivity {
    TextView infoView;
    TextView castingTimeView;
    TextView rangeView;
    TextView componentsView;
    TextView durationView;
    TextView descView;
    TextView higherLevelView;
    TextView higherLevelDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);

        infoView = findViewById(R.id.spellInfo);
        castingTimeView = findViewById(R.id.spellCastingTime);
        rangeView = findViewById(R.id.spellRange);
        componentsView = findViewById(R.id.spellComponents);
        durationView = findViewById(R.id.spellDuration);
        descView = findViewById(R.id.spellDescription);
        higherLevelView = findViewById(R.id.spellHigherLevel);
        higherLevelDescriptionView = findViewById(R.id.spellHigherLevelDescription);

        Intent intent = getIntent();
        Spell spell = (Spell) intent.getSerializableExtra("spell");

        getSupportActionBar().setTitle(spell.name);
        infoView.setText(spell.getInfo());
        castingTimeView.setText(spell.getCastingTime());
        rangeView.setText(spell.range);
        componentsView.setText(spell.getComponents());
        durationView.setText(spell.getDuration());
        descView.setText(spell.desc);
        if (spell.higherLevel != null && ! spell.higherLevel.equals("")) {
            higherLevelView.setText(spell.higherLevel);
        }
        else {
            higherLevelView.setVisibility(View.GONE);
            higherLevelDescriptionView.setVisibility(View.GONE);
        }
    }
}
