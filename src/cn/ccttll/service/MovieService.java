package cn.ccttll.service;


import cn.ccttll.bean.Movie;
import cn.ccttll.bean.User;
import cn.ccttll.dao.MovieDao;

import java.util.List;

public class MovieService {
  private MovieDao movieDao;

  public MovieService(){
      movieDao=new MovieDao();
  }

    public List<Movie> getMovie(String movieType){
        return movieDao.getMovie(movieType);
    }

   public List<Movie> getMovie(String movieType,int curentPage,int sum){
    return movieDao.getMovie(movieType,curentPage,sum);
   }

    public Movie getMovieById(int movieId){
       return movieDao.getMovieById(movieId);
    }
    public List<Movie> getMovieByMovieScore(String movieType){
        return movieDao.getMovieByMovieScore(movieType);
    }

    public int countMovie(String movieType){
        return movieDao.countMovie(movieType);
    }
    public List<Movie> getMoviesByMovieName(String movieName) {
        return movieDao.getMoviesByMovieNames(movieName);
    }


}