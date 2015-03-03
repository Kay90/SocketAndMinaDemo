package com.k.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created by k on 2015/3/3.
 */
public class MyTextLineFactory implements ProtocolCodecFactory{

    private MyTextLineEncoder mEncoder;
    private MyTextLineDecoder mDecoder;
    private MyTextLineCumulativeDecoder mCumulativeDecoder;

    public MyTextLineFactory(){
        mEncoder = new MyTextLineEncoder();
        mDecoder = new MyTextLineDecoder();
        mCumulativeDecoder = new MyTextLineCumulativeDecoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return mEncoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return mCumulativeDecoder;
    }
}
