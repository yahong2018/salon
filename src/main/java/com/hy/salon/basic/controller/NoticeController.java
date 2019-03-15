package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.NoticeDAO;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.dao.VisualRangeDAO;
import com.hy.salon.basic.dao.VisualRangeMappingDAO;
import com.hy.salon.basic.entity.Notice;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.VisualRange;
import com.hy.salon.basic.entity.VisualRangeMapping;
import com.hy.salon.basic.util.PinYinUtil;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@Api(value = "NoticeController| 公告控制器")
@RequestMapping("/hy/basic/notice")
public class NoticeController extends SimpleCRUDController<Notice> {

    @Resource(name = "noticeDAO")
    private NoticeDAO noticeDAO;

    @Resource(name = "visualRangeDAO")
    private VisualRangeDAO visualRangeDao;

    @Resource(name = "visualRangeMappingDAO")
    private VisualRangeMappingDAO visualRangeMappingDao;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Override
    protected BaseDAOWithEntity<Notice> getCrudDao() {
        return noticeDAO;
    }


    /**
     * 发布公告
     */
    @ResponseBody
    @RequestMapping(value = "addNotice", method = RequestMethod.POST)
    @ApiOperation(value = "发布公告", notes = "发布公告")
    public Result updateSalon(Notice condition) {
        Result r = new Result();
        try {
            //先写死，后面改
            String bindingJson = "[{\"stuffId\": 1},{\"stuffId\": 2},{\"stuffId\": 3}]";

            int ii = noticeDAO.insert(condition);
            if (ii != 0) {
                //解析绑定json，绑定关系
                JSONArray jsonArr = JSONArray.parseArray(bindingJson);
                if (jsonArr != null) {
                    for (int i = 0; i < jsonArr.size(); i++) {
                        JSONObject jsonObj = jsonArr.getJSONObject(i);
                        String stuffId = jsonObj.getString("stuffId");
                        VisualRange vr = new VisualRange();
                        vr.setStuffId(Long.parseLong(stuffId));
                        vr.setStatu(0);
                        visualRangeDao.insert(vr);

                        VisualRangeMapping vrm = new VisualRangeMapping();
                        vrm.setVisualRangeId(vr.getRecordId());
                        vrm.setNoticeId(condition.getRecordId());
                        visualRangeMappingDao.insert(vrm);
                    }
                }


            }

            r.setMsg("发布成功");
            r.setMsgcode(StatusUtil.OK);
            r.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }

        return r;
    }


    /**
     * 根据中文与英文首字母排序查找该门店下的家人
     */
    @ResponseBody
    @RequestMapping(value = "queryStuffForLetter", method = RequestMethod.GET)
    @ApiOperation(value = "根据中文与英文首字母排序查找该门店下的家人", notes = "根据中文与英文首字母排序查找该门店下的家人")
    public Result queryStuffForLetter(Long recordId) {
        Result r = new Result();
        try {
            List<Stuff> stuffList= stuffDao.getStuffForStoreId(recordId);
            List<String> letterList=new ArrayList<>();
            JSONArray jsonArr=new JSONArray();
            if(stuffList.size() != 0){
                for (Stuff s:stuffList){
                    //获取员工名首个字符并转化成英文
                    char c=s.getStuffName().charAt(0);
                    String str = String.valueOf(c);
                    String letter=PinYinUtil.getPinYinHeadChar(str);

                        int i= 0;
                        for(String s1:letterList){
                            if(s1.equals(letter)){
                                i=1;
                            }
                        }
                    letterList.add(letter);
                    if(i==0){
                        JSONObject jsonObj=new JSONObject();
                        jsonObj.put("letter",letter);
                        JSONArray jsonArr2=new JSONArray();
                        for(Stuff ss:stuffList){
                            char c2=ss.getStuffName().charAt(0);
                            String str2 = String.valueOf(c2);
                            String letter2=PinYinUtil.getPinYinHeadChar(str2);
                            if(letter2.equals(letter)){
                                jsonArr2.add(ss);
                            }
                        }
                        jsonObj.put("stuffList",jsonArr2);
                        jsonArr.add(jsonObj);
                    }

                }

            }
            //转list 比较大小，通过字母大小排序
            List<JSONObject> list = JSONArray.parseArray(jsonArr.toJSONString(), JSONObject.class);
            Collections.sort(list, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    char a = o1.getString("letter").charAt(0);
                    char b = o2.getString("letter").charAt(0);
                    if (a > b) {
                        return 1;
                    } else if(a == b) {
                        return 0;
                    } else
                        return -1;
                }
            });
            JSONArray jsonArray = JSONArray.parseArray(list.toString());

            r.setData(jsonArray);
            r.setMsg("获取成功");
            r.setMsgcode(StatusUtil.OK);
            r.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }

        return r;
    }

}
