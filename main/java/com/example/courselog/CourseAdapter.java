package com.example.courselog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private ArrayList<Course> courses;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(View itemView,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Course c = courses.get(i);


        TextView tv = viewHolder.course;
        tv.setText(c.getName());

        tv = viewHolder.totalMarks;
        tv.setText(Integer.toString(c.getTotalMarks()));

        tv = viewHolder.obtainedMarks;
        tv.setText(Float.toString(c.getObtainedMarks()));

        float a = c.getObtainedMarks();
        float b = (float) c.getTotalMarks();
        float x,y=100;
        ProgressBar p = viewHolder.pb;
        if (b != 0) {
            x=a/b;
            x=x*y;
            p.setProgress((int)x);

        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public ProgressBar pb;
        public TextView course;
        public TextView obtainedMarks;
        public TextView totalMarks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.textView52);
            obtainedMarks = itemView.findViewById(R.id.textView56);
            totalMarks = itemView.findViewById(R.id.textView70);
            pb = itemView.findViewById(R.id.progressBar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(v, position);
                        }
                    }
                }
            });
        }
    }

    CourseAdapter(ArrayList<Course> l)
    {
        courses = l;
    }

}
