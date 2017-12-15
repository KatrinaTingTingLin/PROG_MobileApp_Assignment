package com.example.tlin7877.assignment_1.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.example.tlin7877.assignment_1.dao.CardDao;
import com.example.tlin7877.assignment_1.dao.DrinkDao;
import com.example.tlin7877.assignment_1.dao.OrderDao;
import com.example.tlin7877.assignment_1.dao.UserDao;
import com.example.tlin7877.assignment_1.entity.Card;
import com.example.tlin7877.assignment_1.entity.Drink;
import com.example.tlin7877.assignment_1.entity.Order;
import com.example.tlin7877.assignment_1.entity.User;

/**
 * Created by tlin7877 on 12/14/2017.
 */

@Database(entities = {User.class,Card.class,Drink.class,Order.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract CardDao cardDao();
    public abstract DrinkDao drinkDao();
    public abstract OrderDao orderDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "NotStarbucksDatabase")
                    .allowMainThreadQueries()
                    .build();

            //Create default user
            if (INSTANCE.userDao().countUsers() == 0){
                INSTANCE.userDao().insert(new User("test@email.com","password","Ting","Lin","11 fake street",
                        "Kitchener","Ontario","N1N2N3","May 1",0));
            }

            //Create default drinks
            if (INSTANCE.drinkDao().countDrinks() == 0){
                INSTANCE.drinkDao().insert(new Drink("Salted Caramel Mocha Frappuccino",
                        "salted_caramel_frap", 5.00,
                        "We blend mocha sauce and toffee nut syrup with coffee, " +
                                "milk and ice then finish it off with sweetened whipped cream, " +
                                "caramel sauce and a blend of turbinado sugar and sea salt " +
                                "for an explosion of flavor in every sip."));
                INSTANCE.drinkDao().insert(new Drink("Nitro Teavana Peach Tea",
                        "nitro_peach_tea", 4.00,
                        "Our Teavana Peach Tea, made from real fruit and incredibly flavorful " +
                                "botanical blends, is infused with nitrogen and served cold to " +
                                "bring out velvety sweet refreshing sips."));
                INSTANCE.drinkDao().insert(new Drink("Featured Dark Roast",
                        "dark_roasted", 3.50,
                        "This full-bodied dark roast coffee has the bold, robust flavors " +
                                "to showcase our roasting and blending artistry."));
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
