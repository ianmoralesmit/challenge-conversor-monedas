import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();
        int opcion = 0;

        String menu = """
                ***************************************************
                Sea bienvenido/a al Conversor de Monedas =]
                
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileño
                4) Real brasileño =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Salir
                Elija una opción válida:
                ***************************************************
                """;

        while (opcion != 7) {
            System.out.println(menu);
            opcion = lectura.nextInt();

            // Consumimos el salto de línea que queda flotando
            lectura.nextLine();

            if (opcion == 7) {
                System.out.println("Saliendo del programa.");
                break;
            }

            String base = "";
            String target = "";

            switch (opcion) {
                case 1: base = "USD"; target = "ARS"; break;
                case 2: base = "ARS"; target = "USD"; break;
                case 3: base = "USD"; target = "BRL"; break;
                case 4: base = "BRL"; target = "USD"; break;
                case 5: base = "USD"; target = "COP"; break;
                case 6: base = "COP"; target = "USD"; break;
                default:
                    System.out.println("Opción no válida. Intentá de nuevo.");
                    continue;
            }

            System.out.println("Ingrese el valor que deseas convertir:");
            double cantidad = lectura.nextDouble();

            try {
                // Buscamos la tasa de conversión en vivo
                Moneda moneda = consulta.buscarMoneda(base, target);

                // Multiplicamos la cantidad por la tasa de cambio
                double resultado = cantidad * moneda.conversion_rate();

                System.out.println("El valor " + cantidad + " [" + base + "] corresponde al valor final de =>>> " + resultado + " [" + target + "]\n");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}