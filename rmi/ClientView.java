package rmi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import Chat_Swing.ClientChatter;
import Chat_Swing.ManagerChatter;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class ClientView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtAuthor;
	private JTextField txtCategory;
	private JTextField txtPrice;
	private JButton btnStaff;
	private JButton btnDelete;
	private JButton btnShiw;
	private JButton btnEdit;
	private JScrollPane scrollPane;
	private JTextField textSearch;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientView frame = new ClientView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientView() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(437, 17, 478, 417);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"BookID", "Name", "Author", "Category", "Price"
			}
		));
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 78, 85, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 128, 85, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Author");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 178, 85, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Category");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 228, 85, 30);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 277, 85, 30);
		contentPane.add(lblNewLabel_4);
		
		txtID = new JTextField();
		txtID.setBounds(103, 74, 307, 39);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(103, 124, 307, 39);
		contentPane.add(txtName);
		
		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(103, 174, 307, 39);
		contentPane.add(txtAuthor);
		
		txtCategory = new JTextField();
		txtCategory.setColumns(10);
		txtCategory.setBounds(103, 224, 307, 39);
		contentPane.add(txtCategory);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(103, 273, 307, 39);
		contentPane.add(txtPrice);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            String bookID = txtID.getText();
		            String name = txtName.getText();
		            String author = txtAuthor.getText();
		            String category = txtCategory.getText();
		            String price = txtPrice.getText();
		            IBookDAO bookDAO = (IBookDAO) Naming.lookup("rmi://localhost:2008/hehe");
		            String result = bookDAO.insert(bookID, name, author, category, price);
		            JOptionPane.showMessageDialog(null, result, "success", JOptionPane.INFORMATION_MESSAGE);
		        } catch (Exception e1) {
		             JOptionPane.showMessageDialog(null, e1, "Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnInsert.setBounds(10, 351, 85, 30);
		contentPane.add(btnInsert);
		
		btnStaff = new JButton("Port");
		btnStaff.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerChatter scren = new ManagerChatter();
				scren.setVisible(true);
			}
		});
		btnStaff.setBounds(10, 404, 85, 30);
		contentPane.add(btnStaff);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            String bookID = txtID.getText();
		            IBookDAO bookDAO = (IBookDAO) Naming.lookup("rmi://localhost:2008/hehe");
		            String result = bookDAO.Delete(bookID);
		            JOptionPane.showMessageDialog(null, result, "Success", JOptionPane.INFORMATION_MESSAGE);
		        } catch (Exception e1) {
		             JOptionPane.showMessageDialog(null, e1, "Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnDelete.setBounds(113, 351, 85, 30);
		contentPane.add(btnDelete);
		
		btnShiw = new JButton("Show");
		btnShiw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnShiw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					IBookDAO cl = (IBookDAO) Naming.lookup("rmi://localhost:2008/hehe");
					String[] colums = { "BookID", "Name", "Author", "Category", "Price"};
					Vector name = new Vector();
					Vector data = cl.getList();
					
					for (int i = 0; i < colums.length; i++) {
						name.add(colums[i]);
					}
					DefaultTableModel model = new DefaultTableModel(data, name);
					table.setModel(model);
					
					
					

				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		btnShiw.setBounds(325, 351, 85, 30);
		contentPane.add(btnShiw);
		
		btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					IBookDAO bookDAO = (IBookDAO) Naming.lookup("rmi://localhost:2008/hehe");
		            String bookID = txtID.getText();
		            String name = txtName.getText();
		            String author = txtAuthor.getText();
		            String category = txtCategory.getText();
		            String price = txtPrice.getText();
		            
		            String result = bookDAO.Edit(bookID, name, author, category, price);
		            JOptionPane.showMessageDialog(null, result, "success", JOptionPane.INFORMATION_MESSAGE);
		        } catch (Exception e1) {
		             JOptionPane.showMessageDialog(null, e1, "Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnEdit.setBounds(219, 351, 85, 30);
		contentPane.add(btnEdit);
		
		textSearch = new JTextField();
		textSearch.setColumns(10);
		textSearch.setBounds(103, 17, 213, 34);
		contentPane.add(textSearch);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					IBookDAO cl = (IBookDAO) Naming.lookup("rmi://localhost:2008/hehe");
					String[] colums = { "BookID", "Name", "Author", "Category", "Price"};
					Vector name = new Vector();
					Vector vData = cl.Search(textSearch.getText());
					
					for (int i = 0; i < colums.length; i++) {
						name.add(colums[i]);
					}
					DefaultTableModel model = new DefaultTableModel(vData, name);
					table.setModel(model);

				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(325, 17, 85, 34);
		contentPane.add(btnNewButton);
		
		JButton btnAdmin_1 = new JButton("Chat with Admin");
		btnAdmin_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientChatter screen = new ClientChatter();
				screen.setVisible(true);
			}
		});
		btnAdmin_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdmin_1.setBounds(113, 404, 144, 30);
		contentPane.add(btnAdmin_1);
	}
}
