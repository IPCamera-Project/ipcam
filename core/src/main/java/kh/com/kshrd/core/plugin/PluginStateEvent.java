package kh.com.kshrd.core.plugin;

/**
 * Created by sophatvathana, rina on 15/12/16.
 */

public interface PluginStateEvent {

	/**
	 * Performs the plugin action.
	 */
	public void run();
	public String helloWorld();

	boolean left();
	boolean right();
	boolean up();
	boolean down();
	boolean zoomIn();
	boolean zoomOut();
	boolean stop();

	String getRtsp();

	void setConnection(String host, int port, String user,String pass);


}
