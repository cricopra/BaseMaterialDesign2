package co.com.gane.activosfijos.DBManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import co.com.gane.activosfijos.orm.entity.Activosfijos;
import co.com.gane.activosfijos.orm.entity.Bodegas;

import java.sql.SQLException;


/**
 * Usada para la creacion y actualizacion de la base de datos
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "activosfijos.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the Bodegas table
    private Dao<Bodegas, Integer> bodegasDao = null;
    private RuntimeExceptionDao<Bodegas, Integer> bodegasRuntimeDao = null;
    private Dao<Activosfijos, Integer> activosfijosDao = null;
    private RuntimeExceptionDao<Activosfijos, Integer> activosfijosRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {

            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Bodegas.class);
            TableUtils.createTable(connectionSource, Activosfijos.class);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

    }

    /**
     * Esto se llama cuando se actualiza la aplicación y tiene un número de versión más alto.
     * Esto permite ajustar los diversos datos para que coincida con el nuevo número de versión.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Bodegas.class, true);
            TableUtils.dropTable(connectionSource, Activosfijos.class, true);

            // después de borrar la base de datos vieja, creamos la nueva
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Devuelve el objeto de base de datos Access (DAO) para nuestra clase Bodegas.
     * Se va a crear o simplemente devuelve de la caché
     */
    public Dao<Bodegas, Integer> getBodegasDao() throws SQLException {
        if (bodegasDao == null) {
            bodegasDao = getDao(Bodegas.class);
        }
        return bodegasDao;
    }

    public Dao<Activosfijos, Integer> getActivosfijosDao() throws SQLException {
        if (activosfijosDao == null) {
            activosfijosDao = getDao(Activosfijos.class);
        }
        return activosfijosDao;
    }

    /**
     * Devuelve la versión RuntimeExceptionDao (acceso de base de datos de objetos) de un Dao para nuestra clase Trabajador.
     * Se va a crear o simplemente dar el valor almacenado en caché.
     * RuntimeExceptionDao sólo a través de excepciones en tiempo de ejecucion.
     */

    public RuntimeExceptionDao<Bodegas, Integer> getREBodegasDao() {
        if (bodegasRuntimeDao == null) {
            bodegasRuntimeDao = getRuntimeExceptionDao(Bodegas.class);
        }
        return bodegasRuntimeDao;
    }

    public RuntimeExceptionDao<Activosfijos, Integer> getREActivosfijosDao() {
        if (activosfijosRuntimeDao == null) {
            activosfijosRuntimeDao = getRuntimeExceptionDao(Activosfijos.class);
        }
        return activosfijosRuntimeDao;
    }

    /**
     * Cerrar las conexiones de base de datos y borrar cualquier caché del DAO.
     */
    @Override
    public void close() {
        super.close();
        bodegasDao = null;
        activosfijosDao = null;
    }
}
