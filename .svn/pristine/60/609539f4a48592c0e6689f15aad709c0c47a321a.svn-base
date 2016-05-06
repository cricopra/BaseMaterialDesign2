package co.com.gane.activosfijos.orm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bodegas")
public class Bodegas {

    @DatabaseField(generatedId = true, canBeNull = false)
    int id;
    @DatabaseField(canBeNull = true, columnName = "codigo")
    private String codigo;
    @DatabaseField(canBeNull = true, columnName = "nombre")
    private String nombre;
    @DatabaseField(canBeNull = true, columnName = "direccion")
    private String direccion;

    public Bodegas() {
    }

    public Bodegas(String codigo, String nombre, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
