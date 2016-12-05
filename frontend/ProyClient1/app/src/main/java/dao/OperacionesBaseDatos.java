package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import model.User;
import util.UtilDates;


public final class OperacionesBaseDatos {

    private static BaseDatosDHL baseDatos;

    private static OperacionesBaseDatos instancia = new OperacionesBaseDatos();


    private OperacionesBaseDatos() {
    }

    public static OperacionesBaseDatos obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new BaseDatosDHL(contexto);
        }
        return instancia;
    }

//    public Cursor obtenerCabecerasPedidos() {
//        SQLiteDatabase db = baseDatos.getReadableDatabase();
//
//        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
//
//        builder.setTables(CABECERA_PEDIDO_JOIN_CLIENTE_Y_FORMA_PAGO);
//
//        return builder.query(db, proyCabeceraPedido, null, null, null, null, null);
//    }
//
//    public Cursor obtenerCabeceraPorId(String id) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        String selection = String.format("%s=?", CabecerasPedido.ID);
//        String[] selectionArgs = {id};
//
//        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
//        builder.setTables(CABECERA_PEDIDO_JOIN_CLIENTE_Y_FORMA_PAGO);
//
//        String[] proyeccion = {
//                Tablas.CABECERA_PEDIDO + "." + CabecerasPedido.ID,
//                CabecerasPedido.FECHA,
//                Clientes.NOMBRES,
//                Clientes.APELLIDOS,
//                FormasPago.NOMBRE};
//
//        return builder.query(db, proyeccion, selection, selectionArgs, null, null, null);
//    }
//
//    public String insertarCabeceraPedido(CabeceraPedido pedido) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        // Generar Pk
//        String idCabeceraPedido = CabecerasPedido.generarIdCabeceraPedido();
//
//        ContentValues valores = new ContentValues();
//        valores.put(CabecerasPedido.ID, idCabeceraPedido);
//        valores.put(CabecerasPedido.FECHA, pedido.fecha);
//        valores.put(CabecerasPedido.ID_CLIENTE, pedido.idCliente);
//        valores.put(CabecerasPedido.ID_FORMA_PAGO, pedido.idFormaPago);
//
//        // Insertar cabecera
//        db.insertOrThrow(Tablas.CABECERA_PEDIDO, null, valores);
//
//        return idCabeceraPedido;
//    }
//
//    public boolean actualizarCabeceraPedido(CabeceraPedido pedidoNuevo) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        ContentValues valores = new ContentValues();
//        valores.put(CabecerasPedido.FECHA, pedidoNuevo.fecha);
//        valores.put(CabecerasPedido.ID_CLIENTE, pedidoNuevo.idCliente);
//        valores.put(CabecerasPedido.ID_FORMA_PAGO, pedidoNuevo.idFormaPago);
//
//        String whereClause = String.format("%s=?", CabecerasPedido.ID);
//        String[] whereArgs = {pedidoNuevo.idCabeceraPedido};
//
//        int resultado = db.update(Tablas.CABECERA_PEDIDO, valores, whereClause, whereArgs);
//
//        return resultado > 0;
//    }
//
//    public boolean eliminarCabeceraPedido(String idCabeceraPedido) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        String whereClause = CabecerasPedido.ID + "=?";
//        String[] whereArgs = {idCabeceraPedido};
//
//        int resultado = db.delete(Tablas.CABECERA_PEDIDO, whereClause, whereArgs);
//
//        return resultado > 0;
//    }
//    // [/OPERACIONES_CABECERA_PEDIDO]
//
//    // [OPERACIONES_DETALLE_PEDIDO]
//    public Cursor obtenerDetallesPedido() {
//        SQLiteDatabase db = baseDatos.getReadableDatabase();
//
//        String sql = String.format("SELECT * FROM %s", Tablas.DETALLE_PEDIDO);
//
//        return db.rawQuery(sql, null);
//    }
//
//    public Cursor obtenerDetallesPorIdPedido(String idCabeceraPedido) {
//        SQLiteDatabase db = baseDatos.getReadableDatabase();
//
//        String sql = String.format("SELECT * FROM %s WHERE %s=?",
//                Tablas.DETALLE_PEDIDO, CabecerasPedido.ID);
//
//        String[] selectionArgs = {idCabeceraPedido};
//
//        return db.rawQuery(sql, selectionArgs);
//
//    }
//
//    public String insertarDetallePedido(DetallePedido detalle) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        ContentValues valores = new ContentValues();
//        valores.put(DetallesPedido.ID, detalle.idCabeceraPedido);
//        valores.put(DetallesPedido.SECUENCIA, detalle.secuencia);
//        valores.put(DetallesPedido.ID_PRODUCTO, detalle.idProducto);
//        valores.put(DetallesPedido.CANTIDAD, detalle.cantidad);
//        valores.put(DetallesPedido.PRECIO, detalle.precio);
//
//        db.insertOrThrow(Tablas.DETALLE_PEDIDO, null, valores);
//
//        return String.format("%s#%d", detalle.idCabeceraPedido, detalle.secuencia);
//
//    }
//
//    public boolean actualizarDetallePedido(DetallePedido detalle) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        ContentValues valores = new ContentValues();
//        valores.put(DetallesPedido.SECUENCIA, detalle.secuencia);
//        valores.put(DetallesPedido.CANTIDAD, detalle.cantidad);
//        valores.put(DetallesPedido.PRECIO, detalle.precio);
//
//        String selection = String.format("%s=? AND %s=?",
//                DetallesPedido.ID, DetallesPedido.SECUENCIA);
//        final String[] whereArgs = {detalle.idCabeceraPedido, String.valueOf(detalle.secuencia)};
//
//        int resultado = db.update(Tablas.DETALLE_PEDIDO, valores, selection, whereArgs);
//
//        return resultado > 0;
//    }
//
//    public boolean eliminarDetallePedido(String idCabeceraPedido, int secuencia) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        String selection = String.format("%s=? AND %s=?",
//                DetallesPedido.ID, DetallesPedido.SECUENCIA);
//        String[] whereArgs = {idCabeceraPedido, String.valueOf(secuencia)};
//
//        int resultado = db.delete(Tablas.DETALLE_PEDIDO, selection, whereArgs);
//
//        return resultado > 0;
//    }
//    // [/OPERACIONES_DETALLE_PEDIDO]
//
//    // [OPERACIONES_PRODUCTO]
    public Cursor obtenerUser() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosDHL.Tablas.USER);
        return db.rawQuery(sql, null);
    }
    public User getUser() {
        User user = null;
        Cursor c = this.obtenerUser();
        while (c.moveToNext()) {
            user = new User();
            user.setId(c.getLong(c.getColumnIndex(BaseDatosDHL.ColumnasUser.ID)));
            user.setLastName(c.getString(c.getColumnIndex(BaseDatosDHL.ColumnasUser.LAST_NAME)));

        }
        c.close();
        return user;
    }
