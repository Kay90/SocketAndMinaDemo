package com.k.client;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * Created by k on 2015/3/3.
 */
public class MinaClient {

    public static void main(String[] args){
        MinaClient client = new MinaClient();
        try {
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        NioSocketConnector connector = new NioSocketConnector();
        connector.setHandler(new MyClientHandler());
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
        ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 9000));
        future.awaitUninterruptibly();
        IoSession session = future.getSession();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputContent;
        while (!(inputContent = reader.readLine()).equals("bye")){
            session.write(inputContent);
        }

    }
}
