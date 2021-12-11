package fciencias.edatos.proyecto02;    

//import java.util.Random;
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
		

		
		tree.insert("agua",1,1);
		tree.insert("grande",2,1);
		tree.insert("vuela",3,1);
		tree.insert("Es ballena",4,0);
		tree.insert("depredador",6,1);
		tree.insert("es aguila",12,0);
		tree.insert("es colibri",13,0);
		tree.insert("es Perro",7,0);

		//tree.inorden();
		
		//System.out.println("listo 3");
		tree.retrieveBFS();	






		/*
		tree.insert("grande",2,1);
		tree.insert("vuela",3,1);
		tree.insert("Es ballena",4,0);
		tree.insert("depredador",6,1);
		tree.insert("es aguila",12,0);
		tree.insert("es colibri",13,0);
		tree.insert("es Perro",7,0);
		*/

		//tree.juego();

		//bancoPreguntas(directorio,tree);

		//No pude adivinar al animal en el que pensaste.
		//Ayudame a aprender, define una pregunta para encontrar a tu animal
		//*** aqui pido la pregunta ***													IMPORTANTE: este nodo no es hoja
		// Perfecto, ¿ahora cual es el animal en el que estabas pensando?
		//*** Pido el animal.
		//*** Sin mostrar al usuario formulo la pregunta ¿Tu animal es *** ? 			IMPORTANTE: este nodo es hoja

		//System.out.println("");

		
		//tree.inorden();











/*
		TDADoubleList<String> lista = new DoubleLinkedList<>();

		lista.add(0,"Hola");
		lista.add(1,"Esto");
		lista.add(2,"es");
		lista.add(3,"una");
		lista.add(4,"prueba");
		lista.add(5,"xd");

		System.out.println(lista.toString());
		lista.remove(0);
		lista.remove(0);
		lista.remove(0);

		System.out.println(lista.toString());
		*/
	}

}