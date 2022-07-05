package Login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField tfTaikhoan;
	private JTextField tfMatkhau;


	public static void main(String[] args) {
		  try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Nimbus".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 404);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbDangKy = new JLabel("Register");
		lbDangKy.setHorizontalAlignment(SwingConstants.CENTER);
		lbDangKy.setForeground(new Color(0, 0, 0));
		lbDangKy.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbDangKy.setBounds(152, 43, 198, 38);
		contentPane.add(lbDangKy);
		
		JLabel lbTaikhoan = new JLabel("Username :");
		lbTaikhoan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTaikhoan.setBounds(27, 113, 132, 38);
		contentPane.add(lbTaikhoan);
		
		JLabel lbMatkhau = new JLabel("Password :");
		lbMatkhau.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbMatkhau.setBounds(27, 201, 122, 35);
		contentPane.add(lbMatkhau);
		
		tfTaikhoan = new JTextField();
		tfTaikhoan.setBounds(159, 113, 310, 38);
		contentPane.add(tfTaikhoan);
		tfTaikhoan.setColumns(10);
		
		tfMatkhau = new JTextField();
		tfMatkhau.setBounds(159, 201, 310, 38);
		contentPane.add(tfMatkhau);
		tfMatkhau.setColumns(10);
		
		
		
		JButton btnDangKy = new JButton("Sign Up");
		btnDangKy.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnDangKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/fulib?characterEncoding=UTF-8", "root", "");
					
				String sql = "INSERT INTO  accounttbl (username,password) values (?,?)";
				PreparedStatement pst = con.prepareStatement(sql);
				
				pst.setString(1,tfTaikhoan.getText());
				if(tfTaikhoan.getText().equals("")||tfMatkhau.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nhap lai thong tin");
				}
				else {
				
				pst.setString(2, encrypt(tfMatkhau.getText()));
				
				int count  = pst.executeUpdate();
				if(count > 0) {
					JOptionPane.showMessageDialog(null,"Sign Up complete");
				}
				con.close();
			
				
					// TODO Auto-generated catch block
					
				} }catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnDangKy.setBounds(91, 281, 122, 38);
		contentPane.add(btnDangKy);
		
		JButton btnDangnhap = new JButton("Sign In");
		btnDangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lg = new Login();
				lg.setVisible(true);
			}
		});
		btnDangnhap.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnDangnhap.setBounds(283, 281, 132, 38);
		contentPane.add(btnDangnhap);
	}

	protected Object md5(String text) {
		// TODO Auto-generated method stub
		return null;
	}
}
