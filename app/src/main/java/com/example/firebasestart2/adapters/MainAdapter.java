package com.example.firebasestart2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasestart2.R;
import com.example.firebasestart2.pojo.Person;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    List<Person> people;

    public MainAdapter() {
        this.people = new ArrayList<>();
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Person person = people.get(position);
        holder.textViewName.setText(person.getName());
        holder.textViewLastName.setText(person.getLastName());
        holder.textViewAge.setText(String.format("%s",person.getAge()));
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private TextView textViewLastName;
        private TextView textViewAge;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);
            textViewAge = itemView.findViewById(R.id.textViewAge);
        }
    }
}
