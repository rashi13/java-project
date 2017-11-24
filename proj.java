
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.io.*;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
public class proj{
	String x= "C:\\Users\\RASHI\\Desktop\\";
	JFrame jfr;
	JPanel jpn,jpn1, jpn2;
	JTree jtree;
	JLabel jlbl;
	JScrollPane scroller,scroller2;
	JTextArea jtxt;
	JMenuBar menu;
	JMenu filemenu;
	JMenuItem open,exit;
	JFileChooser filechooser;
	JEditorPane editor;
	JTextField txf;
	proj(){
		txf= new JTextField("C:\\\\",50);
		jpn2= new JPanel();
		jpn2.setLayout(new BorderLayout());
	    jfr = new JFrame("Hierarchy");	   
		jfr.setLayout(new BorderLayout());
	    jpn = new JPanel();
		jfr.add(jpn,BorderLayout.WEST);
		jfr.setSize(500, 500);
		jfr.setVisible(true);
		jpn1=new JPanel();
		menu=new JMenuBar();
	
		filemenu = new JMenu("File");
		
		open=new JMenuItem("Open");
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent  g) {
				// TODO Auto-generated method stub
				filechooser=new JFileChooser();
				int result= filechooser.showOpenDialog(editor);
				String filex=filechooser.getSelectedFile().getAbsolutePath();
				switch(result) {
				case JFileChooser.APPROVE_OPTION: 
	               if (filex.endsWith(".txt")) {
					jpn1.add(jtxt);
					jtxt.setFont(jtxt.getFont().deriveFont(20f));
					scroller2= new  JScrollPane(jtxt);
					jpn1.add(scroller2);
					scroller2.setPreferredSize(new Dimension(1500,1000));

					File file = new File(filex);
				
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new FileReader(file));
						String text = null;
						String savetext=null;
						     		
						while ((text = reader.readLine()) != null) {
							savetext += text+"\n";
						
						}
						jtxt.setText(savetext+"\n");
						
					}catch(FileNotFoundException e4) {
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			 	   }else if((filex.endsWith(".jpg"))||(filex.endsWith(".png"))||(filex.endsWith(".jpeg"))){
					
				         ImageIcon imageIcon = new ImageIcon(new ImageIcon(filex).getImage().getScaledInstance(1500, 1000, Image.SCALE_DEFAULT));
				         JLabel label = new JLabel(imageIcon);
				         jpn1.add(label);
				   } else if(filex.endsWith(".pdf")){
					try {
						Desktop.getDesktop().open(new File(filex));
					 } catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					 }
				   }
				}
			 }
			});
        exit=new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
			
		});
		filemenu.add(open);
		filemenu.addSeparator();
		filemenu.add(exit);
		menu.add(filemenu);
		
		jpn2.add(menu,BorderLayout.NORTH);
		jpn2.add(txf,BorderLayout.SOUTH);
		jfr.add(jpn2,BorderLayout.NORTH);
		jtxt =new JTextArea("",1300,900);		
		jfr.add(jpn1,BorderLayout.CENTER);
		  
		
		DefaultMutableTreeNode root = new  DefaultMutableTreeNode("Desktop");
		jtree= new JTree(root);
		jpn.add(jtree);	 
		
		 scroller = new JScrollPane(jtree);
		 scroller.setPreferredSize(new Dimension(400,1000));
		 scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 jlbl = new JLabel("");
		 
		 jpn.add(scroller);
		 jfr.add(jlbl,BorderLayout.SOUTH);
		 jtree.addTreeSelectionListener(new TreeSelectionListener() {
 			@Override
 			
			public void valueChanged(TreeSelectionEvent e) {
			String filex =e.getPath().getLastPathComponent().toString();
			String abspath= x+filex;
			txf.setText(abspath);
			jlbl.setText("Total Space: "+((new File(abspath)).getTotalSpace())/(1024*1024*1024)+"gb");
			
			if(filex.endsWith(".txt")) {
				jpn1.add(jtxt);
				jtxt.setFont(jtxt.getFont().deriveFont(20f));
				scroller2= new  JScrollPane(jtxt);
				jpn1.add(scroller2);
				scroller2.setPreferredSize(new Dimension(1500,1000));

				File file = new File(abspath);
			
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(file));
					String text;
					String savetext=null;
					     		
					while ((text = reader.readLine()) != null) {
						savetext += text+"\n";
					
					}
					jtxt.setText(savetext+"\n");
					
				}catch(FileNotFoundException e4) {
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}else if((filex.endsWith(".jpg"))||(filex.endsWith(".png"))||(filex.endsWith(".jpeg"))){
				
			         ImageIcon imageIcon = new ImageIcon(new ImageIcon(abspath).getImage().getScaledInstance(1500, 1000, Image.SCALE_DEFAULT));
			         JLabel label = new JLabel(imageIcon);
			         jpn1.add(label);

			      
			       
			}else if(filex.endsWith(".pdf")){
				try {
					Desktop.getDesktop().open(new File(abspath));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		 }
		 });
		
		File f = new File(x);
		
			File [] x1 = f.listFiles();
			for(int j=0;j<x1.length;j++) {			
				 DefaultMutableTreeNode y=new DefaultMutableTreeNode(x1[j].getName());
				 root.add(y);
				
				 if(x1[j].isDirectory())
				 nodes(y,x1[j].getAbsolutePath());			
			}
			
	}
	public void nodes(DefaultMutableTreeNode x,String path) {
		   File f3 = new File(path);
		  File [] f=f3.listFiles();
			for(int i=0;i<f.length;i++) {
				 DefaultMutableTreeNode y1=new DefaultMutableTreeNode(f[i].getName());
				   x.add(y1);
			   if(f[i].isDirectory()) {
				   nodes(y1,f[i].getAbsolutePath());
			   }
		   }
	}
	public static void main(String [] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				proj file_explorer = new proj();
			}
			
		});
		
	}
}