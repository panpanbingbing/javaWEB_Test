package com.Servlet;

import com.Mysql_Util.MysqlUtil_Test;
import com.alibaba.fastjson.JSON;
import com.baen.Code;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/code/shen","/code/shi","/code/qu"})
public class CodeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/code/shen")){
            odshen(request, response);
        }else if (path.equals("/code/shi")){
            odshi(request, response);
        }else if (path.equals("/code/qu")){
            odqu(request, response);
        }
    }

    private void odqu(HttpServletRequest request, HttpServletResponse response) {

    }

    private void odshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/http;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String t_code=request.getParameter("code");
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        MysqlUtil_Test myu=new MysqlUtil_Test();
        List<Code> codes=new ArrayList<Code>();

        try {

            conn=myu.deit();
            String sql="select code,name from t_code where pcode =?;";

            ps= conn.prepareCall(sql);
            ps.setString(1,t_code);
            rs= ps.executeQuery();
            while (rs.next()){
                String code= rs.getString("code");
                String name= rs.getString("name");
                codes.add(new Code(code,name));
            }
            out.println(JSON.toJSONString(codes));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            myu.close(conn,ps,rs);
        }
    }

    private void odshen(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/http;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        MysqlUtil_Test myu=new MysqlUtil_Test();
        List<Code> codes=new ArrayList<Code>();

        try {

            conn=myu.deit();
            String sql="select code,name from t_code where pcode is null;";
            ps= conn.prepareCall(sql);
            rs= ps.executeQuery();
            while (rs.next()){
                String code= rs.getString("code");
                String name= rs.getString("name");
                codes.add(new Code(code,name));
            }
            out.println(JSON.toJSONString(codes));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            myu.close(conn,ps,rs);
        }
    }
}
