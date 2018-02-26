package com.josephcostlow.mvpsample.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.josephcostlow.mvpsample.R;
import com.josephcostlow.mvpsample.contract.DetailActivityContract;
import com.josephcostlow.mvpsample.presentation.DetailActivityPresenterImpl;

public class DetailActivity extends AppCompatActivity implements DetailActivityContract.View {

    TextView itemDetailText;
    DetailActivityContract.Presenter presenter;
    private static final String INTENT_EXTRA = "clickedItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemDetailText = findViewById(R.id.detail_text);

        presenter = new DetailActivityPresenterImpl(this);

        presenter.utilizeIntent(presenter.getIntent());
    }

    @Override
    public String getClickedItemIntent() {
        return getIntent().getStringExtra(INTENT_EXTRA);
    }

    @Override
    public void setDetailText(String intentExtra) {
        itemDetailText.setText(intentExtra);
    }

    @Override
    public void setPresenter(DetailActivityContract.Presenter impl) {
        presenter = impl;
    }
}
