import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class InventarioTiendaTest {

    private Object[][] productos;

    @BeforeEach
    void crearMatriz() {
      productos = new Object[10][3];
    }

    @Test
    void agregarProductos() {
        String resultado = InventarioTienda.agregarProductos(1, 5, "manzana", productos);
        assertEquals("Producto agregado correctamente.", resultado);
        assertEquals(1, productos[0][0]);
        assertEquals("manzana", productos[0][1]);
        assertEquals(5, productos[0][2]);
    }

    @Test
    void restarPoductos() {

        InventarioTienda.agregarProductos(1, 10, "manzana", productos);
        String resultado = InventarioTienda.restarProductos(1, 5, productos);
        assertEquals("Producto actualizado correctamente.", resultado);
        assertEquals(5, productos[0][2]);
        resultado = InventarioTienda.restarProductos(1, 10, productos);
        assertEquals("Cantidad insuficiente en el inventario.", resultado);


    }

    @Test
    void consultarDisponibilidad() {
        InventarioTienda.agregarProductos(1, 10, "manzana", productos);
        int cantidad = InventarioTienda.consultarDisponibilidad(1, productos);
        assertEquals(10, cantidad);
        cantidad = InventarioTienda.consultarDisponibilidad(2, productos);
        assertEquals(0, cantidad);
    }

    @Test
    void listarPoductos() {
        String resultado = InventarioTienda.listarProductos(productos);
        assertEquals("No hay productos.", resultado);
        InventarioTienda.agregarProductos(1, 10, "manzana", productos);
        resultado = InventarioTienda.listarProductos(productos);
        assertTrue(resultado.contains("ID: 1"));
        assertTrue(resultado.contains("Nombre: manzana"));
        assertTrue(resultado.contains("Cantidad: 10"));

    }
}