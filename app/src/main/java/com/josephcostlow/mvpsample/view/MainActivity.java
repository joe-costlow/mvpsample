package com.josephcostlow.mvpsample.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.josephcostlow.mvpsample.R;
import com.josephcostlow.mvpsample.model.ListItem;
import com.josephcostlow.mvpsample.presentation.MainActivityPresenterImpl;
import com.josephcostlow.mvpsample.repository.RepositoryImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private EditText input;
    private TextView textButton, result;
    private RecyclerView recycler;
    private CustomAdapter adapter;
    private LayoutInflater layoutInflater;
    private List<ListItem> dataList;
    private MainActivityPresenterImpl presenter;
    private static final String INTENT_EXTRA = "clickedItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.edit_input);
        result = findViewById(R.id.result);
        textButton = findViewById(R.id.textView_button);
        recycler = findViewById(R.id.recycler);

        layoutInflater = getLayoutInflater();

        presenter = new MainActivityPresenterImpl(this, new RepositoryImpl());

        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAddButtonClick();
            }
        });
    }

    @Override
    public String getInput() {
        return input.getText().toString();
    }

    @Override
    public void displayResult(String editTextInput) {
        result.setText(editTextInput);
        input.setText("");
    }

    @Override
    public void displayError() {
        Toast.makeText(this, R.string.input_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupAdapter(List<ListItem> dataList) {
        this.dataList = dataList;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        adapter = new CustomAdapter();
        recycler.setAdapter(adapter);
    }

    @Override
    public void updateRecycler(ListItem listItem) {
        dataList.add(listItem);
        adapter.notifyItemInserted(dataList.size() - 1);
    }


    /***********************************************************************************************
     *
     * Adapter class for RecyclerView - START
     *
     **********************************************************************************************/

    private class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.list_item, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            ListItem currentItem = dataList.get(position);

            holder.dataItemText.setText(currentItem.getInput());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView dataItemText;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.dataItemText = itemView.findViewById(R.id.list_item_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ListItem currentItem = dataList.get(this.getAdapterPosition());
//            int position = this.getAdapterPosition();
//            String positionText = Integer.toString(position);
//            Toast.makeText(getApplicationContext(), positionText, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(INTENT_EXTRA, currentItem.getInput());

            startActivity(intent);
        }
    }
}
