package FileUpload;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket client = null;
		try {
			server = new ServerSocket(55000);
			while(true) {
				client = server.accept();
				System.out.println("클라이언트 연결됨");
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());
				File file = (File)in.readObject();
				int i=0;
				File copyfile = new File(file.getName()+".copy"+i++);
				System.out.println("복사한파일위치"+copyfile.toURI());
				FileWriter fw = new FileWriter(copyfile);
				FileReader fr = new FileReader(file);
				double size =0;
				double total =0;
				while(true) {
					char[] c = new char[1024*1024];
					size = fr.read(c);
					if(size == -1)break;
					total += size;
					fw.write(c);
					fw.flush();
				}
				System.out.println("전송받은 파일크기:"+file.length());
				System.out.println("복사한 파일크기:"+total);
				fw.close();
				fr.close();
				in.close();
				client.close();
				System.out.println("파일업로드 성공 다음 클라이언트 대기중....");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
