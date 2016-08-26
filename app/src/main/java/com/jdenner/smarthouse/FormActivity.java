package com.jdenner.smarthouse;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by Juliano on 26/08/2016.
 */
public class FormActivity extends AppCompatActivity {

    private Link link;
    private EditText nome;
    private EditText urlOn;
    private EditText urlOff;
    private LinearLayout pnBtn;
    private Button btnSalvar;
    private ImageButton btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nome = (EditText) findViewById(R.id.nome);
        urlOn = (EditText) findViewById(R.id.urlOn);
        urlOff = (EditText) findViewById(R.id.urlOff);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnExcluir = (ImageButton) findViewById(R.id.btnExcluir);
        pnBtn = (LinearLayout) findViewById(R.id.pn_btn);

        link = (Link) getIntent().getExtras().getSerializable("link");
        nome.setText(link.getNome());
        urlOn.setText(link.getUrlOn());
        urlOff.setText(link.getUrlOff());

        if (link.getCodigo() == 0) {
            pnBtn.removeView(btnExcluir);
        }
    }

    public void handleOnClickSalvar(View view) {
        link.setNome(nome.getText().toString());
        link.setUrlOn(urlOn.getText().toString());
        link.setUrlOff(urlOff.getText().toString());

        SaveData sd = new SaveData();
        sd.execute(link);
    }

    public void handleOnClickExcluir(View view) {
        DeleteData dd = new DeleteData();
        dd.execute(link);
    }

    private class SaveData extends AsyncTask<Link, Void, Void> {

        @Override
        protected Void doInBackground(Link... link) {
            LinkDAO.salvar(getApplicationContext(), link[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    private class DeleteData extends AsyncTask<Link, Void, Void> {

        @Override
        protected Void doInBackground(Link... link) {
            LinkDAO.excluir(getApplicationContext(), link[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
