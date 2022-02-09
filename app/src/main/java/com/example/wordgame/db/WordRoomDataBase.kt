package com.example.wordgame.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by PrernaSurbhi on 08/02/22.
 */
abstract class WordRoomDataBase: RoomDatabase() {

    abstract  fun wordDao():WordDAO


    companion object{
        @Volatile
        private var INSTANCE :WordRoomDataBase ? = null

        //fun for getting data base class instance
        fun getInstance(context:Context):WordRoomDataBase{
            synchronized(this){
                var instance = INSTANCE

                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        WordRoomDataBase::class.java,
                        "WordRoomDataBase_class"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        // function to destroy data base instance

        fun destroy(){
             INSTANCE = null
        }



    }

}