//
    public void insertUser(User user) {

//        this.db = helper.getWritableDatabase();
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
//        valores.put(BaseDatosDHL.ColumnasUser.ID, user.getId());
        valores.put(BaseDatosDHL.ColumnasUser.LOGIN, user.getLogin());
        valores.put(BaseDatosDHL.ColumnasUser.EMAIL, user.getEmail());
//        valores.put(BaseDatosDHL.ColumnasUser.FIRST_NAME, user.getFirstName());
//        valores.put(BaseDatosDHL.ColumnasUser.LAST_NAME, user.getLastName());
//        valores.put(BaseDatosDHL.ColumnasUser.ACTIVATED, user.getActivated());
//        valores.put(BaseDatosDHL.ColumnasUser.WEIGH, user.getWeigh());
//        valores.put(BaseDatosDHL.ColumnasUser.HEIGHT, user.getHeight());
//        if(user.getBirthday()!=null){
//            valores.put(BaseDatosDHL.ColumnasUser.BIRTHDAY, UtilDates.convertDateToString(user.getBirthday()));
//        }
//
//        valores.put(BaseDatosDHL.ColumnasUser.SEX, user.getSex());
//        valores.put(BaseDatosDHL.ColumnasUser.COUNTRY, user.getCountry());
//        valores.put(BaseDatosDHL.ColumnasUser.DISABLED_PROFILE, user.getDisabledProfile());
//        valores.put(BaseDatosDHL.ColumnasUser.SHOW_WEIGH, user.getShowWeigh());
//        valores.put(BaseDatosDHL.ColumnasUser.SHOW_HEIGHT, user.getShowHeight());
//        valores.put(BaseDatosDHL.ColumnasUser.SHOW_BIRTHDAY, user.getShowBirthday());
//        valores.put(BaseDatosDHL.ColumnasUser.SHOW_SEX, user.getShowSex());
//        valores.put(BaseDatosDHL.ColumnasUser.SHOW_COUNTRY, user.getShowCountry());
//        valores.put(BaseDatosDHL.ColumnasUser.SHOW_LANGUAJE, user.getShowLanguaje());
//        valores.put(BaseDatosDHL.ColumnasUser.NOTIFICATION_NEWS, user.getNotificationNews());
//        valores.put(BaseDatosDHL.ColumnasUser.OPTIONS, user.getOptions());
//        valores.put(BaseDatosDHL.ColumnasUser.USER_IMAGEN_PATH_IMAGE, user.getImage());
//        valores.put(BaseDatosDHL.ColumnasUser.HASH_LIKE, user.getHashLike());
//        valores.put(BaseDatosDHL.ColumnasUser.HASH_DISLIKE, user.getHashDislike());

        db.insert(BaseDatosDHL.Tablas.USER, null, valores);
        baseDatos.close();
//        if(db.isOpen()){
//            db.close();
//        }

    }
    public boolean updateUser(User user) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(BaseDatosDHL.ColumnasUser.FIRST_NAME, user.getFirstName());
        valores.put(BaseDatosDHL.ColumnasUser.LAST_NAME, user.getLastName());
        valores.put(BaseDatosDHL.ColumnasUser.ACTIVATED, user.getActivated());
        valores.put(BaseDatosDHL.ColumnasUser.WEIGH, user.getWeigh());
        valores.put(BaseDatosDHL.ColumnasUser.HEIGHT, user.getHeight());
        if(user.getBirthday()!=null){
            valores.put(BaseDatosDHL.ColumnasUser.BIRTHDAY, UtilDates.convertDateToString(user.getBirthday()));
        }
        valores.put(BaseDatosDHL.ColumnasUser.SEX, user.getSex());
        valores.put(BaseDatosDHL.ColumnasUser.COUNTRY, user.getCountry());
        valores.put(BaseDatosDHL.ColumnasUser.DISABLED_PROFILE, user.getDisabledProfile());
        valores.put(BaseDatosDHL.ColumnasUser.SHOW_WEIGH, user.getShowWeigh());
        valores.put(BaseDatosDHL.ColumnasUser.SHOW_HEIGHT, user.getShowHeight());
        valores.put(BaseDatosDHL.ColumnasUser.SHOW_BIRTHDAY, user.getShowBirthday());
        valores.put(BaseDatosDHL.ColumnasUser.SHOW_SEX, user.getShowSex());
        valores.put(BaseDatosDHL.ColumnasUser.SHOW_COUNTRY, user.getShowCountry());
        valores.put(BaseDatosDHL.ColumnasUser.SHOW_LANGUAJE, user.getShowLanguaje());
        valores.put(BaseDatosDHL.ColumnasUser.NOTIFICATION_NEWS, user.getNotificationNews());
        valores.put(BaseDatosDHL.ColumnasUser.OPTIONS, user.getOptions());
        valores.put(BaseDatosDHL.ColumnasUser.USER_IMAGEN_PATH_IMAGE, user.getImage());
        valores.put(BaseDatosDHL.ColumnasUser.HASH_LIKE, user.getHashLike());
        valores.put(BaseDatosDHL.ColumnasUser.HASH_DISLIKE, user.getHashDislike());

        String whereClause = String.format("%s=?", BaseDatosDHL.ColumnasUser.EMAIL);
        String[] whereArgs = { user.getEmail()};

        int resultado = db.update(BaseDatosDHL.Tablas.USER, valores, whereClause, whereArgs);

        return resultado > 0;
    }
