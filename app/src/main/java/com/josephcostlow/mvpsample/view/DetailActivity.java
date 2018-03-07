package com.josephcostlow.mvpsample.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.josephcostlow.mvpsample.Injection;
import com.josephcostlow.mvpsample.R;
import com.josephcostlow.mvpsample.contract.DetailActivityContract;
import com.josephcostlow.mvpsample.presentation.DetailActivityPresenterImpl;

public class DetailActivity extends AppCompatActivity implements DetailActivityContract.View {

    TextView itemDetailText;
    DetailActivityContract.Presenter presenter;
    private static final String INTENT_EXTRA_AUTHOR = "searchedAuthor";
    private static final String INTENT_EXTRA_POSITION = "clickedPosition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemDetailText = findViewById(R.id.detail_text);

        presenter = new DetailActivityPresenterImpl(this, Injection.provideRepository());

        presenter.start();
    }

    @Override
    public void getClickedItemIntent() {
        String author = getIntentExtraAuthor();
        int position = getIntentExtraPosition();
        presenter.utilizeIntent(author, position);
    }

    @Override
    public void setDetailText(String intentExtra) {
        itemDetailText.setText(intentExtra);
    }

    @Override
    public void setTitle(String author) {
        getSupportActionBar().setTitle(author);
    }

    @Override
    public void setPresenter(DetailActivityContract.Presenter impl) {
        presenter = impl;
    }

    private String getIntentExtraAuthor() {
        return getIntent().getStringExtra(INTENT_EXTRA_AUTHOR);
    }

    private int getIntentExtraPosition() {
        return getIntent().getIntExtra(INTENT_EXTRA_POSITION, 0);
    }
}
