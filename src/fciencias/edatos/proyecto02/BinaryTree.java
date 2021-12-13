package fciencias.edatos.proyecto02;
	
import java.util.Date;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

/**
 * Implementación de un árbol binario.
 * @author Pintor Muñoz Pedro Joshue - 420053796
 * @version 1.0 Diciembre 2021
 * @since Estructuras de Datos 2022-1.
 */
public class BinaryTree <T>{

	/* Identifica cuando agregar preguntas */
	private int universalError;

	/* Clave universal */
	private int universalKey;

	/* Conteo general de preguntas */
	private int conteo;

	/**
	 * Nodo para un árbol binario.
	 */
	public class BinaryNode {

		/** Clave. */
		public int key;

		/** Pregunta */
		public int question;	//0 si es una respuesta, 1 si es una pregunta.

		/** Fecha de insersion */
		public String date;

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
		 * @param question Si es pregunta o respuesta.
		 * @param date La fecha de insersion.
		 */
		public BinaryNode(T element, BinaryNode parent, int key, int question, String date){
			this.key=key;
			this.question = question;
			this.element = element;
			this.parent = parent;
			this.date = date;
		}
	}

	/** Root */
	private BinaryNode root;

	/**
	 * Recupera el nodo con clave k.
	 * @param k la clave a buscar.
	 * @return el elemento con clave k o null si no existe.
	 */
	public BinaryNode retrieveBFS(int k){		//Busqueda por BFS 

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

					cola.enqueue(actual.left);
					cola.enqueue(actual.rigth);

				}else
					node=null;
			}
		}
		return node;
	}

	/**
	 * Inserta un nuevo elemento al árbol.
	 * @param e el elemento a ingresar.
	 * @param k la clave del elemento a ingresar.
	 * @param question Indica si es una pregunta o resultado.
	 * @param date Fecha de intersion
	 */
	public void insert(T e, int k, int question, String date){

		Date fechaHora = new Date();

		// Si es vacío entonces insertamos al nuevo elemento como la raíz del árbol
		if(root == null){
			if(date.equals(".")){
				BinaryNode inicio = new BinaryNode(e, null, k, question,""+fechaHora);
				this.root = inicio;
				colaPreguntas(root);
				return;
			} else {
				BinaryNode inicio = new BinaryNode(e, null, k, question,date);
				this.root = inicio;
				return;
			}
		} else {			 
			if(k%2==0){
				if(date.equals(".")){

					BinaryNode aux = retrieveBFS(k/2);
					aux.left = new BinaryNode(e,aux,k,question,""+fechaHora);
					colaPreguntas(aux.left);
					return;

				} else {						
					BinaryNode aux = retrieveBFS(k/2);
					aux.left = new BinaryNode(e,aux,k,question,date);
					return;
				}
			}
			else{
				if(date.equals(".")){
					BinaryNode aux = retrieveBFS(k/2);
					aux.rigth = new BinaryNode(e,aux,k,question,""+fechaHora);
					colaPreguntas(aux.rigth);
					return;
				} else {
					BinaryNode aux = retrieveBFS(k/2);
					aux.rigth = new BinaryNode(e,aux,k,question,date);
					return;
				}
			}
		}	
	}

	/**
	 * Metodo que se encarga de cargar las preguntas de un archivo txt.
	 * @param rutaArchivo Es la ruta que tiene el archivo
	 * @param tree Es el arbol donde se guardarán las preguntas
	 * */
	public static void bancoPreguntas(String rutaArchivo, BinaryTree tree){

        String linea = null;     

        try{
            Reader reader = new FileReader(rutaArchivo);                                //Reader permite leer el archivo como un objeto de tipo File
            BufferedReader lector = new BufferedReader(reader);                         //BufferedReader permite leer el contenido en forma de cadenas 
            while((linea = lector.readLine()) != null){                                 //es por eso que el buffer recibe como parametro el reader anterior
                if(linea != null){                                                      //Para toma los datos de ese objeto para ser leidos se usa el método readLine().  
                    String[] cadena=linea.split("[$]");                                 //split Corta las lineas de codigo cada que encuentra un $.
                    tree.insert(cadena[0],Integer.parseInt(cadena[1]),Integer.parseInt(cadena[2]),cadena[3]); //RECORDATORIO: Integer.parceInt(cadena[1]) ---- Para Que convierta la cadena en un entero.                                             
                }                                                                       
            }   
            lector.close();            
        }catch(FileNotFoundException fnfe){
            System.out.println("No se encuentra el archivo");
        }catch(IOException ioe){
            System.out.println("Error en la lectura del archivo");
        }
	}

	/**
	 * Método que hace las preguntas del juego.
	 * @param tree El arbol con las preguntas.
	 * */
	public BinaryNode juego(){

		BinaryNode resultado = null;
		BinaryNode node = root;
		Scanner sc = new Scanner(System.in);
		String rojo = "\u001B[31m", blanco = "\u001B[37m";
		String respuesta = "";
		Boolean ready = false;

		do{
			try{
				if(node.left != null && node.rigth != null){
					conteo++;
					System.out.println(node.element);
					respuesta = sc.nextLine();

					if( !respuesta.equals("si") && !respuesta.equals("no") ){
						throw new Exception();
					}

					if(respuesta.equals("si"))
						resultado = juego(node.left);

					if(respuesta.equals("no"))
						resultado = juego(node.rigth);

					ready = true;

				}
			}catch(Exception e){
				System.out.println("\n\n"+rojo+"SOLO RESPUESTAS 'si' o 'no'."+blanco+"\n\n");
				conteo--;
				ready = false;
			}

			
		}while( ready == false );

		if(universalError == 1 || universalError == 2 || universalError == 3){								//AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
			addNew(resultado,this);												
		}

		return resultado;
	}

	/**
	 * Método auxiliar del juego que hace la recursión.
	 * */
	public BinaryNode juego(BinaryNode node){

		BinaryNode actual = node;
		Scanner sc = new Scanner(System.in);
		String rojo = "\u001B[31m", blanco = "\u001B[37m";
		String respuesta = "";
		Boolean ready = false;

		if(conteo>20){
			universalError = 3;
			return null;
		}

		if( actual.question == 0 ){		//Define si la pregunta está en una hoja
			do{
				try{
					conteo++;
					System.out.println(node.element);
					respuesta = sc.nextLine();

					if( !respuesta.equals("si") && !respuesta.equals("no") ){
						throw new Exception();
					}

					if(respuesta.equals("si")){
						System.out.println("Lo Sabía! ¡¡PUNTO PARA LOS ROBOTS!!\n");
				        try{
				            Thread.sleep(2500);
				        }catch(InterruptedException ie){}
						return actual;
					} else {														
						if(conteo>20){
							universalError = 3;
							return null;
						}
						System.out.println("Vaya... no tengo una respuesta para tu animal.");
						this.universalError = 2;
						this.universalKey = actual.key;
						return actual;
					}

				}catch(Exception e){
					System.out.println("\n\n"+rojo+"SOLO RESPUESTAS 'si' o 'no'."+blanco+"\n\n");
					conteo--;
					ready = false;
				}
				
			}while( ready == false );

		} else {						//Hace recursión hasta llegar a una hoja

			do{
				try{
					conteo++;
					System.out.println(node.element);
					respuesta = sc.nextLine();

					if( !respuesta.equals("si") && !respuesta.equals("no") ){
						throw new Exception();
					}

					if(respuesta.equals("si")){
						if(node.left == null){
							this.universalError = 1;
							this.universalKey = node.key*2;
							System.out.println("Vaya... no tengo una respuesta a eso.");
							return actual;
						}
						else
							return juego(node.left);
					}
					else{
						if(node.rigth == null){
							this.universalError = 1;
							this.universalKey = (node.key*2)+1;
							System.out.println("Vaya... no tengo una respuesta a eso.");
							return actual;
						}
						else
							return juego(node.rigth);
					}

				}catch(Exception e){
					System.out.println("\n\n"+rojo+"SOLO RESPUESTAS 'si' o 'no'."+blanco+"\n\n");
					conteo--;
					ready = false;
				}

			}while( ready == false );
		}
		return null;
	}

	/**
	 * Método para que el usuario agregue nuevas preguntas
	 * 
	 * */
	public void addNew(BinaryNode node, BinaryTree tree){

		String respuesta = "";
		String respuesta2 = "";
		Scanner sc = new Scanner(System.in);

		switch(universalError){
			case 1:				//Cuando no tiene respuesta
		        try{
		            Thread.sleep(2500);
		        }catch(InterruptedException ie){}

				System.out.println("En que animal estabas pensado");
				respuesta = sc.nextLine(); 

				tree.insert("Tu animal es "+respuesta, universalKey, 0,".");
				universalKey = 0;
				universalError = 0;

				System.out.println("Gracias !! Lo recordaré");

				try{
					Thread.sleep(2500);
				}catch(InterruptedException ie){}

				break;

			case 2:				//Cuando falló en la respuesta

				BinaryNode aux = retrieveBFS(universalKey);

		        try{
		            Thread.sleep(2500);
		        }catch(InterruptedException ie){}

				System.out.println("En que animal estabas pensado");
				respuesta = sc.nextLine(); 
				System.out.println("¿Que tiene de diferente tu animal?");
				respuesta2 = sc.nextLine();

				if(universalKey%2==0)
					retrieveBFS(universalKey).parent.left = null;
				else					
					retrieveBFS(universalKey).parent.rigth = null;


				tree.insert("¿"+respuesta2+"?",universalKey,1,".");
				aux.key = (universalKey*2)+1;
				retrieveBFS(universalKey).rigth = aux;

				tree.insert("Tu animal es "+respuesta, universalKey*2, 0,".");

				universalKey = 0;
				universalError = 0;

				System.out.println("Gracias !! Lo recordaré");

				try{
					Thread.sleep(2500);
				}catch(InterruptedException ie){}

				break;

			case 3:				//Excepcion cuando se terminó las 20 preguntas

				System.out.println("20 preguntas no fueron suficiente, me haz vencido.");
				try{
					Thread.sleep(2500);
				}catch(InterruptedException ie){}

				break;
		}

	}


	/**
	 * Metodo que se encarga de escribir las preguntas en un archivo txt.
	 * @param rutaArchivo Es la ruta que tiene el archivo
	 * @param tree Es el arbol donde se guardarán las preguntas
	 * */
	public void escribePreguntas(){

		try{
			File directorio = new File("src/fciencias/edatos/proyecto02/preguntas");
			File archivo = new File(directorio,"binaryTree.txt");

			FileWriter escritor = new FileWriter(archivo);
			BufferedWriter escritorBuff = new BufferedWriter(escritor);

			BinaryNode node = root;
			if(node != null){
				TDAQueue<BinaryNode> cola = new Queue<>();
				cola.enqueue(node);

				while(!cola.isEmpty()){

					BinaryNode actual = cola.dequeue();
					if(actual != null){

						escritorBuff.write(actual.element+"$"+actual.key+"$"+actual.question+"$"+actual.date+"\n");

						cola.enqueue(actual.left);
						cola.enqueue(actual.rigth);

					}else
						node=null;
				}
			}

			escritorBuff.close(); 

		}catch(IOException ioe){
            System.out.println("Error en la lectura del archivo");
        }
	}


	/**
	 * Método que recive una pregunta y la guarda en un archivo de texto
	 * @param node Nodo para guardar elemento en le archivo.
	 * */
	public void colaPreguntas(BinaryNode node){

		if(node.question == 1){
			try{
				File directorio = new File("src/fciencias/edatos/proyecto02/preguntas");
				File archivo = new File(directorio,"preguntas.txt");

				FileWriter escritor = new FileWriter(archivo,true);
				BufferedWriter escritorBuff = new BufferedWriter(escritor);

				escritorBuff.write(node.element+"  // Fecha:  "+node.date+"\n");

				escritorBuff.close(); 

			}catch(IOException ioe){
	            System.out.println("Error en la lectura del archivo");
	        }
		}
		else {
			try{
				File directorio = new File("src/fciencias/edatos/proyecto02/preguntas");
				File archivo = new File(directorio,"animales.txt");

				FileWriter escritor = new FileWriter(archivo,true);
				BufferedWriter escritorBuff = new BufferedWriter(escritor);

				
				escritorBuff.write(node.element+"  // Fecha:"+node.date+"\n");

				escritorBuff.close(); 

			}catch(IOException ioe){
	            System.out.println("Error en la lectura del archivo");
	        }
		}
	}

	/**
	 * Muestra las preguntas o respuestas en el orden de entrada
	 * @param i un entero que identifica si se tienen que mostrar las respuestas o preguntas.
	 * */
	public void entradaPreguntas(int i){

		if(i==1){
        	String linea = null;     
	        try{
	            Reader reader = new FileReader("src/fciencias/edatos/proyecto02/preguntas/preguntas.txt");                               
	            BufferedReader lector = new BufferedReader(reader);                        
	            while((linea = lector.readLine()) != null){                              
					System.out.println(linea);                                                                     
	            }   
	            lector.close();            
	        }catch(FileNotFoundException fnfe){
	            System.out.println("No se encuentra el archivo");
	        }catch(IOException ioe){
	            System.out.println("Error en la lectura del archivo");
	        }

		} else {
        	String linea = null;     
	        try{
	            Reader reader = new FileReader("src/fciencias/edatos/proyecto02/preguntas/animales.txt");                               
	            BufferedReader lector = new BufferedReader(reader);                        
	            while((linea = lector.readLine()) != null){                              
					System.out.println(linea);                                                                     
	            }   
	            lector.close();            
	        }catch(FileNotFoundException fnfe){
	            System.out.println("No se encuentra el archivo");
	        }catch(IOException ioe){
	            System.out.println("Error en la lectura del archivo");
	        }
		}
	}

	/**
	 * Muestra las preguntas o respuestas en el orden de entrada
	 * @param i un entero que identifica si se tienen que mostrar las respuestas o preguntas.
	 * */
	public void alfabeticoPreguntas(int i){

		if(i==1){
			int contLineas = 0;
        	String linea = null;     
	        try{
	            Reader reader = new FileReader("src/fciencias/edatos/proyecto02/preguntas/preguntas.txt");                               
	            BufferedReader lector = new BufferedReader(reader);                        
	            while((linea = lector.readLine()) != null){

					contLineas++;                                                                  
	            }   
	            lector.close();            
	        }catch(FileNotFoundException fnfe){
	            System.out.println("No se encuentra el archivo");
	        }catch(IOException ioe){
	            System.out.println("Error en la lectura del archivo");
	        }
    		
    		String[] cadenas = new String[contLineas];
    		int cont = 0;

		    try{
		        Reader reader = new FileReader("src/fciencias/edatos/proyecto02/preguntas/preguntas.txt");                               
		        BufferedReader lector = new BufferedReader(reader);                        
		        while((linea = lector.readLine()) != null){

					String[] pregunta=linea.split("[//]");
						cadenas[cont++] = pregunta[0];  
						                                                                  
		        }   
		        lector.close();            
		    }catch(FileNotFoundException fnfe){
		        System.out.println("No se encuentra el archivo");
		    }catch(IOException ioe){
		        System.out.println("Error en la lectura del archivo");
		    }

	        for(int j=0; j<cadenas.length; j++){
	        	for(int k=0; k<cadenas.length && j!=k; k++){
	        		if( cadenas[j].compareToIgnoreCase(cadenas[k])<0){
	        			String aux = cadenas[j];
	        			cadenas[j] = cadenas[k];
	        			cadenas[k] = aux;
	        		}
	        	}
	        }

	        for(int j=0; j<cadenas.length; j++)
	        	System.out.println(cadenas[j]);
	        	

		} else {
			int contLineas = 0;
        	String linea = null;     
	        try{
	            Reader reader = new FileReader("src/fciencias/edatos/proyecto02/preguntas/animales.txt");                               
	            BufferedReader lector = new BufferedReader(reader);                        
	            while((linea = lector.readLine()) != null){

					contLineas++;                                                                  
	            }   
	            lector.close();            
	        }catch(FileNotFoundException fnfe){
	            System.out.println("No se encuentra el archivo");
	        }catch(IOException ioe){
	            System.out.println("Error en la lectura del archivo");
	        }
    		
    		String[] cadenas = new String[contLineas];
    		int cont = 0;


		    try{
		        Reader reader = new FileReader("src/fciencias/edatos/proyecto02/preguntas/animales.txt");                               
		        BufferedReader lector = new BufferedReader(reader);                        
		        while((linea = lector.readLine()) != null){
					String[] pregunta=linea.split("[//]");
					cadenas[cont++] = pregunta[0];  					                                                                  
		        }   
		        lector.close();            
		    }catch(FileNotFoundException fnfe){
		        System.out.println("No se encuentra el archivo");
		    }catch(IOException ioe){
		        System.out.println("Error en la lectura del archivo");
		    }

	        for(int j=0; j<cadenas.length; j++){
	        	for(int k=0; k<cadenas.length && j!=k; k++){
	        		if( cadenas[j].compareToIgnoreCase(cadenas[k])<0){
	        			String aux = cadenas[j];
	        			cadenas[j] = cadenas[k];
	        			cadenas[k] = aux;
	        		}
	        	}
			}

	        for(int j=0; j<cadenas.length; j++)
	        	System.out.println(cadenas[j]);
		}

	}
}