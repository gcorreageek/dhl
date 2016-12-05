package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;
import android.util.Log;


/**
 * Clase que administra la conexión de la base de datos y su estructuración
 */
public class BaseDatosDHL extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "dhl.db";

    private static final int VERSION_ACTUAL = 2;

    private final Context contexto;

    interface Tablas {
        String USER = "user";
//        String DETALLE_PEDIDO = "detalle_pedido";
//        String PRODUCTO = "producto";
//        String CLIENTE = "cliente";
//        String FORMA_PAGO = "forma_pago";
    }
    interface ColumnasUser {
        String ID = "id";
        String LOGIN = "login";
        String EMAIL = "email";
        String FIRST_NAME = "first_name";
        String LAST_NAME = "last_name";
        String ACTIVATED = "activated";
        String LANG_KEY = "lang_key";
        String WEIGH = "weigh";
        String HEIGHT = "height";
        String BIRTHDAY = "birthday";
        String SEX = "sex";
        String COUNTRY = "country";
        String DISABLED_PROFILE = "disabled_profile";
        String SHOW_WEIGH = "show_weigh";
        String SHOW_HEIGHT = "show_height";
        String SHOW_BIRTHDAY = "show_birthday";
        String SHOW_SEX = "show_sex";
        String SHOW_COUNTRY = "show_country";
        String SHOW_LANGUAJE = "show_languaje";
        String NOTIFICATION_NEWS = "notification_news";
        String OPTIONS = "options";
        String USER_IMAGEN_PATH_IMAGE = "user_imagen_path_image";
        String HASH_LIKE = "hash_like";
        String HASH_DISLIKE = "hash_dislike";
    }

    interface Referencias {

//        String ID_CABECERA_PEDIDO = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
//                Tablas.CABECERA_PEDIDO, CabecerasPedido.ID);
//
//        String ID_PRODUCTO = String.format("REFERENCES %s(%s)",
//                Tablas.PRODUCTO, Productos.ID);
//
//        String ID_CLIENTE = String.format("REFERENCES %s(%s)",
//                Tablas.CLIENTE, Clientes.ID);
//
//        String ID_FORMA_PAGO = String.format("REFERENCES %s(%s)",
//                Tablas.FORMA_PAGO, FormasPago.ID);
    }

    public BaseDatosDHL(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = contexto;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE user (id INTEGER PRIMARY KEY, name TEXT);";
        String createTableUser = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s INTEGER," +
                        "%s TEXT,"+"%s TEXT,"+"%s TEXT,"+"%s TEXT,"+
                        "%s TEXT ,"+"%s TEXT,"+"%s TEXT,"+"%s TEXT,"+
                        "%s DATETIME ,"+"%s TEXT,"+"%s TEXT,"+"%s TEXT,"+
                        "%s TEXT ,"+"%s TEXT,"+"%s TEXT,"+"%s TEXT,"+
                        "%s TEXT ,"+"%s TEXT,"+"%s TEXT,"+"%s TEXT,"+
                        "%s TEXT,"+"%s TEXT,"+"%s TEXT"+");",
                Tablas.USER, BaseColumns._ID,ColumnasUser.ID,
                ColumnasUser.LOGIN,ColumnasUser.EMAIL, ColumnasUser.FIRST_NAME, ColumnasUser.LAST_NAME,
                ColumnasUser.ACTIVATED,ColumnasUser.LANG_KEY,ColumnasUser.WEIGH,ColumnasUser.HEIGHT,
                ColumnasUser.BIRTHDAY,ColumnasUser.SEX,ColumnasUser.COUNTRY,ColumnasUser.DISABLED_PROFILE,
                ColumnasUser.SHOW_WEIGH,ColumnasUser.SHOW_HEIGHT,ColumnasUser.SHOW_BIRTHDAY,ColumnasUser.SHOW_SEX,
                ColumnasUser.SHOW_COUNTRY,ColumnasUser.SHOW_LANGUAJE,ColumnasUser.NOTIFICATION_NEWS,ColumnasUser.OPTIONS,
                ColumnasUser.USER_IMAGEN_PATH_IMAGE,ColumnasUser.HASH_LIKE,ColumnasUser.HASH_DISLIKE);
        Log.i(this.toString(), createTableUser);
        db.execSQL(createTableUser);

//        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "%s TEXT NOT NULL %s,%s INTEGER NOT NULL CHECK (%s>0),%s INTEGER NOT NULL %s," +
//                        "%s INTEGER NOT NULL,%s REAL NOT NULL,UNIQUE (%s,%s) )",
//                Tablas.DETALLE_PEDIDO, BaseColumns._ID,
//                DetallesPedido.ID, Referencias.ID_CABECERA_PEDIDO,
//                DetallesPedido.SECUENCIA, DetallesPedido.SECUENCIA,
//                DetallesPedido.ID_PRODUCTO, Referencias.ID_PRODUCTO,
//                DetallesPedido.CANTIDAD, DetallesPedido.PRECIO,
//                DetallesPedido.ID, DetallesPedido.SECUENCIA));
//
//        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s REAL NOT NULL," +
//                        "%s INTEGER NOT NULL CHECK(%s>=0) )",
//                Tablas.PRODUCTO, BaseColumns._ID,
//                Productos.ID, Productos.NOMBRE, Productos.PRECIO,
//                Productos.EXISTENCIAS, Productos.EXISTENCIAS));
//
//        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s TEXT NOT NULL,%s )",
//                Tablas.CLIENTE, BaseColumns._ID,
//                Clientes.ID, Clientes.NOMBRES, Clientes.APELLIDOS, Clientes.TELEFONO));
//
//        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL )",
//                Tablas.FORMA_PAGO, BaseColumns._ID,
//                FormasPago.ID, FormasPago.NOMBRE));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.USER);
//        db.execSQL("DROP TABLE IF EXISTS " + Tablas.DETALLE_PEDIDO);
//        db.execSQL("DROP TABLE IF EXISTS " + Tablas.PRODUCTO);
//        db.execSQL("DROP TABLE IF EXISTS " + Tablas.CLIENTE);
//        db.execSQL("DROP TABLE IF EXISTS " + Tablas.FORMA_PAGO);

        onCreate(db);
    }


}