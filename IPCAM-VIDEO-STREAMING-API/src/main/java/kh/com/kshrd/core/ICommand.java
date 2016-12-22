package kh.com.kshrd.core;

/**
 * Created by sophatvathana on 15/12/16.
 */
public interface ICommand {
    void    setConnection(String host, int port, String user, String pass);
    boolean right(int speed);
    boolean left(int speed);
    boolean up(int speed);
    boolean down(int speed);
    boolean stop();
    boolean wide(int speed);
    boolean tele(int speed);
}
