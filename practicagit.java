import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class M03_UF1_PR01_GonzalezMarc_CastellarnauLucca_BoschPol {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[][] agenda = new String[12][31];
        int opcion = 0;
        int dia = 0;
        int mes = 0;
        int dia_max = 0;
        int any = 0; //creem una variable automàtica que ens permetrà controlar els anys de trespàs
        int c_mesos = 1;
        boolean a_buida = false; //fa referencia a "Agenda Buida"
        String acontecimiento= "";
        String respuesta = "";

        //començarem i acabarem amb una estructura de do-while per a que el programma nomes es tanqui
        //quan seleccionem la opció corresponent

        do {
            //obtenim l'any
            LocalDate data_actual = LocalDate.now();
            any = data_actual.getYear();


            //incorporem el menu principal

            System.out.println();
            System.out.println("--- Menú Principal ---");
            System.out.println("1. Añadir un acontecimiento");
            System.out.println("2. Eliminar un acontecimiento");
            System.out.println("3. Consultar la agenda");
            System.out.println("4. Intrucciones de la agenda");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            //llegim opcio i apliquem control de variable per a que no exploti
            try {
                opcion = scanner.nextInt();
            }catch (InputMismatchException e){
                System.out.println("INTRODUCELO NUMERICAMENTE!");
                System.out.println();
                continue;

            }finally {
                scanner.nextLine();
            }
            //creem el switch que ens permetra viatjar per el programma
            switch (opcion) {
                case 1:

                    //llegirem el mes i el dia que volem afegir un event i apliquem control de variable per a que no
                    // exploti el programma i ho perdem tot
                    // e incormprarem una linia nova per a poder llegir tot el que l'usuari escriu
                    // com una cadena String estem parlan del "scanner.NextLine"
                    try {
                        System.out.print("Ingrese el mes (1-12): ");
                        mes = scanner.nextInt();
                        System.out.print("Ingrese el día: ");
                        dia = scanner.nextInt();
                        scanner.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Introduce una fecha valida");
                        scanner.nextLine();
                        break;
                    }

                    //Configurem la variable dia_max per a que s'adapti als dies del mes en funcio del mes en el que
                    //ens trobem

                    if ((any % 4 == 0) && (mes == 2)) {
                        dia_max = 29;
                    } else if ((any % 4 != 0) && (mes == 2)) {
                        dia_max = 28;
                    } else if ((mes % 2 == 0) && (mes <= 7)) {
                        dia_max = 30;
                    } else if (mes % 2 == 0) {
                        dia_max = 31;
                    } else if ((mes % 2 != 0) && (mes <= 7)) {
                        dia_max = 31;
                    } else {
                        dia_max = 30;
                    }

                    //aquest codndicional es el que comproba que la data sigui vàlida per a poder guardarla.
                    //Si podem entrar la data sera valida i per tant demanarem al usurai que vol introduir a les seva
                    // agenda i guardarem la informació introduida per el usuari.

                    if ((mes >= 1 && mes <= 12) && (dia >= 1 && dia <= dia_max)) {
                        System.out.print("Ingrese el acontecimiento: ");
                        acontecimiento = scanner.nextLine();
                        if (agenda[mes - 1][dia - 1] == null) {
                            agenda[mes - 1][dia - 1] = acontecimiento;
                        } else {
                            agenda[mes - 1][dia - 1] += "\n" + acontecimiento;
                        }
                        System.out.println("Acontecimiento añadido correctamente.");

                    } else {

                        System.out.println("Fecha no válida.");
                    }
                    break;

                case 2:
                    //reiniciem la variable que ens permet mostrar els mesos ja que sino com mes cops entrem a la case
                    // mes grans seran els dies del mes
                    c_mesos = 1;

                    //comprobem si hi ha alguna cosa a la agenda ja que si no hi ha res aquesta opcio no te cap sentit

                    a_buida = false;

                    for (int i = 0; i < agenda.length; i++) {
                        for (int j = 0; j < agenda[i].length; j++) {
                            if (agenda[i][j] != null) {
                                a_buida = true;
                            }
                        }
                    }

                    if (!a_buida) {
                        System.out.println("No hay acontecimientos en la agenda");
                        break;
                    }

                    //Per a ubicar la data seguim el mateix procediment que al case 1:
                    try {
                        System.out.print("Ingrese el mes (1-12): ");
                        mes = scanner.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("INTRODUCELO NUMERICAMENTE!");
                        scanner.nextLine();
                        break;
                    }

                    //Configurem la variable dia_max per a que s'adapti als dies del mes en funcio del mes en el que
                    //ens trobem

                    if ((any % 4 == 0) && (mes == 2)) {
                        dia_max = 29;
                    } else if ((any % 4 != 0) && (mes == 2)) {
                        dia_max = 28;
                    } else if ((mes % 2 == 0) && (mes <= 7)) {
                        dia_max = 30;
                    } else if (mes % 2 == 0) {
                        dia_max = 31;
                    } else if ((mes % 2 != 0) && (mes <= 7)) {
                        dia_max = 31;
                    } else {
                        dia_max = 30;
                    }

                    //un cop sabem el mes que volem consultar mostrem al usuari el mes sencer i marquem
                    // amb un * els dies que tenen alguna cosa apuntada

                    for (int i2 = 0; i2 < dia_max; i2++) {
                        if (agenda[mes - 1][i2] == null) {
                            System.out.print(" " + c_mesos);
                        } else {
                            System.out.print(" " + c_mesos + "*");
                        }

                        if (c_mesos % 7 == 0) {
                            System.out.println();
                        }
                        c_mesos++;
                    }

                    try {
                        System.out.println();
                        System.out.print("Ingrese el día: ");
                        dia = scanner.nextInt();
                        scanner.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("INTRODUCELO NUMERICAMENTE!");
                        break;
                    }

                    //farem un if que ens permetra comprovar la data, un cop fet aixo reestablirem el
                    //espai del array com a null nomes si la confirmació d'eliminació es positiva fent-ho aixi mes realista

                    if ((mes >= 1 && mes <= 12) && (dia >= 1 && dia <= dia_max)) {
                        if (agenda[mes - 1][dia - 1] != null) {
                            System.out.println("Acontecimientos en el día seleccionado:");
                            System.out.println(agenda[mes - 1][dia - 1]);
                            System.out.print("¿Desea eliminar todos los acontecimientos de este día? (S/Qualquier letra): ");
                            respuesta = scanner.nextLine();
                            if (respuesta.equalsIgnoreCase("S")) {
                                agenda[mes - 1][dia - 1] = null;
                                System.out.println("Acontecimientos eliminados correctamente.");
                            } else {
                                System.out.println("Acontecimientos no eliminados.");
                            }
                        } else {
                            System.out.println("No hay acontecimientos en el día seleccionado.");
                        }
                    } else {
                        System.out.println("Fecha no valida");
                    }
                    break;

                case 3:
                    //reiniciem la variable que ens permet mostrar els mesos ja que sino com mes cops entrem a la case
                    // mes grans seran els dies del mes igual que al case 2
                    c_mesos = 1;

                    //comprobem si hi ha alguna cosa a la agenda ja que si no hi ha res aquesta opcio no te cap sentit
                    //igual que als case 2

                    a_buida = false;

                    for (int i = 0; i < agenda.length; i++) {
                        for (int j = 0; j < agenda[i].length; j++) {
                            if (agenda[i][j] != null) {
                                a_buida = true;
                            }
                        }
                    }

                    if (!a_buida) {
                        System.out.println("No hay acontecimientos en la agenda");
                        break;
                    }

                    //Per a ubicar la data seguim el mateix procediment que al case 1 i 2:
                    try {
                        System.out.print("Ingrese el mes (1-12): ");
                        mes = scanner.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("INTRODUCELO NUMERICAMENTE!");
                        scanner.nextLine();
                        break;
                    }

                    //Configurem la variable dia_max per a que s'adapti als dies del mes en funcio del mes en el que
                    //ens trobem igual que al case 2

                    if ((any % 4 == 0) && (mes == 2)) {
                        dia_max = 29;
                    } else if ((any % 4 != 0) && (mes == 2)) {
                        dia_max = 28;
                    } else if ((mes % 2 == 0) && (mes <= 7)) {
                        dia_max = 30;
                    } else if (mes % 2 == 0) {
                        dia_max = 31;
                    } else if ((mes % 2 != 0) && (mes <= 7)) {
                        dia_max = 31;
                    } else {
                        dia_max = 30;
                    }

                    //un cop sabem el mes que volem consultar mostrem al usuari el mes sencer i marquem
                    // amb un * els dies que tenen alguna cosa apuntada

                    for (int i2 = 0; i2 < dia_max; i2++) {
                        if (agenda[mes - 1][i2] == null) {
                            System.out.print(" " + c_mesos);
                        } else {
                            System.out.print(" " + c_mesos + "*");
                        }

                        if (c_mesos % 7 == 0) {
                            System.out.println();
                        }
                        c_mesos++;
                    }

                    try {
                        System.out.println();
                        System.out.print("Ingrese el día: ");
                        dia = scanner.nextInt();
                        scanner.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("INTRODUCELO NUMERICAMENTE!");
                        break;
                    }

                    //filtrem la informació a través de condicionals i mostrem el que l'usuari ha demanat
                    if ((mes >= 1 && mes <= 12) && (dia >= 1 && dia <= 31)) {
                        if (agenda[mes - 1][dia - 1] != null) {
                            System.out.println("Acontecimientos en el día seleccionado:");
                            System.out.println(agenda[mes - 1][dia - 1]);
                        } else {
                            System.out.println("No hay acontecimientos en el día seleccionado.");
                        }
                    } else {
                        System.out.println("Fecha no válida.");
                    }
                    break;

                case 4:
                    //aquest apartat ens servira per a que l'usuari pugui informar-se una mica sobre el funcionament i
                    // un parell de consells per al ús del nostre projecte, es un apartat amb complexitat 0 ja que no
                    // hem treballat la lectura de fitxers

                    System.out.println();
                    System.out.println("- Sigue las intrucciones del programa sin adelantarte");
                    System.out.println("- La agenda contemplara automaticamente si nos encontramos en año viciesto");
                    System.out.println("- Debes intentar no cometer errores de escritura ya que aunque la mayoria estan controlados a veces puede fallar");
                    System.out.println("- Una cunado consultes o quieras eliminar, una vez seleccionado el mes, este aparecera gràficament de forma en que los dias con acontecimientos tendran un '*' al lado");
                    System.out.println("- Se prudente ya que si cierras la agenda perderas todos los datos que has guardado");
                    System.out.println("- Introduce todos los valores numericamente excepto los acontecimientos o los casos en los que se indica lo contrario");
                    System.out.println();
                    break;

                case 5:
                    //aquest case ens servirà per mostrar un petit missatge per demostra que l'agenda no s'ha tancat per error.
                    // Es un missatge de comiat
                    System.out.println("S'HAN ACABAT ELS COMPROMISOS. ¡FINS AVIAT!");
                    break;

                default:
                    //default del bucle principal que ens permet que el programa no exploti en cas d'introduir una opció
                    // diferent al interval 1-5 a mes de mostrar un petit missatge al usuari per a dir-li que s'ha equivocat

                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }

        } while (opcion != 5);

        //tanquem scanner un cop acabat el programa per a optimitzar recursos
        scanner.close();
    }
}
