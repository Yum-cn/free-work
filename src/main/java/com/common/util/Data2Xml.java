package com.common.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Data2Xml {

	public static void main(String[] args) throws IOException {

		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element Station = document.addElement("Station");
		Station.addComment("版本信息");
		Element Version = Station.addElement("Version");
		Version.addAttribute("VersionId", "Station201612250001");
		Version.addAttribute("VersionDate", "20161225094417");
		Version.addAttribute("VersionType", "1");
		Version.addAttribute("VersionValidate", "20161225094728");
		Version.addAttribute("VersionSystem", "99");
		Version.addAttribute("VersionOper", "000000");
		Version.addAttribute("FormatVersion", "01");

		Station.addComment("线路信息");
		Element LineBaseInfo = Station.addElement("LineBaseInfo");
		Element BasicInfo = LineBaseInfo.addElement("BasicInfo");
		BasicInfo.addAttribute("Id", "01");
		BasicInfo.addAttribute("Name", "一号线");
		Element StationList = BasicInfo.addElement("StationList");
		StationList.addAttribute("StationId", "0102");
		StationList.addAttribute("SortId", "11");
		Station.addComment("车站信息");
		Element StationInfo = Station.addElement("StationInfo");
		Element BasicInfo2 = StationInfo.addElement("BasicInfo");
		BasicInfo2.addAttribute("StationId", "0102");
		BasicInfo2.addAttribute("StationName", "二号站");
		BasicInfo2.addAttribute("StationName_En", "Dongwu Boulevard");
		BasicInfo2.addAttribute("ExchgStationId", "0");
		BasicInfo2.addAttribute("CoordinateX", "0");
		BasicInfo2.addAttribute("CoordinateY", "0");

		Data2Xml data2Xml = new Data2Xml();
		// String asXML = document.asXML();
		System.out.println(data2Xml.prettysString(document));

		File file = new File("D:/test.xml");
		data2Xml.writeFile4Pretty(file, document);
	}

	/**
	 * 对xml格式化并写入文件
	 * 
	 * @param file
	 *            文件
	 * @param documentXML
	 *            写入的内容
	 * @throws IOException
	 */
	protected void writeFile4Pretty(File file, Document document) throws IOException {

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(document.getXMLEncoding());
		XMLWriter writer = new XMLWriter(new FileWriter(file), format);
		writer.write(document);
		writer.flush();
		writer.close();
	}

	/***
	 * 格式化xml为string
	 * 
	 * @param document
	 * @return
	 * @throws IOException
	 */
	protected String prettysString(Document document) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(document.getXMLEncoding());
		StringWriter stringWriter = new StringWriter();
		XMLWriter writer = new XMLWriter(stringWriter, format);
		writer.write(document);
		writer.close();
		return stringWriter.toString();
	}

}