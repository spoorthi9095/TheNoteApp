package com.spoorthi.noteapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spoorthi.noteapp.R;
import com.spoorthi.noteapp.storage.NoteBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    Context context;
    List<NoteBean> noteBeanList;
    NoteClick noteClick;

    public NoteAdapter(Context context, List<NoteBean> noteBeanList, NoteClick noteClick) {
        this.context = context;
        this.noteBeanList = noteBeanList;
        this.noteClick = noteClick;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        /*populating each item*/
        NoteBean noteBean = noteBeanList.get(position);
        holder.title.setText(noteBean.getTitle());
        holder.content.setText(noteBean.getContent());
    }

    @Override
    public int getItemCount() {
        return noteBeanList.size();
    }

    public void setNotes(List<NoteBean> noteBeans) {
        noteBeanList = noteBeans;
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView title,content;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            content = itemView.findViewById(R.id.item_content);

            /**/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    noteClick.OnNoteClick(getAdapterPosition());
                }
            });
        }
    }

    /*interface to handle note click on list*/
    public interface NoteClick{
        void OnNoteClick(int position);
    }

}
