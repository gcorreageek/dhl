package dao;

/**
 * Created by Gustavo on 26/11/16.
 */

public class UserDao   {

    private DaoFactory daoFactory;


    public UserDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    public UserDao() {
    }


//
//    public void insert(User user)  {
//        SQLiteDatabase db = daoFactory.getWritableDatabase();
//
//        ContentValues dados = new ContentValues();
//        dados.put("name", user.getName());
//
//        db.insert("user", null, dados );
//        daoFactory.close();
//    }
//
//    public List<User> searchUser()  {
//        String sql = "SELECT * FROM user;";
//
//        SQLiteDatabase db  = daoFactory.getReadableDatabase();
//        Cursor c = db.rawQuery(sql, null);
//
//        List<User> users = new ArrayList<User>();
//        while (c.moveToNext()) {
//            User user = new User();
//            user.setId(c.getLong(c.getColumnIndex("id")));
//            user.setName(c.getString(c.getColumnIndex("name")));
//
//            users.add(user);
//        }
//        c.close();
//        daoFactory.close();
//        return users;
//
//    }










}
