package EjercicioSocket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SPU9_CLIENT {
    static String direccion = "127.0.0.1";
    static int puerto = 9090;
    static boolean verMenu=false;

    private static boolean acabarConexion=false;

    //private static String rutaEnvio ="/home/users/inf/wiam2/iam47264842/Escritorio/envio/";
    //private static String rutaRecibir ="/home/users/inf/wiam2/iam47264842/Escritorio/sockets/";

    private static String rutaEnvio ="C:\\Users\\monts\\Desktop\\a\\sockets\\envio";
    private static String rutaRecibir ="C:\\Users\\monts\\Desktop\\a\\sockets\\recibir";


    public static void main(String[] args) throws IOException {
        System.out.println("CLIENTE: INICIO");
        conectarAlServidor(direccion, puerto);

        System.out.println("CLIENTE: FIN");
    }

    public static void conectarAlServidor(String direccion, int puerto) {
        Socket socket;
        BufferedReader leer;
        PrintWriter escribir = null;
        String mensaje = "";
        boolean acabar=false;

        try {
            socket = new Socket(InetAddress.getByName(direccion), puerto);//te conectas a esa direccion con el nombre

            //inicias el leer ese servidor
            leer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escribir = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            System.out.println("Pidiendo permisos de conexion...");

            escribir.println("DEMANAR_PERMIS_CONNEXIO");
            escribir.flush();

            //recuperamos el mensaje del server, si nos podemos conectar o no
            mensaje = leer.readLine();

            if (mensaje.equals("PERMIS_CONNEXIO_CONCEDIT")) {
                System.out.println("Permiso concedido, conectando");

                while(acabarConexion == false){
//                    System.out.println("conectarse> "+acabar);
                    acabarConexion=comunicarseConElServer(socket, leer, escribir);
                }

            } else if (mensaje.equals("PERMIS_CONNEXIO_DENEGAT")) {
                System.out.println("Permiso NO concedido");
            }
            tancarClient(socket);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean comunicarseConElServer(Socket socket, BufferedReader leer, PrintWriter escribir) {
        String serverResponse = "";
        Scanner sc = new Scanner(System.in);
        String mensaje="";
        boolean cortarComunicacion=false;



        if(verMenu==false){
            try {
                serverResponse = leer.readLine();

                //System.out.println(serverResponse);
                if (serverResponse != null) {//si se ha recibido un mensaje
                    System.out.println("CLIENTE: El mensaje recibido del servidor es: " + serverResponse);

                    if (serverResponse.equalsIgnoreCase("RETORN_CONTROL")) {
                        verMenu=true;
                    }
                    else if (serverResponse.equalsIgnoreCase("INICIALITZAR_CHAT")) {
                        //System.out.println("Opcion seleccionada: INICIALITZAR_CHAT");

                        chat(false, leer, escribir);
                    }
                    else if (serverResponse.equalsIgnoreCase("INICIALITZAR_FTP")) {
                       // System.out.println("Opcion seleccionada: INICIALITZAR_FTP");

                        ftp(false, socket, leer, escribir);
                    }
                } else {
                    verMenu=false;
                    cortarComunicacion=true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                cortarComunicacion=menuCliente(socket, leer, escribir);

            } catch (IOException e) {e.printStackTrace();}
        }


//        System.out.println("comunicarse> "+cortarComunicacion);
        return cortarComunicacion;
    }


    private static void retornControl(boolean seeMenu, PrintWriter printStream) {
        printStream.println("RETORN_CONTROL");
        printStream.flush();// limpias
    }

    private static void tancarClient(Socket clientSocket) {


        System.out.println("Cerrando socket...");

            if ((clientSocket != null) && (!clientSocket.isClosed())) {// sino esta cerrado de antes

                try {
                    if (!clientSocket.isInputShutdown()) {// comprobamos que el input (escribir) no ha cerrado
                        clientSocket.shutdownInput();
                    }

                    if (!clientSocket.isOutputShutdown()) {// comprobamos que el output (leer) no ha cerrado
                        clientSocket.shutdownOutput();
                    }

                    clientSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

    }


    private static void chat(boolean iniciadoPorCliente, BufferedReader leeReader, PrintWriter printStream) {
        String enviar = "";
        String recibido = "";
        Scanner sc = new Scanner(System.in);
        boolean acabar = false;

        if (iniciadoPorCliente) {
//            System.out.println("CLIENTE: INICIANDO CHAT");
            printStream.println("INICIALITZAR_CHAT");
            printStream.flush();

            try {
                while (!acabar) {

                    recibido = leeReader.readLine();

                    System.out.println("CLIENTE: Mensaje recibido del servidor: " + recibido);

                    if (recibido.equalsIgnoreCase("FINALITZAR_CHAT")) {
                        System.out.println("Chat finalizado");
                        acabar = true;
                    }//el server quiere acabar el chat, sal del bucle

                    else{//si no quiere acabar el chat, envia una respuesta
                        System.out.println("CLIENTE: Que mensaje quieres enviarle al servidor?");
                        enviar = sc.nextLine();

                        if (enviar.equalsIgnoreCase("FINALITZAR_CHAT")) {
                            acabar = true;
                        }//si quieres acabar el chat, sal del bucle
                    }

                    printStream.println(enviar);
                    printStream.flush();


                }
            } catch (IOException e) {e.printStackTrace();}


        }

        if (!iniciadoPorCliente) {
            retornControl(false, printStream);
            try {
                while (!acabar) {

                    recibido = leeReader.readLine();

                    System.out.println("CLIENTE: Mensaje recibido del servidor: " + recibido);

                    if (recibido.equalsIgnoreCase("FINALITZAR_CHAT")) {//si se pide acabar el chat
                        System.out.println("Chat finalizado por el server");
                        retornControl(false, printStream);
                        acabar = true;
                    }
                    else {
                        System.out.println("CLIENTE: Que mensaje quieres enviarle al servidor?");
                        enviar = sc.nextLine();

                        if (enviar.equalsIgnoreCase("FINALITZAR_CHAT")) {
                            System.out.println("Chat finalizado por el cliente");
                            acabar = true;
                        }

                        printStream.println(enviar);
                        printStream.flush();

                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void ftp(boolean iniciado, Socket clientSocket, BufferedReader leeReader, PrintWriter printStream) {

        String nombrArchivoEnvio = "datosApiAndroid2.txt";
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        InputStream inputStream = null;
        String recibido = "";

        String archivoRecibido2 = "";

        if (iniciado) {//si se quiere enviar algo
            printStream.println("INICIALITZAR_FTP");
            printStream.flush();

            try {
                recibido = leeReader.readLine();
                System.out.println("CLIENTE: Mensaje recibido del cliente: " + recibido);

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                //le decimos el nombre
                printStream.println(nombrArchivoEnvio);
                printStream.flush();


                //por alguna razon se envian a la vez

                //le decimos el tamanio
                File archivo = new File(rutaEnvio+nombrArchivoEnvio);
                printStream.println(archivo.length());
                printStream.flush();


                // envio, pero en array de bytes porque se envia en paquetes
                byte[] arrayByteEnvio = new byte[1024 * 16];

                try {
                    Thread.currentThread().sleep(500);// a veces peta porque se envian muchos a la vez
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // leemos, enviamos
                bufferedInputStream = new BufferedInputStream(new FileInputStream(archivo));
                outputStream = clientSocket.getOutputStream();

                System.out.println("CLIENTE-FPT: Transmitiendo el archivo " + nombrArchivoEnvio + " - Total: "
                        + archivo.length() + " bytes");

                int leido = -1;

                while ((leido = bufferedInputStream.read(arrayByteEnvio)) > 0) {
                    // leido ahora lee en la sentencia del while, nos ahorra una linea debajo
                    outputStream.write(arrayByteEnvio, 0, leido);// escribe
                    outputStream.flush();

                    System.out.println("CLIENTE: Enviados " + leido + " bytes");
                }
                System.out.println("CLIENTE: Fin del envio de ficheros");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
            try {
                recibido=leeReader.readLine();
                System.out.println("CLIENTE: Mensaje recibido del server: "+recibido);
            } catch (IOException e) {e.printStackTrace();}



        } else {//sino se ha iniciado el cliente

            retornControl(false, printStream);

            //recibimos los ficheros
            try {
                archivoRecibido2 = leeReader.readLine();//nombre
                archivoRecibido2 = rutaRecibir + archivoRecibido2;//ruta

                int size = Integer.parseInt(leeReader.readLine());


                System.out.println("CLIENTE: Ruta del archivo a recibir: " + archivoRecibido2);
                System.out.println("CLIENTE: Tamanio del archivo a recibir: " + size + " bytes");

                System.out.println("CLIENTE-FTP: Recibiendo...");

                byte[] arrayBytesRecibir = new byte[1024 * 16];

                inputStream = clientSocket.getInputStream();
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(archivoRecibido2));

                int leido = -1;
                int bytesRecibidos = 0;

                while ((leido = inputStream.read(arrayBytesRecibir)) != -1) {
                    bufferedOutputStream.write(arrayBytesRecibir, 0, leido);

                    bytesRecibidos += leido;
                    System.out.println("CLIENTE: Recibido " + leido + " bytes");

                    if (bytesRecibidos == size) {//si se han recibido todos, parar
                        break;
                    }
                }

                bufferedOutputStream.close();

                System.out.println("CLIENTE: Archivo recibido. Recogidos " + bytesRecibidos + " bytes");

                printStream.println("CLIENTE: FINALITZAT_FTP: Recibidos " + bytesRecibidos + " bytes");
                printStream.flush();



            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static boolean menuCliente(Socket clientSocket, BufferedReader leeReader, PrintWriter printStream)
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
            menu.append("1. Retornar el control de les comunicacions al SERVER");
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
                    return true;

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
                    return true;

                default:
                    System.out.println("COMANDA NO RECONEGUDA");
            }
        } while (!opcio.equals("50") && verMenu==true);
        return false;
    }


}
