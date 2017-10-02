package com.ravi.practiceapp.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ravi.practiceapp.DBInterface.CartDao;
import com.ravi.practiceapp.DBentities.CartProducts;

/**
 * Created by ravi on 01/10/17.
 */


@Database(entities = {CartProducts.class},version = 2)
public abstract class ShoppingDataBase extends RoomDatabase{

    private static ShoppingDataBase mShoppingDataBase;


    public abstract CartDao mCartDao();

    public static ShoppingDataBase getInMemoryDatabase(Context context) {
        if (mShoppingDataBase == null) {
           /* mShoppingDataBase =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), ShoppingDataBase.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();*/
            mShoppingDataBase =  Room.databaseBuilder(context.getApplicationContext(),
                    ShoppingDataBase.class, "shopping-db").fallbackToDestructiveMigration().build();
        }
        return mShoppingDataBase;
    }

    public static void destroyInstance() {
        mShoppingDataBase = null;
    }

}
