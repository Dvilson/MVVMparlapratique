package com.dvilson.mvvmmparlapratique.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.dvilson.mvvmmparlapratique.R;
import com.dvilson.mvvmmparlapratique.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteRecyclerViewAdapter  extends ListAdapter<Note,NoteRecyclerViewAdapter.NoteViewHolder> {
    OnItemClickListner listner;

    public NoteRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }
    private static  final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() ==  oldItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_item,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.title.setText(currentNote.getTitle());
        holder.description.setText(currentNote.getDescription());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));

    }



    public Note getNoteAt(int position){
        return getItem(position);
    }



    public class NoteViewHolder extends RecyclerView.ViewHolder{

        private TextView title,description,priority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            description = itemView.findViewById(R.id.text_view_description);
            priority = itemView.findViewById(R.id.text_view_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listner != null && position != RecyclerView.NO_POSITION){
                        listner.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListner {
        void onItemClick(Note note);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner = listner;


    }


}
