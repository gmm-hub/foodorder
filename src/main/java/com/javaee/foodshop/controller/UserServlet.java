package com.javaee.foodshop.controller;

import com.javaee.foodshop.entity.User;
import com.javaee.foodshop.service.UserService;
import com.javaee.foodshop.utils.CommonUtils;
import com.javaee.foodshop.utils.MD5Utils;
import com.javaee.foodshop.utils.MailUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    private UserService service = new UserService();

    //注册
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得页面表单中的数据
        Map<String, String[]> map = request.getParameterMap();

        //封装User对象
        User user = new User();

        //时间转换器：将String-->Date
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object o) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse(o.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date;
            }
        }, Date.class);

        try {
            //给user对象依次赋值属性
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //uid 用户编号
        String uid = CommonUtils.getUUID().replaceAll("-","");
        user.setId(uid);

        //code  激活码
        String code  = CommonUtils.getUUID().replaceAll("-","");
        user.setCode(code);

        //将密码加密存储
        user.setPassword(MD5Utils.md5(user.getPassword()));

        //执行service中的注册逻辑
        boolean flag = service.regist(user);
        if(flag){
            //注册成功，跳转到registSuccess.jsp
            //发送邮件（包含激活码）
            String emailMsg = "恭喜您注册成功，请点击激活" +
                    "<a style='color:red' href='http://localhost:8080/user?method=active&code="+code+"'>"+code+"</a>";
            try {
                MailUtils.sendMail(user.getEmail(),"邮件激活",emailMsg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("registSuccess.jsp").forward(request,response);
        }else{
            //注册失败，跳转到registFail.jsp
            request.getRequestDispatcher("registFail.jsp").forward(request,response);
        }

    }


    //账户激活
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据激活码code，修改状态state
        String code = request.getParameter("code");

        //调用service进行修改状态
        boolean flag = service.active(code);
        if(flag){
            //激活成功
            response.sendRedirect("index.jsp");
        }else{
            //激活失败
            response.sendRedirect("error.jsp");
        }
    }

    //校验用户名是否存在
    public void validateName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        boolean flag = service.validateName(username);

        String json = "{\"flag\":"+flag+"}";
        response.getWriter().write(json);
    }
    //用户登录

}
