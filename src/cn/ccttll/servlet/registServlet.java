package cn.ccttll.servlet;
import cn.ccttll.bean.User;
import cn.ccttll.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class registServlet extends HttpServlet {

    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        userService=new UserService();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String sex=req.getParameter("sex");

        String usernameRegex = "[a-zA-Z]{6,12}";
        boolean flag1 = username.matches(usernameRegex);
        String passRegex = "[0-9]{6,}";
        boolean flag2 = password.matches(passRegex);
        String phoneRegex ="[1][3578][0-9]{9}";
        boolean flag3 = phone.matches(phoneRegex);
        String emialRegex ="[a-zA-Z_0-9]{3,}@([a-zA-Z]+|\\d+)(\\.[a-zA-Z]+)+";
        boolean flag4 = email.matches(emialRegex);
        if(!(flag1&&flag2&&flag3&&flag4)){
            String msg="请按要求的格式输入";
            req.setAttribute("msg",msg);
            req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/register").forward(req,resp);
        }

        User user=new User(username, password,phone,email,sex,null);

        if(userService.insertUser(user)){
            req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/login.jsp").forward(req, resp);
        }
        else{
            req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/regist.jsp").forward(req, resp);
        }
    }

}
