/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;


public interface IBookDAO extends Remote{
	public int getLogin(String user,String pass) throws RemoteException;
    public String insert(String bookID, String name, String author, String category, String price) throws RemoteException;
    public String Delete(String bookID)  throws RemoteException;
    public String Edit(String bookID, String name, String author, String category, String price) throws RemoteException;
    public Vector Search(String bookID) throws RemoteException;
    public Vector getList() throws RemoteException;
    
  
}
