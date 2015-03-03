package com.k.server;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by k on 2015/3/3.
 */
public class MinaServer {

    public static void main(String[] args){
        try {
            NioSocketAcceptor acceptor = new NioSocketAcceptor();
            acceptor.setHandler(new MyServerHandler());
            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyTextLineFactory()));
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5);
            acceptor.bind(new InetSocketAddress(9000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
