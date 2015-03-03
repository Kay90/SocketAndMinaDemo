package com.k.server;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * Created by k on 2015/3/3.
 */
public class MyTextLineCumulativeDecoder extends CumulativeProtocolDecoder {

    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput output) throws Exception {
        int startPosition = ioBuffer.position();
        while (ioBuffer.hasRemaining()){
            byte b = ioBuffer.get();
            if (b == '\n'){
                int currentPosition = ioBuffer.position();
                int limit = ioBuffer.limit();
                ioBuffer.position(startPosition);
                ioBuffer.limit(currentPosition);
                IoBuffer buf = ioBuffer.slice();
                byte[] data = new byte[buf.limit()];
                buf.get(data);
                String str = new String(data);
                output.write(str);
                ioBuffer.position(currentPosition);
                ioBuffer.limit(limit);
                return true;
            }
        }
        ioBuffer.position(startPosition);
        return false;
    }
}
