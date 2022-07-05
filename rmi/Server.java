/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import static rmi.DatabaseInfo.connectionURL;
import static rmi.DatabaseInfo.password;
import static rmi.DatabaseInfo.userName;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;






public class Server extends UnicastRemoteObject implements IBookDAO,DatabaseInfo{

    public Server() throws RemoteException{		

    }

    @Override
    public String insert(String bookID, String name, String author, String category, String price) throws RemoteException {
        int rc=0;
        try {
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            String sql = "Insert into books values(?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, bookID);
            stmt.setString(2, name);
            stmt.setString(3, author);
            stmt.setString(4, category);
            stmt.setString(5, price);
            rc = stmt.executeUpdate();
            
            
            
            return "Update successful";
        } catch (Exception e) {
            return e.toString();
        }
    }
    public static void main(String[] args) {
    	
    	
        try {
            LocateRegistry.createRegistry(2008);
            try {
                Naming.rebind("rmi://localhost:2008/hehe", new Server());
            } catch (MalformedURLException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.out.println("Server ready");
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	@Override
	public String Delete(String bookID) throws RemoteException {
		 int rc=0;
	        try {
	            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	            String sql = "delete from books where bookID=?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, bookID);	           
	            rc = stmt.executeUpdate();	            	            
	            
	            return "Update successful";
	        } catch (Exception e) {
	            return e.toString();
	        }
	}

	@Override
	public String Edit(String bookID, String name, String author, String category, String price)
			throws RemoteException {
        try {
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            String sql1 = "UPDATE `books` SET `name`=?,`author`=?,`category`=?,`price`=? WHERE bookID=?";
            PreparedStatement stmt = conn.prepareStatement(sql1); 
            stmt.setString(1, name);
            stmt.setString(2, author);
            stmt.setString(3, category);
            stmt.setString(4, price);
            stmt.setString(5, bookID);
            stmt.executeUpdate();	            	            
            return "Update successful";
        } catch (Exception e2) {
            return e2.toString();
        }
	}



	@Override
	public Vector Search(String bookID) throws RemoteException {
		Vector data = new Vector();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "SELECT * FROM `books` where bookID = '" + bookID+ " ' ";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fulib?characterEncoding=UTF-8", "root", "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmt = rs.getMetaData();
            int col = rsmt.getColumnCount();
            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i <= col; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            st.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;

	}
	
	@Override
	public int getLogin(String user, String pass) throws RemoteException {
		int ans=0;
		Connection con;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/fulib?characterEncoding=UTF-8", "root", "");
			String sql = " SELECT *FROM accounttbl where username = ? and password = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,user);
			pst.setString(2, encrypt(pass));
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				ans++;
			}
			rs.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}
	
	public static String encrypt(String srcText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String enrText;
		MessageDigest msd = MessageDigest.getInstance("MD5");

		byte[] srcTextBytes = srcText.getBytes("UTF-8");
		byte[] enrTextBytes = msd.digest(srcTextBytes);

		BigInteger bigInt = new BigInteger(1, enrTextBytes);
		enrText = bigInt.toString(16);

		return enrText;

	}
	public Vector getList() throws RemoteException {
		Vector data = new Vector();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "SELECT * FROM `books`";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fulib?characterEncoding=UTF-8", "root", "");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmt = rs.getMetaData();
            int col = rsmt.getColumnCount();
            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i <= col; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            st.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
	}
	
//	
}
