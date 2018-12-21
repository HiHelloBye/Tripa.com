package service.admin;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

import dao.Accom;
import dao.Accom_info;
import dao.Aphoto;
import dao.adminAccomDao;
import service.CommandProcess;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class adminAccomWriteProAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 파일이 저장될 서버의 경로. 되도록이면 getRealPath를 이용하자.
		// String savePath = "D:/Projects/workspace/projectName/WebContent/folderName";
		String savePath = "C:/jspSrc/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/J20180902/img2";
		 
		// 파일 크기 15MB로 제한
		int sizeLimit = 1024*1024*15;
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		try {
	        System.out.println("adminAccomWriteProAction Start ....");
	       
	        
	        Accom accom = new Accom();
	        Aphoto aphoto = new Aphoto();
	        Accom_info info = new Accom_info();
	      

	        // 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
	        String fileName = multi.getFilesystemName("accom_photo");
	        String fileName2 = multi.getFilesystemName("accom_photo2");
	        String fileName3 = multi.getFilesystemName("accom_photo3");
	        String fileName4 = multi.getFilesystemName("accom_photo4");
	        
	        // 업로드한 파일의 전체 경로를 DB에 저장하기 위함
	        String m_fileFullPath = "../img2/" + fileName;
	        String m_fileFullPath2 = "../img2/" + fileName2;
	        String m_fileFullPath3 = "../img2/" + fileName3;
	        String m_fileFullPath4 = "../img2/" + fileName4;
	        System.out.println("m_fileFullPath->"+m_fileFullPath);
	        

	        System.out.println("adminAccomWriteProAction accom_name->"+multi.getParameter("accom_name"));
	        accom.setAccom_name(multi.getParameter("accom_name"));
	        System.out.println("adminAccomWriteProAction accom_type->"+multi.getParameter("accom_type"));
			accom.setAccom_type(multi.getParameter("accom_type"));
			System.out.println("adminAccomWriteProAction accom_addr->"+multi.getParameter("accom_addr"));
	        accom.setAccom_addr(multi.getParameter("accom_addr"));
	        System.out.println("adminAccomWriteProAction accom_func->"+multi.getParameter("accom_func"));
			accom.setAccom_func(multi.getParameter("accom_func"));
			System.out.println("adminAccomWriteProAction accom_map->"+multi.getParameter("accom_map"));
	        accom.setAccom_map(multi.getParameter("accom_map"));
	        //사진첨부
	        System.out.println("adminAccomWriteProAction accom_photo->"+m_fileFullPath);
			accom.setAccom_photo(m_fileFullPath);
			//DB: 사진 테이블에 저장
			System.out.println("adminAccomWriteProAction aphoto_photo->"+m_fileFullPath);
			aphoto.setPhoto(m_fileFullPath);
			System.out.println("adminAccomWriteProAction aphoto_photo2->"+m_fileFullPath2);
			aphoto.setPhoto2(m_fileFullPath2);
			System.out.println("adminAccomWriteProAction aphoto_photo3->"+m_fileFullPath3);
			aphoto.setPhoto3(m_fileFullPath3);
			System.out.println("adminAccomWriteProAction aphoto_photo4->"+m_fileFullPath4);
			aphoto.setPhoto4(m_fileFullPath4);
			
			
			
	        System.out.println("adminAccomWriteProAction accom_cont->"+multi.getParameter("accom_cont"));
			accom.setAccom_cont(multi.getParameter("accom_cont"));
			
			//방 정보
			System.out.println("adminAccomWriteProAction accom_info_price->"+multi.getParameter("price"));
			info.setPrice(Integer.parseInt(multi.getParameter("price")));
			System.out.println("adminAccomWriteProAction accom_info_num_people->"+multi.getParameter("num_people"));
			info.setNum_people(Integer.parseInt(multi.getParameter("num_people")));
			
			System.out.println("adminAccomWriteProAction accom_info_price2->"+multi.getParameter("price2"));
			info.setPrice2(Integer.parseInt(multi.getParameter("price2")));
			System.out.println("adminAccomWriteProAction accom_info_num_people2->"+multi.getParameter("num_people2"));
			info.setNum_people2(Integer.parseInt(multi.getParameter("num_people2")));
			
			System.out.println("adminAccomWriteProAction accom_info_price3->"+multi.getParameter("price3"));
			info.setPrice3(Integer.parseInt(multi.getParameter("price3")));
			System.out.println("adminAccomWriteProAction accom_info_num_people3->"+multi.getParameter("num_people3"));
			info.setNum_people3(Integer.parseInt(multi.getParameter("num_people3")));
			
			
			System.out.println("adminAccomWriteProAction inout->"+multi.getParameter("inout"));
			accom.setInout(multi.getParameter("inout"));
			System.out.println("adminAccomWriteProAction area->"+multi.getParameter("area"));
			accom.setArea(multi.getParameter("area"));
		   
		    
	        adminAccomDao adPro = adminAccomDao.getInstance();//DB 
	        int result = adPro.insert(accom);
	        adPro.insertAphoto(aphoto);
	        adPro.insertAccom_info(info);
	        
	        System.out.println("result->"+result);
	       
	        request.setAttribute("result", result);
	        
		} catch(Exception e) { System.out.println(e.getMessage()); }
        return "adminAccomWritePro.jsp";
	}

}
