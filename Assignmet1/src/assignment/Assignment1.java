package assignment;

import java.awt.*;  
import java.awt.event.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class Assignment1 {

	// JDBC driver name and database URL
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 static final String DB_URL = "jdbc:mysql://localhost/";
	 static final String DISABLE_SSL = "?useSSL=false";
	 // Database credentials
	 static final String USER = "root";
	// static final String PASS = "fewWvvL8Fw=p"; // insert the password to SQL server

	 static final String PASS = "root";
	 
	 public static void main(String[] args) {
//	 public static void Breakers() {
	 Connection conn = null;
	 Statement stmt = null;
	 try{
		// Register JDBC driver
	 Class.forName(JDBC_DRIVER);
	 // Open a connection
	 System.out.println("Connecting to SQL server...");
	 conn = DriverManager.getConnection(DB_URL+DISABLE_SSL, USER, PASS);
	 System.out.println("Connected database successfully...");
	 
	// execute a query to create database
	 System.out.println("Creating database...");
	 stmt = conn.createStatement();
	 String sql = "CREATE DATABASE IF NOT EXISTS PowerSystem";
	 stmt.executeUpdate(sql);
	 System.out.println("Database created successfully...");

	 // Connect to the created database powersystem and create table breaker
	 conn = DriverManager.getConnection(DB_URL + "PowerSystem"+DISABLE_SSL, USER, PASS);
	 sql = "USE PowerSystem";
	 stmt.executeUpdate(sql);
	 sql = "DROP TABLE IF EXISTS BaseVoltage";
	 stmt.executeUpdate(sql);
	 sql = "DROP TABLE IF EXISTS Substation";
	 stmt.executeUpdate(sql);
	 sql = "DROP TABLE IF EXISTS VoltageLevel";
	 stmt.executeUpdate(sql);
	 sql = "DROP TABLE IF EXISTS GeneratingUnit";
	 stmt.executeUpdate(sql);
	 sql = "DROP TABLE IF EXISTS SynchronousMachine";
	 stmt.executeUpdate(sql);
	 sql = "DROP TABLE IF EXISTS RegulatingControl";
	 stmt.executeUpdate(sql);
	 sql = "DROP TABLE IF EXISTS PowerTransformer";
	 stmt.executeUpdate(sql);
	 sql = "DROP TABLE IF EXISTS EnergyConsumer";
	 stmt.executeUpdate(sql);
	 sql = "DROP TABLE IF EXISTS Breakers";
	 stmt.executeUpdate(sql);
	 sql = "DROP TABLE IF EXISTS PowerTransformerEnd";
	 stmt.executeUpdate(sql);
	 sql = "DROP TABLE IF EXISTS RatioTapChanger";
	 stmt.executeUpdate(sql);
	 
	 
//
	 
//	 ReadXML3.extractNode041(node);

 
	 
	 sql = "CREATE TABLE IF NOT EXISTS BaseVoltage( NorminalValue VARCHAR(255), RDF_ID VARCHAR(255) ,  PRIMARY KEY(RDF_ID))"; // create table Breaker with corresponding attributes
	 stmt.executeUpdate(sql) ;
	 sql = "CREATE TABLE IF NOT EXISTS Substation( Name VARCHAR(255), RDF_ID VARCHAR(255) , Region_rdfID VARCHAR(255), PRIMARY KEY(RDF_ID))"; // create table Breaker with corresponding attributes
	 stmt.executeUpdate(sql) ;
	 sql = "CREATE TABLE IF NOT EXISTS VoltageLevel( Name VARCHAR(255), RDF_ID VARCHAR(255) , Substation_rdfID VARCHAR(255), BaseVoltage_rdfID VARCHAR(255) , PRIMARY KEY(RDF_ID))"; // create table Breaker with corresponding attributes
	 stmt.executeUpdate(sql) ;
	 sql = "CREATE TABLE IF NOT EXISTS GeneratingUnit (Name VARCHAR(255), RDF_ID VARCHAR(255) , MaxP VARCHAR(255), MinP VARCHAR(255), EquipmentContainer_rdfID VARCHAR(255),PRIMARY KEY(RDF_ID))"; // create table Breaker with corresponding attributes
	 stmt.executeUpdate(sql) ;
	 sql = "CREATE TABLE IF NOT EXISTS SynchronousMachine( Name VARCHAR(255), RDF_ID VARCHAR(255) , RatedS VARCHAR(255), P VARCHAR(255) , Q VARCHAR(255) , GenUnit_rdfID VARCHAR(255), RegControl_rdfID VARCHAR(255) ,EquipmentContainer_rdfID VARCHAR(255), PRIMARY KEY(RDF_ID))"; // create table Breaker with corresponding attributes
	 stmt.executeUpdate(sql) ;
	 sql = "CREATE TABLE IF NOT EXISTS RegulatingControl( Name VARCHAR(255), RDF_ID VARCHAR(255) , TargetValue VARCHAR(255),  PRIMARY KEY(RDF_ID))"; // create table Breaker with corresponding attributes
	 stmt.executeUpdate(sql) ;
	 sql = "CREATE TABLE IF NOT EXISTS PowerTransformer( Name VARCHAR(255), RDF_ID VARCHAR(255) , EquipmentContainer_RDF VARCHAR(255), PRIMARY KEY(RDF_ID))"; // create table Breaker with corresponding attributes
	 stmt.executeUpdate(sql) ;
	 sql = "CREATE TABLE IF NOT EXISTS EnergyConsumer( Name VARCHAR(255), RDF_ID VARCHAR(255) , P VARCHAR(255) , Q VARCHAR(255) , EquipmentContainer_RDF VARCHAR(255), PRIMARY KEY(RDF_ID))"; // create table Breaker with corresponding attributes
	 stmt.executeUpdate(sql) ;
	 sql = "CREATE TABLE IF NOT EXISTS PowerTransformerEnd( Name VARCHAR(255), RDF_ID VARCHAR(255) ,Transformer_r VARCHAR(255) ,Transformer_x VARCHAR(255), Transformer_rdfID VARCHAR(255),BaseVoltage_rdfID VARCHAR(255) ,PRIMARY KEY(RDF_ID))"; // create table Breaker with corresponding attributes
	 stmt.executeUpdate(sql) ;
	 sql = "CREATE TABLE IF NOT EXISTS Breakers( Name VARCHAR(255), RDF_ID VARCHAR(255) ,EquipmentContainer_rdfID VARCHAR(255) ,State VARCHAR(255) , PRIMARY KEY(RDF_ID))"; // create table Breaker with corresponding attributes
	 stmt.executeUpdate(sql) ;
	 sql = "CREATE TABLE IF NOT EXISTS RatioTapChanger( Name VARCHAR(255), RDF_ID VARCHAR(255) , Step VARCHAR(255), PRIMARY KEY(RDF_ID))"; // create table Breaker with corresponding attributes
	 stmt.executeUpdate(sql) ;
	 
	 
//	 stmt.executeUpdate(sql) ; // execute query

	 System.out.println("Created table in given database successfully...");
	 
	 // insert values into the table 
	 
	 
  	    File XmlFile1 = new File("Assignment_EQ_reduced.xml");
		File XmlFile2 = new File("Assignment_SSH_reduced.xml");
	 
	 
	 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc1 = dBuilder.parse(XmlFile1);
			Document doc2 = dBuilder.parse(XmlFile2);
			
			
					 
			// normalize XML file
			doc1.getDocumentElement().normalize();
			doc2.getDocumentElement().normalize();
			
			NodeList subList = doc1.getElementsByTagName("cim:Substation");
			NodeList voltList = doc1.getElementsByTagName("cim:VoltageLevel");
			
			NodeList genList = doc1.getElementsByTagName("cim:SynchronousMachine");
			NodeList genListSSH = doc2.getElementsByTagName("cim:SynchronousMachine");
			
			NodeList breakerList = doc1.getElementsByTagName("cim:Breaker");
			NodeList breakerListSSH = doc2.getElementsByTagName("cim:Breaker");
			
			NodeList generatingUnit = doc1.getElementsByTagName("cim:GeneratingUnit");
			
			NodeList regulatingcontrol = doc1.getElementsByTagName("cim:RegulatingControl");
			NodeList regulatingcontrolSSH = doc2.getElementsByTagName("cim:RegulatingControl");
			
			NodeList basevolList =doc1.getElementsByTagName("cim:BaseVoltage");
			
			NodeList powertranList = doc1.getElementsByTagName("cim:PowerTransformer");//power transformer
			
			NodeList energyList = doc1.getElementsByTagName("cim:EnergyConsumer");
			NodeList energyListSSH = doc2.getElementsByTagName("cim:EnergyConsumer");//energy consumer
			
			NodeList transendList = doc1.getElementsByTagName("cim:PowerTransformerEnd");//power transformer end
			NodeList tapchangerList = doc1.getElementsByTagName("cim:RatioTapChanger");
			NodeList tapchangerListSSH = doc2.getElementsByTagName("cim:RatioTapChanger");//ratio tap changer
			
			NodeList terminalList=doc1.getElementsByTagName("cim:Terminal");// Terminal
			NodeList ConnectivitynodeList=doc1.getElementsByTagName("cim:ConnectivityNode");
			NodeList AClineList = doc1.getElementsByTagName("cim:ACLineSegment");
			
			NodeList BusbarList = doc1.getElementsByTagName("cim:BusbarSection");
			NodeList ShuntList =doc1.getElementsByTagName("cim:LinearShuntCompensator");
			NodeList LineList = doc1.getElementsByTagName("cim:ACLineSegment");
			
			
			String[] Substations = new String[ subList.getLength()];
			String[] Voltagelevel= new String[voltList.getLength()];
			Double[] Transformer_r =new Double[transendList.getLength()];
			Double[] Transformer_x =new Double[transendList.getLength()];
			String[] Transformer_basevol =new String[transendList.getLength()];
			Double[] load_p=new Double[energyListSSH.getLength()];
			Double[] load_q=new Double[energyListSSH.getLength()];
			Double[] Shunt_b=new Double[ShuntList.getLength()];
			Double[] Shunt_g=new Double[ShuntList.getLength()];
			Double[] Line_r=new Double[LineList.getLength()];
			Double[] Line_x=new Double[LineList.getLength()];
			Double[] Line_bch=new Double[LineList.getLength()];
			Double[] Line_gch=new Double[LineList.getLength()];
			
			for(int i=0; i<ShuntList.getLength();i++){
				Element element = (Element) ShuntList.item(i);
				String shunt_b = element.getElementsByTagName("cim:LinearShuntCompensator.bPerSection").item(0).getTextContent();
				String shunt_g = element.getElementsByTagName("cim:LinearShuntCompensator.gPerSection").item(0).getTextContent();
				System.out.println("shunt info:" + "\n");
				 System.out.println("shunt_b " + shunt_b +"\n" + "shunt_g" + shunt_g  );
				double shuntb=Double.parseDouble(shunt_b);
				double shuntg=Double.parseDouble(shunt_g);
				Shunt_b[i]=shuntb;
				Shunt_g[i]=shuntg;
			}
			
			for(int i=0; i<LineList.getLength();i++){
				Element element = (Element) LineList.item(i);
				String line_r = element.getElementsByTagName("cim:ACLineSegment.r").item(0).getTextContent();
				String line_x = element.getElementsByTagName("cim:ACLineSegment.x").item(0).getTextContent();
				String line_bch = element.getElementsByTagName("cim:ACLineSegment.bch").item(0).getTextContent();
				String line_gch = element.getElementsByTagName("cim:ACLineSegment.gch").item(0).getTextContent();
				System.out.println("line info:" + "\n");
				System.out.println("line_r " + line_r+"\n" + "line_x" + line_x+"\n" + "line_bch" + line_bch+"\n" + "line_gch" + line_gch  );
				double liner=Double.parseDouble(line_r);
				double linex=Double.parseDouble(line_x);
				double linebch=Double.parseDouble(line_bch);
				double linegch=Double.parseDouble(line_gch);
				Line_r[i]=liner;
				Line_x[i]=linex;
				Line_bch[i]=linebch;
				Line_gch[i]=linegch;
			}
			
			
			for (int i = 0; i < subList.getLength(); i++) {
				Element element = (Element) subList.item(i);

				// substation info
				String subrdfID = element.getAttribute("rdf:ID");
				String subname = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
				String region_rdfID = ((Element) element.getElementsByTagName("cim:Substation.Region").item(0)).getAttribute("rdf:resource");
			
				 String query = "insert into Substation values(?, ?, ?)";
				 PreparedStatement preparedStmt = conn.prepareStatement(query);
				 
				 preparedStmt = conn.prepareStatement(query);
				 preparedStmt.setString(1, subname);
				 preparedStmt.setString(2, subrdfID);
				 preparedStmt.setString(3, region_rdfID);
				 preparedStmt.executeUpdate();
				 System.out.println("substation info:" + "\n");
				 System.out.println("rdfID: " + subrdfID +"\n" + "SubstationName: " + subname +"\n" +"region_rdfID: " + region_rdfID +"\n"  );
				
				
				 Substations[i]= subname;
				 
				
			}
		
					
			for (int i = 0; i < voltList.getLength(); i++) {
				Element element = (Element) voltList.item(i);
				System.out.println("votagelvel info:" + "\n");
				
				String volrdfID = element.getAttribute("rdf:ID");
				String volname = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
				String subregion_rdfID =  ((Element) element.getElementsByTagName("cim:VoltageLevel.Substation").item(0)).getAttribute("rdf:resource");
				String baseVoltage_rdfID = ((Element) element.getElementsByTagName("cim:VoltageLevel.BaseVoltage").item(0)).getAttribute("rdf:resource");
				
				String query = "insert into VoltageLevel values(?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				 
				 preparedStmt = conn.prepareStatement(query);
				 preparedStmt.setString(1, volname);
				 preparedStmt.setString(2, volrdfID);
				 preparedStmt.setString(3, subregion_rdfID);
				 preparedStmt.setString(4, baseVoltage_rdfID);
				 
				 preparedStmt.executeUpdate();
				 
				 
				System.out.println("rdfID: " + volrdfID +"\n" + "VoltageLevel: " + volname +"\n" +"subregion_rdfID: " + subregion_rdfID +"\n" + "Basevoltage_rdfID: " + baseVoltage_rdfID +"\n");
			
				Voltagelevel[i]= baseVoltage_rdfID;
				    
			}
			
			for (int i = 0; i < genList.getLength(); i++) {
			
					Element element = (Element) genList.item(i);
					
					System.out.println("synchronousmachine info:" + "\n");
					// synchronousmachine info
					String synchronousmachinerdfID = element.getAttribute("rdf:ID");
					String synchronousmachinename = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
					String ratedS=((Element) element.getElementsByTagName("cim:RotatingMachine.ratedS").item(0)).getTextContent();
					String genUnit_rdfID=((Element) element.getElementsByTagName("cim:RotatingMachine.GeneratingUnit").item(0)).getAttribute("rdf:resource");
					String regControl_rdfID=((Element) element.getElementsByTagName("cim:RegulatingCondEq.RegulatingControl").item(0)).getAttribute("rdf:resource");
					String EquipmentContainer_rdfID=((Element) element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0)).getAttribute("rdf:resource");

					
					//String syncbasevoltage_rdfID= ((Element) element.getElementsByTagName("cim:VoltageLevel.BaseVoltage").item(0)).getAttribute("rdf:resource");
					
					Element element2 = (Element) genListSSH.item(i);
					String P=((Element) element2.getElementsByTagName("cim:RotatingMachine.p").item(0)).getTextContent();
					String Q=((Element) element2.getElementsByTagName("cim:RotatingMachine.q").item(0)).getTextContent();
					
					System.out.println("P: " + P +"\n" + "Q: " + Q +"\n" );
					System.out.println("rdfID: " + synchronousmachinerdfID +"\n" + "objectName: " + synchronousmachinename +"\n" + "EquipmentContainer_rdfID: " + EquipmentContainer_rdfID +"\n" + "regControl_rdfID: " + regControl_rdfID +"\n" + "genUnit_rdfID: " + genUnit_rdfID +"\n"+ "ratedS: " + ratedS);
					//System.out.println("syncBasevoltage_rdfID: " +syncbasevoltage_rdfID );
					

					String query = "insert into SynchronousMachine values(?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					 
					 preparedStmt = conn.prepareStatement(query);
					 preparedStmt.setString(1, synchronousmachinename);
					 preparedStmt.setString(2, synchronousmachinerdfID);
					 preparedStmt.setString(3, ratedS);
					 preparedStmt.setString(4, P);
					 preparedStmt.setString(5, Q);
					 preparedStmt.setString(6, genUnit_rdfID);
					 preparedStmt.setString(7, regControl_rdfID);
					 preparedStmt.setString(8, EquipmentContainer_rdfID);
					 
					 
					 preparedStmt.executeUpdate();
					
				}

	
		  
			for (int i = 0; i < breakerList.getLength(); i++) {
			    Element element = (Element) breakerList.item(i);
				
				// breaker info
				String breakerrdfID = element.getAttribute("rdf:ID");
				String breakername = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
			    String breakercontainer =((Element)element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0)).getAttribute("rdf:resource");
				System.out.println("breaker info:" + "\n");
				System.out.println("breakerrdfID: " + breakerrdfID +"\n" + "breakerobjectName: " + breakername +  "Equipmentcontainer: " + breakercontainer + "\n" );
			   
			    
			    Element element2 = (Element) breakerListSSH.item(i);
			
				String breakerstate =((Element)element2.getElementsByTagName("cim:Switch.open").item(0)).getTextContent();
				System.out.println("breakerState: " + breakerstate +"\n" );
				
		
				String query = "insert into Breakers values(?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				 
				 preparedStmt = conn.prepareStatement(query);
				 preparedStmt.setString(1, breakername);
				 preparedStmt.setString(2, breakerrdfID);
				 preparedStmt.setString(3, breakercontainer);
				 preparedStmt.setString(4, breakerstate);
				 preparedStmt.executeUpdate();
	    
		       }
			
 
		
		   for (int i = 0; i < generatingUnit.getLength(); i++) {
		            Element element = (Element) generatingUnit.item(i);
		        	
		        	System.out.println("Generatingunit info:" + "\n");
		        	
		        	String GenUnitrdfID = element.getAttribute("rdf:ID");
		        	String GenUnitname = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
		        	String maxP = ((Element) element.getElementsByTagName("cim:GeneratingUnit.maxOperatingP").item(0)).getTextContent();
		        	String minP = ((Element) element.getElementsByTagName("cim:GeneratingUnit.minOperatingP").item(0)).getTextContent();
		        	String EquipmentContainer_rdfID = ((Element) element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0)).getAttribute("rdf:resource");
		        	
		        	System.out.println("rdfID: " + GenUnitrdfID +"\n" + "objectName: " + GenUnitname +"\n" +"MaxP: " + maxP +"\n" + "MinP: "  + minP + "\n" +"EquipmentContainer_rdfID: " + EquipmentContainer_rdfID);

		        	
		        	
		        	String query = "insert into GeneratingUnit values(?, ?, ?, ?,?)";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					 
					 preparedStmt = conn.prepareStatement(query);
					 preparedStmt.setString(1, GenUnitname);
					 preparedStmt.setString(2, GenUnitrdfID);
					 preparedStmt.setString(3, maxP);
					 preparedStmt.setString(4, minP);
					 preparedStmt.setString(5, EquipmentContainer_rdfID);
					 
					 preparedStmt.executeUpdate();
		        	
	          }
		

		for (int i = 0; i < regulatingcontrol.getLength(); i++) {
	
	            	Element element = (Element) regulatingcontrol.item(i);
	            	System.out.println("regulatingcontrol info:" + "\n");
	            	// regulatingcontrol info
	            	String regulatingcontrolrdfID = element.getAttribute("rdf:ID");
	            	String regulatingcontrolname = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	            	

	            	
	            	System.out.println("regulatingcontrolrdfID: " + regulatingcontrolrdfID +"\n" + "regulatingcontrolobjectName: " + regulatingcontrolname);
	               
	            
	           
	            	Element element2 = (Element) regulatingcontrolSSH.item(i);
	            	String targetValue = ((Element) element2.getElementsByTagName("cim:RegulatingControl.targetValue").item(0)).getTextContent();
	            	System.out.println("targetValue : " + targetValue  +"\n" );
	            	
	
	        	 
	        	 
	        	 String query = "insert into RegulatingControl values(?, ?, ?)";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					 
					 preparedStmt = conn.prepareStatement(query);
					 preparedStmt.setString(1, regulatingcontrolname);
					 preparedStmt.setString(2, regulatingcontrolrdfID);
					 preparedStmt.setString(3, targetValue);
		
					 preparedStmt.executeUpdate();	
	            	
	            }
		
		String[] Basevoltagerdf = new String[ basevolList.getLength()];
		double[] Basevoltage= new double[basevolList.getLength()] ;
		
		for (int i = 0; i < basevolList.getLength(); i++) {
		  	    
		  	  Element element = (Element) basevolList.item(i);
		  	
		   	  System.out.println("Basevoltage info:" + "\n");
		  	// basevolatge info
		  	  String basevolatgerdfID = element.getAttribute("rdf:ID");
		  	  String norminalvalue = element.getElementsByTagName("cim:BaseVoltage.nominalVoltage").item(0).getTextContent();
		   	  System.out.println("rdfID: " + basevolatgerdfID +"\n" + "norminalvalue: " + norminalvalue +"\n" );
		   	  
		   	 String query = "insert into BaseVoltage values(?, ?)";
			 PreparedStatement preparedStmt = conn.prepareStatement(query);
			 
			 preparedStmt = conn.prepareStatement(query);
			 preparedStmt.setString(1, norminalvalue);
			 preparedStmt.setString(2, basevolatgerdfID);
	
			 preparedStmt.executeUpdate();	
			 
			 
	            Basevoltagerdf[i]= basevolatgerdfID;
	            double basevoltage= Double.parseDouble(norminalvalue);
	            Basevoltage[i]=basevoltage;

		}
		
		


		for (int i = 0; i < powertranList.getLength(); i++) {
	  	        Element element = (Element) powertranList.item(i);
	  	       // powertransformer info

	  	    	System.out.println("Powertransformer info:" + "\n");
	  	    	String PowertransformerrdfID = element.getAttribute("rdf:ID");
	  	    	String Powertransformername = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	  	    	String EquipmentContainer_rdfID = ((Element) element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0)).getAttribute("rdf:resource");
	  	    	
	  	    	System.out.println("Powertransformer_rdfID: " + PowertransformerrdfID +"\n" + "PowertransformerName: " + Powertransformername +"\n" + "EquipmentContainer_rdfID: " +EquipmentContainer_rdfID+"\n");
	  	    	
	  	    	String query = "insert into PowerTransformer values(?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
					 
			    preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, Powertransformername);
				preparedStmt.setString(2, PowertransformerrdfID);
				preparedStmt.setString(3, EquipmentContainer_rdfID);
		
				preparedStmt.executeUpdate();	
				
				
	          
	    }
		
		for (int i = 0; i < energyList.getLength(); i++) {

		            Element element = (Element) energyList.item(i);
		            
		           // EnergyConsumer info

		        	System.out.println("EnergyConsumer info:" + "\n");
		        	String EnergyConsumerrdfID = element.getAttribute("rdf:ID");
		        	String EnergyConsumername = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
		        	String EquipmentContainer_rdfID = ((Element) element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0)).getAttribute("rdf:resource");
		        	
		        	System.out.println("EnergyConsumerrdfID: " + EnergyConsumerrdfID +"\n" + "EnergyConsumername: " + EnergyConsumername +"\n" + "EquipmentContainer_rdfID: " +EquipmentContainer_rdfID);
		        	

		            Element element2 = (Element) energyListSSH.item(i);
		            
		            String loadp=((Element) element2.getElementsByTagName("cim:EnergyConsumer.p").item(0)).getTextContent();
		        	String loadq=((Element) element2.getElementsByTagName("cim:EnergyConsumer.q").item(0)).getTextContent();
		        	
		        	System.out.println("LoadP: " + loadp +"\n" + "LoadQ: " + loadq +"\n" );
		            
		  	    	String query = "insert into EnergyConsumer values(?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
						 
				    preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString(1, EnergyConsumername);
					preparedStmt.setString(2, EnergyConsumerrdfID);
					preparedStmt.setString(3, loadp);
					preparedStmt.setString(4, loadq);
					preparedStmt.setString(5, EquipmentContainer_rdfID);
			
					preparedStmt.executeUpdate();	
					
					double Loadp=Double.parseDouble(loadp);
					double Loadq=Double.parseDouble(loadq);
		        	load_p[i]=Loadp;
		        	load_q[i]=Loadq;
		  
	    }
		

		String[] Transendname = new String[ transendList.getLength()];
		String[] Transbaserdf = new String[ transendList.getLength()];
		double[] Transbase = new double[ transendList.getLength()];
		for (int i = 0; i < transendList.getLength(); i++) {
			
	                Element element = (Element) transendList.item(i);
	                
	            	System.out.println("transformerEnd info:" + "\n");
	            	
	            	String rdfID = element.getAttribute("rdf:ID");
	            	String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
	            	String transformerr =((Element)element.getElementsByTagName("cim:PowerTransformerEnd.r").item(0)).getTextContent();
	            	String transformerx =((Element)element.getElementsByTagName("cim:PowerTransformerEnd.x").item(0)).getTextContent();
	            	String transformerrdf =((Element)element.getElementsByTagName("cim:PowerTransformerEnd.PowerTransformer").item(0)).getAttribute("rdf:resource");
	            	String transbasevol =((Element)element.getElementsByTagName("cim:TransformerEnd.BaseVoltage").item(0)).getAttribute("rdf:resource");
	            	String ratedS=((Element)element.getElementsByTagName("cim:PowerTransformerEnd.ratedS").item(0)).getTextContent();
	            	
	            	
	            	System.out.println("transformerEnd_rdfID: " +  rdfID + "\n"+ "transformerEndname: " + name);
	            	System.out.println("transformer r: "+transformerr + "\n"+"transformer x: "+ transformerx +"\n"+ "transformer_rdf: " +transformerrdf +"\n"+ "baseVoltage: "+ transbasevol+"\n" + "RatedS: "+ ratedS + "\n");

	            	String query = "insert into PowerTransformerEnd values(?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
						 
				    preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString(1, name);
					preparedStmt.setString(2, rdfID);
					preparedStmt.setString(3, transformerr);
					preparedStmt.setString(4, transformerx);
					preparedStmt.setString(5, transformerrdf);
					preparedStmt.setString(6, transbasevol);
			
					preparedStmt.executeUpdate();
					
					
				    double Transformerr=Double.parseDouble(transformerr);
				    double Transformerx=Double.parseDouble(transformerx);
				   
					Transformer_r[i]= Transformerr;
					Transformer_x[i]= Transformerx;
					Transformer_basevol[i]=transbasevol;
					
					Transbaserdf[i] = transbasevol ;
					Transendname[i] =	name;		
	            	
	            }
		         
		for (int i = 0; i < transendList.getLength(); i++) {

		    for (int j = 0;  j< basevolList.getLength(); j++){
             String transrdf= new String(Transbaserdf[i]);
             String baserdf= new String("#"+Basevoltagerdf[j]);
             
	         if (transrdf.equals(baserdf)){
				Transbase[i]= Basevoltage[j];
			 }
		}
		System.out.println(Transendname[i]+"\t"+Transbaserdf[i]+"\t"+Transbase[i]);
		}

		
		for (int i = 0; i < tapchangerList.getLength(); i++) {

		            Element element = (Element) tapchangerList.item(i);
		            
		        	System.out.println("Tapchanger info:" + "\n");
		        	
		        	String rdfID = element.getAttribute("rdf:ID");
		        	String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();

		        	System.out.println("Tapchanger_rdfID: " +  rdfID + "\n"+ "Tapchangername: " + name);
	
		            Element element2 = (Element) tapchangerListSSH.item(i);
		            String step=  element2.getElementsByTagName("cim:TapChanger.step").item(0).getTextContent();
		            System.out.println("Step: " +  step +"\n");
		            
		            
	            	String query = "insert into RatioTapChanger values(?, ?, ?)";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
						 
				    preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString(1, name);
					preparedStmt.setString(2, rdfID);
					preparedStmt.setString(3, step);
				
					preparedStmt.executeUpdate();	
					
		      }	
		
		for (int i = 0; i < terminalList.getLength(); i++) {

			            Element element = (Element) terminalList.item(i);	
			            
			            String rdfID =element.getAttribute("rdf:ID");
			            String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
			            String Connectivitynode_rdfID =((Element) element.getElementsByTagName ("cim:Terminal.ConnectivityNode").item(0)).getAttribute("rdf:resource");
			            
			            
			            System.out.println("Terminal_rdfID: " +  rdfID + "\n"+ "Connectivitynode_rdfID: " + Connectivitynode_rdfID + "\n"+ "Terminalnodename: " + name + "\n" );
					
		}
		
		for (int i = 0; i < ConnectivitynodeList.getLength(); i++) {

            Element element = (Element) ConnectivitynodeList.item(i);	
            
            String rdfID =element.getAttribute("rdf:ID");
            String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
         
            
            System.out.println("Connectivitynode_rdfID: " +  rdfID + "\n" + "Connectivitynodename: " + name + "\n");
		
       }
		
		
	
		for (int i = 0; i < AClineList.getLength(); i++) {

            Element element = (Element) AClineList.item(i);	
		    
            String rdfID =element.getAttribute("rdf:ID");
            String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
            
            System.out.println("ACline_rdfID: " +  rdfID +  "\n"+ "AClinename: " + name + "\n" );
    		
		}
		
		for (int i = 0; i < BusbarList.getLength(); i++) {

            Element element = (Element) BusbarList.item(i);	
		    
            String rdfID =element.getAttribute("rdf:ID");
            String name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();
            
            System.out.println("Busbar_rdfID: " +  rdfID +  "\n"+ "Busbarname: " + name + "\n" );
    		
		}
		
	
		
	 
	 System.out.println("The table is updated...");
	 conn.close();
	
	 double Sbase=100;
	 double Zbase0=Transbase[0]*Transbase[0]/Sbase;
	 double Zbase1=Transbase[1]*Transbase[1]/Sbase;
	 double Zbase2=Transbase[2]*Transbase[2]/Sbase;
	 double Zbase3=Transbase[3]*Transbase[3]/Sbase;
	 double Zbase4=Transbase[4]*Transbase[4]/Sbase;
	 double Zbase5=Transbase[5]*Transbase[5]/Sbase;
	 double t_r1=Transformer_r[0]*Zbase0/(Transformer_r[0]*Transformer_r[0]+Transformer_x[0]*Transformer_x[0]);
	 double t_x1=-Transformer_x[0]*Zbase0/(Transformer_r[0]*Transformer_r[0]+Transformer_x[0]*Transformer_x[0]);		 
	 System.out.println(t_r1+"+j"+t_x1);
	 
	 double t_r2=Transformer_r[2]*Zbase2/(Transformer_r[2]*Transformer_r[2]+Transformer_x[2]*Transformer_x[2]);
	 double t_x2=-Transformer_x[2]*Zbase2/(Transformer_r[2]*Transformer_r[2]+Transformer_x[2]*Transformer_x[2]);
	 System.out.println(t_r2+"+j"+t_x2);
	 
	 double t_r3=Transformer_r[4]*Zbase4/(Transformer_r[4]*Transformer_r[4]+Transformer_x[4]*Transformer_x[4]);
	 double t_x3=-Transformer_x[4]*Zbase4/(Transformer_r[4]*Transformer_r[4]+Transformer_x[4]*Transformer_x[4]);
	 System.out.println(t_r3+"+j"+t_x3);
	 
	 double l_r1=Line_r[0]*Zbase2/(Line_r[0]*Line_r[0]+Line_x[0]*Line_x[0]);
	 double l_x1=-Line_x[0]*Zbase2/(Line_r[0]*Line_r[0]+Line_x[0]*Line_x[0]);
	 double l_bch1=Line_bch[0]/2*Zbase2;
	 double l_gch1=Line_gch[0]/2*Zbase2;
	 System.out.println(l_r1+"+j"+l_x1);
	 System.out.println(l_bch1+"+j"+l_gch1);
	 
	 double l_r2=Line_r[1]*Zbase2/(Line_r[1]*Line_r[1]+Line_x[1]*Line_x[1]);
	 double l_x2=-Line_x[1]*Zbase2/(Line_r[1]*Line_r[1]+Line_x[1]*Line_x[1]);
	 double l_bch2=Line_bch[1]/2*Zbase2;
	 double l_gch2=Line_gch[1]/2*Zbase2;
	 System.out.println(l_r2+"+j"+l_x2);
	 System.out.println(l_bch2+"+j"+l_gch2);
	 
	 double sh_b1=Shunt_b[0]*Zbase3;
	 double sh_g1=Shunt_g[0]*Zbase3;
	 System.out.println(sh_b1+"+j"+sh_g1);
	 
	 double sh_b2=Shunt_b[1]*Zbase0;
	 double sh_g2=Shunt_g[1]*Zbase0;
	 System.out.println(sh_b2+"+j"+sh_g2);
	 
	 double load_r1=load_p[0]*Zbase4/(Transbase[4]*Transbase[4]);
     double load_x1=-load_q[0]*Zbase4/(Transbase[4]*Transbase[4]);
	 System.out.println(load_r1+"+j"+load_x1);
	 
	 double load_r2=load_p[1]*Zbase2/(Transbase[2]*Transbase[2]);
	 double load_x2=-load_q[1]*Zbase2/(Transbase[2]*Transbase[2]);
	 System.out.println(load_r2+"+j"+load_x2);
	 
	 double load_r3=load_p[2]*Zbase2/(Transbase[2]*Transbase[2]);
	 double load_x3=-load_q[2]*Zbase2/(Transbase[2]*Transbase[2]);
	 System.out.println(load_r3+"+j"+load_x3);
	 
	 
	 double a1_r=sh_b2+t_r1;
	 double a1_i=sh_g2+t_x1;
	 double a2_r=0;
	 double a2_i=0;
	 double a3_r=0;
	 double a3_i=0;
	 double a4_r=0;
	 double a4_i=0;
	 double a5_r=-t_r1;
	 double a5_i=-t_x1;
	 double b1_r=0,b1_i=0;
	 double b2_r=l_r1+l_r2+l_bch1+l_bch2+load_r3;
	 double b2_i=l_x1+l_x2+l_gch1+l_gch2+load_x3;
	 double b3_r=-(l_r1+l_r2+l_bch1+l_bch2);
	 double b3_i=-(l_x1+l_x2+l_gch1+l_gch2);
	 double b4_r=0,b4_i=0;
	 double b5_r=0,b5_i=0;
	 double c1_r=0,c1_i=0;
	 double c2_r=b3_r;
	 double c2_i=b3_i;
	 double c3_r=l_r1+l_r2+l_bch1+l_bch2+load_r2+t_r2;
	 double c3_i=l_x1+l_x2+l_gch1+l_gch2+load_x2+t_x2;
	 double c4_r=0,c4_i=0;
	 double c5_r=-t_r2;
	 double c5_i=-t_x2;
	 double d1_r=0,d1_i=0;
	 double d2_r=0,d2_i=0;
	 double d3_r=0,d3_i=0;
	 double d4_r=t_r3;
	 double d4_i=t_x3;
	 double d5_r=-d4_r;
	 double d5_i=-d4_i;
	 double e1_r=-t_r1,e1_i=-t_x1;
	 double e2_r=0,e2_i=0;
	 double e3_r=c5_r;
	 double e3_i=c5_i;
	 double e4_r=d5_r;
	 double e4_i=d5_i;
	 double e5_r=t_r1+t_r2+t_r3+load_r1+sh_b1;
	 double e5_i=t_x1+t_x2+t_x3+load_x1+sh_g1;
