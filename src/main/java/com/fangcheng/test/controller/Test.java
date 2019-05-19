package com.fangcheng.test.controller;

import com.fangcheng.test.entity.Application;

import com.fangcheng.test.service.ApplicationService;
import com.fangcheng.test.service.AreasService;
import com.fangcheng.test.service.FileService;
import com.fangcheng.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fangcheng.test.entity.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @ProjectName: _222
 * @Package: com.fc.test
 * @ClassName: Test
 * @Description: java类作用描述
 * @Author: FangCheng
 * @CreateDate: 2019/3/24 14:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/3/24 14:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
@RequestMapping("/test")
public class Test {
    @Autowired
    UserService userService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    AreasService areasService;
    @Autowired
    FileService fileService;
//    public static Test test;
    public static void main(String[] args) {
        Application application = new Application();
        Test test = new Test();
    }

    @RequestMapping(value = { "/testEntity" }, method = RequestMethod.POST)
    public String testEntity(@Valid User application, BindingResult result,
                           ModelMap model,HttpServletResponse response) {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //调用业务层处理
        return "registrationsuccess";
    }

    @RequestMapping(value="/fixLeader1", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String fixLeader(@Valid User user) {
        return "OK";
    }
    @RequestMapping(value = { "/fixLeader" }, method = RequestMethod.POST)
    public void name(@Valid ModelMap modelMap,
                          @RequestParam String id,
                          HttpServletRequest request, HttpServletResponse response) {
    }
    //得到当前用户所有菜单
    @RequestMapping("/treeMenu")
    @ResponseBody
    public List<User> treeMenu(){
        return  userService.findAllUsers();
    }

    @RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> upload(@RequestParam("file") MultipartFile multipartFile,HttpServletRequest request) {
       if(!multipartFile.isEmpty()){
           String contextPath = request.getContextPath();//"/SpringMvcFileUpload"
           String servletPath = request.getServletPath();//"/gotoAction"
           String scheme = request.getScheme();//"http"C:\Users\FangCheng\Downloads\SpringMVCHibernateWithSpringSecurityExample\_222\src\main\webapp\static\data
           String storePath= "C:\\Users\\FangCheng\\Downloads\\SpringMVCHibernateWithSpringSecurityExample\\_222\\src\\main\\webapp\\static"+getSchoolYear()+getUserCollege()+getPrincipal();//存放我们上传的文件路径
//           String fileName = multipartFile.getOriginalFilename();
           String fileName = getPrincipal()+getSchoolYear()+".zip";
           File filepath = new File(storePath, fileName);
           if (!filepath.getParentFile().exists()) {
               filepath.getParentFile().mkdirs();//如果目录不存在，创建目录
           }
           try {
               multipartFile.transferTo(new File(storePath+File.separator+fileName));//把文件写入目标文件地址
           } catch (Exception e) {

               e.printStackTrace();
               return null;

           }
       }
        return null;
    }
    @RequestMapping(value = "/exportVehicleInfo",method = RequestMethod.POST)
    public void exportVehicleInfo(HttpServletRequest req, HttpServletResponse resp) {
        String filename = req.getParameter("filename");
        DataInputStream in = null;
        OutputStream out = null;
        try{
            resp.reset();// 清空输出流

            String resultFileName = filename + System.currentTimeMillis() + ".xls";
            resultFileName = URLEncoder.encode(resultFileName,"UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Content-disposition", "attachment; filename=" + resultFileName);// 设定输出文件头
            resp.setContentType("application/octet-stream");// 定义输出类型
            //输入流：本地文件路径
            in = new DataInputStream(
                    new FileInputStream(new File(/*downloadPath + */"test.zip")));
            //输出流
            out = resp.getOutputStream();
            //输出文件
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
        } catch(Exception e){
            e.printStackTrace();
            resp.reset();
            try {
                OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream(), "UTF-8");
                String data = "<script language='javascript'>alert(\"\\u64cd\\u4f5c\\u5f02\\u5e38\\uff01\");</script>";
                writer.write(data);
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally {
            if(null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @RequestMapping(value = "/userPicUpload",method = RequestMethod.POST)
    @ResponseBody
    public void userPicUpload(@RequestParam("file") MultipartFile file1){
/*        boolean isMultipart = ServletFileUpload.isMultipartContent(request);*/
/*        if(isMultipart){
            ServletFileUpload upload = new ServletFileUpload();
            upload.setHeaderEncoding("UTF-8");*/

            int i = 0;
/*            try {
                FileItemIterator iter = upload.getItemIterator(request);
                while(iter.hasNext()){
                    i++;
                    FileItemStream fi = iter.next();
                    InputStream in = null;
                    OutputStream fileout = null;
                    try {
                        String fileName = fi.getName();
                        File file = new File("f:/upload_test/"+ fileName.substring(0, fileName.lastIndexOf(".")) + "_" + i + fileName.substring(fileName.lastIndexOf(".")));
                        in = fi.openStream();
                        ByteArrayOutputStream bstream = new ByteArrayOutputStream();
                        Streams.copy(in, bstream, true);
                        fileout = new FileOutputStream(file);
                        bstream.writeTo(fileout);
                    } catch (IOException e) {
                        throw new RuntimeException("file copy error!",e);
                    }finally{
                        if(in != null){
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(fileout != null){
                            try {
                                fileout.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException("upload file error.",e);
            }*/
//        }
    }
    private String getPrincipal(){
        String userId = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userId = ((UserDetails)principal).getUsername();
        } else {
            userId = principal.toString();
        }
        return userId;
    }
    private String getUserCollege(){
        User user = userService.findByUserId(getPrincipal());
        String userCollege = user.getUserCollege();
        return userCollege;
    }
    private String getUserName(){
        String userId = getPrincipal();
        User user = userService.findByUserId(userId);
        return user.getUserName();
    }
    private String getSchoolYear(){
        String schoolYear = "";
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        if(8<=month){
            schoolYear =  year + "-" +(year+1);
        }
        if(month<=6){
            schoolYear = (year-1) + "-" + year;
        }
        return schoolYear;
    }
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void exportVehicleInfo(HttpServletRequest req, HttpServletResponse resp,String applicationNumber) {
        String filename = req.getParameter("filename");
        //从数据库获取文件存储的地址
        com.fangcheng.test.entity.File file = fileService.findById("");
        DataInputStream in = null;
        OutputStream out = null;
        try{
            resp.reset();// 清空输出流
            String resultFileName = filename + System.currentTimeMillis() + ".zip";
            resultFileName = URLEncoder.encode(resultFileName,"UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Content-disposition", "attachment; filename=" + resultFileName);// 设定输出文件头
            resp.setContentType("application/msexcel");// 定义输出类型
            //输入流：本地文件路径
            in = new DataInputStream(
                    new FileInputStream(new File(/*downloadPath*/  "test.xls")));
            //输出流
            out = resp.getOutputStream();
            //输出文件
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
        } catch(Exception e){
            e.printStackTrace();
            resp.reset();
            try {
                OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream(), "UTF-8");
                String data = "<script language='javascript'>alert(\"\\u64cd\\u4f5c\\u5f02\\u5e38\\uff01\");</script>";
                writer.write(data);
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally {
            if(null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
