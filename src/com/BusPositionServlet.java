package com;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.helpers.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/BusPositionServlet.do")
public class BusPositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BusPositionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		String addr = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?serviceKey=";
		String serviceKey = "%2BzO0IbOjOIGGfuDhxI%2F7rvUOcT17c5QgRZHV%2F2SRutl4T%2FHHzN9PAxouZDyxykk6xUN381k97UZq55LaBDQz6A%3D%3D";
		String parameter = "";
		
		//serviceKey = URLEncoder.encode(serviceKey,"UTF-8");
		
		parameter = parameter + "&" + "busRouteId=100100118";
		parameter = parameter + "&" + "startOrd=1";
		parameter = parameter + "&" + "endOrd=10";
		parameter = parameter + "&" + "resultType=json";
		parameter = parameter + "&" + "_type=json";
		
		addr = addr + serviceKey + parameter;
		
		URL url = new URL(addr);
		System.out.println(addr);
		
		InputStream in = url.openStream();
		//CachedOutputStream  bos = new CachedOutputStream();
		ByteArrayOutputStream  bos = new ByteArrayOutputStream();
		IOUtils.copy(in, bos);
		in.close();
		bos.close();
		
		//String data = bos.getOut().toString();
		String data = bos.toString("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		byte[] b = data.getBytes("UTF-8");
		String s = new String(b, "UTF-8");
		try {
			JSONParser jsonParse = new JSONParser();
			JSONObject json = (JSONObject)jsonParse.parse(s);
			System.out.println(json);
			System.out.println(json.get("msgBody"));
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
