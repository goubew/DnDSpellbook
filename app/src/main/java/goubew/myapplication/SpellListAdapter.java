package goubew.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpellListAdapter extends
        RecyclerView.Adapter<SpellListAdapter.SpellViewHolder> {

    class SpellViewHolder extends RecyclerView.ViewHolder {
        public final TextView spellTextView;
        public final TextView spellSummaryTextView;
        final SpellListAdapter adapter;

        public SpellViewHolder(View itemView, SpellListAdapter adapter) {
            super(itemView);
            spellTextView = itemView.findViewById(R.id.spell);
            spellSummaryTextView = itemView.findViewById(R.id.spellSummary);
            this.adapter = adapter;
        }
    }

    private final SpellBook spellBook;
    private final LayoutInflater inflater;

    public SpellListAdapter(Context context, SpellBook spellBook) {
        inflater = LayoutInflater.from(context);
        this.spellBook = spellBook;
    }

    @NonNull
    @Override
    public SpellListAdapter.SpellViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        View itemView = inflater.inflate(R.layout.spell_list_item,
                parent, false);
        return new SpellViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SpellListAdapter.SpellViewHolder holder, int position) {
        Spell current = spellBook.spellList.get(position);
        holder.spellTextView.setText(current.name);
        holder.spellSummaryTextView.setText(current.getInfo());
    }

    @Override
    public int getItemCount() {
        return spellBook.spellList.size();
    }
}
