package kh.com.kshrd.core.buffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by sophatvathana on 27/12/16.
 */
public class StreamBuffer  extends InputStream {
    private final static String TAG="MJpegStream";

    public static final String mBoundary = "video boundary--";
    private static final String mContentType = "Content-Type: image/jpeg\r\n\r\n";
    private static final String mContentLength = "Content-Length: %d\r\n";
    //private static final String mTimeStamp = "X-Timestamp: %d\r\n";
    public static final String mNext = "\r\n--" + mBoundary + "\r\n";

    private ByteBuffer mVideoBuffer;
    private boolean writeable;

    private long last_timestamp = 0;

    public StreamBuffer() {
        writeable = true;
    }

    @Override
    public int available() throws IOException {
/*
		int size = 0;
		try {
			size = mBuffers.size();
		} catch(Exception e) {

		}
*/
        int size = 0;
        try {
            size = mVideoBuffer.capacity();
        } catch(Exception e) {

        }
        return size;
    }

    @Override
    public void close() throws IOException {
        // mBuffers = null;
        mVideoBuffer = null;
        super.close();
    }

    @Override
    public synchronized void reset() throws IOException {
        mVideoBuffer = null;
        writeable = true;
        super.reset();
    }

    public void clear() {
        mVideoBuffer = null;
        writeable = true;
    }

    @Override
    public int read() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean saveBuffer(byte[] bytearray, int size) {
        //Log.d(TAG, "saveBuffer");
        long timestamp = System.currentTimeMillis();
        if(last_timestamp==0) {
            last_timestamp = timestamp;
        }
        long diff = timestamp-last_timestamp;
        //Log.d(TAG, "diff time=" + diff + ", size=" + size);
        last_timestamp = timestamp;
        if(writeable) {
            byte[] array = new byte[size];
            System.arraycopy(bytearray, 0, array, 0, size);
            mVideoBuffer = ByteBuffer.wrap(array);
            writeable = false;
        } else {
            return false;
        }
        return true;
    }


    public ByteBuffer getVideoBuffer() {
        return mVideoBuffer;
    }
    public byte[] getHeader() {
        byte[] header = new byte[1024];
        int len = 0;

        System.arraycopy(mNext.getBytes(), 0, header, len, mNext.length());
        len += mNext.length();
        String str = String.format(mContentLength, mVideoBuffer.array().length);
        System.arraycopy(str.getBytes(), 0, header, len, str.length());
        len += str.length();
        System.arraycopy(mContentType.getBytes(), 0, header, len, mContentType.length());
        len += mContentType.length();
        byte[] ret = new byte[len];
        System.arraycopy(header, 0, ret, 0, len);
        return ret;
    }
    public boolean getWriteable() {
        return writeable;
    }

}