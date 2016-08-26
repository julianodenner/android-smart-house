package com.jdenner.smarthouse;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinkAdapter adapter;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new LinkAdapter(this);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

        LoadList load = new LoadList();
        load.execute();
    }

    public void handleOnClickAdd(View view) {
        //TODO
    }

    public void handleOnClickEditar(View view) {
        //TODO
    }

    private class LoadList extends AsyncTask<Void, Void, List<Link>> {

        @Override
        protected List<Link> doInBackground(Void... params) {
            return LinkDAO.listar(getApplicationContext());
        }

        @Override
        protected void onPostExecute(List<Link> links) {
            adapter.setLinks(links);
            gridView.setAdapter(adapter);
        }
    }
}
