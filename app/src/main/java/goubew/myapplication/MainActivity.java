package goubew.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private SpellBook spellBook;
    private RecyclerView recyclerView;
    private SpellListAdapter adapter;

    public void openSpell(View view) {
        TextView txtView = view.findViewById(R.id.spell);
        Spell spell = spellBook.getSpell(txtView.getText().toString());
        if (spell != null) {
            Intent intent = new Intent(this, SpellActivity.class);
            intent.putExtra("spell", spell);
            startActivity(intent);
        }
    }

    private void filterAndNotifyAdapter(String filter) {
        spellBook.filterSpellList(filter);
        adapter.notifyDataSetChanged();
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            filterAndNotifyAdapter(query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                filterAndNotifyAdapter(newText);
                return false;
            }
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                filterAndNotifyAdapter("");
                return false;
            }
        });

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spellBook = new SpellBook();
        InputStream spellStream = getResources().openRawResource(R.raw.spells);
        spellBook.initialize(spellStream);

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new SpellListAdapter(this, spellBook);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        handleIntent(getIntent());
    }
}
