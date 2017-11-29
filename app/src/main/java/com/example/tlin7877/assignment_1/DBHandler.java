package com.example.tlin7877.assignment_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlin7877 on 11/22/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "NotStarbucks";
    // Contacts table name
    private static final String TABLE_USER = "User";
    private static final String TABLE_CARD = "Card";
    private static final String TABLE_DRINK = "Drink";
    private static final String TABLE_ORDER = "Order";
    // User Table Columns names
    private static final String USER_EMAIL = "Email";
    private static final String USER_PASSWORD = "Password";
    private static final String USER_FIRST_NAME = "FirstName";
    private static final String USER_LAST_NAME = "LastName";
    private static final String USER_ADDR = "Address";
    private static final String USER_CITY = "City";
    private static final String USER_PROVINCE = "Province";
    private static final String USER_POSTALCODE = "PostalCode";
    private static final String USER_BIRTHDAY = "Birthday";
    private static final String USER_RECEIVEEMAIL = "ReceiveEmail";
    // Card Table Columns names
    private static final String CARD_NUMBER = "CardNumber";
    private static final String CARD_VALUE = "Value";
    private static final String CARD_PICTURE = "Picture";
    private static final String CARD_USER_EMAIL = "UserEmail";
    // Drink Table Columns names
    private static final String DRINK_ID = "DrinkID";
    private static final String DRINK_NAME = "Name";
    private static final String DRINK_PRICE = "Price";
    private static final String DRINK_DESCRIPTION = "Description";
    // Order Table Columns names
    private static final String ORDER_ID = "OrderID";
    private static final String ORDER_REF = "ReferenceNumber";
    private static final String ORDER_DRINK_ID = "DrinkID";
    private static final String ORDER_USER_EMAIL = "UserEmail";
    private static final String ORDER_SIZE = "Size";
    private static final String ORDER_COMMENT = "Comment";
    private static final String ORDER_Date = "Date";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table User
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + USER_EMAIL + " TEXT PRIMARY KEY,"
                + USER_PASSWORD + " TEXT NOT NULL,"
                + USER_FIRST_NAME + " TEXT NOT NULL,"
                + USER_LAST_NAME + " TEXT NOT NULL,"
                + USER_ADDR + " TEXT NOT NULL,"
                + USER_CITY + " TEXT NOT NULL,"
                + USER_PROVINCE + " TEXT NOT NULL,"
                + USER_POSTALCODE + " TEXT NOT NULL,"
                + USER_BIRTHDAY + " TEXT NOT NULL,"
                + USER_RECEIVEEMAIL + " INTEGER NOT NULL DEFAULT 1" + ");";
        //Boolean flag = (cursor.getInt(cursor.getColumnIndex("flag")) == 1);
        db.execSQL(CREATE_USER_TABLE);
        // Create table Card
        String CREATE_CARD_TABLE = "CREATE TABLE " + TABLE_CARD + "("
                + CARD_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CARD_VALUE + " REAL NOT NULL,"
                + CARD_PICTURE + " TEXT NOT NULL,"
                + CARD_USER_EMAIL + " TEXT NOT NULL,"
                + " FOREIGN KEY (" +CARD_USER_EMAIL +") REFERENCES "
                + TABLE_USER + "(" + USER_EMAIL + "));";
        db.execSQL(CREATE_CARD_TABLE);
        // Create table Drink
        String CREATE_DRINK_TABLE = "CREATE TABLE " + TABLE_DRINK + "("
                + DRINK_ID + " INTEGER PRIMARY KEY,"
                + DRINK_NAME + " TEXT NOT NULL,"
                + DRINK_PRICE + " REAL NOT NULL,"
                + DRINK_DESCRIPTION + " TEXT NOT NULL"+ ");";
        db.execSQL(CREATE_DRINK_TABLE);
        // Create table Order
        String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + "("
                + ORDER_ID + " INTEGER PRIMARY KEY,"
                + ORDER_REF + " TEXT NOT NULL,"
                + ORDER_DRINK_ID + " INTEGER NOT NULL,"
                + ORDER_USER_EMAIL + " TEXT NOT NULL,"
                + ORDER_SIZE + " TEXT NOT NULL,"
                + ORDER_COMMENT + " TEXT,"
                + ORDER_Date + " TEXT NOT NULL,"
                + " FOREIGN KEY (" + ORDER_DRINK_ID +") REFERENCES "
                + TABLE_DRINK + "(" + DRINK_ID + "),"
                + " FOREIGN KEY (" + ORDER_USER_EMAIL +") REFERENCES "
                + TABLE_USER + "(" + USER_EMAIL + "));";
        db.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        // Creating tables again
        onCreate(db);
    }


    /************************** User CRUD ****************************************/
    // Create new User
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_FIRST_NAME, user.getFirstName());
        values.put(USER_LAST_NAME, user.getLastName());
        values.put(USER_ADDR, user.getAddress());
        values.put(USER_CITY, user.getCity());
        values.put(USER_PROVINCE, user.getProvince());
        values.put(USER_POSTALCODE, user.getPostalCode());
        values.put(USER_BIRTHDAY, user.getBirthday());
        values.put(USER_RECEIVEEMAIL, user.getReceiveEmail());
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    // Read one User
    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { USER_EMAIL,
                        USER_PASSWORD,USER_FIRST_NAME, USER_LAST_NAME, USER_ADDR,
                        USER_CITY,USER_PROVINCE,USER_POSTALCODE,
                        USER_BIRTHDAY,USER_RECEIVEEMAIL}, USER_EMAIL + "=?",
                new String[] { email }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        User user = new User(cursor.getString(0),cursor.getString(1),
                cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5),cursor.getString(6),
                cursor.getString(7),cursor.getString(8),
                Integer.parseInt(cursor.getString(9)));
        // return User
        return user;
    }

    // Getting All Users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setEmail(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                user.setFirstName(cursor.getString(2));
                user.setLastName(cursor.getString(3));
                user.setAddress(cursor.getString(4));
                user.setCity(cursor.getString(5));
                user.setProvince(cursor.getString(6));
                user.setPostalCode(cursor.getString(7));
                user.setBirthday(cursor.getString(8));
                user.setReceiveEmail(Integer.parseInt(cursor.getString(9)));
                // Adding user to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        // return user list
        return userList;
    }

    // Getting users Count
    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    // Updating a user
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_FIRST_NAME, user.getFirstName());
        values.put(USER_LAST_NAME, user.getLastName());
        values.put(USER_ADDR, user.getAddress());
        values.put(USER_CITY, user.getCity());
        values.put(USER_PROVINCE, user.getProvince());
        values.put(USER_POSTALCODE, user.getPostalCode());
        values.put(USER_BIRTHDAY, user.getBirthday());
        values.put(USER_RECEIVEEMAIL, user.getReceiveEmail());
        // updating row
        return db.update(TABLE_USER, values, USER_EMAIL + " = ?",
                new String[]{String.valueOf(user.getEmail())});
    }

    // Deleting a user
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, USER_EMAIL + " = ?",
                new String[] { String.valueOf(user.getEmail()) });
        db.close();
    }


    /************************** Card CRUD ****************************************/
    // Create new Card
    public void addCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CARD_VALUE, card.getValue());
        values.put(CARD_PICTURE, card.getPicture());
        values.put(CARD_USER_EMAIL, card.getUserEmail());
        // Inserting Row
        db.insert(TABLE_CARD, null, values);
        db.close(); // Closing database connection
    }

    // Read one Card
    public Card getCard(int number) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CARD, new String[] { CARD_NUMBER,
                        CARD_VALUE, CARD_PICTURE, CARD_USER_EMAIL}, CARD_NUMBER + "=?",
                new String[] { String.valueOf(number) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Card card = new Card(Integer.parseInt(cursor.getString(0)),
                Float.parseFloat(cursor.getString(1)), cursor.getString(2),
                cursor.getString(3));
        // return Card
        return card;
    }

    // Getting All Cards
    public List<Card> getAllCards() {
        List<Card> cardList = new ArrayList<Card>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CARD;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Card card = new Card();
                card.setCardNumber(Integer.parseInt(cursor.getString(0)));
                card.setValue(Float.parseFloat(cursor.getString(1)));
                card.setPicture(cursor.getString(2));
                card.setUserEmail(cursor.getString(3));
                // Adding card to list
                cardList.add(card);
            } while (cursor.moveToNext());
        }
        // return card list
        return cardList;
    }

    // Getting cards Count
    public int getCardsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CARD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    // Updating a card
    public int updateCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CARD_VALUE, card.getValue());
        values.put(CARD_PICTURE, card.getPicture());
        values.put(CARD_USER_EMAIL, card.getUserEmail());

        // updating row
        return db.update(TABLE_CARD, values, CARD_NUMBER + " = ?",
                new String[]{String.valueOf(card.getCardNumber())});
    }

    // Deleting a card
    public void deleteCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CARD, CARD_NUMBER + " = ?",
                new String[] { String.valueOf(card.getCardNumber()) });
        db.close();
    }

    /************************** Drink CRUD ****************************************/
    // Create new Drink
    public void addDrink(Drink drink) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DRINK_NAME, drink.getName());
        values.put(DRINK_PRICE, drink.getPrice());
        values.put(DRINK_DESCRIPTION, drink.getDescription());
        // Inserting Row
        db.insert(TABLE_DRINK, null, values);
        db.close(); // Closing database connection
    }

    // Read one Drink
    public Drink getDrink(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DRINK, new String[] { DRINK_ID,
                        DRINK_NAME, DRINK_PRICE, DRINK_DESCRIPTION}, DRINK_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Drink drink = new Drink(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                Float.parseFloat(cursor.getString(2)),cursor.getString(3));
        // return Drink
        return drink;
    }

    // Getting All Drinks
    public List<Drink> getAllDrinks() {
        List<Drink> drinkList = new ArrayList<Drink>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_DRINK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Drink drink = new Drink();
                drink.setDrinkID(Integer.parseInt(cursor.getString(0)));
                drink.setName(cursor.getString(1));
                drink.setPrice(Float.parseFloat(cursor.getString(2)));
                drink.setDescription(cursor.getString(3));
                // Adding drink to list
                drinkList.add(drink);
            } while (cursor.moveToNext());
        }
        // return drink list
        return drinkList;
    }

    // Getting drinks Count
    public int getDrinksCount() {
        String countQuery = "SELECT * FROM " + TABLE_DRINK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    // Updating a drink
    public int updateDrink(Drink drink) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DRINK_NAME, drink.getName());
        values.put(DRINK_PRICE, drink.getPrice());
        values.put(DRINK_DESCRIPTION, drink.getDescription());
        // updating row
        return db.update(TABLE_DRINK, values, DRINK_ID + " = ?",
                new String[]{String.valueOf(drink.getDrinkID())});
    }

    // Deleting a drink
    public void deleteDrink(Drink drink) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DRINK, DRINK_ID + " = ?",
                new String[] { String.valueOf(drink.getDrinkID()) });
        db.close();
    }


    /************************** Order CRUD ****************************************/
    // Create new Order
    public void addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORDER_REF, order.getReferenceNumber());
        values.put(ORDER_DRINK_ID, order.getDrinkID());
        values.put(ORDER_USER_EMAIL, order.getUserEmail());
        values.put(ORDER_SIZE, order.getSize());
        values.put(ORDER_COMMENT, order.getComment());
        values.put(ORDER_Date, order.getDate());
        // Inserting Row
        db.insert(TABLE_ORDER, null, values);
        db.close(); // Closing database connection
    }

    // Read one Order
    public Order getOrder(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ORDER, new String[] { ORDER_ID,ORDER_REF,
                        ORDER_DRINK_ID, ORDER_USER_EMAIL, ORDER_SIZE,
                        ORDER_COMMENT,ORDER_Date}, ORDER_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Order order = new Order(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                cursor.getString(3),
                cursor.getString(4), cursor.getString(5),cursor.getString(6));
        // return Order
        return order;
    }

    // Getting All Orders
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<Order>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ORDER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setOrderID(Integer.parseInt(cursor.getString(0)));
                order.setReferenceNumber(cursor.getString(1));
                order.setDrinkID(Integer.parseInt(cursor.getString(2)));
                order.setUserEmail(cursor.getString(3));
                order.setSize(cursor.getString(4));
                order.setComment(cursor.getString(5));
                order.setDate(cursor.getString(6));
                // Adding order to list
                orderList.add(order);
            } while (cursor.moveToNext());
        }
        // return order list
        return orderList;
    }

    // Getting orders Count
    public int getOrdersCount() {
        String countQuery = "SELECT * FROM " + TABLE_ORDER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    // Updating a order
    public int updateOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORDER_REF, order.getReferenceNumber());
        values.put(ORDER_DRINK_ID, order.getDrinkID());
        values.put(ORDER_USER_EMAIL, order.getUserEmail());
        values.put(ORDER_SIZE, order.getSize());
        values.put(ORDER_COMMENT, order.getComment());
        values.put(ORDER_Date, order.getDate());
        // updating row
        return db.update(TABLE_ORDER, values, ORDER_ID + " = ?",
                new String[]{String.valueOf(order.getOrderID())});
    }

    // Deleting a order
    public void deleteOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ORDER, ORDER_ID + " = ?",
                new String[] { String.valueOf(order.getOrderID()) });
        db.close();
    }
}
