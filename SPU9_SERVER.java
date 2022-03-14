package Sockets2;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class SPU9_SERVER {

	private static int puerto=9090;
	private static int puertoFTP=9091;
	private static boolean verMenu=true;
	private static boolean acabarConexion=false;
	private static boolean finConexion=false;
	//private static String rutaEnvio ="/home/users/inf/wiam2/iam47264842/Escritorio/envio/";
	//private static String rutaRecibir ="/home/users/inf/wiam2/iam47264842/Escritorio/sockets/";

	private static String rutaEnvio ="C:\\Users\\monts\\Desktop\\a\\sockets\\dirServidor\\envio\\";
	private static String rutaRecibir ="C:\\Users\\monts\\Desktop\\a\\sockets\\dirServidor\\recibir\\";



	public static void main(String[] args) throws IOException {
		System.out.println("SERVER: INICIO");
		escoltar();
		System.out.println("SERVER: FIN");
	}

	private static void escoltar() {


		boolean end = false;

		ServerSocket serverSocket = null;
		Socket clientSocket = null;

		try {
			serverSocket = new ServerSocket(puerto);// crea un server socket que responde a la gente que se conecta a ese
													// puerto

			while (!acabarConexion) {// hace bucle hastqa que alguien pida algo

				clientSocket = serverSocket.accept();

				procesarComunicacionsAmbClient(clientSocket);// procesamos lo que quiere el cliente


				tancarSocketServidor(clientSocket);// cerramos el socket temporal para atender el cliente
			}

			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			} // si nadie mas pide cosas, cerramos el socket principal
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void procesarComunicacionsAmbClient(Socket clientSocket) {

		boolean acabarComunicacion = false;
		String mensaje = "";
		PrintWriter printStream=null;
		BufferedReader leeReader=null;


		try {
			leeReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));// iniciamos el leer
			printStream = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);// iniciamos el escrubur

			while (!acabarConexion) {

				mensaje = leeReader.readLine();// leemos lo que nos envia

				acabarConexion = procesarMissatgesDelClient(clientSocket, mensaje, leeReader, printStream);

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static boolean procesarMissatgesDelClient(Socket clientSocket, String mensaje, BufferedReader leeReader,
			PrintWriter printStream) {



		// nos envia el texto. Hemos de mirar que sea uno de los dichos y entonces
		// llamar al que debes

		if (mensaje != null) {

			if (!mensaje.equals(null) || !mensaje.equals("") || !mensaje.equals(" ")) {

				if (mensaje.equals("DEMANAR_PERMIS_CONNEXIO")) {
					System.out.println("Opcion seleccionada: DEMANAR_PERMIS_CONNEXIO");
					respondreAPermisConnexio(printStream);

				}
				if (mensaje.equals("RETORN_CONTROL")) {
					verMenu=true;
					//retornControl(true,printStream);

				}
				//esto lo envia el cliente, asi que el inicio es de el, no nuestro
				if (mensaje.equals("INICIALITZAR_CHAT")) {
					System.out.println("Opcion seleccionada: INICIALITZAR_CHAT");

					chat(false, leeReader, printStream);
				}
				if (mensaje.equals("INICIALITZAR_FTP")) {
					System.out.println("Opcion seleccionada: INICIALITZAR_FTP");

					ftp(false, clientSocket, leeReader, printStream);
				}

			}

			if(verMenu==true){
				try {

					menuServer(clientSocket, leeReader, printStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else{acabarConexion=true;}

		return acabarConexion;

	}

	private static void ftp(boolean iniciado, Socket clientSocket, BufferedReader leeReader, PrintWriter printStream) {

		String nombrArchivoEnvio="envioServidor.txt";
		BufferedInputStream bufferedInputStream = null;
		OutputStream outputStream = null;
		BufferedOutputStream bufferedOutputStream=null;
		InputStream inputStream=null;
		String recibido = "";
		
		String archivoRecibido2="";
		String archivoRecibido2Ruta="";

		if(iniciado) {//si se quiere enviar algo
			printStream.println("INICIALITZAR_FTP");
			printStream.flush();

			try {
				recibido=leeReader.readLine();
				System.out.println("SERVER: Mensaje recibido del cliente: "+recibido);
				//recibe retorn_control

			} catch (IOException e) {e.printStackTrace();}
			
			try {
				printStream.println(nombrArchivoEnvio);//enviar el nombre
				printStream.flush();
				
				
				File archivo=new File(rutaEnvio+nombrArchivoEnvio);
				System.out.println(rutaEnvio+nombrArchivoEnvio);
				printStream.println(archivo.length());//enviar el tamanio
				printStream.flush();

				//abrir un nuevo socket
				Socket socketFTP=establirConnexioTmpViaSocket(true, puertoFTP);

				
				// envio, pero en array de bytes porque se envia en paquetes
				byte[] arrayByteEnvio = new byte[1024 * 16];

				try {
					Thread.currentThread().sleep(500);// a veces peta porque se envian muchos a la vez
				} catch (InterruptedException e) {e.printStackTrace();}

				// leemos, enviamos
				bufferedInputStream = new BufferedInputStream(new FileInputStream(archivo));//lee el fichero
				outputStream = socketFTP.getOutputStream();//output es para enviarlo al cliente

				System.out.println("SERVER-FPT: Transmitiendo el archivo " + nombrArchivoEnvio + " - Total: "
						+ archivo.length() + " bytes");

				int leido = -1;
				char caracter;

				while ((leido = bufferedInputStream.read(arrayByteEnvio)) > 0) {
					// leido ahora lee en la sentencia del while, nos ahorra una linea debajo

					outputStream.write(arrayByteEnvio, 0, leido);// escribe
					outputStream.flush();

					System.out.println("SERVER: Enviados " + leido + " bytes");
				}
				System.out.println("SERVER: Fin del envio de ficheros");

				tancarSocketServidor(socketFTP);//cerramos el socket


			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {e.printStackTrace();}
			finally {
				
				if(bufferedInputStream!=null) {
					try {bufferedInputStream.close();}
					catch (IOException e) {e.printStackTrace();}
				}

			}

			try {
				recibido = leeReader.readLine();
				System.out.println("SERVER: mensaje del cliente: "+recibido);
			} catch (IOException e) {e.printStackTrace();}


			
		}
		
		else {//recibe algo
			
			retornControl(false, printStream);
			
			//recibimos los ficheros
			try {
				archivoRecibido2=leeReader.readLine();//nombre

				archivoRecibido2Ruta=rutaRecibir+archivoRecibido2;//ruta

				String size1=leeReader.readLine();//tamanio
				int size=Integer.parseInt(size1);


				System.out.println("SERVER: Nombre del archivo: "+archivoRecibido2);
				System.out.println("SERVER: Tamanio del archivo: "+size+" bytes");
				
				System.out.println("SERVER-FTP: Recibiendo...");

				//abrir un nuevo socket para recibir cosas
				Socket socketFTP=establirConnexioTmpViaSocket(false, puertoFTP);
				
				byte [] arrayBytesRecibir  = new byte [1024*16];
				
				inputStream=socketFTP.getInputStream();//lee el fichero que nos envian
				bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(archivoRecibido2Ruta));//escribe el fichero que nos envia
				
				int leido=-1;
				int bytesRecibidos=0;
				char caracter;
				
				while((leido=inputStream.read(arrayBytesRecibir))!=-1) {
					caracter= (char) leido;
					bufferedOutputStream.write(arrayBytesRecibir,0,caracter);
					
					bytesRecibidos+=leido;
					System.out.println("SERVER: Recibido " + leido + " bytes");
					
					if(bytesRecibidos==leido) {break;}
				}
				
				bufferedOutputStream.close();
				
				System.out.println("SERVER: Archivo recibido. Recogidos "+bytesRecibidos+" bytes");

				//cerramos socket
				tancarSocketServidor(socketFTP);

				//enviamos bytes recibidos
				printStream.println("SERVER: FINALITZAT_FTP: Recibidos "+bytesRecibidos+" bytes en el servidor");
				printStream.flush();

				
				
				
			} catch (IOException e) {e.printStackTrace();}
			
		}
	}

	private static Socket establirConnexioTmpViaSocket(boolean enviaArchivos, int puerto) {

		if(enviaArchivos){

			try {
				ServerSocket abridorSocketFPT = new ServerSocket(puerto);//esto abre el puerto

				Socket socketFPT = abridorSocketFPT.accept();//esto es la conexion con el cliente

				return socketFPT;

			} catch (IOException e) {e.printStackTrace();}
		}
		else if(!enviaArchivos){
			try {
				Thread.sleep(500);//dormimos
			} catch (InterruptedException e) {e.printStackTrace();}

			String direccionIP = "127.0.0.1";

			InetSocketAddress inetSocketAddress=new InetSocketAddress(direccionIP,puerto);//ip y puerto

			//crear socket
			Socket socketConexion=new Socket();


			while(!socketConexion.isConnected()){
				//for hasta que se conecte
				try {
					socketConexion.connect(inetSocketAddress);//conectar con el inet

				} catch (IOException e) {e.printStackTrace();}
			}

			return socketConexion;//cuando se conecta ,lo devuelves
		}
		return null;
	}

	public static void menuServer(Socket clientSocket, BufferedReader leeReader, PrintWriter printStream)
			throws IOException {
		String opcio;
		Scanner sc = new Scanner(System.in);
		StringBuilder menu = new StringBuilder("");

		do {
			menu.delete(0, menu.length());

			menu.append(System.getProperty("line.separator"));
			menu.append("SPU-9 SERVER ");
			menu.append(System.getProperty("line.separator"));
			menu.append(System.getProperty("line.separator"));
			menu.append("0. Desconnectar-se del CLIENT");

			menu.append(System.getProperty("line.separator"));
			menu.append(System.getProperty("line.separator"));
			menu.append("1. Retornar el control de les comunicacions al CLIENT");
			menu.append(System.getProperty("line.separator"));
			menu.append("2. CHAT");
			menu.append(System.getProperty("line.separator"));
			menu.append("3. FTP");

			menu.append(System.getProperty("line.separator"));
			menu.append(System.getProperty("line.separator"));
			menu.append("50. Tancar el sistema");
			menu.append(System.getProperty("line.separator"));

			System.out.print(Pantalles.MenuConstructorPantalla.constructorPantalla(menu));

			opcio = sc.next();

			switch (opcio) {
				case "0":
					acabarConexion=true;
					verMenu=false;
					break;//este return es acabarConexionConElCliente

				case "1":
					retornControl(true, printStream);
					verMenu=false;
					break;

				case "2":
					chat(true, leeReader, printStream);
					break;

				case "3":
					ftp(true, clientSocket, leeReader, printStream);
					break;

				case "50":
					acabarConexion=true;
					verMenu=false;
					break;

				default:
					System.out.println("COMANDA NO RECONEGUDA");
			}
		} while (!opcio.equals("50") && acabarConexion==false && verMenu==true);

	}

	private static void chat(boolean iniciadoPorServer, BufferedReader leeReader, PrintWriter printStream) {
		String envio = "";
		String recibido = "";
		Scanner sc = new Scanner(System.in);
		boolean salir = false;

		if (iniciadoPorServer) {// si se ha inicializado el chat, aka se ha apretado la opcion con true

			printStream.println("INICIALITZAR_CHAT");
			printStream.flush();

			try {
				while (!salir) {

					recibido = leeReader.readLine();// lee el mensaje que le ha escrito

					System.out.println("SERVER: Mensaje enviado por el cliente: " + recibido);

					if (recibido.equalsIgnoreCase("FINALITZAR_CHAT")) {
						System.out.println("Chat finalizado por el cliente");
						salir = true;
					}
					else {
						System.out.println("SERVER: Cual es tu respuesta al mensaje del cliente? ");
						envio = sc.nextLine();

						if (envio.equalsIgnoreCase("FINALITZAR_CHAT")) {
							System.out.println("Chat finalizado por el server");
							salir = true;
						}
						printStream.println(envio);
						printStream.flush();
					}


				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			
		}
		
		if(!iniciadoPorServer) {//el cliente inicia el chat, no el server
			//decimos al cliente que lo pida
			retornControl(false, printStream);
			
			//si devuelve, que lo envie
			try {
				while (!salir){
					recibido = leeReader.readLine();// lee el mensaje que le ha escrito
					System.out.println("SERVER: Mensaje enviado por el cliente: " + recibido);

					if (recibido.equalsIgnoreCase("FINALITZAR_CHAT")) {
						System.out.println("Chat finalizado por el cliente");

						retornControl(false, printStream);
						salir=true;
					} else {
						System.out.println("SERVER: Cual es tu respuesta al mensaje del cliente? ");
						envio = sc.nextLine();

						if (envio.equalsIgnoreCase("FINALITZAR_CHAT")) {
							System.out.println("Chat finalizado por el servidor");
							salir=true;
						}

						printStream.println(envio);
						printStream.flush();
					}
				}



			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		

	}

	private static void respondreAPermisConnexio(PrintWriter printStream) {
		Scanner sc = new Scanner(System.in);
		String respuestaString = "";
		boolean salir = false;


		while (!salir) {
			System.out.println("Permites la conexion del cliente al servidor?");
			respuestaString = sc.next();

			if (respuestaString.equalsIgnoreCase("Si") || respuestaString.equalsIgnoreCase("No")) {

				if (respuestaString.equalsIgnoreCase("Si")) {
					printStream.println("PERMIS_CONNEXIO_CONCEDIT");
				} else if (respuestaString.equalsIgnoreCase("No")) {
					printStream.println("PERMIS_CONNEXIO_DENEGAT");
				}

				salir = true;
				printStream.flush();
			} else {
				System.out.println("Solo respuestas de SI o NO");
			}
		}

	}

	private static void tancarSocketServidor(Socket clientSocket) {

		if(clientSocket.getPort()==puertoFTP){
			System.out.println("Cerrando socket temporal");
		}
		else{
			System.out.println("Cerrando socket");
		}

			if ((clientSocket != null) && (!clientSocket.isClosed())) {// sino esta cerrado de antes
				try {
					if (!clientSocket.isInputShutdown()) {// comprobamos que el input (escribir) no ha cerrado
						clientSocket.shutdownInput();
					}
					if (!clientSocket.isOutputShutdown()) {// comprobamos que el output (leer) no ha cerrado
						clientSocket.shutdownOutput();
					}
					clientSocket.close();

				}catch (IOException ex) {ex.printStackTrace();}
		}

	}

	private static void retornControl(boolean seeMenu, PrintWriter printStream) {
		printStream.println("RETORN_CONTROL");
		printStream.flush();// limpias


	}



}