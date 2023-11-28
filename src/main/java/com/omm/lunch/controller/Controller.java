package com.omm.lunch.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.omm.lunch.dao.DaoBoard;
import com.omm.lunch.dto.Dto;
import com.omm.lunch.service.Service;

@WebServlet("/lunch/*")
public class Controller extends HttpServlet {
	String nextPage;
	DaoBoard dao;
	Service service;
	@Override
	public void init() throws ServletException{
		dao = new DaoBoard();
		service = new Service();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		System.out.println("action:"+action);
		if(action!=null) {
			switch(action) {
			
			case "/del":
				System.out.println("삭제");
				nextPage="/lunch/freeList";
				service.del(request.getParameter("no"));	
//				response.sendRedirect(request.getContextPath() + "/list.jsp"); send든 foward든 처리가 됐다고 해서 실행흐름이 바로 점프하는게 아니다. (페이지가 바뀐다고 실행흐름이 넘어간게 아니라는뜻.) 
				break;
			case "/write":
				System.out.println("쓰기");
				nextPage="/lunch/freeList";
				Dto dto = new Dto(
						request.getParameter("title"),
						request.getParameter("id"),
						request.getParameter("text")
						);
				service.write(dto);				
				break;
			case "/edit_insert"://했음
				System.out.println("수정-insert");
				nextPage="/edit.jsp";
				request.setAttribute("post", service.read(request.getParameter("no")));				
				break;	
			case "/edit_proc"://했음
				System.out.println("수정-proc");
				nextPage="/lunch/freeList";
				service.edit(
						new Dto(
								request.getParameter("title"),
								request.getParameter("text")
								)
						,request.getParameter("no")
						);				
				break;
			case "/read":
				System.out.println("읽기");
				nextPage="/read.jsp";
				Dto d=service.read(request.getParameter("no"));
				request.setAttribute("post", d);
				break;
			case "/freeList"://todo
				System.out.println("리스트");
				nextPage="/freeList.jsp";
				ArrayList<Dto> posts = service.list();
				request.setAttribute("posts", posts);
				break;
			case "/bestboard":
				System.out.println("베스트 보드");
				nextPage="/bestboard.jsp";
				String page = request.getParameter("page");
				ArrayList<Dto> bestposts = service.listBest(page);
				request.setAttribute("posts", bestposts);
			}
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request,response);
			
		}
	}
}
