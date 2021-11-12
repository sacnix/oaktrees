package co.com.oaktrees.enums;

public enum EstadoPedidoNombre {

    RECIBIDO("Recibido"),
    EN_ENTREGA("En entrega"),
    ENTREGADO("Entregado"),
    CANCELADO("Cancelado"),
    LISTO("Listo para recoger");

    private final String nombre;

    EstadoPedidoNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public static EstadoPedidoNombre buscar (String nombre) {
        for (EstadoPedidoNombre estadoPedidoNombre : EstadoPedidoNombre.values()){
            if(estadoPedidoNombre.getNombre().equals(nombre)){
                return estadoPedidoNombre;
            }
        }
        return null;
    }
}
