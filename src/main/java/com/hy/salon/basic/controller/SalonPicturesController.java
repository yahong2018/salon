package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.PicturesDAO;
import com.hy.salon.basic.entity.Pictures;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@Api(value = "SalonPicturesController|美容院照片控制器")
@RequestMapping("/hy/basic/SalonPictures")
public class SalonPicturesController extends SimpleCRUDController<Pictures> {

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    Long rid=null;


    @Override
    protected BaseDAOWithEntity<Pictures> getCrudDao() {
        return picturesDao;
    }



    @ResponseBody
    @RequestMapping(value = "/uploadServicePic", method = RequestMethod.POST)
    public Result uploadServicePic(MultipartFile file, HttpServletRequest request, Pictures condition) throws IllegalStateException, IOException {
        Result result = new Result();
        System.out.println("新增图片");
        if (file != null) {      // 判断上传的文件是否为空
            String path = null;// 文件路径
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:" + fileName);
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {// 判断文件类型是否为空
                if ( "BMP".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase()) || "JPEG".equals(type.toUpperCase() )) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    //path=realPath+/*System.getProperty("file.separator")+*/trueFileName;
                    UUID uuid = UUID.randomUUID();
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                    System.out.println(df.format(new Date()));

                    String dir="C:/picture/pic/"+df.format(new Date());
//                    String dir = request.getServletContext().getRealPath("/pic/"+df.format(new Date()));
                    java.io.File folder = new java.io.File(dir);
                    if (!folder.exists()) {
                        folder.mkdirs();     ///如果不存在，创建目录
                    }
                    path = dir+"/"+uuid+"."+type;
                    System.out.println("地址"+path);

                    condition.setPicUrl("/pic/"+df.format(new Date())+"/"+uuid+"."+type);
                    file.transferTo(new File(path));

                    System.out.println("文件成功上传到指定目录下"+condition.getRecordId());
                } else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    return null;
                }
            } else {
                System.out.println("文件类型为空");
                result.setSuccess(false);
                result.setMsgcode(StatusUtil.ERROR);
                result.setMsg("文件类型为空");
                return result;
            }
        } else {
            System.out.println("没有找到相对应的文件");
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("没有找到相对应的文件");
            return null;
        }



        picturesDao.insert(condition);
        JSONObject jsonObj=new JSONObject();
        jsonObj.put("picId",condition.getRecordId());
        System.out.println("文件成功上传到指定目录下"+condition.getRecordId());
        jsonObj.put("picUrl",condition.getPicUrl());



        result.setData(jsonObj);
        result.setSuccess(true);
        result.setMsgcode(StatusUtil.OK);
        result.setMsg("上传成功");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadPicForPC", method = RequestMethod.POST)
    public Result uploadPicForPC(MultipartFile  file, HttpServletRequest request, Pictures condition) throws IllegalStateException, IOException {
        Result result = new Result();

        System.out.println("进入方法");

        //获取拼接的图片id
        String str =request.getParameter("picId");

        String[] strArray = null;
        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        int s=strArray.length;
       // condition.setMasterDataId();
        try {
        for (int i =0; i<s;i++){
            condition.setRecordId(Long.parseLong(strArray[i]));

            System.out.println("图片id="+condition.getRecordId());

            if(condition.getRecordId() != null || condition.getRecordId() == 0){
                picturesDao.update(condition);


                result.setSuccess(true);
                result.setMsgcode(StatusUtil.OK);
                result.setMsg("修改成功");
            }
            System.out.println("数组="+strArray[i]);


        }
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
            result.setMsg("修改失败");
        }




        return result;
    }
}
