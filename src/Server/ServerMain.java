package Server;

import Command.CollectionManager.CollectionManager;

import Command.CommandList.Save.Save;
import Command.CommandProcessor.Command;
import Command.CommandProcessor.SoundPlayer;
import Command.ListCommand;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

public class ServerMain {
    //TODO перенести тримап с командами в отдельный класс done
    //TODO разделить сервер на модули и классы
    ListCommand commandsList = new ListCommand();
    CollectionManager cm = new CollectionManager(commandsList.getCommands());
   private DatagramSocket datagramSocket;


    public ServerMain(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }
    private byte[] buffer = new byte[65507];
    private static Logger logger = Logger.getLogger(ServerMain.class.getName());
    boolean result = true;
    public void execute(String file) throws IOException, NoSuchCommandException, IllegalValueException, IllegalKeyException {

        DatagramPacket datagramPacket;
        InetAddress inetAddress;
        try {
            cm.getWorkerFile(file);
            cm.checkWorkFile();
        }catch (NoSuchElementException e) {
            SoundPlayer.playSound("Pud_ability_hook_miss_01_ru.wav",2500);
            System.out.println("Они убили Кени");
            result = false;}
        int port;

        Runnable consoleReader = () -> {
            Scanner scan = new Scanner(new InputStreamReader(System.in));
            while (true) {
                try {
                    String commandWithOutArgs = "";
                    String args = "";

                    String[] commandLine = scan.nextLine().split(" ");
                    commandWithOutArgs = commandLine[0];
                    args = "";
                    if (commandLine.length > 1) {
                        args = commandLine[1];
                    }
                    if(commandWithOutArgs.toUpperCase().equals("SAVE")){
                        save(args);
                    }
                    else {
                        throw new NoSuchCommandException();
                    }



                } catch (NoSuchElementException e){
                    try {
                        save("");
                    } catch (NoSuchCommandException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalValueException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalKeyException ex) {
                        throw new RuntimeException(ex);
                    }
                    logger.warning("Отключение сервера: cntl+D");
                    System.exit(0);
                } catch (NoSuchCommandException e) {
                    logger.warning("на сервере реализованна только команда save");
                } catch (IllegalValueException e) {
                    logger.warning("что-то не так с аргументом save");
                } catch (IllegalKeyException e) {
                    logger.warning("что-то не так с аргументом save");
                }
            }
        };

        new Thread(consoleReader).start();




        while (result){
            try {

                buffer = new byte[65507];
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                inetAddress = datagramPacket.getAddress();
                port = datagramPacket.getPort();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {

                buffer = datagramPacket.getData();
                ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buffer));
                Command command = (Command)in.readObject();
                command.setCm(cm);



                
                String result = command.execute(command.getArgument());
                logger.info(command.getName());
                String str = command.getName()+" execute"+"\n"+result;
                cm.historyFiller(command.getName());
                sendMessage(str, inetAddress,port);





            } catch (IOException e) {
                sendMessage("сообщение слишком большое не передать словами, а точнее байтами",inetAddress,port);
                save("");
            } catch (ClassNotFoundException | NoSuchCommandException | IllegalValueException | IllegalKeyException e) {
    sendMessage(e.getMessage(),inetAddress,port);
            }
            catch (NoSuchElementException e){

            }
        }
    }
    public void save(String args) throws NoSuchCommandException, IllegalValueException, IllegalKeyException {
        Save save = new Save(cm);
        logger.info(save.execute(args));
    }

    public void sendMessage (String thing, InetAddress inetAddress, int port) throws IOException {
        byte[] data = new byte[65507];
        data = thing.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress,port);
        datagramSocket.send(datagramPacket);
    }

    public static void main(String[] args) throws IOException, NoSuchCommandException, IllegalValueException, IllegalKeyException {
        DatagramSocket socket = new DatagramSocket(1237);
        ServerMain server = new ServerMain(socket);
        System.out.println("Я сервер");
        server.execute(args[0]);
    }
}

/*
package Server;

import Command.CollectionManager.CollectionManager;
import Command.CommandList.Save.Save;
import Command.CommandProcessor.Command;
import Command.CommandProcessor.SoundPlayer;
import Command.ListCommand;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

public class ServerMain {
    //TODO перенести тримап с командами в отдельный класс done
    //TODO разделить сервер на модули и классы
    ListCommand commandsList = new ListCommand();
    CollectionManager cm = new CollectionManager(commandsList.getCommands());
    private ServerSocket serverSocket;


    public ServerMain(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    private static Logger logger = Logger.getLogger(ServerMain.class.getName());
    boolean result = true;

    public void execute(String file) throws IOException {

        try {
            cm.getWorkerFile(file);
            cm.checkWorkFile();
        } catch (NoSuchElementException e) {
            SoundPlayer.playSound("Pud_ability_hook_miss_01_ru.wav", 2500);
            System.out.println("Они убили Кени");
            result = false;
        }

        Runnable consoleReader = () -> {
            Scanner scan = new Scanner(System.in);
            while (true) {
                try {
                    String commandWithOutArgs = "";
                    String args = "";

                    String[] commandLine = scan.nextLine().split(" ");
                    commandWithOutArgs = commandLine[0];
                    args = "";
                    if (commandLine.length > 1) {
                        args = commandLine[1];
                    }
                    if (commandWithOutArgs.toUpperCase().equals("SAVE")) {
                        save(args);
                    } else {
                        throw new NoSuchCommandException();
                    }


                } catch (NoSuchElementException e) {
                    try {
                        save("");
                    } catch (NoSuchCommandException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalValueException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalKeyException ex) {
                        throw new RuntimeException(ex);
                    }
                    logger.warning("Отключениеервера: cntl+D");
                    System.exit(0);
                } catch (NoSuchCommandException e) {
                    logger.warning("на сервере реализована только команда save");
                } catch (IllegalValueException e) {
                    logger.warning("что-то не так с аргументом save");
                } catch (IllegalKeyException e) {
                    logger.warning("что-то не так с аргументом save");
                }
            }
        };

        new Thread(consoleReader).start();

        while (result) {
            try {
                Socket socket = serverSocket.accept();
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                String clientAddress = socket.getInetAddress().getHostAddress();
                int clientPort = socket.getPort();
                logger.info("Connected to client at " + clientAddress + " on port " + clientPort);

                while (true) {
                    try {
                        Command command = (Command) input.readObject();
                        command.setCm(cm);

                        String result = command.execute(command.getArgument());
                        logger.info(command.getName());
                        String str = command.getName() + " execute" + "\n" + result;
                        cm.historyFiller(command.getName());
                        output.print(str);

                    } catch (ClassNotFoundException | NoSuchCommandException | IllegalValueException |
                             IllegalKeyException e) {
                        output.write(e.getMessage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            } catch (IOException e) {
                logger.warning("Error accepting connection." + e.getMessage());
            }
        }
    }

    public void save(String args) throws NoSuchCommandException, IllegalValueException, IllegalKeyException {
        Save save = new Save(cm);
        logger.info(save.execute(args));
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1237);
        ServerMain server = new ServerMain(serverSocket);
        System.out.println("Я сервер");
        server.execute(args[0]);
    }
}*/
