package yyz.njupt.server;

public class start {
	
	public static void main(String[] args){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				new LoginServer(8063).startServer();
				
			}
		}).start();

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					UDPMessageServer.openServer();
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		}).start();

	}

}
