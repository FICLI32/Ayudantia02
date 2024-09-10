import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


public class InventarioTienda {

    public static void main(String[] args) {
        Object[][] productos = new Object[10][3];
        ejecutarMenu(productos);

    }

    public static void ejecutarMenu(Object[][] productos) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            ejecutarOpcion(opcion, productos);
        } while (opcion != 5);
    }

    public static void mostrarMenu() {
        System.out.println("\nInventario de Tienda:");
        System.out.println("1. Agregar producto");
        System.out.println("2. Quitar productos");
        System.out.println("3. Consultar disponibilidad de Producto");
        System.out.println("4. Listar Productos");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static Scanner crearScanner() {
        return new Scanner(System.in);
    }

    public static int leerOpcion() {
        int opcion = 0;

        while (true) {
            Scanner scanner = crearScanner();
            try {
                opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= 5) {
                    break;
                } else {
                    System.out.print("Opción inválida. Intente nuevamente: ");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ingrese un numero para su opcion.");
                scanner.next();
                scanner.close();
            }
        }
        return opcion;
    }

    public static int idProductoScanner() {
        Scanner scanner = crearScanner();
        System.out.print("Ingrese el ID del producto: ");
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido.");
                scanner.next();
            }
        }
    }

    public static int cantidadScanner() {
        Scanner scanner = crearScanner();
        System.out.print("Ingrese la cantidad: ");
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido.");
                scanner.next();
            }
        }
    }

    public static String nombreScanner() {
        Scanner scanner = crearScanner();
        System.out.print("Ingrese el nombre del producto: ");
        return scanner.next();
    }


    public static void ejecutarOpcion(int opcion, Object[][] productos) {
        Scanner scanner = crearScanner();
        int idProducto;
        String nombre;
        int cantidad;

        switch (opcion) {
            case 1:
                idProducto = idProductoScanner();
                nombre = nombreScanner();
                cantidad = cantidadScanner();
                agregarProductos(idProducto, cantidad, nombre, productos);
                break;
            case 2:
                idProducto = idProductoScanner();
                cantidad = cantidadScanner();
                restarProductos(idProducto, cantidad, productos);
                break;

            case 3:
                idProducto = idProductoScanner();
                System.out.println(consultarDisponibilidad(idProducto, productos));
                break;
            case 4:
                System.out.println(listarProductos(productos));
                break;
            case 5:
                System.out.println("cerrando sistema");
                break;
        }
    }

    public static String aumentarProductos(int idProducto, int cantidad, Object[][] productos) {
        for (Object[] producto : productos) {
            if (producto[0] != null && (int) producto[0] == idProducto) {
                producto[2] = (int) producto[2] + cantidad;
                return "Producto actualizado correctamente.";
            }
        }
        return "No se pudo agregar correctamente, intente nuevamente.";
    }

    public static String agregarProductos(int idProducto, int cantidad, String nombre, Object[][] productos) {
        for (int i = 0; i < productos.length; i++) {
            if (productos[i][0] == null) {
                productos[i][0] = idProducto;
                productos[i][1] = nombre;
                productos[i][2] = cantidad;
                return "Producto agregado correctamente.";
            } else if ((int) productos[i][0] == idProducto) {
                return aumentarProductos(idProducto, cantidad, productos);
            }
        }
        return "No se pudo agregar correctamente, intente nuevamente.";
    }

    public static String restarProductos(int idProducto, int cantidad, Object[][] productos) {
        for (Object[] producto : productos) {
            if (producto[0] != null && (int) producto[0] == idProducto) {
                if ((int) producto[2] >= cantidad) {
                    producto[2] = (int) producto[2] - cantidad;
                    return "Producto actualizado correctamente.";
                } else {
                    return "Cantidad insuficiente en el inventario.";
                }
            }
        }
        return "Producto no encontrado, intente nuevamente.";
    }

    public static int consultarDisponibilidad(int idProducto, Object[][] productos) {
        for (Object[] producto : productos) {
            if (producto[0] != null && (int) producto[0] == idProducto) {
                return (int) producto[2];
            }
        }
        return 0;
    }

    public static String listarProductos(Object[][] productos) {
        String lista = "";
        for (Object[] producto : productos) {
            if (producto[0] != null) {
                lista += "ID: " + producto[0] + ", Nombre: " + producto[1] + ", Cantidad: " + producto[2] + "\n";
            }
        }

        return lista.isEmpty() ? "No hay productos." : lista;
    }
}



