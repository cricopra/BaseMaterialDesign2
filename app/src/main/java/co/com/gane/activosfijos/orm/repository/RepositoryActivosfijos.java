package co.com.gane.activosfijos.orm.repository;

import android.content.Context;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import co.com.gane.activosfijos.DBManager.DatabaseHelper;
import co.com.gane.activosfijos.orm.entity.Activosfijos;

public class RepositoryActivosfijos {

    private Context ctx;
    private DatabaseHelper objDatabaseHelper;

    public RepositoryActivosfijos(Context ctx) {
        this.ctx = ctx;
        objDatabaseHelper = new DatabaseHelper(ctx);
    }

    public List<Activosfijos> getAll() {
        RuntimeExceptionDao<Activosfijos, Integer> simpleDao = objDatabaseHelper.getREActivosfijosDao();
        List<Activosfijos> list = simpleDao.queryForAll();
        return list;
    }

 /*   public List<Categoria> getByUnidadNegocio(int idUnidadNegocio) {
        RuntimeExceptionDao<Categoria, Integer> simpleDao = objDatabaseHelper.getRECategoriaDao();
        HashMap<String, Objec objMap = new HashMap<String, Object>(){};

        objMap.put(Categoria.FIELD_UNIDAD_NEGOCIO, idUnidadNegocio);
        List<Categoria> list = simpleDao.queryForFieldValues(objMap);
        return list;
    }*/
}
