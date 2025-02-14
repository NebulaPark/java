package GUIchat.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class WriteThread
{
    
    Socket socket;
    
    ClientFrame cf;
    
    String str;
    
    String id;
    
    public WriteThread(ClientFrame cf)
    {
        this.socket = cf.socket;
        this.cf = cf;
    }
    
    public void sendMsg()
    {
        
        PrintWriter writer = null; // 입력 메시지 출력용
        
        try
        {
            writer = new PrintWriter(socket.getOutputStream(), true);
            if (cf.isFirst == true) // IPClass 활성화
            {
                
                InetAddress iaddr = socket.getLocalAddress();
                String ip = iaddr.getHostAddress();
                getIP();
                System.out.println("ip: " + ip + "id: " + id);
                str= "[" + id + "] 님 로그인 ("+ip+")";
            }
            else
            { // ClientFrame 활성화
                str = "[" + id + "]@" + cf.txtF.getText();
            }
            writer.println(str);
            
        }
        catch (IOException e)
        {
            // TODO 자동 생성된 catch 블록
            e.printStackTrace();
        }
        
    }
    
    public void getIP()
    {
        id = IPClass.getIP();
    }
    
}
