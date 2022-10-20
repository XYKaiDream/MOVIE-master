package cn.ccttll.dao;

import cn.ccttll.bean.Movie;
import cn.ccttll.common.ConnectionUtil;
import cn.ccttll.common.ConnectionUtil2;
import cn.ccttll.common.SubCEStrUtils;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    public List<Movie> getMovie(String movieType){
        List<Movie> movies=new ArrayList<Movie>();
        try {
            conn= ConnectionUtil2.getConnection();
            String sql="SELECT * FROM movie WHERE mtype LIKE ?  LIMIT ?,?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,'%'+movieType+'%');
            pstmt.setInt(2,0);
            pstmt.setInt(3,12);
            rs=pstmt.executeQuery();
            while (rs.next()){
                String movieName=rs.getString("mname");
                int i=movieName.length();
                if(i>11){
                    try {
                        movieName= SubCEStrUtils.splitChineseEnglish(3,movieName,33);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                movies.add(new Movie(
                        rs.getInt("mid"),
                        movieName,
                        rs.getString("mimgurl")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SELECT  DATABASE  Failure");
            movies=null;
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs,pstmt,conn);
        }
        return movies;
    }


    public List<Movie> getMovie(String movieType,int page,int pageSize){
        List<Movie> movies=new ArrayList<Movie>();
        try {
            conn= ConnectionUtil2.getConnection();
            String sql="SELECT * FROM movie WHERE mtype LIKE ? ORDER BY mid DESC LIMIT ?,?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,'%'+movieType+'%');
            pstmt.setInt(2,(page-1)*pageSize);
            pstmt.setInt(3,pageSize);
            rs=pstmt.executeQuery();
            while (rs.next()){

                String movieName=rs.getString("mname");
                int i=movieName.length();
                if(i>8){

                    try {
                        movieName= SubCEStrUtils.splitChineseEnglish(3,movieName,21)+"...";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                    movies.add(new Movie(
                            rs.getInt("mid"),
                            movieName,
                            rs.getString("mimgurl"),
                            rs.getString("mscore"),
                            rs.getString("mdirector"),
                            rs.getString("mstar"),
                            rs.getString("mtype"),
                            rs.getString("marea"),
                            rs.getString("myear"),
                            rs.getString("msumary"),
                            rs.getString("mplayurl")
                    ));
                }
        } catch (SQLException e) {
            System.out.println("SELECT  DATABASE  Failure");
            movies=null;
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs,pstmt,conn);
        }
        return movies;
    }

    public int countMovie(String movieType){
        try {
            conn= ConnectionUtil2.getConnection();
            String sql="SELECT COUNT(*) total FROM movie WHERE mtype LIKE ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,'%'+movieType+'%');
            rs=pstmt.executeQuery();
            boolean flag=rs.next();
            while (flag) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.release(rs, pstmt, conn);
        }
        return 0;

    }

    public Movie getMovieById(int movieId){
        Movie movie=null;
        try {
            conn= ConnectionUtil2.getConnection();
            String sql="SELECT * FROM movie WHERE mid =?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,movieId);
            rs=pstmt.executeQuery();
            while (rs.next()){
                movie=new Movie(
                        rs.getInt("mid"),
                        rs.getString("mname"),
                        rs.getString("mimgurl"),
                        rs.getString("mscore"),
                        rs.getString("mdirector"),
                        rs.getString("mstar"),
                        rs.getString("mtype"),
                        rs.getString("marea"),
                        rs.getString("myear"),
                        rs.getString("msumary"),
                        rs.getString("mplayurl")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.release(rs,pstmt,conn);
        }
        return movie;
    }

    public List<Movie> getMovieByMovieScore(String movieType){
        List<Movie> movies=new ArrayList<Movie>();
        try {
            conn= ConnectionUtil2.getConnection();
            String sql="SELECT * FROM movie WHERE mtype LIKE ? ORDER BY mscore ASC LIMIT ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,'%'+movieType+'%');
            pstmt.setInt(2,10);
            rs=pstmt.executeQuery();
            while (rs.next()){
                String movieName=rs.getString("mname");
                int i=movieName.length();
                if(i>10){
                    try {
                        movieName= SubCEStrUtils.splitChineseEnglish(3,movieName,30);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                movies.add(new Movie(
                        rs.getInt("mid"),
                        movieName,
                        rs.getString("mimgurl"),
                        rs.getString("mscore"),
                        rs.getString("mdirector"),
                        rs.getString("mstar"),
                        rs.getString("mtype"),
                        rs.getString("marea"),
                        rs.getString("myear"),
                        rs.getString("msumary"),
                        rs.getString("mplayurl")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SELECT  DATABASE  Failure");
            movies=null;
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs,pstmt,conn);
        }
        return movies;
    }

    public List<Movie> getMoviesByMovieNames(String movieName) {
        List<Movie> movies=new ArrayList<Movie>();
        try {
            conn= ConnectionUtil2.getConnection();
            String sql="SELECT * FROM movie WHERE mname LIKE ?  ";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,'%'+movieName+'%');
            rs=pstmt.executeQuery();
            while (rs.next()){
                movies.add(new Movie(
                        rs.getInt("mid"),
                        rs.getString("mname"),
                        rs.getString("mimgurl"),
                        rs.getString("mscore"),
                        rs.getString("mdirector"),
                        rs.getString("mstar"),
                        rs.getString("mtype"),
                        rs.getString("marea"),
                        rs.getString("myear"),
                        rs.getString("msumary"),
                        rs.getString("mplayurl")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SELECT  DATABASE  Failure");
            movies=null;
            e.printStackTrace();
        } finally {
            ConnectionUtil.release(rs,pstmt,conn);
        }
        return movies;
    }
}
