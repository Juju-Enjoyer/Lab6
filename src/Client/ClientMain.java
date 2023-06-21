package Client;

import Command.CommandList.CommandWithFlat;
import Command.CommandList.ExecuteScript.ExecuteScriptNewEra;
import Command.CommandList.Exit.Exit;

import Command.CommandList.kostyl;
import Command.CommandProcessor.Command;
import Command.ListCommand;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientMain {
    private ListCommand commandList = new ListCommand();
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer = new byte[65507];
    String result="";

    public ClientMain(DatagramSocket datagramSocket, InetAddress inetAddress) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

//todo поченить не правильный файл
    public void execute() throws NoSuchCommandException, IllegalValueException, IllegalKeyException, IOException {

        datagramSocket.setSoTimeout(2000);
        Command command;
        Scanner scan = new Scanner(System.in);
        try {
        while (!result.equals("EXIT execute\nBye")) {
           try {
               String[] str = scan.nextLine().trim().split(" ");
            String commandWithOutArgs = separator(str)[0];
            String args = separator(str)[1];

            if (!commandList.checkCommands(commandWithOutArgs)) {
                throw new NoSuchCommandException();
            } else {
                command = commandList.returnCommand(commandWithOutArgs);
            }
                command.setArgument(args);
            if (command instanceof CommandWithFlat) {
                ((CommandWithFlat) command).setFlat();
            }
            if (command instanceof ExecuteScriptNewEra) {
                File file;
                ExecuteScriptNewEra executeScript = new ExecuteScriptNewEra();
                Scanner scFile = new Scanner(System.in);



                if (args.isEmpty()){
                    throw new NoSuchCommandException();
                }
                else {

                        file = new File(args);
                        if (file.exists()) {
                            while ((!file.canRead())&(!file.canWrite())) {
                                System.out.println("что то не так или не читабельно либо не записываемо");
                                file = new File(scFile.next());
                            }}else {
                            System.out.println("файла не существует,напишите существующий скрипт или напишите 'CANCEL' для отмены команды ");
                            while (!file.exists()){
                                file = new File(scFile.next());
                                if (file.getName().toUpperCase().equals("CANCEL")){
                                    break;
                                }                            }
                        }
                        if (file.getName().toUpperCase().equals("CANCEL")){
                            continue;
                        }
                }


                            InputStream isr = new FileInputStream(file);
                            InputStreamReader inputStreamReader = new InputStreamReader(isr);
                            BufferedReader reader = new BufferedReader(inputStreamReader);
                            String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    String commandScript = separator(line.split(" "))[0];
                    String argsScript = separator(line.split(" "))[1];

                    try {

                        if (!commandList.checkCommands(commandScript)) {
                            throw new NoSuchCommandException();
                        }
                    } catch (NoSuchCommandException e) {
                        continue;
                    }
                    try {

                    command = commandList.returnCommand(commandScript);
                    if (commandList.returnCommand(commandScript) instanceof CommandWithFlat) {
                        long idWh = Long.parseLong(argsScript);
                        ((CommandWithFlat)command).setFlatScript(executeScript.scriptFill(reader,idWh));
                    }}catch (NumberFormatException e){
                        continue;
                    }
                        command.setArgument(argsScript);
                    sendCommand(command);
                     result = receiveAnswer();
                    System.out.println(result);
                }
                reader.close();
            }else {
                sendCommand(command);
               try {
                   if (datagramSocket.getSoTimeout() != 0) {
                       String message = receiveAnswer();
                       result = message;
                       System.out.println(message);
                       byte[] buffer = new byte[65507];
                       datagramSocket.setSoTimeout(2000);


                   }
               }catch (SocketTimeoutException e){
                   System.out.println("сервер не доступен");
                   continue;
               }
            }


        }catch (NoSuchCommandException e){
               System.out.println(e.getMessage());
           }
        }
        } catch (NoSuchElementException e){
                sendExit();
                result = "EXIT execute\nBye";
            } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("стринг там где не надо");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            }


    }


    public void sendExit() throws IOException {
        Exit exit = new Exit();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(exit);
        buffer = bos.toByteArray();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length,inetAddress,1237);
        datagramSocket.send(datagramPacket);
    }
    public void sendCommand(Command command) throws IOException {
        buffer = objToByte(command);
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1237);
        datagramSocket.send(datagramPacket);
    }
    public String receiveAnswer() throws IOException {
        byte[] data = new byte[65507];
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
        datagramSocket.receive(datagramPacket);
        return new String(datagramPacket.getData(), 0, datagramPacket.getLength());
    }

    public String[] separator(String[] str) {
        String commandWithOutArgs = "";
        String args = "";
        String[] result = new String[2];
            String[] commandLine = str;
            commandWithOutArgs = commandLine[0];
            args = "";
            if (commandLine.length > 1) {
                args = commandLine[1];
            }
            result[0]=commandWithOutArgs;
            result[1]=args;
        return result;
    }
    public byte[] objToByte(Command command) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(command);
        return bos.toByteArray();

    }

    public static void main(String[] args) throws IOException, NoSuchCommandException, IllegalValueException, IllegalKeyException {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        ClientMain client = new ClientMain(datagramSocket,inetAddress);
        System.out.println("Я клиент");
        client.execute();
    }
}
/*
package Client;

import Command.CollectionManager.CollectionManager;
import Command.CommandList.CommandWithFlat;
import Command.CommandList.Exit.Exit;
import Command.CommandList.Help.Help;
import Command.CommandList.Insert.Insert;
import Command.CommandList.kostyl;
import Command.CommandProcessor.Command;
import Command.ListCommand;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientMain {
    private ListCommand commandList = new ListCommand();
    private Socket socket;
    private BufferedReader input;
    private ObjectOutputStream output;

    public ClientMain(Socket socket) throws IOException {
        this.socket = socket;
        this.output = new ObjectOutputStream(socket.getOutputStream());
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void execute() throws NoSuchCommandException, IllegalValueException, IllegalKeyException, IOException {
byte[] buffer=new byte[65507];
        Command command = new kostyl();
        Scanner scan = new Scanner(System.in);
        String result = "";
        while (!result.equals("EXIT execute\nBye")) {
            String commandWithOutArgs = "";
            String args = "";
            try {
                String[] commandLine = scan.nextLine().trim().split(" ");
                commandWithOutArgs = commandLine[0];
                args = "";
                if (commandLine.length > 1) {
                    args = commandLine[1];
                }
            } catch (NoSuchElementException e){
                sendExit();
                result = "EXIT execute\nBye";
                continue;
            }
            if (!commandList.checkCommands(commandWithOutArgs)) {
                throw new NoSuchCommandException();
            }else {
                command =commandList.returnCommand(commandWithOutArgs);
            }
            command.setArgument(args);
            if (command instanceof CommandWithFlat){
                ((CommandWithFlat) command).setFlat();
            }
            output.writeObject(command);

            String message = input.z();
            result = message;
            System.out.println(message);
        }

    }

    public void sendExit() throws IOException {
        Exit exit = new Exit();
        output.writeObject(exit);
    }

    public static void main(String[] args) throws IOException, NoSuchCommandException, IllegalValueException, IllegalKeyException {
        Socket socket = new Socket("localhost", 1237);
        ClientMain client = new ClientMain(socket);
        System.out.println("Я клиент");
        client.execute();
    }
}*/
