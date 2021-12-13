package fciencias.edatos.proyecto02;    

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Clase principal donde se ejecuta el juego.
 * @author Pintor Muñoz Pedro Joshue - 420053796
 * @version 1.0 Diciembre 2021
 * @since Clase de Estructuras de Datos 2022-1
 */

public class Juego extends BinaryTree{

	public static void main(String[] args){

		BinaryTree<String> tree = new BinaryTree<>();
		String directorio = "src/fciencias/edatos/proyecto02/preguntas/binaryTree.txt";
		bancoPreguntas(directorio, tree);
		Scanner sc = new Scanner(System.in);

        int opc = 0;

	    System.out.println("PROYECTO 02 --- Juego de las 20 preguntas.\n\nEl juego de las 20 preguntas o 20Q consiste en que alguien"+
	        				" piense en un objeto \ny el resto de los jugadores deberá adivinar "+
	        				"cuál es el objeto haciendo a lo más 20 preguntas \ncuyas respuestas solo pueden ser si o no.\n\nEn este caso"+
	        				" será la computadora quien haga las preguntas y el usuario solo responderá sı́ o no.");

	    System.out.println("\n──▒▒▒▒▒▒───▄████▄\n─▒─▄▒─▄▒──███▄█▀ \n─▒▒▒▒▒▒▒─▐████──█─█ \n─▒▒▒▒▒▒▒──█████▄ \n─▒─▒─▒─▒───▀████▀ \n");
	    System.out.println("\nPresiona Enter para comenzar.");
	    sc.nextLine();

		tree.juego();			
		tree.escribePreguntas();

		do{
			try{
                System.out.println("\nMenú:\n\n"+
                						"1) Ver preguntas en orden alfabetico"+
                						"\n2) Ver preguntas en orden de entrada"+
                						"\n3) Ver animales en orden alfabetico"+
                						"\n4) Ver animales en orden de entrada"+
                						"\n5) Salir del programa");
                opc = sc.nextInt();
                switch(opc){

                	case 1:
                		tree.alfabeticoPreguntas(1);
						try{
				            Thread.sleep(2500);
				        }catch(InterruptedException ie){}
                		break;

                	case 2:
                		tree.entradaPreguntas(1);
						try{
				            Thread.sleep(2500);
				        }catch(InterruptedException ie){}
                		break;

                	case 3:
                		tree.alfabeticoPreguntas(0);
						try{
				            Thread.sleep(2500);
				        }catch(InterruptedException ie){}
                		break;

                	case 4:
                		tree.entradaPreguntas(0);
						try{
				            Thread.sleep(2500);
				        }catch(InterruptedException ie){}

                		break;

                	case 5:
                		break;

                	default:
                		throw new Exception();

                }

            }catch(InputMismatchException ime){
                System.out.println("ERROR: Ingresa un numero.");
                sc.nextLine();
            }catch(Exception e){
                System.out.println("ERROR: Opcion invalida.");
                sc.nextLine();
            }

		}while(opc!=5);
	}

}