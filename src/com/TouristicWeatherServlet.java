package com;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.json.simple.JSONObject;

@WebServlet("/TouristicWeatherServlet.do")
public class TouristicWeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TouristicWeatherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String addr = "http://apis.data.go.kr/1360000/TourStnInfoService/getTourStnVilageFcst?ServiceKey=";
		String serviceKey = "%2BzO0IbOjOIGGfuDhxI%2F7rvUOcT17c5QgRZHV%2F2SRutl4T%2FHHzN9PAxouZDyxykk6xUN381k97UZq55LaBDQz6A%3D%3D";
		String parameter = "";
		
		//serviceKey = URLEncoder.encode(serviceKey,"UTF-8");
		parameter = parameter + "&" + "numOfRows=10";
		parameter = parameter + "&" + "pageNo=1";
		parameter = parameter + "&" + "dataType=JSON";
		parameter = parameter + "&" + "CURRENT_DATE=20211120";
		parameter = parameter + "&" + "HOUR=24";
		parameter = parameter + "&" + "COURSE_ID=1";
		parameter = parameter + "&" + "_type=json";
		
		addr = addr + serviceKey + parameter;
		
		URL url = new URL(addr);
		
		InputStream in = url.openStream();
		CachedOutputStream  bos = new CachedOutputStream();
		IOUtils.copy(in, bos);
		in.close();
		bos.close();
		
		String data = bos.getOut().toString();
		
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		json.put("data", data);
		System.out.println("json: "+json);
		out.print(json);
	}

}
