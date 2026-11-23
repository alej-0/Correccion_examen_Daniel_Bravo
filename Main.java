import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Envio> enTransito = new HashMap<>();
        List<Envio> entregados = new ArrayList<>();
        int contador = 1001;

        int opcion = 1;
        String guia;
        while (opcion != 0) {

            System.out.println("---- MENU ---- ");
            System.out.println("1. Registrar envio");
            System.out.println("2. Consultar envio");
            System.out.println("3. Entregar envio");
            System.out.println("4. Listar entregados");
            System.out.println("0. Salir");

            System.out.println("Ingrese una opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Tipo de servicio:");
                    System.out.println("1 nacional, 2 express, 3 internacional");
                    int servicio = Integer.parseInt(sc.nextLine());

                    System.out.println("Destinatario: ");
                    String destinatario = sc.nextLine();

                    System.out.println("Ciudad destino: ");
                    String ciudad = sc.nextLine();

                    System.out.println("Peso en Kg: ");
                    double peso = Double.parseDouble(sc.nextLine());

                    if (peso <= 0) {
                        System.out.println("Debe ser positivo\n");
                        break;
                    }

                    if (servicio == 1 || servicio == 2) {
                        System.out.println("La entrega es a domicilio? (S o N)");
                        boolean entrega = sc.nextLine().equalsIgnoreCase("S");

                        if (servicio == 1) {
                            guia = "NAC" + String.valueOf(contador);
                            Envio en = new EnvioNacional(guia, destinatario, ciudad, peso, entrega);
                            enTransito.put(guia, en);
                            contador++;
                            System.out.println("Guia asignada: " + guia + " | Costo: " + en.calcularCosto());
                        } else {
                            guia = "EXP" + String.valueOf(contador);
                            System.out.println("Eligio la entrega en 24 horas (S o N)");
                            boolean respuesta = sc.nextLine().equalsIgnoreCase("S");
                            Envio ex;
                            if (respuesta) {
                                ex = new EnvioExpress(guia, destinatario, ciudad, peso, entrega, 24);
                            } else {
                                ex = new EnvioExpress(guia, destinatario, ciudad, peso, entrega);
                            }
                            enTransito.put(guia, ex);
                            contador++;
                            System.out.println("Guia asignada: " + guia + " | Costo: " + ex.calcularCosto());
                        }
                    } else if (servicio == 3) {
                        System.out.println("Valor de la compra en dolares: ");
                        double valor = Double.parseDouble(sc.nextLine());
                        guia = "INT" + String.valueOf(contador);
                        EnvioInternacional ei = new EnvioInternacional(guia, destinatario, ciudad, peso, valor);

                        if (!ei.cumple4x4()) {
                            System.out.println("Rechazado: no cumple con la regla 4x4");
                            break;
                        }

                        enTransito.put(guia, ei);
                        contador++;
                        System.out.println("Guia asignada: " + guia + " | Costo: " + ei.calcularCosto());
                    } else {
                        System.out.println("Servicio invalido");
                    }
                    break;

                case 2:
                    System.out.println("Ingrese su guia: ");
                    guia = sc.nextLine();
                    if (!enTransito.containsKey(guia)) {
                        System.out.println("No existe un envio en transito con esa guia");
                    } else {
                        Envio encontrado = enTransito.get(guia);
                        System.out.println(encontrado.describir());
                        System.out.println("Costo: " + encontrado.calcularCosto());
                    }
                    break;

                case 3:
                    System.out.println("Ingrese su guia: ");
                    guia = sc.nextLine();
                    if (!enTransito.containsKey(guia)) {
                        System.out.println("No existe un envio en transito con esa guia");
                    } else {
                        Envio entregado = enTransito.remove(guia);
                        entregados.add(entregado);
                        System.out.println("Envio " + guia + " marcado como entregado");
                    }
                    break;

                case 4:
                    if (entregados.isEmpty()) {
                        System.out.println("Todavia no hay entregas");
                    } else {
                        double totalRecaudado = 0;
                        for (Envio e : entregados) {
                            System.out.println(e.describir() + " | Costo: " + e.calcularCosto());
                            totalRecaudado += e.calcularCosto();
                        }
                        System.out.println("Total recaudado: " + totalRecaudado);
                    }
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    opcion = 0;
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}