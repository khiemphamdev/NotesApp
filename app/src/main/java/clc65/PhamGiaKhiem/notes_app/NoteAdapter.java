package clc65.PhamGiaKhiem.notes_app;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    Context context;
    ArrayList<NoteModel> arrayList = new ArrayList<>();
    ArrayList<NoteModel> noteListFull = new ArrayList<>();


    public NoteAdapter(Context context, ArrayList<NoteModel> arrayList) {
        this.context = context;
        this.noteListFull = new ArrayList<>(arrayList); // danh sách gốc
        this.arrayList = new ArrayList<>(arrayList);     // danh sách hiển thị
    }



    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item,parent,false);
        return new ViewHolder(view);

    }
    // filter
    public void filter(String text){
        arrayList.clear();
        if(text.isEmpty()){
            arrayList.addAll(noteListFull);
        } else {
            text = text.toLowerCase();
            for(NoteModel note: noteListFull){
                if(note.getTitle().toLowerCase().contains(text) ||
                        note.getDescription().toLowerCase().contains(text)){
                    arrayList.add(note);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {


        holder.tvTitle.setText(arrayList.get(position).getTitle());
        holder.tvDesc.setText(arrayList.get(position).getDescription());


        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Entry")
                        .setMessage("Are you sure want to delete this entry?")

                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                NoteHelper noteHelper = new NoteHelper(context);
                                String id = String.valueOf(arrayList.get(position).getId());
                                NoteModel noteToDelete = arrayList.get(position);
                                noteHelper.deleteData(id);
                                arrayList.remove(position);
                                noteListFull.remove(noteToDelete);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, arrayList.size());
                                dialogInterface.dismiss();
                            }
                        })

                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(R.drawable.baseline_add_alert_24)
                        .show();
                return false;
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity3.class);
                intent.putExtra("title",arrayList.get(position).getTitle());
                intent.putExtra("description",arrayList.get(position).getDescription());
                intent.putExtra("id",arrayList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDesc;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
