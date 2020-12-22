package com.core.ligasport.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;

public class ReadFileFromStream {

	public static byte[] readFileFromStream(InputStream inputStream) throws Exception
    {
		
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        byte[] data = new byte[4096];
        int count = inputStream.read(data);
        
        while(count != -1)
        {
            dos.write(data, 0, count);
            count = inputStream.read(data);
        }

        return baos.toByteArray();
        
    }
	
}
