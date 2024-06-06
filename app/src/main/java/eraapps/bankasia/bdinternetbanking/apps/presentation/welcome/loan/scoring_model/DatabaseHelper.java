package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "ModelDB.bd";

    // Table Names
    private static final String TABLE_COMPONENT = "component";
    private static final String TABLE_VARIABLE = "variable";
    private static final String TABLE_VALUE = "value";

    // private static final String TABLE_TODO_TAG = "todo_tags";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // Component Table - column names
    public static final String ComponentID = "_componentID";
    public static final String ComponentDataSourceID = "data_source_id";
    public static final String ComponentName = "name";
    public static final String ComponentWeight = "weight";
    //public static final String ComponentMaxPoint = "max_point";

    // Variable Table - column names
    public static final String VariableID = "_variableID";
    public static final String VariableComponentID = "component_id";
    public static final String VariableName = "variable_name";

    // Value Table - column names
    public static final String ValueID = "_valueID";
    public static final String ValueVariableID = "variable_id";
    public static final String ValueName = "value_name";
    public static final String ValueScore = "value_score";
    public static final String MaxValue = "max_value";
    public static final String MinValue = "min_value";
    public static final String Flag = "flag";



    // NOTE_TAGS Table - column names
    private static final String KEY_TODO_ID = "todo_id";
    private static final String KEY_TAG_ID = "tag_id";

    // Table Create Statements
    // Component table create statement
    public static final String CREATE_TABLE_COMPONENT = "CREATE TABLE " + TABLE_COMPONENT +
            " (" + ComponentID + " INTEGER PRIMARY KEY, "
            + ComponentDataSourceID + " VARCHAR(255) ,"
            + ComponentName + " VARCHAR(255) ,"
            + ComponentWeight + " VARCHAR(255));";
           // + ComponentMaxPoint + " VARCHAR(225));";

    // Variable table create statement
    public static final String CREATE_TABLE_VARIABLE = "CREATE TABLE " + TABLE_VARIABLE +
            " (" + VariableID + " INTEGER PRIMARY KEY, "
            + VariableComponentID + " VARCHAR(255) ,"
            + VariableName + " VARCHAR(225));";

    // Value table create statement
    public static final String CREATE_TABLE_VALUE = "CREATE TABLE " + TABLE_VALUE +
            " (" + ValueID + " INTEGER PRIMARY KEY, "
            + ValueName + " VARCHAR(255) ,"
            + ValueVariableID + " VARCHAR(255) ,"
            + Flag + " VARCHAR(255) ,"
            + MinValue + " VARCHAR(255) ,"
            + MaxValue + " VARCHAR(255) ,"
            + ValueScore + " VARCHAR(255));";



    //db.execSQL("delete from "+ TABLE_NAME);


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void deteleDatad(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_COMPONENT);
        db.execSQL("delete from "+TABLE_VARIABLE);
        db.execSQL("delete from "+TABLE_VALUE);
    }

    public boolean checkIfTableExists(SQLiteDatabase db, String tableName) {

        Cursor c = null;
        boolean tableExists = false;
        /* get cursor on it */
        try {
            c = db.query("tbl_example", null,
                    null, null, null, null, null);
            tableExists = true;
        } catch (Exception e) {
            /* fail */
            Log.d("logdata", TABLE_COMPONENT + " doesn't exist :(((");
        }

        return tableExists;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (checkIfTableExists(db, TABLE_COMPONENT)) {
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPONENT);
            db.execSQL("delete from " + TABLE_COMPONENT);
            Log.d("delete", "TABLE_COMPONENT has deleted");
        }
        if (checkIfTableExists(db, TABLE_VARIABLE)) {
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_VARIABLE);
            db.execSQL("delete from " + TABLE_VARIABLE);
            Log.d("delete", "TABLE_VARIABLE has deleted");
        }
        if (checkIfTableExists(db, TABLE_VALUE)) {
            //   db.execSQL("DROP TABLE IF EXISTS " + TABLE_VALUE);
            db.execSQL("delete from " + TABLE_VALUE);
            Log.d("delete", "TABLE_VALUE has deleted");
        }


        // creating required tables
        db.execSQL(CREATE_TABLE_COMPONENT);
        db.execSQL(CREATE_TABLE_VARIABLE);
        db.execSQL(CREATE_TABLE_VALUE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPONENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VARIABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VALUE);


        // create new tables
        onCreate(db);
    }

    public long createComponent(int component_id, String DataSourceID, String Name, String Weight) {
        //Log.d("logdata","DataSourceID "+ DataSourceID);
        try{
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPONENT);
        }catch (Exception e){
            e.printStackTrace();
        }


        SQLiteDatabase dbb = this.getWritableDatabase();
        //dbb.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPONENT);
        ContentValues contentValues = new ContentValues();
        //Log.d("logdata","DataSourceID "+ DataSourceID);
        //Log.d("logdata","Name "+ Name);

        contentValues.put(this.ComponentID, component_id);
        contentValues.put(this.ComponentDataSourceID, DataSourceID);
        contentValues.put(this.ComponentName, Name);
        contentValues.put(this.ComponentWeight, Weight);

        long id = dbb.insert(this.TABLE_COMPONENT, null, contentValues);
        Log.d("createComponentID", "id " + id);
        return id;
    }


    public long createVariable(int variable_id, String ComponentID, String VariableName) {

       /* try{
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_VARIABLE);
        }catch (Exception e){
            e.printStackTrace();
        }
        */

        //Log.d("logdataVar","msg "+ "YOOOOOOOOOOO");
        SQLiteDatabase dbb = this.getWritableDatabase();
        //dbb.execSQL("DROP TABLE IF EXISTS " + TABLE_VARIABLE);
        ContentValues contentValues = new ContentValues();
        //Log.d("logdataVar","ComponentID "+ ComponentID);
        //Log.d("logdataVar","VariableName "+ VariableName);


        // Variable Table - column names
        contentValues.put(this.VariableID, variable_id);
        contentValues.put(this.VariableComponentID, ComponentID);
        contentValues.put(this.VariableName, VariableName);

        Log.d("logdataVar", "contentValues " + contentValues.toString());
        long id = dbb.insert(this.TABLE_VARIABLE, null, contentValues);
        Log.d("createVariableID", "id " + id);
        return id;
    }

    public long createValue(int value_id, String variable_id, String value_name, String value_score, String flag,
                            String min_value, String max_value) {
/*
        try{
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_VALUE);
        }catch (Exception e){
            e.printStackTrace();
        }
        */
        SQLiteDatabase dbb = this.getWritableDatabase();
        //dbb.execSQL("DROP TABLE IF EXISTS " + TABLE_VALUE);
        ContentValues contentValues = new ContentValues();
        //Log.d("createValue","value_name "+ value_name);
        //Log.d("createValue","value_score "+ value_score);


        // Value Table - column names
        contentValues.put(this.ValueID, value_id);
        contentValues.put(this.ValueVariableID, variable_id);
        contentValues.put(this.ValueName, value_name);
        contentValues.put(this.ValueScore, value_score);
        contentValues.put(this.Flag, flag);
        contentValues.put(this.MinValue, min_value);
        contentValues.put(this.MaxValue, max_value);

        Log.d("createValue", "contentValues " + contentValues.toString());
        long id = dbb.insert(this.TABLE_VALUE, null, contentValues);
        Log.d("createValueID", "id " + id);
        return id;
    }

    SQLiteDatabase db;
    String selectQuery;

    public List<Component> getAllComponents() {
        List<Component> componentList = new ArrayList<Component>();
        // Select All Query
        selectQuery = "SELECT  * FROM " + TABLE_COMPONENT;

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Component component = new Component();
                component.set_id(Integer.parseInt(cursor.getString(0)));
                component.set_name(cursor.getString(2));

                // Adding contact to list
                componentList.add(component);
            } while (cursor.moveToNext());
        }
        return componentList;
    }

        public List<Variable> getAllVariables () {
            List<Variable> variableList = new ArrayList<Variable>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_VARIABLE;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursorVB = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursorVB.moveToFirst()) {
                do {
                    Variable variable = new Variable();
                    variable.set_id(Integer.parseInt(cursorVB.getString(0)));
                    variable.set_name(cursorVB.getString(2));
                    variable.set_component_id(Integer.parseInt(cursorVB.getString(1)));

                    // Adding variables to list
                    variableList.add(variable);
                } while (cursorVB.moveToNext());
            }


            return variableList;
        }

        public List<Value> getAllValues () {
            List<Value> valueList = new ArrayList<Value>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_VALUE;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursorVL = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursorVL.moveToFirst()) {
                do {

                    Value value = new Value();

//                    Log.d("value","set_value_name0: "+cursorVL.getString(0));
//                    Log.d("value","set_value_name1: "+cursorVL.getString(1));
//                    Log.d("value","set_value_name2: "+cursorVL.getString(2));
//                    Log.d("value","set_value_name3: "+cursorVL.getString(3));
//                    Log.d("value","set_value_name4: "+cursorVL.getString(4));
//                    Log.d("value","set_value_name5: "+cursorVL.getString(5));
//                    Log.d("value","set_value_name6: "+cursorVL.getString(6));
                    //Log.d("value","set_value_name7: "+cursorVL.getString(7));

                    value.set_value_name(cursorVL.getString(1));
                    value.set_value_score(cursorVL.getString(6));
                    value.set_value_variable_id(Integer.parseInt(cursorVL.getString(2)));
                    value.set_flag(cursorVL.getString(3));
                    value.set_min_value(cursorVL.getString(4));
                    value.set_max_value(cursorVL.getString(5));


                    // Adding variables to list
                    valueList.add(value);
                } while (cursorVL.moveToNext());
            }


            return valueList;
        }


        public void closeDB () {
            SQLiteDatabase db = this.getReadableDatabase();
            if (db != null && db.isOpen())
                db.close();
        }


    }

