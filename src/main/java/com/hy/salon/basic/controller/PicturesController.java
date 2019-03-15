package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.PicturesDAO;
import com.hy.salon.basic.entity.Pictures;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
import java.util.List;
import java.util.UUID;

@Controller
@Api(value = "PicturesController|照片控制器")
@RequestMapping("/hy/basic/pictures")
public class PicturesController extends SimpleCRUDController<Pictures> {

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Override
    protected BaseDAOWithEntity<Pictures> getCrudDao() {
        return picturesDao;
    }

    /**
     * 图片上传
     *
     * @param file
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    public Result uploadPic(MultipartFile file, HttpServletRequest request, Pictures condition) throws IllegalStateException, IOException {
        Result result = new Result();

        if (file != null) {      // 判断上传的文件是否为空
            String path = null;// 文件路径
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:" + fileName);
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    //path=realPath+/*System.getProperty("file.separator")+*/trueFileName;
                    UUID uuid = UUID.randomUUID();
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                    System.out.println(df.format(new Date()));


                    String dir = request.getServletContext().getRealPath("/"+df.format(new Date()));
                    java.io.File folder = new java.io.File(dir);
                    if (!folder.exists()) {
                        folder.mkdirs();     ///如果不存在，创建目录
                    }
                    path = dir+"/"+uuid+"."+type;
                    System.out.println("地址"+path);
//                    String dir = "D:\\picFile\\"+df.format(new Date());
//                    java.io.File folder = new java.io.File (dir);
//                    if(!folder.exists()){
//                        folder.mkdirs();     ///如果不存在，创建目录
//                    }
//                    //新路径
//                    path=dir+"\\"+uuid+"."+type;
//                    condition.setPicUrl(path);
//                    System.out.println("存放图片文件的路径:"+path);
//                    // 转存文件到指定的路径
                    condition.setPicUrl("/"+df.format(new Date())+"/"+uuid+"."+type);
                    file.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下");
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


        result.setSuccess(true);
        result.setMsgcode(StatusUtil.OK);
        result.setMsg("上传成功");
        return result;
    }


    /**
     * 修改图片
     *
     * @param file
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/updatePic", method = RequestMethod.POST)
    public Result updatePic(MultipartFile file, HttpServletRequest request, String picName) throws IllegalStateException, IOException {
        Result result = new Result();
        String picUrl = "";
        String newPicUrl = null;// 数据库地址
        if (file != null) {      // 判断上传的文件是否为空
            String path = null;// 文件路径
            String type = null;// 文件类型

            String fileName = file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:" + fileName);
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    //path=realPath+/*System.getProperty("file.separator")+*/trueFileName;
                    UUID uuid = UUID.randomUUID();
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                    System.out.println(df.format(new Date()));
                    String dir = request.getServletContext().getRealPath("/"+df.format(new Date()));
                    java.io.File folder = new java.io.File(dir);
                    if (!folder.exists()) {
                        folder.mkdirs();     ///如果不存在，创建目录
                    }
                    path = dir+"/"+uuid+"."+type;
                    System.out.println("地址"+path);
//                    String dir = "D:\\picFile\\"+df.format(new Date());
//                    java.io.File folder = new java.io.File (dir);
//                    if(!folder.exists()){
//                        folder.mkdirs();     ///如果不存在，创建目录
//                    }
//                    //新路径
//                    path=dir+"\\"+uuid+"."+type;
//                    condition.setPicUrl(path);
//                    System.out.println("存放图片文件的路径:"+path);
                    newPicUrl="/"+df.format(new Date())+"/"+uuid+"."+type;
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下");
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
        Pictures picCondition = picturesDao.getOnePicturesForId(picName);
        picCondition.setPicUrl(newPicUrl);
        int i = picturesDao.update(picCondition);

        result.setSuccess(true);
        result.setMsgcode(StatusUtil.OK);
        result.setMsg("修改成功");
        return result;
    }

    /**
     * 公共获取图片接口
     */

    @ResponseBody
    @RequestMapping(value = "/getPictures", method = RequestMethod.GET)
    @ApiOperation(value = "获取系列", notes = "通过品牌Id获取该Id系列")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "masterDataId", value = "主记录编号", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "query", name = "recordType", value = "美容院Id", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType = "query", name = "picType", value = "美容院Id", required = true, dataType = "Byte"),
    })
    public Result getPictures(Pictures condition) {
        Result result = new Result();
        try {
            List<Pictures> pic = picturesDao.getPicturesForCondition(condition.getMasterDataId(), condition.getRecordType(), condition.getPicType());
            result.setData(pic);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
            result.setMsg("获取成功");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result;
    }

    /**
     * 头像上传
     *
     * @param file
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/uploadHeadPic", method = RequestMethod.POST)
    public Result uploadHeadPic(MultipartFile file, HttpServletRequest request, Pictures condition) throws IllegalStateException, IOException {
        Result result = new Result();

        Pictures pic=picturesDao.getOnePicturesForCondition(condition.getMasterDataId(),new Byte("1"),new Byte("0"));
        String newPicUrl = null;// 数据库地址

            if (file != null) {      // 判断上传的文件是否为空
                String path = null;// 文件路径
                String type = null;// 文件类型
                String fileName = file.getOriginalFilename();// 文件原名称
                System.out.println("上传的文件原名称:" + fileName);
                // 判断文件类型
                type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
                if (type != null) {// 判断文件类型是否为空
                    if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                        // 项目在容器中实际发布运行的根路径
                        String realPath = request.getSession().getServletContext().getRealPath("/");
                        // 自定义的文件名称
                        String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                        // 设置存放图片文件的路径
                        //path=realPath+/*System.getProperty("file.separator")+*/trueFileName;
                        UUID uuid = UUID.randomUUID();
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                        System.out.println(df.format(new Date()));
                        String dir = request.getServletContext().getRealPath("/"+df.format(new Date()));
                        java.io.File folder = new java.io.File(dir);
                        if (!folder.exists()) {
                            folder.mkdirs();     ///如果不存在，创建目录
                        }
                        path = dir+"/"+uuid+"."+type;
                        System.out.println("地址"+path);
                        newPicUrl="/"+df.format(new Date())+"/"+uuid+"."+type;
//                    condition.setPicUrl("/"+df.format(new Date())+"/"+uuid+"."+type);
                        file.transferTo(new File(path));
                        System.out.println("文件成功上传到指定目录下");
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

        if(null == pic){
            condition.setPicUrl(newPicUrl);
            picturesDao.insert(condition);
        }else{
            pic.setPicUrl(newPicUrl);
            picturesDao.update(pic);
        }




        result.setSuccess(true);
        result.setMsgcode(StatusUtil.OK);
        result.setMsg("上传成功");
        return result;
    }



}
