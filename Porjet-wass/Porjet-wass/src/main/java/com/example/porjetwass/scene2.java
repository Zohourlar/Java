package com.example.porjetwass;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import com.vdurmont.emoji.EmojiParser;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class scene2 {
    @FXML
    private TextField portid;
    @FXML
    private TextField hostid;
    @FXML
    private ScrollPane sp_main;
    @FXML
    private TextField messageid;
    @FXML
    private VBox vbox_messages;
    PrintWriter pw ;

    @FXML
    protected void onconnect() throws IOException {
        vbox_messages.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                sp_main.setVvalue((Double) newValue);
            }
        });

        String host = hostid.getText();
        int port = Integer.parseInt(portid.getText());
        Socket s = new Socket(host,port);
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = s.getOutputStream();
        String Ip = s.getRemoteSocketAddress().toString();
        pw = new PrintWriter(os,true);
        new Thread(()-> {
            while (true){
                try {
                    String rep = br.readLine();
                    Platform.runLater(()->{
                        //testview.getItems().add(rep);
                        HBox hbox= new HBox();
                        hbox.setAlignment(Pos.CENTER);
                        hbox.setPadding(new Insets(5,5,5,10));

                        Text text = new Text(rep);
                        TextFlow textFlow = new TextFlow(text);
                        textFlow.setPadding(new Insets(5,10,5,10));
                        hbox.getChildren().add(textFlow);
                        vbox_messages.getChildren().add(hbox);
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    @FXML
    public void onsubmit(){
            String message=EmojiParser.parseToUnicode(messageid.getText());
        if(!message.isEmpty()){
            pw.println(message);
            messageid.clear();
        }

    }}
