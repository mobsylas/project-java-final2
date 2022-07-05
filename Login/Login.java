package Login;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import rmi.ClientView;
import rmi.IBookDAO;

import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.rmi.*;
import java.security.*;
import java.awt.*;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPass;

	public static void main(String[] args) {
		  try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 406);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbLogin = new JLabel("Login");
		lbLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lbLogin.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbLogin.setBounds(193, 31, 151, 46);
		contentPane.add(lbLogin);

		JLabel lblNewLabel = new JLabel("Username : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(22, 103, 116, 46);
		contentPane.add(lblNewLabel);

		JLabel Password = new JLabel("Password :");
		Password.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Password.setBounds(22, 186, 116, 46);
		contentPane.add(Password);

		txtUser = new JTextField();
		txtUser.setBounds(148, 106, 346, 46);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		txtPass = new JTextField();
		txtPass.setBounds(148, 189, 346, 46);
		contentPane.add(txtPass);
		txtPass.setColumns(10);

		JButton btDangNhap = new JButton("Sign In");
		btDangNhap.setFont(new Font("Dialog", Font.BOLD, 17));
		btDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
				IBookDAO cl= (IBookDAO)Naming.lookup("rmi://localhost:2008/hehe");
				int ans=cl.getLogin(txtUser.getText(), txtPass.getText());
				if(ans!=0) {
					ClientView frame = new ClientView();
					frame.setVisible(true);
					dispose();
				}else {
					  JOptionPane.showMessageDialog(null, "Wrong login please re-enter");
						
				}
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		btDangNhap.setBounds(95, 280, 132, 46);
		contentPane.add(btDangNhap);

		JButton btDangKy = new JButton("Sign Up");
		btDangKy.setFont(new Font("Dialog", Font.BOLD, 17));
		btDangKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp frame = new SignUp();
				frame.setVisible(true);
			}
		});
		btDangKy.setBounds(321, 280, 124, 46);
		contentPane.add(btDangKy);
	}
}
