package com.jdenner.smarthouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juliano on 26/08/2016.
 */
public class LinkDAO {

    public static void salvar(Context context, Link link) {
        if (link.getCodigo() == 0) {
            inserir(context, link);
        } else {
            alterar(context, link);
        }
    }

    private static void inserir(Context context, Link link) {
        String table = "TbLink";

        ContentValues values = new ContentValues();
        values.put("nome", link.getNome());
        values.put("urlOn", link.getUrlOn());
        values.put("urlOff", link.getUrlOff());

        Conexao con = new Conexao(context);
        SQLiteDatabase db = con.getWritableDatabase();
        db.insert(table, null, values);
        db.close();
        con.close();
    }

    private static void alterar(Context context, Link link) {
        String table = "TbLink";

        ContentValues values = new ContentValues();
        values.put("nome", link.getNome());
        values.put("urlOn", link.getUrlOn());
        values.put("urlOff", link.getUrlOff());

        String whereClause = "codigo=?";
        String whereArgs[] = {String.valueOf(link.getCodigo())};

        Conexao con = new Conexao(context);
        SQLiteDatabase db = con.getWritableDatabase();
        db.update(table, values, whereClause, whereArgs);
        db.close();
        con.close();
    }

    public static void excluir(Context context, Link link) {
        String table = "TbLink";

        String whereClause = "codigo=?";
        String whereArgs[] = {String.valueOf(link.getCodigo())};

        Conexao con = new Conexao(context);
        SQLiteDatabase db = con.getWritableDatabase();
        db.delete(table, whereClause, whereArgs);
        db.close();
        con.close();
    }

    public static List<Link> listar(Context context) {
        String table = "TbLink";

        String[] columns = new String[]{"codigo", "nome", "urlOn", "urlOff"};

        Conexao con = new Conexao(context);
        SQLiteDatabase db = con.getWritableDatabase();
        Cursor cursor = db.query(table, columns, null, null, null, null, null);

        List<Link> lista = new ArrayList<>();
        while (cursor.moveToNext()) {
            Link link = new Link();
            link.setCodigo(cursor.getInt(0));
            link.setNome(cursor.getString(1));
            link.setUrlOn(cursor.getString(2));
            link.setUrlOff(cursor.getString(3));
            lista.add(link);
        }

        db.close();
        con.close();

        return lista;
    }
}
