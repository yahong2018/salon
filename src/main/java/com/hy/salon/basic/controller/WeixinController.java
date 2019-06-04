package com.hy.salon.basic.controller;

import java.io.IOException;

import java.util.Locale;
import java.util.Map;

import com.hy.salon.basic.util.HttpClientUtil;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hy.salon.basic.util.WxConfigUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import com.alibaba.fastjson.JSONObject;
 


@Controller
@RequestMapping("/weixin")
public class WeixinController {
	private final static String PKFCUP_APPID = "wxaabe9d965a16ab93";
	private final static String UP_SECRET = "c4a2acc48c676f5fe2673ef0ccce3e0a";
	@RequestMapping(value = "/share", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> share(HttpServletRequest request) {
		String urlTemp = "http://" + request.getServerName() + request.getContextPath();
		String urlpath = "http://" + request.getServerName();
		String appUrl = request.getParameter("url");
		if (request.getParameter("code") != null) {
			appUrl += "&code=" + request.getParameter("code");
		}
		if (request.getParameter("state") != null) {
			appUrl += "&state=" + request.getParameter("state");
		}
		return WxConfigUtil.getSignature(appUrl, PKFCUP_APPID, UP_SECRET, urlTemp, urlpath);
	}
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getCode")
	public void toIndexPath(HttpServletRequest req, Model model, HttpServletResponse response) {
		System.out.println("getCode================");
		if (!StringUtils.isEmpty(req.getSession().getAttribute("openId"))) {
			//return "redirect:map";
		}
		String appId = PKFCUP_APPID; // appid
		String serverName=  "";
		String url =  "https://" + serverName
				+ req.getContextPath() + "/weixin/openId";
		System.out.println("url==="+url);
		String redirectUri = url; // 回调url
		redirectUri = URLEncoder.encode(redirectUri);
		//String scope = "snsapi_base"; // 静默授权，不能拉取用户信息
		String scope = "snsapi_userinfo"; // 弹出授权页面，可以拉取用户信息
		String wxUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?";
		String para = "appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STATE#wechat_redirect";
		String param = String.format(Locale.ENGLISH, para, appId, redirectUri,
				scope);
		model.addAttribute("authPageUrl", wxUrl + param);

		try {
			response.sendRedirect(wxUrl + param);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return "getCode";
	}

	/**
	 * 网页授权获取openId, http://localhost:8080/app/wx/openId
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/openId")
	@ResponseBody
	public String openId(String code, HttpServletRequest req, HttpServletResponse response, ModelMap map) throws Exception {
		//String code = req.getParameter("code");
		System.out.print("********************code"+code);
		String openId = "";
		String access_token = "";
		String appid =PKFCUP_APPID;
		String appsecret = UP_SECRET;
		//获取openid 以及 网页授权accesstoken
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ appid + "&secret=" + appsecret + "&code=" + code
				+ "&grant_type=authorization_code";
		JSONObject jsonObject = HttpClientUtil.httpsRequest(URL, "GET", null);
		if (null != jsonObject) {
			//获取openid
			openId = jsonObject.getString("openid");
			//获取网页授权accesstoken
			access_token = jsonObject.getString("access_token");
			map.put("openId", openId);
			System.out.print("********************openId" + openId+",access_token="+access_token);
		}

		return openId;
	}

	/**
	 * 根据微信openId 获取用户是否订阅
	 * @param openId 微信openId
	 * @return 是否订阅该公众号标识
	 */
	public static Integer subscribeState(String openId){
		String tmpurl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+WxConfigUtil.getToken(PKFCUP_APPID,UP_SECRET) +"&openid="+openId;
		JSONObject result = WxConfigUtil.httpRequest(tmpurl, "GET",null);
		JSONObject resultJson = new JSONObject(result);
		String errmsg = (String) resultJson.get("errmsg");
		if(errmsg==null){
			//用户是否订阅该公众号标识（0代表此用户没有关注该公众号 1表示关注了该公众号）。
			Integer subscribe = (Integer) resultJson.get("subscribe");
			return subscribe;
		}
		return -1;
	}

	/**
	 * 根据微信openId 获取用户信息
	 * @param openId 微信openId
	 * @return 是否订阅该公众号标识
	 */
	public static JSONObject subscribeUserInfo(String openId){
		String tmpurl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+WxConfigUtil.getToken(PKFCUP_APPID,UP_SECRET) +"&openid="+openId;
		JSONObject result = WxConfigUtil.httpRequest(tmpurl, "GET",null);
		return result;
	}
}