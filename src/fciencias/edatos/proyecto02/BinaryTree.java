package fciencias.edatos.proyecto02;
	
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

/**
* Implementación de un árbol binario.
* @author Pintor Muñoz Pedro Joshue - 420053796
* @version 1.0 Diciembre 2021
* @since Estructuras de Datos 2022-1.
*/
public class BinaryTree <T>{
	
	/**
	 * Nodo para un árbol binario.
	 */
	public class BinaryNode {

		/** Clave. */
		public int key;

		/** Pregunta */
		public int question;	//0 si es una respuesta, 1 si es una pregunta.

		/** Elemento. */
		public T element;

		/** Padre del nodo. */
		public BinaryNode parent;

		/** Hijo izquierdo. */
		public BinaryNode left;

		/** Hijo derecho. */
		public BinaryNode rigth;

		/**
		 * Crea un nuevo nodo.
		 * @param key la clave.
		 * @param element el elemento a almacenar.
		 * @param parent el padre del nodo.
		 */
		public BinaryNode(T element, BinaryNode parent, int key, int question){
			this.key=key;
			this.question = question;
			this.element = element;
			this.parent = parent;
		}
	}

	/** Root */
	private BinaryNode root;

	public BinaryNode retrieveBFS(int k){						//Busqueda por BFS 

		BinaryNode node = root;
		if(node == null)
			return null;
		else{
			TDAQueue<BinaryNode> cola = new Queue<>();
			cola.enqueue(node);

			while(!cola.isEmpty()){

				BinaryNode actual = cola.dequeue();
				if(actual != null){

					if(actual.key == k)
						return actual;

					if(actual.left != null)
						cola.enqueue(actual.left);

					if(actual.rigth != null)
						cola.enqueue(actual.rigth);
				}else
					node=null;
			}
		}
		return node;

	}

	public void insert(T e, int k, int question){
		// Si es vacío entonces insertamos al nuevo elemento como la raíz del árbol
		if(root == null){
			BinaryNode inicio = new BinaryNode(e, null, k, question);
			this.root = inicio;
			return;
		} else {			 
			if(k%2==0){
				BinaryNode aux = retrieveBFS(k/2);
				aux.left = new BinaryNode(e,aux,k,question);
				return;
			}
			else{
				BinaryNode aux = retrieveBFS(k/2);
				aux.rigth = new BinaryNode(e,aux,k,question);
				return;
			}
		}	
	}

	/**
	 * Método auxiliar de retrieve.
	 * */
	/*private BinaryNode retrieve(BinaryNode actual, K k){
		// No se encuentra el elemento
		if(actual == null)
			return null;

		// Si encontramos el elemento
		
		//Elemento a buscar 12 
		retrieve(6).left = new BinaryNode(elemento,retrieve(6),k,esPregunta)
	}*/


	/**
	 * Metodo que se encarga de cargar las preguntas de un archivo txt.
	 * @param rutaArchivo Es la ruta que tiene el archivo
	 * @param tree Es el arbol donde se guardarán las preguntas
	 * 
	public static void bancoPreguntas(String rutaArchivo, BinaryTree tree){

        String linea = null;     

        try{
            Reader reader = new FileReader(rutaArchivo);                                //Reader permite leer el archivo como un objeto de tipo File
            BufferedReader lector = new BufferedReader(reader);                         //BufferedReader permite leer el contenido en forma de cadenas 
            while((linea = lector.readLine()) != null){                                 //es por eso que el buffer recibe como parametro el reader anterior
                if(linea != null){                                                      //Para toma los datos de ese objeto para ser leidos se usa el método readLine().  
                    String[] cadena=linea.split("[$]");                                 //split Corta las lineas de codigo cada que encuentra un $.
                    tree.insert(cadena[0],cadena[1]);					        		//RECORDATORIO: Integer.parceInt(cadena[1]) ---- Para Que convierta la cadena en un entero.                                             
                }                                                                       
            }   
            lector.close();            
        }catch(FileNotFoundException fnfe){
            System.out.println("No se encuentra el archivo");
        }catch(IOException ioe){
            System.out.println("Error en la lectura del archivo");
        }
	}
*/



/* METODOS QUE PROBABLEMENTE SE BORREN (Para pruebas)*/

	public void inorden(){
		this.inorden(root);
	}

	private void inorden(BinaryNode node){

		// Primero verifica la raiz
		if(node == null)
			return;

		// Aplica inorden al izquierdo
		inorden(node.left);

		System.out.println(node.element);

		// Aplica inorden al derecho
		inorden(node.rigth);
	}

	public BinaryNode retrieveBFS(){						//Busqueda por BFS 

		BinaryNode node = root;
		if(node == null)
			return null;
		else{
			TDAQueue<BinaryNode> cola = new Queue<>();
			cola.enqueue(node);

			while(!cola.isEmpty()){
				BinaryNode actual = cola.dequeue();
				if(actual != null){

					System.out.println(actual.element);
					cola.enqueue(actual.left);
					cola.enqueue(actual.rigth);
				}
			}
		}
		return node;

	}


}