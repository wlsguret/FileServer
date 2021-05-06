package FileUpload;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		Fdialog dig = new Fdialog();
		File file = new File(dig.filepath());
		try {
			Socket socket = new Socket("192.168.0.9",55000);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(file);
			out.flush();	
			socket.close();
			System.out.println("전송성공");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
class Fdialog {
	FileDialog fdig;
	public Fdialog() {
		fdig = new FileDialog(new Frame(), "FileDialog");
		fdig.setVisible(true);
		System.out.println(fdig.getDirectory()+fdig.getFile());
	}
	
	String filepath () {
		return fdig.getDirectory()+fdig.getFile();
	}
}
