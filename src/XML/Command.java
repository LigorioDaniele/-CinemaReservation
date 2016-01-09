package XML;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class Command {
	public Command(){
	}
	public static String Encoder(BaseXMLCommand genericObject){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(baos);
            XMLEncoder _encoder = new XMLEncoder(bos);
            _encoder.writeObject(genericObject.getKey());
            _encoder.writeObject(genericObject.getArgument());
            _encoder.writeObject(genericObject.getValue());
            _encoder.close();
            return baos.toString().replace("\n", "");
	}
	public static BaseXMLCommand Decoder(String bufString){
            ByteArrayInputStream baos = new ByteArrayInputStream(bufString.replace("\n", "").getBytes());
            BufferedInputStream bos = new BufferedInputStream(baos);
            XMLDecoder _decoder = new XMLDecoder(bos);
            String commandStr = (String)_decoder.readObject();
            String argumentStr = (String)_decoder.readObject();
            Object item = (Object)_decoder.readObject();
            _decoder.close();
            BaseXMLCommand command = new BaseXMLCommand<>(commandStr, argumentStr, item);
            return command;
	}
}
