package com.omm.lunch.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.omm.lunch.dto.BoardDto;
import com.omm.lunch.dto.RouletteDto;
import com.omm.lunch.service.Service;
import com.omm.lunch.board.BoardListProcessor;
import com.omm.lunch.dao.DaoBoard.RouletteResult;

@WebServlet("/lunch/*")
public class Controller extends HttpServlet {
	String nextPage;
	Service service;
	String category;
	@Override
	public void init() throws ServletException{
		service = new Service();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		category = request.getParameter("category");//카테고리 정의
		String action = request.getPathInfo();
		System.out.println("action:"+action);
		if(action!=null) {
			switch(action) {
			
			case "/del":
				System.out.println("삭제");
				nextPage="/lunch/freeList";
				service.del(category, request.getParameter("no"));	
//				response.sendRedirect(request.getContextPath() + "/list.jsp"); send든 foward든 처리가 됐다고 해서 실행흐름이 바로 점프하는게 아니다. (페이지가 바뀐다고 실행흐름이 넘어간게 아니라는뜻.) 
				break;
			case "/write":
				System.out.println("쓰기");
				System.out.println("/write후 category:"+category);
				nextPage="/lunch/freeList";
				BoardDto dto = new BoardDto(
						category,
						request.getParameter("title"),
						request.getParameter("id"),
						request.getParameter("text")
						);
				service.write(dto);	
				
				break;
			case "/edit_insert"://했음
				System.out.println("수정-insert");
				nextPage="/edit.jsp";
				request.setAttribute("post", service.read(category, request.getParameter("no")));				
				break;	
			case "/edit_proc"://했음
				System.out.println("수정-proc");
				nextPage="/lunch/freeList";
				service.edit(
						new BoardDto(
								request.getParameter("title"),
								request.getParameter("text")
								)
						,request.getParameter("no")
						);				
				break;
			case "/read":
				System.out.println("읽기");
				nextPage="/read.jsp";
				BoardDto d=service.read(category, request.getParameter("no"));
				request.setAttribute("post", d);
				break;
			case "/freeList"://todo
				System.out.println("리스트");
				nextPage="/freeList.jsp";
				BoardListProcessor blp = service.list(category, request.getParameter("page"),request.getParameter("word"));	//🐇서비스🐇:리스트 기능
				request.setAttribute("blp", blp);
				break;
			case "/bestboard":
				System.out.println("베스트 보드");
				nextPage="/bestboard.jsp";
				String page = request.getParameter("page");
				String orderByColumn = request.getParameter("orderByColumn");
				System.out.println("page:"+page);
				ArrayList<RouletteDto> bestposts = service.listBest(page, orderByColumn);
				request.setAttribute("posts", bestposts);
				break;
			case "/weeklyBest":
				System.out.println("베스트 보드"
						+ "");
				nextPage="/weeklyBest.jsp";
				String page2 = request.getParameter("page");
				String orderByColumn2 = request.getParameter("orderByColumn");
				System.out.println("page:"+page2);
				ArrayList<RouletteDto> weeklybestposts = service.listBest(page2, orderByColumn2);
				request.setAttribute("posts", weeklybestposts);
				break;	
			case "/monthBest":
				System.out.println("베스트 보드"
						+ "");
				nextPage="/monthBest.jsp";
				String page3 = request.getParameter("page");
				String orderByColumn3 = request.getParameter("orderByColumn");
				System.out.println("page:"+page3);
				ArrayList<RouletteDto> monthbestposts = service.listBest(page3, orderByColumn3);
				request.setAttribute("posts", monthbestposts);
				break;
			
			case "/addMenu":
				System.out.println("메뉴추가");
				nextPage="/bestboard.jsp";
				RouletteDto menu = new RouletteDto(
						request.getParameter("menu"),
						request.getParameter("category"),
						request.getParameter("total_like"),
						request.getParameter("id")
						);
				service.addMenu(menu);	
				
				break;
			case "/roulette":
				System.out.println("읽기");
				nextPage="/rouletteList.jsp";
				RouletteResult d2=service.roulette();
				request.setAttribute("post", d2);
				break;
			}
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request,response);
			
		}
	}
}
