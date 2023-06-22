package com.example.porjetwass;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class chatServer extends Thread{
    private int ClientNbr;
    private List<Communication> cliensconnectes = new ArrayList<Communication>();

    public static void main(String[] args){
        new chatServer().start();
    }
    @Override
    public void run(){
        try {
            ServerSocket ss= new ServerSocket(1234);
            System.out.println("le serveur demarre");
            while (true){
                Socket s=ss.accept();
                ++ClientNbr;
                Communication newCommunication = new Communication(s,ClientNbr);
                cliensconnectes.add(newCommunication);
                newCommunication.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class Communication extends Thread{
        private Socket s;
        private int ClientNumber;
        Communication(Socket s,int ClientNumber){
            this.s=s;
            this.ClientNumber=ClientNumber;
        }
        @Override
        public  void run(){
            try {
                InputStream is = s.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader br= new BufferedReader(isr);
                OutputStream os=s.getOutputStream();
                String ip = s.getRemoteSocketAddress().toString();
                System.out.println("Le client numero "+ClientNumber+"et son ip" +ip);
                PrintWriter pw = new PrintWriter(os,true);
                pw.println("vous etes le client"+ClientNumber);
                pw.println("Entrez le message");
                while(true){
                    String UserRequest = br.readLine();
                    if(UserRequest.contains("=>")){
                        String[] usermessage = UserRequest.split("=>");
                        if(usermessage.length==2){
                            String msg = usermessage[1];
                            int numCl = Integer.parseInt(usermessage[0]);
                            Send(msg,s,numCl);
                        }
                    }
                    else{
                        Send(UserRequest,s,-1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        void Send(String UserRequest,Socket soc,int nbre) throws IOException {
            for(Communication client : cliensconnectes){
                    if(client.ClientNumber==nbre || nbre ==-1){
                    PrintWriter pw = new PrintWriter(client.s.getOutputStream(),true);
                    pw.println(UserRequest);
                   }

            }
        }
    }
}