//
//    public boolean eliminarProducto(String idProducto) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        String whereClause = String.format("%s=?", Productos.ID);
//        String[] whereArgs = {idProducto};
//
//        int resultado = db.delete(Tablas.PRODUCTO, whereClause, whereArgs);
//
//        return resultado > 0;
//    }
//    // [/OPERACIONES_PRODUCTO]
//
//    // [OPERACIONES_CLIENTE]
//    public Cursor obtenerClientes() {
//        SQLiteDatabase db = baseDatos.getReadableDatabase();
//
//        String sql = String.format("SELECT * FROM %s", Tablas.CLIENTE);
//
//        return db.rawQuery(sql, null);
//    }
//
//    public String insertarCliente(Cliente cliente) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        // Generar Pk
//        String idCliente = Clientes.generarIdCliente();
//
//        ContentValues valores = new ContentValues();
//        valores.put(Clientes.ID, idCliente);
//        valores.put(Clientes.NOMBRES, cliente.nombres);
//        valores.put(Clientes.APELLIDOS, cliente.apellidos);
//        valores.put(Clientes.TELEFONO, cliente.telefono);
//
//        return db.insertOrThrow(Tablas.CLIENTE, null, valores) > 0 ? idCliente : null;
//    }
//
//    public boolean actualizarCliente(Cliente cliente) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        ContentValues valores = new ContentValues();
//        valores.put(Clientes.NOMBRES, cliente.nombres);
//        valores.put(Clientes.APELLIDOS, cliente.apellidos);
//        valores.put(Clientes.TELEFONO, cliente.telefono);
//
//        String whereClause = String.format("%s=?", Clientes.ID);
//        final String[] whereArgs = {cliente.idCliente};
//
//        int resultado = db.update(Tablas.CLIENTE, valores, whereClause, whereArgs);
//
//        return resultado > 0;
//    }
//
//    public boolean eliminarCliente(String idCliente) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        String whereClause = String.format("%s=?", Clientes.ID);
//        final String[] whereArgs = {idCliente};
//
//        int resultado = db.delete(Tablas.CLIENTE, whereClause, whereArgs);
//
//        return resultado > 0;
//    }
//    // [/OPERACIONES_CLIENTE]
//
//    // [OPERACIONES_FORMA_PAGO]
//    public Cursor obtenerFormasPago() {
//        SQLiteDatabase db = baseDatos.getReadableDatabase();
//
//        String sql = String.format("SELECT * FROM %s", Tablas.FORMA_PAGO);
//
//        return db.rawQuery(sql, null);
//    }
//
//    public String insertarFormaPago(FormaPago formaPago) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        // Generar Pk
//        String idFormaPago = FormasPago.generarIdFormaPago();
//
//        ContentValues valores = new ContentValues();
//        valores.put(FormasPago.ID, idFormaPago);
//        valores.put(FormasPago.NOMBRE, formaPago.nombre);
//
//        return db.insertOrThrow(Tablas.FORMA_PAGO, null, valores) > 0 ? idFormaPago : null;
//    }
//
//    public boolean actualizarFormaPago(FormaPago formaPago) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        ContentValues valores = new ContentValues();
//        valores.put(FormasPago.NOMBRE, formaPago.nombre);
//
//        String whereClause = String.format("%s=?", FormasPago.ID);
//        String[] whereArgs = {formaPago.idFormaPago};
//
//        int resultado = db.update(Tablas.FORMA_PAGO, valores, whereClause, whereArgs);
//
//        return resultado > 0;
//    }
//
//    public boolean eliminarFormaPago(String idFormaPago) {
//        SQLiteDatabase db = baseDatos.getWritableDatabase();
//
//        String whereClause = String.format("%s=?", FormasPago.ID);
//        String[] whereArgs = {idFormaPago};
//
//        int resultado = db.delete(Tablas.FORMA_PAGO, whereClause, whereArgs);
//
//        return resultado > 0;
//    }
//
    public SQLiteDatabase getDb() {
        return baseDatos.getWritableDatabase();
    }
//
//
//    // [/OPERACIONES_FORMA_PAGO]
//
//
//    private static final String CABECERA_PEDIDO_JOIN_CLIENTE_Y_FORMA_PAGO = "cabecera_pedido " +
//            "INNER JOIN cliente " +
//            "ON cabecera_pedido.id_cliente = cliente.id " +
//            "INNER JOIN forma_pago " +
//            "ON cabecera_pedido.id_forma_pago = forma_pago.id";
//
//
//    private final String[] proyCabeceraPedido = new String[]{
//            Tablas.CABECERA_PEDIDO + "." + CabecerasPedido.ID,
//            CabecerasPedido.FECHA,
//            Clientes.NOMBRES,
//            Clientes.APELLIDOS,
//            FormasPago.NOMBRE};

}
