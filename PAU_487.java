package EjerciciosThread;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;


public class PAU_487 {
    
    public static void bloquejarPantalla() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nToca 'C' per a continuar ");
        while (in.hasNext()) {
            if ("C".equalsIgnoreCase(in.next())) break;
        }
    }
    
    
    public static void menuPAU_487() throws IOException  {
        String opcio;
        Scanner sc = new Scanner(System.in);
        StringBuilder menu = new StringBuilder("");
        
        do {
        	menu.delete(0, menu.length());
            
            menu.append(System.getProperty("line.separator"));
            menu.append("PAU-487 ");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            menu.append("1. Threads implements Runnable");
            menu.append(System.getProperty("line.separator"));
            menu.append("2. Threads extends Thread (I)"); 
            menu.append(System.getProperty("line.separator"));
            menu.append("3. Threads extends Thread (II)");
            menu.append(System.getProperty("line.separator"));
            menu.append("4. Threads amb wait() i notify()");
            menu.append(System.getProperty("line.separator"));
            menu.append("5. Threads amb semafors (I)");
            menu.append(System.getProperty("line.separator"));
            menu.append("6. Threads amb semafors (II)");
            
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            menu.append("10. Llancar tots el terpedes");
            
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            menu.append("50. Tancar el sistema");
            menu.append(System.getProperty("line.separator"));
            
            System.out.print(Pantalles.MenuConstructorPantalla.constructorPantalla(menu));
            
            opcio = sc.next();
            
            switch (opcio) {
                case "1":
                	Exercici_1.inicialitzarPrograma();
                    bloquejarPantalla();
                    break;
                    
                case "2":
                	Exercici_2_v1.inicialitzarPrograma();
                    bloquejarPantalla();
                    break;
                    
                case "3":
                	Exercici_2_v2.inicialitzarPrograma();
                    bloquejarPantalla();
                    break;
                case "4":
                	
                	Exercici_2_v3.inicialitzarPrograma();
                    bloquejarPantalla();
                    break;
                    
                case "5":
                	Exercici_2_v4.inicialitzarPrograma();
                	bloquejarPantalla();
                    break;
                    
                case "6":
                	Exercici_2_v5.inicialitzarPrograma();
                	bloquejarPantalla();
                	break;
                
                case "10":
                	Exercici_2_v6.inicialitzarPrograma();
                	bloquejarPantalla();
                	break;
                	
                case "50":
                    break; 
                default:
                    System.out.println("COMANDA NO RECONEGUDA");
            }  
        } while (!opcio.equals("50"));
    }
    
}