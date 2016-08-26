package com.jdenner.smarthouse;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.GridView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juliano on 26/08/2016.
 */
public class LinkAdapter extends BaseAdapter {

    private Context context;
    private List<Link> links = new ArrayList<>();

    public LinkAdapter(Context context) {
        this.context = context;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public int getCount() {
        return links.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (position == links.size()) {
            return null;
        }
        return links.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (position == links.size()) {
            return 0;
        }
        return links.get(position).getCodigo();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (position == links.size()) {
            CheckedTextView btnAdd = (CheckedTextView) inflater.inflate(R.layout.btn_add, null);
            btnAdd.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 300));
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    open(new Link());
                }
            });
            return btnAdd;
        }

        CheckedTextView btn = (CheckedTextView) inflater.inflate(R.layout.btn, null);
        btn.setText(links.get(position).getNome());
        btn.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 300));
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                open((Link) getItem(position));
                return true;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckedTextView btn = (CheckedTextView) v;
                String url = "";
                if (btn.isChecked()) {
                    url = ((Link) getItem(position)).getUrlOn();
                } else {
                    url = ((Link) getItem(position)).getUrlOff();
                }
                OpenLink task = new OpenLink(url, btn);
                task.execute();
            }
        });
        return btn;
    }

    private void open(Link link) {
        Intent intent = new Intent(context, FormActivity.class);
        intent.putExtra("link", link);
        context.startActivity(intent);
    }

    private class OpenLink extends AsyncTask<Void, Void, Boolean> {

        private String path;
        private CheckedTextView btn;

        public OpenLink(String path, CheckedTextView btn) {
            this.path = path;
            this.btn = btn;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            Boolean sucesso = true;
            try {
                URL url = new URL(path);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(100);
                urlConnection.setConnectTimeout(100);
                urlConnection.connect();
                urlConnection.getContent();
            } catch (IOException e) {
                Log.e("IOException ", e.getMessage(), e);
                sucesso = false;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return sucesso;
        }

        @Override
        protected void onPostExecute(Boolean sucesso) {
            super.onPostExecute(sucesso);
            if (sucesso) {
                btn.setChecked(!btn.isChecked());
            }
        }
    }

}
