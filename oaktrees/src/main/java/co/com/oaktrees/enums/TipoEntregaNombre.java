package co.com.oaktrees.enums;

public enum TipoEntregaNombre {

    TIENDA("Recogida en tienda"),
    DOMICILIO("Domicilio (contra entrega)");


    private final String nombre;

    TipoEntregaNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public static TipoEntregaNombre buscar (String nombre) {
        for (TipoEntregaNombre tipoEntregaNombre : TipoEntregaNombre.values()){
            if(tipoEntregaNombre.getNombre().equals(nombre)){
                return tipoEntregaNombre;
            }
        }
        return null;
    }
}
