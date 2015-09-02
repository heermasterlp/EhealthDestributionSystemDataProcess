package myApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirectoryManager;

import org.json.JSONObject;

import com.mysql.jdbc.Driver;

public class DBread {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stubs
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("success load dirver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String urlString = "jdbc:mysql://10.119.180.33:3306/ehr";
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(urlString,"ehr","ehr");
			Statement statement = conn.createStatement();
			System.out.println("success connection db");
			
			// 1. patient
			String sql = "select * from patient";
			ResultSet rs = statement.executeQuery(sql);
			File pathFile = new File("/Users/heermaster/Documents/document/patient.json");
			Writer writer = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(pathFile ),"UTF-8" ));
            
			while(rs.next()){
				HashMap<String, String> content = new HashMap<String, String>();
				content.put("Patient_ID", rs.getString(1));
				content.put("Name", rs.getString(2));
				content.put("Sumame", rs.getString(3));
				content.put("Gender", rs.getString(4));
				content.put("DoB", rs.getString(5));
				content.put("Country", rs.getString(6));
				content.put("StreetAddress", rs.getString(7));
				content.put("City", rs.getString(8));
				content.put("Postal", rs.getString(9));
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("patient", content);
				writer.write(jsonObject.toString());
				writer.write("\n");
			}
			writer.flush();
			// 2. clinicaldetection
			
			sql = "select * from clinicaldetection";
			rs = statement.executeQuery(sql);
			
			pathFile = new File("/Users/heermaster/Documents/document/clinicaldetection.json");
			writer = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(pathFile ),"UTF-8" ));
			
			while(rs.next()){
				HashMap<String, String> content = new HashMap<String, String>();
				content.put("Detection_ID", rs.getString(1));
				content.put("Patient_ID", rs.getString(2));
				content.put("DetectedDate", rs.getString(3));
				content.put("Times", rs.getString(4));
				content.put("AntiHBs", rs.getString(5));
				content.put("HBsAg", rs.getString(6));
				content.put("HIV", rs.getString(7));
				content.put("HDV", rs.getString(8));
				content.put("HVC", rs.getString(9));
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("clinicaldetection", content);
				writer.write(jsonObject.toString());
				writer.write("\n");
				
			}
			writer.flush();
			// 3. examination
			sql = "select * from examination";
			rs = statement.executeQuery(sql);
			
			pathFile = new File("/Users/heermaster/Documents/document/examination.json");
			writer = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(pathFile ),"UTF-8" ));
			
			while(rs.next()){
				HashMap<String, String> content = new HashMap<String, String>();
				content.put("Report_ID", rs.getString(1));
				content.put("Patient_ID", rs.getString(2));
				content.put("Endoscopy_Date", rs.getString(3));
				content.put("Diagnoses_Text", rs.getString(4));
				content.put("Doctor", rs.getString(5));
				

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("examination", content);
				writer.write(jsonObject.toString());
				writer.write("\n");
				
			}
			
			statement.close();
			conn.close();
			writer.close();
			System.out.println("successed!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
