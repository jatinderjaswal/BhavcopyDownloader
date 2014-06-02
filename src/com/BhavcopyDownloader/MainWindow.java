package com.BhavcopyDownloader;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.util.Calendar;
import java.util.Date;
import com.BhavcopyDownloader.Utilities;

public class MainWindow extends JFrame implements ActionListener {
	
	JPanel pnlControls = new JPanel();
	
	UtilDateModel dtFromUDM = new UtilDateModel();
	JDatePanelImpl dtFromDPI = new JDatePanelImpl(dtFromUDM);
	JDatePickerImpl dtFromDatePicker = new JDatePickerImpl(dtFromDPI);
	
	UtilDateModel dtToUDM = new UtilDateModel();
	JDatePanelImpl dtToDPI = new JDatePanelImpl(dtToUDM);
	JDatePickerImpl dtToDatePicker = new JDatePickerImpl(dtToDPI);


	public MainWindow(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Bhavcopy Downloader");
		this.setSize(800, 200);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		JLabel lblTitle = new JLabel("Bhavcopy Downloader");
		lblTitle.setFont(new Font("Seriff",Font.PLAIN,20));
		this.add(lblTitle,BorderLayout.PAGE_START);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblFrom = new JLabel("From Date:");
		lblFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrom.setVerticalAlignment(SwingConstants.TOP);

		pnlControls.add(lblFrom); 
		pnlControls.add(dtFromDatePicker);
		
		JLabel lblTo = new JLabel("To Date:");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setVerticalAlignment(SwingConstants.TOP);
	
		pnlControls.add(lblTo);
		pnlControls.add(dtToDatePicker);
		
		JButton btnGetDetails = new JButton("Get Details");
		btnGetDetails.setSize(new Dimension(20, 20));
		btnGetDetails.addActionListener(this);
		pnlControls.add(btnGetDetails);
		this.add(pnlControls,BorderLayout.CENTER);
	}
	
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(((Date) dtFromDatePicker.getModel().getValue() != null) || ((Date) dtToDatePicker.getModel().getValue())!=null){
			com.BhavcopyDownloader.Utilities objUtils = new com.BhavcopyDownloader.Utilities();
			Calendar startDate = Calendar.getInstance();
			startDate.setTime((Date) dtFromDatePicker.getModel().getValue());
		
			Calendar endDate = Calendar.getInstance();
			endDate.setTime((Date) dtToDatePicker.getModel().getValue());
		
			while(!startDate.after(endDate)){
				try{
					objUtils.saveFile(objUtils.createDownloadURL(startDate.getTime()));				
				}
				catch (Exception e){
					System.out.println("Error " + e.getMessage());
				}
				startDate.add(Calendar.DATE, 1);
			}
			JOptionPane.showMessageDialog(this, "Files downloaded");
		}
		else{
			JOptionPane.showMessageDialog(this, "Select valid \"From\" and \"To\" Dates");
		}
		
	}
}
