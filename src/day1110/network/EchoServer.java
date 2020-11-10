/*
 * 네트워크에 대한 지식이 없어도 네트워크를 사용할 수 있게 해주는 소켓
 * 소켓이란?
 * - 일상 생활에서 전구 등에 꼽는 접점도구이다..
 * 역할?
 * - 전기 지식이 없어도, 전구를 동작시킬 수 있다..
 * 프로그래밍 분야 - 네트워크 지식이 없어도, 네트워크 제어 프로그래밍이 가능하게 하는 것
 *	  					따라서, 사실상 프로그래밍 언어에서 개발자가 제어하는 대상은 스트림인 것 뿐이다...
 *
 *소켓은 자바 언어만 지원하는 기술, 용어이다 X
 *- 현존하는 대부분의 응용 어플리케이션에서 지원하고 있다..(컴파일 : c, java,c#,python(인터프리터))
 * */
package day1110.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	ServerSocket server;
	int port = 8282;

	public EchoServer() {
		try {
			server = new ServerSocket(port);//서버소켓 생성 (아직 가동은 아님)
			Socket socket = server.accept(); //서버가동 및 클라이언트 접속 기다림 ( 접속이 들어올때까지 무한 대기)
			System.out.println("접속자 발견!");
			//개발자는 반환받는 소켓으로부터 동신에 필요한 입출력 스트림을 얻을 수 있다!!
			//이때 개발자는 네트워크 하부에 대한 아무런 지식이 없이 그냥 스트림처리만 하면 알아서 원격지으 대화상대와
			//통신이 가능하며, 이 모든 것들은 소켓이 알아서 해주는 거다..
			InputStream is= socket.getInputStream(); //(바이트기반 입력스트림이므로 한글해석X)
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buffr = new BufferedReader(reader);
			String data;
			while(true) {
				data=buffr.readLine(); //1byte읽기 (소켓과 연결된 스트림으로부터)
				System.out.print(data);	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EchoServer();
	}
}
