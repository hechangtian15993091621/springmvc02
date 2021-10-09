package cn.buaa.controller;

import cn.buaa.beans.User;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hct
 * @Slogan 我的代码永远 0 错误
 * @date 2021/4/28
 **/
@Controller
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "/login.jsp";
    }

    @PostMapping("/login")
    public String login(HttpSession httpSession) {
        httpSession.setAttribute("username", "hct");
        return "/admin.jsp";
    }

    @ResponseBody
    @RequestMapping("/interceptor")
    public String interceptor(String name) {
        System.out.println(name);
        System.out.println("方法正在执行中...");
        return "123";
    }

    @RequestMapping("/test")
    public String test(String username, String psw) {
        System.out.println(username);
        System.out.println(psw);
        return "/index.jsp";
    }

//    @ResponseBody
//    @RequestMapping(value = "geth")
//    public String test(HttpSession httpSession) {
//        return "geth";
//    }

    @ResponseBody
    @RequestMapping(value = "geth?")
    public String test01(HttpSession httpSession) {
        return "get?";
    }

    @ResponseBody
    @RequestMapping(value = "geth*")
    public String test001(HttpSession httpSession) {
        return "geth*";
    }

    @PostMapping("/hello")
    public String hello(@Valid User user, BindingResult bindingResult, Model model) {
        System.out.println(user);
        Map<String, String> error = new HashMap();
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError f : fieldErrors) {
                error.put(f.getField(), f.getDefaultMessage());
            }
            model.addAttribute("errors", error);
            return "/a.jsp";
        }

        return "/index.jsp";
    }

    @PostMapping("/json")
    @ResponseBody
    public User json(@RequestBody String name) {
        System.out.println(name);
        User user = new User();
        user.setId(1);
        user.setName("hct");
        return user;
    }

    @PostMapping("/json01")
    @ResponseBody
    public User json01(@RequestBody User user2) {
        System.out.println(user2);
        return user2;
    }

    @PostMapping("/json11")
    @ResponseBody
    public Integer json11(@RequestBody Integer id) {
        System.out.println(id);
        return id;
    }

    @PostMapping("/json02")
    @ResponseBody
    public User json02(@RequestBody List<User> list) {
        System.out.println(list);
        return list.get(1);
    }

    @GetMapping("/download1")
    public String download1(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取要下载文件的绝对路径
        String realPath = req.getServletContext().getRealPath("/file/hct.jpg");
        // 根据文件路径封装成一个File对象
        File file = new File(realPath);
        // 获取文件的文件名称
        String name = file.getName();
        // 设置相应头  content-disposition 设置文件下载的打开方式，默认是在浏览器上打开
        // 设置attachment;filename=xxx  就是为了设置之后以下载方式打开文件
        // URLEncoder.encode(filename,"UTF-8") 文件名如果有中文，就不会乱码
        resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        // 根据文件路径获取文件的输入流
        InputStream io = new FileInputStream(realPath);
        int len;
        // 根据相应对象获取输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        // 定义一个1KB的缓冲区
        byte[] buff = new byte[1024];

        // 循环将从输入流读取到的字节放入buff当中,如果len是-1,表示文件流中字节读取完毕
        while ((len = io.read(buff)) > 0) {
            // 然后使用响应对象的输出流将buff中的字节相应给浏览器
            outputStream.write(buff, 0, len);
        }
        // 关闭文件输入流
        io.close();
        return null;
    }

    @RequestMapping("/load")
    public String load(String desc, MultipartFile[] myFile) throws IOException {
        long start = System.currentTimeMillis();
        for (MultipartFile multipartFile : myFile) {
            System.out.println(multipartFile.getOriginalFilename());
            String tmp = "E:/upload/" + multipartFile.getOriginalFilename();
            File file = new File(tmp);
            multipartFile.transferTo(file);
        }
        long end = System.currentTimeMillis();
        System.out.println("单线程文件上传" + (end - start));
        return "/success.jsp";
    }


    /**
     * 多线程上传文件
     *
     * @param desc
     * @param myFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/load01")
    public String load01(String desc, MultipartFile[] myFile) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        for (MultipartFile multipartFile : myFile) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    String tmp = "E:/upload/" + multipartFile.getOriginalFilename();
                    File file = new File(tmp);
                    try {
                        multipartFile.transferTo(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("多线程文件上传" + (end - start));
        return "/success.jsp";
    }

//    @RequestMapping("/load")
//    public String load(String desc, List<MultipartFile> myFile) throws IOException {
//        System.out.println(desc);
//        for (MultipartFile multipartFile : myFile) {
//            System.out.println(multipartFile.getOriginalFilename());
//            String tmp = "E:/upload/" + multipartFile.getOriginalFilename();
//            File file = new File(tmp);
//            multipartFile.transferTo(file);
//        }
//        return "/success.jsp";
//    }
}
