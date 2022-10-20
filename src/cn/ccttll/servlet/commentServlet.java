package cn.ccttll.servlet;

import cn.ccttll.bean.Comment;
import cn.ccttll.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class commentServlet extends HttpServlet {
    private CommentService commentService;

    @Override
    public void init() throws ServletException {
        commentService=new CommentService();
    }
    @Override

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session值，已登录则获取数据插入数据库，否则跳转登录
        HttpSession session = req.getSession();
        String userName=(String)session.getAttribute("userName");
        if(null != userName && (!"".equals(userName))){
            int movieId=Integer.parseInt(req.getParameter("movieId"));
            String comcontent=req.getParameter("input-comment");
            LocalDateTime ldt = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String commentDataTime = ldt.format(dtf);
            Comment comment=new Comment(movieId,userName,comcontent,commentDataTime,null);
            commentService.subComment(comment);

           req.getRequestDispatcher(req.getContextPath()+"/home.do").forward(req, resp);
        }else {
            req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/login.jsp").forward(req, resp);
        }


    }


}
