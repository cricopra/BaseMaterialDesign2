package co.com.gane.activosfijos.orm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "activosfijos")
public class Activosfijos {

    @DatabaseField(generatedId = true, canBeNull = false)
    private int id;
    @DatabaseField(canBeNull = true, columnName = "id_activo")
    private String id_activo;
    @DatabaseField(canBeNull = true, columnName = "nombre")
    private String nombre;
    @DatabaseField(canBeNull = true, columnName = "tag")
    private String tag;
    @DatabaseField(canBeNull = true, columnName = "ubicacion")
    private String ubicacion;
    @DatabaseField(canBeNull = true, columnName = "nombre_ubicacion")
    private String nombre_ubicacion;
    @DatabaseField(canBeNull = true, columnName = "clase")
    private String clase;
    @DatabaseField(canBeNull = true, columnName = "nombre_clase")
    private String nombre_clase;
    @DatabaseField(canBeNull = true, columnName = "tipo")
    private String tipo;
    @DatabaseField(canBeNull = true, columnName = "nombre_tipo")
    private String nombre_tipo;
    @DatabaseField(canBeNull = true, columnName = "responsable")
    private String responsable;
    @DatabaseField(canBeNull = true, columnName = "nombre_responsable")
    private String nombre_responsable;
    @DatabaseField(canBeNull = true, columnName = "id_cargo")
    private String id_cargo;
    @DatabaseField(canBeNull = true, columnName = "nombre_cargo")
    private String nombre_cargo;

    public Activosfijos() {
    }

    public Activosfijos(String id_activo, String nombre, String tag, String ubicacion, String nombre_ubicacion, String clase, String nombre_clase, String tipo, String nombre_tipo, String responsable, String nombre_responsable, String id_cargo, String nombre_cargo) {
        this.id_activo = id_activo;
        this.nombre = nombre;
        this.tag = tag;
        this.ubicacion = ubicacion;
        this.nombre_ubicacion = nombre_ubicacion;
        this.clase = clase;
        this.nombre_clase = nombre_clase;
        this.tipo = tipo;
        this.nombre_tipo = nombre_tipo;
        this.responsable = responsable;
        this.nombre_responsable = nombre_responsable;
        this.id_cargo = id_cargo;
        this.nombre_cargo = nombre_cargo;
    }

    public String getId_activo() {
        return id_activo;
    }

    public void setId_activo(String id_activo) {
        this.id_activo = id_activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre_ubicacion() {
        return nombre_ubicacion;
    }

    public void setNombre_ubicacion(String nombre_ubicacion) {
        this.nombre_ubicacion = nombre_ubicacion;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getNombre_clase() {
        return nombre_clase;
    }

    public void setNombre_clase(String nombre_clase) {
        this.nombre_clase = nombre_clase;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getNombre_responsable() {
        return nombre_responsable;
    }

    public void setNombre_responsable(String nombre_responsable) {
        this.nombre_responsable = nombre_responsable;
    }

    public String getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(String id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getNombre_cargo() {
        return nombre_cargo;
    }

    public void setNombre_cargo(String nombre_cargo) {
        this.nombre_cargo = nombre_cargo;
    }
}
