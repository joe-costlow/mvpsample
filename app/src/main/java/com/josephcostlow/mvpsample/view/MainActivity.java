package com.josephcostlow.mvpsample.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.josephcostlow.mvpsample.Injection;
import com.josephcostlow.mvpsample.R;
import com.josephcostlow.mvpsample.contract.MainActivityContract;
import com.josephcostlow.mvpsample.model.ListItem;
import com.josephcostlow.mvpsample.presentation.MainActivityPresenterImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private EditText input;
    private TextView textButton, result, emptyRecycler;
    private RecyclerView recycler;
    private CustomAdapter adapter;
    private LayoutInflater layoutInflater;
    private List<ListItem> dataList;
    private MainActivityContract.Presenter presenter;
    private static final String INTENT_EXTRA_AUTHOR = "searchedAuthor";
    private static final String INTENT_EXTRA_POSITION = "clickedPosition";
    private static final String RV_STATE_KEY = "recyclerState";
    private static Bundle rvStateBundle;
    private static String BASE_URL, author;
    Parcelable rvState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BASE_URL = getResources().getString(R.string.github_base_url);

        input = findViewById(R.id.edit_input);
        result = findViewById(R.id.result);
        textButton = findViewById(R.id.textView_button);
        emptyRecycler = findViewById(R.id.empty_recycler);
        recycler = findViewById(R.id.recycler);

        layoutInflater = getLayoutInflater();

        presenter = new MainActivityPresenterImpl(this, Injection.provideRepository());

        if (savedInstanceState != null) {
            presenter.start();
        }

        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
                presenter.onSearchButtonClick(BASE_URL, getInput());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recycler.getLayoutManager() != null) {
            if (rvStateBundle != null) {
                rvState = rvStateBundle.getParcelable(RV_STATE_KEY);
                recycler.getLayoutManager().onRestoreInstanceState(rvState);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (recycler.getLayoutManager() != null) {
            rvStateBundle = new Bundle();
            rvState = recycler.getLayoutManager().onSaveInstanceState();
            rvStateBundle.putParcelable(RV_STATE_KEY, rvState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (recycler.getLayoutManager() != null) {
            rvState = recycler.getLayoutManager().onSaveInstanceState();
            outState.putParcelable(RV_STATE_KEY, rvState);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (recycler.getLayoutManager() != null) {
            rvState = savedInstanceState.getParcelable(RV_STATE_KEY);
        }
    }

    public String getInput() {
        return input.getText().toString();
    }

    @Override
    public void displayResult(String editTextInput) {
        author = editTextInput;
        result.setText(editTextInput);
        input.setText("");
    }

    @Override
    public void displayInputError() {
        Toast.makeText(this, R.string.input_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupAdapter(List<ListItem> data) {
        this.dataList = data;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        adapter = new CustomAdapter();
        recycler.setAdapter(adapter);
    }

    @Override
    public void showRecycler() {
        emptyRecycler.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyRecycler() {
        recycler.setVisibility(View.GONE);
        emptyRecycler.setVisibility(View.VISIBLE);
        emptyRecycler.setText(R.string.empty_recycler);
    }

    @Override
    public void setPresenter(MainActivityContract.Presenter impl) {
        presenter = impl;
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

            holder.dataItemText.setText(currentItem.getName());
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
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(INTENT_EXTRA_AUTHOR, author);
            intent.putExtra(INTENT_EXTRA_POSITION, this.getAdapterPosition());

            startActivity(intent);
        }
    }
}