/*	 System.out.println("YBUS IS EQUAL TO :"+"\n"+a1_r+"+j"+a1_i+"\t"+a2+"\t"+a3+"\t"+a4+"\t"+a5_r+"+j"+a5_i+"\n"
	 +b1+"\t"+b2_r+"+j"+b2_i+"\t"+b3_r+"+j"+b3_i+"\t"+b4+"\t"+b5+"\n"
	 +c1+"\t"+c2_r+"+j"+c2_i+"\t"+c3_r+"+j"+c3_i+"\t"+c4+"\t"+c5_r+"+j"+c5_i+"\n"
	 +d1+"\t"+d2+"\t"+d3+"\t"+d4_r+"+j"+d4_i+"\t"+d5_r+"+j"+d5_i+"\n"
	 +e1+"\t"+e2+"\t"+e3_r+"+j"+e3_i+"\t"+e4_r+"+j"+e4_i+"\t"+e5_r+"+j"+e5_i);*/
	 System.out.printf("YBUS IS EQUAL TO :\n %f+j%f \t %f+j%f \t %f+j%f \t %f+j%f \t %f+j%f \n %f+j%f \t %f+j%f \t %f+j%f \t %f+j%f \t %f+j%f \n %f+j%f \t %f+j%f \t %f+j%f \t %f+j%f \t %f+j%f \n %f+j%f \t %f+j%f \t %f+j%f \t %f+j%f \t %f+j%f \n %f+j%f \t %f+j%f \t %f+j%f \t %f+j%f \t %f+j%f \n",a1_r,a1_i,a2_r,a2_i,a3_r,a3_i,a4_r,a4_i,a5_r,a5_i,b1_r,b1_i,b2_r,b2_i,b3_r,b3_i,b4_r,b4_i,b5_r,b5_i,c1_r,c1_i,c2_r,c2_i,c3_r,c3_i,c4_r,c4_i,c5_r,c5_i,d1_r,d1_i,d2_r,d2_i,d3_r,d3_i,d4_r,d4_i,d5_r,d5_i,e1_r,e1_i,e2_r,e2_i,e3_r,e3_i,e4_r,e4_i,e5_r,e5_i);

	 
	 
	 }catch(SQLException se){
		 //Handle errors for JDBC
		 se.printStackTrace();
		 }catch(Exception e){
		 //Handle errors for Class.forName
		 e.printStackTrace();}
		 System.out.println("Goodbye!");
		 
	
		
		}
         
		 
		 
		  
	 }
     
	 
