package Server;

import Command.CollectionManager.CollectionManager;

import Command.CommandList.Exit.Exit;

import Command.CommandList.Help.Help;
import Command.CommandList.Insert.Insert;

import Command.CommandProcessor.Command;
import Exceptions.IllegalKeyException;
import Exceptions.IllegalValueException;
import Exceptions.NoSuchCommandException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class ServerMain {
    //TODO перенести тримап с командами в отдельный класс
    //TODO разделить сервер на модули и классы

    private Map<String, Command> commands;
    CollectionManager cm = new CollectionManager(commands);
    public ServerMain(){
        commands = new TreeMap<>();
        Command cmd = new Exit(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new Insert(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new Help();
        commands.put(cmd.getName(),cmd);
        cm.fullCommadsList(commands);
    }
   private DatagramSocket datagramSocket;
   private byte[] buffer = new byte[65507];

    public ServerMain(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }
    boolean result = true;
    public void execute(){
        commands = new TreeMap<>();
        Command cmd = new Exit(cm);
        commands.put(cmd.getName(), cmd);
        cmd = new Insert(cm);
        commands.put(cmd.getName(), cmd);
        cm.fullCommadsList(commands);
        while (result){
            try{

                DatagramPacket datagramPacket = new DatagramPacket(buffer,buffer.length);
                datagramSocket.receive(datagramPacket);
                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();




                byte[] data = datagramPacket.getData();
                ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
                Command command = (Command)in.readObject();
                command.setCm(cm);




                if ((commands.get(command.getName().toUpperCase()) == null) | (command.getName().equals(""))) {
                    throw new NoSuchCommandException();
                }
                String result = command.execute(command.getArgument());
                buffer = new byte[65507];
                String str =command.getName()+" execute";
                buffer = str.getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress,port);
                datagramSocket.send(datagramPacket);
                str =result;
                buffer = str.getBytes();
                datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress,port);
                datagramSocket.send(datagramPacket);









            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (NoSuchCommandException e) {
                throw new RuntimeException(e);
            } catch (IllegalValueException e) {
                throw new RuntimeException(e);
            } catch (IllegalKeyException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket(1234);
        ServerMain server = new ServerMain(socket);
        System.out.println("Я сервер");
        server.execute();
    }



}

