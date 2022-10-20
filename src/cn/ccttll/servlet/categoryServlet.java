package cn.ccttll.servlet;

import cn.ccttll.bean.Movie;
import cn.ccttll.service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class categoryServlet extends HttpServlet {
    private MovieService movieService;

    @Override
    public void init() throws ServletException {
        super.init();
        movieService=new MovieService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String movieType=req.getParameter("movieType");
        String pageStr = req.getParameter("page");
        int page=1;
        if (null != pageStr && (!"".equals(pageStr))) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        List<Movie> categoryMovies =movieService.getMovie(movieType,page,15);
        int count=movieService.countMovie(movieType);
        int last=count%15==0?(count/15):((count/15)+1);


        req.setAttribute("last",last);
        req.setAttribute("page",page);
        req.setAttribute("movieType",movieType);
        req.setAttribute("categoryMovies", categoryMovies);
        List<Movie> scoreMovies=movieService.getMovieByMovieScore(movieType);
        req.setAttribute("scoreMovies",scoreMovies);
        req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/category.jsp").forward(req, resp);



    }



}
