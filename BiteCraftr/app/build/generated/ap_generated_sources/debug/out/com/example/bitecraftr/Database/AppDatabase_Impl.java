package com.example.bitecraftr.Database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile AppDao _appDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `Meals` (`idMeal` TEXT NOT NULL, `strMeal` TEXT, `strDrinkAlternate` TEXT, `strCategory` TEXT, `strArea` TEXT, `strInstructions` TEXT, `strMealThumb` TEXT, `strTags` TEXT, `strYoutube` TEXT, `strIngredient1` TEXT, `strIngredient2` TEXT, `strIngredient3` TEXT, `strIngredient4` TEXT, `strIngredient5` TEXT, `strIngredient6` TEXT, `strIngredient7` TEXT, `strIngredient8` TEXT, `strIngredient9` TEXT, `strIngredient10` TEXT, `strIngredient11` TEXT, `strIngredient12` TEXT, `strIngredient13` TEXT, `strIngredient14` TEXT, `strIngredient15` TEXT, `strIngredient16` TEXT, `strIngredient17` TEXT, `strIngredient18` TEXT, `strIngredient19` TEXT, `strIngredient20` TEXT, `strMeasure1` TEXT, `strMeasure2` TEXT, `strMeasure3` TEXT, `strMeasure4` TEXT, `strMeasure5` TEXT, `strMeasure6` TEXT, `strMeasure7` TEXT, `strMeasure8` TEXT, `strMeasure9` TEXT, `strMeasure10` TEXT, `strMeasure11` TEXT, `strMeasure12` TEXT, `strMeasure13` TEXT, `strMeasure14` TEXT, `strMeasure15` TEXT, `strMeasure16` TEXT, `strMeasure17` TEXT, `strMeasure18` TEXT, `strMeasure19` TEXT, `strMeasure20` TEXT, `strSource` TEXT, `strImageSource` TEXT, `strCreativeCommonsConfirmed` TEXT, `dateModified` TEXT, PRIMARY KEY(`idMeal`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Scheduled_Meals` (`ScheduledMealId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` TEXT, `idMeal` TEXT, `strMeal` TEXT, `strDrinkAlternate` TEXT, `strCategory` TEXT, `strArea` TEXT, `strInstructions` TEXT, `strMealThumb` TEXT, `strTags` TEXT, `strYoutube` TEXT, `strIngredient1` TEXT, `strIngredient2` TEXT, `strIngredient3` TEXT, `strIngredient4` TEXT, `strIngredient5` TEXT, `strIngredient6` TEXT, `strIngredient7` TEXT, `strIngredient8` TEXT, `strIngredient9` TEXT, `strIngredient10` TEXT, `strIngredient11` TEXT, `strIngredient12` TEXT, `strIngredient13` TEXT, `strIngredient14` TEXT, `strIngredient15` TEXT, `strIngredient16` TEXT, `strIngredient17` TEXT, `strIngredient18` TEXT, `strIngredient19` TEXT, `strIngredient20` TEXT, `strMeasure1` TEXT, `strMeasure2` TEXT, `strMeasure3` TEXT, `strMeasure4` TEXT, `strMeasure5` TEXT, `strMeasure6` TEXT, `strMeasure7` TEXT, `strMeasure8` TEXT, `strMeasure9` TEXT, `strMeasure10` TEXT, `strMeasure11` TEXT, `strMeasure12` TEXT, `strMeasure13` TEXT, `strMeasure14` TEXT, `strMeasure15` TEXT, `strMeasure16` TEXT, `strMeasure17` TEXT, `strMeasure18` TEXT, `strMeasure19` TEXT, `strMeasure20` TEXT, `strSource` TEXT, `strImageSource` TEXT, `strCreativeCommonsConfirmed` TEXT, `dateModified` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'bd65e147fff345c2753d00314a2a0f16')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `Meals`");
        db.execSQL("DROP TABLE IF EXISTS `Scheduled_Meals`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsMeals = new HashMap<String, TableInfo.Column>(53);
        _columnsMeals.put("idMeal", new TableInfo.Column("idMeal", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeal", new TableInfo.Column("strMeal", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strDrinkAlternate", new TableInfo.Column("strDrinkAlternate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strCategory", new TableInfo.Column("strCategory", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strArea", new TableInfo.Column("strArea", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strInstructions", new TableInfo.Column("strInstructions", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMealThumb", new TableInfo.Column("strMealThumb", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strTags", new TableInfo.Column("strTags", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strYoutube", new TableInfo.Column("strYoutube", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient1", new TableInfo.Column("strIngredient1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient2", new TableInfo.Column("strIngredient2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient3", new TableInfo.Column("strIngredient3", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient4", new TableInfo.Column("strIngredient4", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient5", new TableInfo.Column("strIngredient5", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient6", new TableInfo.Column("strIngredient6", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient7", new TableInfo.Column("strIngredient7", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient8", new TableInfo.Column("strIngredient8", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient9", new TableInfo.Column("strIngredient9", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient10", new TableInfo.Column("strIngredient10", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient11", new TableInfo.Column("strIngredient11", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient12", new TableInfo.Column("strIngredient12", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient13", new TableInfo.Column("strIngredient13", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient14", new TableInfo.Column("strIngredient14", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient15", new TableInfo.Column("strIngredient15", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient16", new TableInfo.Column("strIngredient16", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient17", new TableInfo.Column("strIngredient17", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient18", new TableInfo.Column("strIngredient18", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient19", new TableInfo.Column("strIngredient19", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strIngredient20", new TableInfo.Column("strIngredient20", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure1", new TableInfo.Column("strMeasure1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure2", new TableInfo.Column("strMeasure2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure3", new TableInfo.Column("strMeasure3", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure4", new TableInfo.Column("strMeasure4", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure5", new TableInfo.Column("strMeasure5", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure6", new TableInfo.Column("strMeasure6", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure7", new TableInfo.Column("strMeasure7", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure8", new TableInfo.Column("strMeasure8", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure9", new TableInfo.Column("strMeasure9", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure10", new TableInfo.Column("strMeasure10", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure11", new TableInfo.Column("strMeasure11", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure12", new TableInfo.Column("strMeasure12", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure13", new TableInfo.Column("strMeasure13", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure14", new TableInfo.Column("strMeasure14", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure15", new TableInfo.Column("strMeasure15", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure16", new TableInfo.Column("strMeasure16", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure17", new TableInfo.Column("strMeasure17", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure18", new TableInfo.Column("strMeasure18", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure19", new TableInfo.Column("strMeasure19", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strMeasure20", new TableInfo.Column("strMeasure20", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strSource", new TableInfo.Column("strSource", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strImageSource", new TableInfo.Column("strImageSource", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("strCreativeCommonsConfirmed", new TableInfo.Column("strCreativeCommonsConfirmed", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeals.put("dateModified", new TableInfo.Column("dateModified", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMeals = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMeals = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMeals = new TableInfo("Meals", _columnsMeals, _foreignKeysMeals, _indicesMeals);
        final TableInfo _existingMeals = TableInfo.read(db, "Meals");
        if (!_infoMeals.equals(_existingMeals)) {
          return new RoomOpenHelper.ValidationResult(false, "Meals(com.example.bitecraftr.Model.Meal).\n"
                  + " Expected:\n" + _infoMeals + "\n"
                  + " Found:\n" + _existingMeals);
        }
        final HashMap<String, TableInfo.Column> _columnsScheduledMeals = new HashMap<String, TableInfo.Column>(55);
        _columnsScheduledMeals.put("ScheduledMealId", new TableInfo.Column("ScheduledMealId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("idMeal", new TableInfo.Column("idMeal", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeal", new TableInfo.Column("strMeal", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strDrinkAlternate", new TableInfo.Column("strDrinkAlternate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strCategory", new TableInfo.Column("strCategory", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strArea", new TableInfo.Column("strArea", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strInstructions", new TableInfo.Column("strInstructions", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMealThumb", new TableInfo.Column("strMealThumb", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strTags", new TableInfo.Column("strTags", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strYoutube", new TableInfo.Column("strYoutube", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient1", new TableInfo.Column("strIngredient1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient2", new TableInfo.Column("strIngredient2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient3", new TableInfo.Column("strIngredient3", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient4", new TableInfo.Column("strIngredient4", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient5", new TableInfo.Column("strIngredient5", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient6", new TableInfo.Column("strIngredient6", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient7", new TableInfo.Column("strIngredient7", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient8", new TableInfo.Column("strIngredient8", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient9", new TableInfo.Column("strIngredient9", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient10", new TableInfo.Column("strIngredient10", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient11", new TableInfo.Column("strIngredient11", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient12", new TableInfo.Column("strIngredient12", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient13", new TableInfo.Column("strIngredient13", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient14", new TableInfo.Column("strIngredient14", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient15", new TableInfo.Column("strIngredient15", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient16", new TableInfo.Column("strIngredient16", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient17", new TableInfo.Column("strIngredient17", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient18", new TableInfo.Column("strIngredient18", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient19", new TableInfo.Column("strIngredient19", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strIngredient20", new TableInfo.Column("strIngredient20", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure1", new TableInfo.Column("strMeasure1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure2", new TableInfo.Column("strMeasure2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure3", new TableInfo.Column("strMeasure3", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure4", new TableInfo.Column("strMeasure4", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure5", new TableInfo.Column("strMeasure5", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure6", new TableInfo.Column("strMeasure6", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure7", new TableInfo.Column("strMeasure7", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure8", new TableInfo.Column("strMeasure8", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure9", new TableInfo.Column("strMeasure9", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure10", new TableInfo.Column("strMeasure10", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure11", new TableInfo.Column("strMeasure11", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure12", new TableInfo.Column("strMeasure12", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure13", new TableInfo.Column("strMeasure13", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure14", new TableInfo.Column("strMeasure14", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure15", new TableInfo.Column("strMeasure15", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure16", new TableInfo.Column("strMeasure16", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure17", new TableInfo.Column("strMeasure17", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure18", new TableInfo.Column("strMeasure18", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure19", new TableInfo.Column("strMeasure19", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strMeasure20", new TableInfo.Column("strMeasure20", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strSource", new TableInfo.Column("strSource", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strImageSource", new TableInfo.Column("strImageSource", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("strCreativeCommonsConfirmed", new TableInfo.Column("strCreativeCommonsConfirmed", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduledMeals.put("dateModified", new TableInfo.Column("dateModified", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysScheduledMeals = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesScheduledMeals = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoScheduledMeals = new TableInfo("Scheduled_Meals", _columnsScheduledMeals, _foreignKeysScheduledMeals, _indicesScheduledMeals);
        final TableInfo _existingScheduledMeals = TableInfo.read(db, "Scheduled_Meals");
        if (!_infoScheduledMeals.equals(_existingScheduledMeals)) {
          return new RoomOpenHelper.ValidationResult(false, "Scheduled_Meals(com.example.bitecraftr.Model.ScheduledMeal).\n"
                  + " Expected:\n" + _infoScheduledMeals + "\n"
                  + " Found:\n" + _existingScheduledMeals);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "bd65e147fff345c2753d00314a2a0f16", "d21a285ebda09432c2500cc8eca05bc2");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Meals","Scheduled_Meals");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Meals`");
      _db.execSQL("DELETE FROM `Scheduled_Meals`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AppDao.class, AppDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  protected AppDao loadMealsDAO() {
    if (_appDao != null) {
      return _appDao;
    } else {
      synchronized(this) {
        if(_appDao == null) {
          _appDao = new AppDao_Impl(this);
        }
        return _appDao;
      }
    }
  }
}
