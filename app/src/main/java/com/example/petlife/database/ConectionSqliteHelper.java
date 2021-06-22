package com.example.petlife.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConectionSqliteHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "pet-doacao";
    private static final int DATABASE_VERSION = 4;


    private static final String TABLE_USERS = "usuario";
    private static final String TABLE_PETS = "pet";
    private static final String TABLE_FAVORITOS = "favorito";


    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NOME = "nome";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_TELEFONE = "telefone";
    private static final String KEY_USER_PASSWORD= "password";
    private static final String KEY_USER_ENDERECO= "endereco";
    private static final String KEY_USER_PROFILE_PICTURE_URL = "userPictureUrl";


    private static final String KEY_PET_ID = "id";
    private static final String KEY_PET_NAME = "nome";
    private static final String KEY_PET_IDADE = "idade";
    private static final String KEY_PET_RACA = "raca";
    private static final String KEY_PET_TIPO = "tipo";
    private static final String KEY_PET_SEXO = "sexo";
    private static final String KEY_PET_VACINADO = "vacinado";
    private static final String KEY_PET_CASTRADO = "castrado";
    private static final String KEY_PET_USER_ID_FK = "userId";
    private static final String KEY_PET_PROFILE_PICTURE_URL = "petPictureUrl";


    private static final String KEY_FAVORITO_ID = "id";
    private static final String KEY_FAVORITO_USER_ID_FK = "userId";
    private static final String KEY_FAVORITO_PET_ID_FK = "petId";

    private static ConectionSqliteHelper sInstance;

    public static synchronized ConectionSqliteHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ConectionSqliteHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private ConectionSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" +
                KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_USER_NOME + " VARCHAR(50) NOT NULL ," +
                KEY_USER_EMAIL + " VARCHAR(50) NOT NULL ," +
                KEY_USER_TELEFONE + " VARCHAR(50) ," +
                KEY_USER_PASSWORD + " VARCHAR(50) NOT NULL ," +
                KEY_USER_ENDERECO + " VARCHAR(50) ," +
                KEY_USER_PROFILE_PICTURE_URL + " TEXT " +
                ")";

        String CREATE_PETS_TABLE = "CREATE TABLE " + TABLE_PETS +
                "(" +
                KEY_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                KEY_PET_USER_ID_FK + " INTEGER REFERENCES " + TABLE_USERS + "," +
                KEY_PET_NAME + " VARCHAR(50) NOT NULL ," +
                KEY_PET_IDADE + " INTEGER ," +
                KEY_PET_RACA + " VARCHAR(50) NOT NULL ," +
                KEY_PET_TIPO + " VARCHAR(50) NOT NULL ," +
                KEY_PET_SEXO + " VARCHAR(1) NOT NULL ," +
                KEY_PET_VACINADO + " VARCHAR(1) NOT NULL ," +
                KEY_PET_CASTRADO + " VARCHAR(1) NOT NULL ," +
                KEY_PET_PROFILE_PICTURE_URL + " TEXT " +
                ")";

        String CREATE_FAVORITOS_TABLE = "CREATE TABLE " + TABLE_FAVORITOS +
                "(" +
                KEY_FAVORITO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FAVORITO_USER_ID_FK +" INTEGER REFERENCES " + TABLE_USERS + "," +
                KEY_FAVORITO_PET_ID_FK + " INTEGER REFERENCES " + TABLE_PETS  +
                ")";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_PETS_TABLE);
        db.execSQL(CREATE_FAVORITOS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PETS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITOS);

            onCreate(db);
        }
    }
}
